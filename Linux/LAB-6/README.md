# Description
## Generate public and private keys and enable SSH from your machine to another VM using the key. Configure SSH to just run ‘ssh ivolve’ without specify username, IP and key in the command 

### step 1: Install the SSH Service
To begin, ensure the SSH service is installed and running on your machine: 
```bash
sudo apt install sshd
sudo systemctl enable --now ssh
```

### step 2: Configure SSH Config File:
```bash
# Create or edit SSH config file
vim ~/.ssh/config
```

<img width="960" alt="lab6 1day2" src="https://github.com/user-attachments/assets/922a673c-f1e0-46fb-a28e-d3f52560318c">


### step 3: Generate Public and Private Keys
Run the following command to generate a new SSH key pair:
```bash
ssh-keygen -t rsa -b 4096 -f ~/.ssh/ivolve_key
```

### step 4: Copy the Public Key to the Remote Machine
Use the following command to copy the public key to the remote machine:

```bash
ssh-copy-id -i ~/.ssh/ivolve_key.pub marwanhesham@192.168.242.140
```

### step 5: Test the Configuration
Now, you can connect to the remote machine by simply running:

```bash
ssh ivolve
```

<img width="960" alt="lab6day2" src="https://github.com/user-attachments/assets/2e197fc6-f0e3-40cb-9e63-6cc995340d10">

### step 6: Finally, History of this lab

<img width="960" alt="lab6 2day2" src="https://github.com/user-attachments/assets/3939adf3-623b-450c-a60c-0fd05a96256f">

### Done..!
