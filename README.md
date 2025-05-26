## Contributing Guide

#### Git vs GitHub

**Git**: Tool you download on your computer that tracks code changes. Lets you save snapshots of your work, undo
mistakes, and manage versions locally.

**GitHub**: Website that hosts Git projects online. Lets teams collaborate by sharing code, reviewing changes, and
managing tasks remotely.

### 1. Install Git

Download: https://git-scm.com/downloads
Set name and email in the terminal using these commands:

- `git config --global user.name "Name"` (use same username as GitHub)
- `git config --global user.email "example@email.com"` (use same email as GitHub)

You must also set up an ssh
key: https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account

### 2. Clone the repo

A repo (repository) is a folder tracked by Git, containing all the project files and the version history

Clone with this command:

- `git clone https://github.com/evanpez/eol.git git/eol`

Cloning a repo copies a repo from GitHub to your computer.

Before entering any git commands, make sure you are in the directory of the repo by using the command:

- `cd git/eol`

### 3. Import to Eclipse

- Go to `File` > `Import...`
- Choose `Git` > `Projects from Git`, click `Next`
- Select `Existing local repository`, click `Next`
- Click `Add`, then `Browse` to the `.git` directory inside the cloned repo, select it, then click `Finish`
- Select the repository you just added, click `Next`
- Choose `Import as general project`
- Click `Finish`

this allows the project to be edited with eclipse

### 4. Create a new branch

Branches are versions of the repo. The idea is that the `main` branch is stable and when working on new features/fixes,
you create a seperate branch and make changes there.

Before creating a new branch allows make sure you are branching off main:

- `git checkout main` (switches current branch to main)

Then create a new branch:

- `git checkout -b feature/new-feature` (change "new-feature" to whatever your working on)

This creates and switches to a seperate branch where you can now safely make changes

### 5. Make changes

Modify/add/delete code and stage the changes using this command:

- `git add .`

Then commit the changes using this command:

- `git commit -m "Describe changes here"`

A commit is a snapshot of the project at a point in time

### 6. Push branch to GitHub

Push changes using this command:

- `git push -u origin feature/new-feature`

This adds your branch along with any commits to GitHub, where it can then be merged with the main branch

### 7. Create a pull request

A pull request is a request to merge a feature branch onto the main branch

Go to the repo on GitHub and create a new pull request and wait for someone to approve it/approve it yourself. Delete
the feature branch after merging with main

### 8. Clean up

After merging with main, clean up by switching back to main branch, updating the main branch locally, and deleting the
feature branch locally

- `git checkout main` (switches to main branch)
- `git pull origin main` (updates your local main branch from GitHub)
- `git branch -d feature/new-feature` (deletes the feature branch locally)

### 9. Update local branch from main

If you need to pull from the main branch on GitHub to update your local feature branch, enter these commands:

- `git checkout main` (switch to main branch)
- `git pull origin main` (update main branch)
- `git checkout feature/new-feature` (switch back to feature branch)
- `git merge main` (updates current branch with changes from main)

### 10. Tools

Instead of using terminal commands, Eclipse and VS Code have Git built in.

Eclipse: right click on project and navigate to `team`
