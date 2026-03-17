#!/bin/bash

# Color codes
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Define epics with colors
declare -A EPIC_COLORS=(
    ["Setup"]="C2E0C6"
    ["SSH & Auth"]="A3F0D1"
    ["File Browsing"]="F9DDF5"
    ["File Transfer"]="FBCA04"
    ["Profiles"]="1D76DB"
    ["UI/UX"]="0E3689"
    ["Testing"]="D4C5F9"
    ["Security"]="EB6E1F"
    ["Release"]="BFD4F2"
)

echo "Creating labels..."
for epic in "${!EPIC_COLORS[@]}"; do
    gh label create "epic/$epic" --color "${EPIC_COLORS[$epic]}" --force 2>/dev/null && \
    echo -e "${GREEN}✓${NC} epic/$epic"
done

gh label create "status/todo" --color "0366D6" --force 2>/dev/null && \
echo -e "${GREEN}✓${NC} status/todo"

echo ""
echo "Creating issues..."
counter=0

# Parse and create issues
python3 << 'PYEOF'
import re
import subprocess

with open('docs/IMPLEMENTATION_TASKS.md', 'r') as f:
    content = f.read()

epic_map = {
    'Project Setup & Dependencies': 'Setup',
    'SSH Connection Infrastructure': 'SSH & Auth',
    'SSH Key Management': 'SSH & Auth',
    'Local File Browser': 'File Browsing',
    'Remote File Browser': 'File Browsing',
    'File Transfer Operations': 'File Transfer',
    'Connection Profiles': 'Profiles',
    'Main Application UI & Navigation': 'UI/UX',
    'Error Handling & User Feedback': 'UI/UX',
    'Testing': 'Testing',
    'Security & Optimization': 'Security',
    'Release Preparation': 'Release'
}

pattern = r'## \d+\. (.*?)\n\n(.*?)(?=## \d+\.|$)'
matches = re.findall(pattern, content, re.DOTALL)

for section_name, section_content in matches:
    epic = epic_map.get(section_name, 'Other')
    task_pattern = r'- \[ \] (\d+\.\d+) (.*?)(?=\n- \[ \]|\Z)'
    tasks = re.findall(task_pattern, section_content)
    
    for task_id, task_desc in tasks:
        task_desc = task_desc.strip()
        title = f'[{task_id}] {task_desc}'
        body = f'**Epic**: {section_name}\n\n**Task**: {task_desc}'
        
        cmd = [
            'gh', 'issue', 'create',
            '--title', title,
            '--body', body,
            '--label', f'epic/{epic}',
            '--label', 'status/todo'
        ]
        
        try:
            result = subprocess.run(cmd, capture_output=True, text=True, timeout=10)
            if result.returncode == 0:
                print(f'✓ {title[:70]}')
            else:
                print(f'✗ {title[:70]}')
        except:
            print(f'? {title[:70]}')
PYEOF

echo ""
echo "Done!"
