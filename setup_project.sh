#!/bin/bash

# Create a GitHub Project
echo "Creating GitHub Project..."
PROJECT_DATA=$(gh project create --owner monghithub --title "Android SFTP SSH App" --template table --format json)
PROJECT_ID=$(echo "$PROJECT_DATA" | grep -o '"id":"[^"]*' | cut -d'"' -f4 | head -1)

echo "Project created successfully!"
echo ""
echo "Setting up project with custom fields and views..."

# Note: GitHub CLI doesn't have full project board API support yet
# Manual steps would be needed, but we can create a summary document

cat > PROJECT_SETUP.md << 'DOCEOF'
# Android SFTP SSH App - Project Board Setup

## GitHub Project Configuration

A project board has been created for tracking implementation progress.

### Recommended Views:

**1. By Epic (Kanban)**
- Column: Epic Label
- Issues grouped by: epic/Setup, epic/SSH & Auth, epic/File Browsing, etc.

**2. By Status (Kanban)**
- Columns: To Do → In Progress → In Review → Done
- Filter: label:status/todo

**3. Priority Board**
- Sort by: task number (indicates logical order)
- Sprint planning: Group tasks 1-20, 21-40, 41-60, etc.

### Task Phases:
1. **Phase 1** (Tasks 1.1-1.8): Project Setup & Dependencies
2. **Phase 2** (Tasks 2.1-2.8): SSH Connection Infrastructure
3. **Phase 3** (Tasks 3.1-3.11): SSH Key Management
4. **Phase 4-5** (Tasks 4.1-5.11): File Browsing
5. **Phase 6** (Tasks 6.1-6.12): File Transfer Operations
6. **Phase 7** (Tasks 7.1-7.13): Connection Profiles
7. **Phase 8** (Tasks 8.1-8.10): Main UI & Navigation
8. **Phase 9** (Tasks 9.1-9.8): Error Handling
9. **Phase 10** (Tasks 10.1-10.10): Testing
10. **Phase 11** (Tasks 11.1-11.8): Security & Optimization
11. **Phase 12** (Tasks 12.1-12.10): Release Preparation

### Quick Filters:

**By Epic:**
```
label:"epic/Setup"
label:"epic/SSH & Auth"
label:"epic/File Browsing"
label:"epic/File Transfer"
label:"epic/Profiles"
label:"epic/UI/UX"
label:"epic/Testing"
label:"epic/Security"
label:"epic/Release"
```

**By Status:**
```
label:status/todo
```

### Commands to Continue:

1. **View all issues by epic:**
   ```bash
   gh issue list --label epic/Setup
   gh issue list --label "epic/SSH & Auth"
   ```

2. **Create milestones for phases:**
   ```bash
   gh milestone create --title "Phase 1: Setup" --description "Initial project configuration"
   ```

3. **Assign issues to milestones:**
   ```bash
   gh issue edit <number> --milestone "Phase 1: Setup"
   ```

DOCEOF

echo ""
echo "✅ Project configuration guide created: PROJECT_SETUP.md"
echo ""
echo "Next steps:"
echo "1. Visit: https://github.com/monghithub/apk-sftp/projects"
echo "2. Configure views to group by Epic label"
echo "3. Start assigning issues to team members"

