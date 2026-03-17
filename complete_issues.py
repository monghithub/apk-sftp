#!/usr/bin/env python3
import subprocess
import re

# Read tasks
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

# Get existing issues
result = subprocess.run(
    ['gh', 'issue', 'list', '--json', 'title'],
    capture_output=True,
    text=True
)
existing_titles = set()
for line in result.stdout.split('\n'):
    if '[' in line:
        existing_titles.add(line.strip())

issues_created = 0
for section_name, section_content in matches:
    epic = epic_map.get(section_name, 'Other')
    task_pattern = r'- \[ \] (\d+\.\d+) (.*?)(?=\n- \[ \]|\Z)'
    tasks = re.findall(task_pattern, section_content)
    
    for task_id, task_desc in tasks:
        task_desc = task_desc.strip()
        title = f'[{task_id}] {task_desc}'
        
        # Skip if already exists
        if any(task_id in t for t in existing_titles):
            continue
        
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
                issues_created += 1
            else:
                print(f'✗ {title[:70]}')
        except Exception as e:
            print(f'? {title[:70]} - {str(e)[:50]}')

print(f'\n✅ Created {issues_created} new issues')
