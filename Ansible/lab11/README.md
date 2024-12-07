# Description

# Set up Ansible dynamic inventories to automatically discover and manage AWS infrastructure. Use Ansible Galaxy role to install Apache.

### step 1: install Ansible 
```bash
sudo apt install ansible -y  # For Ubuntu/Debian
```

### step 2: Verify that Python dependencies for AWS are installed
```bash
pip install boto3 botocore
```

### step 3: Install the "geerlingguy.apache" role from Ansible Galaxy
   ```bash
   ansible-galaxy install geerlingguy.apache
   ```

### step 4: Create ansible.cfg 
```bash
vim ansible.cfg
```
<img width="468" alt="lab11 (ansible cfg) day4" src="https://github.com/user-attachments/assets/296c1862-f571-49c2-8e53-bf9f64cfa15a">

### step 5: create aws_ec2.yml
```bash
vim aws_ec2.yml
```
<img width="370" alt="lab11 cat(aws_ec2)day4" src="https://github.com/user-attachments/assets/99053c09-0f09-47de-85f5-6cf7546a2ff3">

### Step 6: Create playbook file
```bash
vim playbook.yml
```
<img width="672" alt="lab11 (playbook) day4" src="https://github.com/user-attachments/assets/1a75d997-024c-4da8-9602-60bf4954c191">

 ### step 6: Verify the Dynamic Inventory
   Test the AWS EC2 dynamic inventory file:
   ```bash
   ansible-inventory -i aws_ec2.yml --graph
   ```
 ### step 7: Run the Playbook
   Execute the playbook to configure Apache on the discovered AWS instances:
   ```bash
   ansible-playbook -i aws_ec2.yml playbook.yml
   ```
<img width="839" alt="lab11 day4(dynamic inventory)" src="https://github.com/user-attachments/assets/8bbc50d7-29f4-4892-8550-ae32685c1a6c">

### step 8: Verify Apache Installation
   Use Ansible ad-hoc commands to verify that Apache is installed and running:
   ```bash
   ansible all -i aws_ec2.yml -m command -a "systemctl status apache2"  # For Ubuntu
   ```
<img width="844" alt="lab11 day4(ad-hock)" src="https://github.com/user-attachments/assets/dd506b92-5f37-4afa-bb3a-94d6385885a5">

### step 9: Finally, History of this lab 

<img width="840" alt="lab11 (history) day4" src="https://github.com/user-attachments/assets/283c6be2-d8ae-44d2-9088-533b68a51824">

### Done !!
