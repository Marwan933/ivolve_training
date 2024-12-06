# Description
## write an ansible playbook to automate the configuration of a web server configure

### Step 1: Create Ansible Inventory
#### 1- **Create an Inventory File:**
```
mkdir lab8.2

vim inventory.ini
```
connect with ec2
<img width="960" alt="lab8 2(inventory)" src="https://github.com/user-attachments/assets/5ae84c2b-5def-4dfc-bb3e-a5bac5c175f3">


### Step 2: Create a playbook file:
 ### 1. create playbook.yml
```
vim playbook.yml
```
<img width="960" alt="lab8 3(playbook)" src="https://github.com/user-attachments/assets/8b0d55e9-c9f0-4eb7-9189-bbac67a4a748">

### Step 3: Run the playbook

#### 1- run the playbook.yml file:

```
ansible-playbook -i inventory.ini playbook.yml
```

### Step 4: ensure that the Web Page is running

in the broswer in the url bar write the ip of the managed node:
<img width="955" alt="lab8 day3(web)" src="https://github.com/user-attachments/assets/206b9955-2e3b-4711-b38b-7b09921d3873">

## it,s Work
 ## Finally, History of lab 8.2:

 <img width="827" alt="lab8 4(history)" src="https://github.com/user-attachments/assets/6170bad5-4f32-4c4e-9978-af82b1e5da25">

