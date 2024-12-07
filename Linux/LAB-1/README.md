# Description
# Configure User and Group for Nginx Installation Without Sudo Password

### step 1: Create a Group
Run the following command to create a new group:
```bash
sudo groupadd <group_name>
sudo groupadd ivolve
```

### step 2: Create a User
Run the following command to create a new User:
```bash
sudo useradd -m -G /bin/bash -s ivolve <user_name>
sudo useradd -m -G /bin/bash -s ivolve marwan-hesham
```

### step 3: Run Command with No password
Add the following line to give members of the group permission to run apt install nginx without a password: `visudo`
```bash
%<group_name> ALL=(ALL) NOPASSWD: Command i want allow user to run with No password
marwan-hesham ALL=(ALL) NOPASSWD: /usr/bin/apt install nginx,    /usr/bin/apt-get install nginx
```


<img width="957" alt="lab1 ivolve" src="https://github.com/user-attachments/assets/acbe2f1f-098c-41d4-9354-76a2a344a017">

### step 4: Install Nginx as `marwan-hesham` user
```bash
sudo -i -u marwan-hesham
sudo apt-get install nginx
```

### step 5: Finally, History of this lab


<img width="960" alt="lab1 3 ivolve" src="https://github.com/user-attachments/assets/66fd26ae-20bf-4317-80cd-4a20211acd95">

### Done ..
