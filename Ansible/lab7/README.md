### Description
---
**install and configure ansible automation platform on control nodes , create inventories of 2 managed hosts and then perform ad-hoc commands to check functionality.**

## Step 1: Installing Ansible on the Control Node

1. **Update the package manager:**
   ```bash
   sudo apt update     # For Debian/Ubuntu
   sudo yum update     # For CentOS/RHEL

2. **Install Ansible:**
   ```bash
   sudo apt install ansible -y       # for Debian/Ubuntu

3. **Verify installation:**
   ```bash
   ansible --version
## Step 2: Configure the Ansible Control Node 

 1. **Generate SSH Keys:**
    ```bash
    ssh-keygen -t rsa -b 2048

  2. **Copy SSH Key to Managed Hosts:**
     ```bash
     ssh-copy-id user@managed_host_1
     ssh-copy-id user@managed_host_2

## Step 3: Create Ansible Inventory

 1. **Create an Inventory File:**
 ```bash
    mkdir ~/ansible
    vim inventory
```
<img width="960" alt="lab7 1day3inventory" src="https://github.com/user-attachments/assets/8c94c420-428e-47e9-9205-573fa385a60e">

## Step 4: Test Connectivity Between Control and Managed nodes:

 1- **Ping the Managed Hosts:**
 ```bash 
 ansible -i ~/ansible/inventory server -m ping
 ```
<img width="949" alt="lab7,2ping" src="https://github.com/user-attachments/assets/bbc09f31-6b86-407e-8bc4-8f8b883abfff">

## Step 5: Perform Ad-Hoc Commands:

1. **Check Disk Usage:**
   ```bash
   ansible -i ~/ansible/inventory server -a "df -h"

2. **Check Free Memory:**
    ```bash
    ansible -i ~/ansible/inventory server -a "free -m"  

3. **Check Uptime:**
   ```bash
   ansible -i ~/ansible/inventory server -a "uptime"
   ```
   <img width="960" alt="lab7day3" src="https://github.com/user-attachments/assets/655f6cf5-0f5f-438d-846a-328618563aa0">

   ### DONE!!
