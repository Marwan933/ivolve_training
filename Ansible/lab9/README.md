# Description
## use ansible vault to encrypt playbook sensitive information, such as database passwords, and incorporate the encrypted data into an Ansible playbook

### Step 1: Create vault.yml file using ansible-vaults
1- using this command:
```bash
ansible-vault create vault.yml
```
<img width="586" alt="lab9 vaultday3" src="https://github.com/user-attachments/assets/921ab88a-645d-4277-9965-722c3c61b77f">

### Step 2: Create inventory
```bash
vim inventory
```
with ec2

<img width="836" alt="lab9 inventory(day3)" src="https://github.com/user-attachments/assets/796f59b6-2066-4002-9bec-4c96fd4d23d9">

### Step 3: Create playbook.yml file
### All tasks in playbook file:

<img width="819" alt="lab9 playbook(day3)" src="https://github.com/user-attachments/assets/1f5d4768-0c1c-49ae-ae82-3902a3a81e76">


### Step 4: run the playbook

#### 1- using this command:
```
ansible-playbook -i inventory playbook.yml --ask-vault-pass
```
<img width="960" alt="lab9 day3" src="https://github.com/user-attachments/assets/61033fbe-4033-4274-8d86-e7d6a434ad0e">

### Step 5: Access tne mysql database

#### 1- ssh and access to the mysql terminal:

<img width="746" alt="lab9 mysql(day3)" src="https://github.com/user-attachments/assets/716afbeb-1f74-4e12-866b-5f59ff05df4c">

### step 6: Finally, History if this Lab:

<img width="514" alt="lab9 history(day3)" src="https://github.com/user-attachments/assets/a44aa8ff-674b-4a38-aa7c-3ee829bf5052">

# Done !!
