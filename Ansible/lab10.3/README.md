# Description
## create an ansible roles for installing jenkins , docker , openshift cli

### Step 1: Create Roles
```
ansible-galaxy init roles/jenkins

ansible-galaxy init roles/docker

ansible-galaxy init roles/oc
```

### Step 2: work on jenkins role
### All Tasks in this playbook, called main.yml

1.**create main.yml**
```
vim roles/jenkins/tasks/main.yml

```

Takecare of this Note: in the loop (openjdk Must Be >or= 17)
<img width="840" alt="lab10 jenkins(day4)" src="https://github.com/user-attachments/assets/41b9b3c7-7329-4667-9089-e93cff7805ef">

### Step 3: work on docker role
### All Tasks in this playbook, called main.yml Also

1.**create main.yml**
```
vim roles/docker/tasks/main.yml

```
<img width="833" alt="lab10 day4(vim docker)" src="https://github.com/user-attachments/assets/41b6a924-9647-40eb-b80f-4ce7340f45e4">

### Step 4: work on oc role
### All Tasks in this playbook, called main.yml Also

1.**create main.yml**
```
vim roles/oc/tasks/main.yml

```
<img width="719" alt="lab10 day(vim oc)" src="https://github.com/user-attachments/assets/3c231b21-c56c-492e-b794-853867030a92">

### Step 5: create inventory file
1. **create inventory**
   ```
   vim inventory

   ```
   <img width="790" alt="lab10 (new inventory)day4" src="https://github.com/user-attachments/assets/812019ae-b4d4-45db-a34a-f3524c467fb4">

### Step 6: Create playbook 
### (called:Site.yml)
```
vim site.yml

```

![image](https://github.com/user-attachments/assets/46a3f159-4209-417e-a4c4-570dcae27f53)


### Step 7: Run the playbook 
```
ansible-playbook -i inventory site.yml

```
<img width="956" alt="lab10 day4(o p)" src="https://github.com/user-attachments/assets/2b9a2d46-c2cc-429e-915a-b430478551d9">

### Step 8: to test the roles

 1. **test docker**
```
docker ps
```

2. **test openshift**
```
oc version
```

3. **test jenkins**
```
sudo systemctl status jenkins.service
```

### in the following photo it explain the previous steps from 1 to 3:

<img width="889" alt="lab10 test,roles ping" src="https://github.com/user-attachments/assets/c620e192-5892-4d2c-b160-9378f60c5645">

### Step 9: Finally, History of this lab:

<img width="525" alt="lab10 3 history" src="https://github.com/user-attachments/assets/4469564b-6b66-4438-bf2b-c64e1ac4f232">

### Done!!
