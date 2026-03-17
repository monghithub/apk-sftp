#!/bin/bash

echo "🎯 Creando 12 Milestones..."
echo ""

# Create milestones
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
    gh milestone create "$milestone" --description "Phase $((i+1)) - Implementation tasks" 2>/dev/null && \
    echo "✓ $milestone"
done

echo ""
echo "📌 Asignando issues a milestones..."
echo ""

# Parse tasks and assign to milestones
python3 << 'PYEOF'
import subprocess
import re

# Read tasks file
with open('docs/IMPLEMENTATION_TASKS.md', 'r') as f:
    content = f.read()

# Map phase to milestone
phase_map = {
    1: "Phase 1: Project Setup & Dependencies",
    2: "Phase 2: SSH Connection Infrastructure",
    3: "Phase 3: SSH Key Management",
    4: "Phase 4: Local File Browser",
    5: "Phase 5: Remote File Browser",
    6: "Phase 6: File Transfer Operations",
    7: "Phase 7: Connection Profiles",
    8: "Phase 8: Main Application UI & Navigation",
    9: "Phase 9: Error Handling & User Feedback",
    10: "Phase 10: Testing",
    11: "Phase 11: Security & Optimization",
    12: "Phase 12: Release Preparation"
}

# Extract all issues and their task IDs
result = subprocess.run(
    ['gh', 'issue', 'list', '--limit', '500', '--json', 'number,title'],
    capture_output=True,
    text=True
)

import json
issues = json.loads(result.stdout)

assigned = 0
for issue in issues:
    title = issue['title']
    number = issue['number']
    
    # Extract task ID like [1.1], [2.5], etc
    match = re.search(r'\[(\d+)\.', title)
    if match:
        phase = int(match.group(1))
        if phase in phase_map:
            milestone = phase_map[phase]
            
            cmd = [
                'gh', 'issue', 'edit', str(number),
                '--milestone', milestone
            ]
            
            try:
                result = subprocess.run(cmd, capture_output=True, text=True, timeout=5)
                if result.returncode == 0:
                    assigned += 1
                    if assigned % 10 == 0:
                        print(f'  ✓ {assigned} issues asignados...')
            except:
                pass

print(f'\n✅ {assigned} issues asignados a milestones')
PYEOF

echo ""
echo "📊 Resumen de Milestones:"
gh milestone list --json title,openIssues,closedIssues --jq '.[] | "\(.title): \(.openIssues) abiertos, \(.closedIssues) cerrados"' | sort

