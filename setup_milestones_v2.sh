#!/bin/bash

echo "🎯 Creando 12 Milestones via GitHub API..."
echo ""

# Create milestones using GitHub API
milestones=(
    "Phase 1: Project Setup & Dependencies"
    "Phase 2: SSH Connection Infrastructure"
    "Phase 3: SSH Key Management"
    "Phase 4: Local File Browser"
    "Phase 5: Remote File Browser"
    "Phase 6: File Transfer Operations"
    "Phase 7: Connection Profiles"
    "Phase 8: Main Application UI & Navigation"
    "Phase 9: Error Handling & User Feedback"
    "Phase 10: Testing"
    "Phase 11: Security & Optimization"
    "Phase 12: Release Preparation"
)

for i in "${!milestones[@]}"; do
    milestone="${milestones[$i]}"
    description="Phase $((i+1)) - Implementation tasks"
    
    gh api repos/monghithub/apk-sftp/milestones \
        -f title="$milestone" \
        -f description="$description" \
        2>/dev/null && echo "✓ $milestone"
done

echo ""
echo "📌 Obteniendo milestones..."
milestone_data=$(gh api repos/monghithub/apk-sftp/milestones --paginate --jq '.[] | {title: .title, number: .number}')

echo ""
echo "📌 Asignando issues a milestones..."
echo ""

python3 << 'PYEOF'
import subprocess
import json
import re

# Get milestones
result = subprocess.run(
    ['gh', 'api', 'repos/monghithub/apk-sftp/milestones', '--paginate'],
    capture_output=True,
    text=True
)

milestones = json.loads(result.stdout)
milestone_map = {}
for m in milestones:
    # Extract phase number from title
    match = re.search(r'Phase (\d+)', m['title'])
    if match:
        phase = int(match.group(1))
        milestone_map[phase] = m['number']

# Get all issues
result = subprocess.run(
    ['gh', 'issue', 'list', '--limit', '500', '--json', 'number,title'],
    capture_output=True,
    text=True
)

issues = json.loads(result.stdout)

assigned = 0
for issue in issues:
    title = issue['title']
    number = issue['number']
    
    # Extract task ID like [1.1], [2.5], etc
    match = re.search(r'\[(\d+)\.', title)
    if match:
        phase = int(match.group(1))
        if phase in milestone_map:
            milestone_num = milestone_map[phase]
            
            cmd = [
                'gh', 'issue', 'edit', str(number),
                '--milestone', str(milestone_num)
            ]
            
            try:
                result = subprocess.run(cmd, capture_output=True, text=True, timeout=5)
                if result.returncode == 0:
                    assigned += 1
                    if assigned % 20 == 0:
                        print(f'  ✓ {assigned} issues asignados...')
            except Exception as e:
                pass

print(f'\n✅ {assigned} issues asignados a milestones')
PYEOF

echo ""
echo "📊 Estado de Milestones:"
gh api repos/monghithub/apk-sftp/milestones --paginate \
    --jq '.[] | "\(.title): \(.open_issues) abiertos"' | sort

