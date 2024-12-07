# Description
### Create a Shell script that automates checking disk storage usage and sends an email alert if uasge exceeds a specified threshold

### Step 1: Install msmtp Package
```
sudo apt update
sudo apt-get install msmtp -y
```

## step 2: Edite msmtprc file
   ```bash
   vim ~/.msmtprc
   ```
   ```bash
   # Gmail SMTP settings
   account gmail
   host smtp.gmail.com
   port 587
   from marwanhesham463@gmail.com
   auth on
   user marwanhesham463@gmail.com
   password kpvt uupy isek bzdu
   tls on
   tls_starttls on
   logfile ~/.msmtp.log
   
   # Set default account to gmail
   account default : gmail
   ```
<img width="960" alt="lab2 1day1 msmtprc" src="https://github.com/user-attachments/assets/104fead6-740f-40ee-9efa-e8c6ec631791">

 ## step 3: Test Setup
   ```bash
       echo "Test email body" | mail -s "Test email subject" recipient-email@gmail.com
   ```

 ## step 4: Schedule a script
   ```bash
   vim disk.sh
   ```

<img width="960" alt="lab2 2day1(disk)" src="https://github.com/user-attachments/assets/095354f5-6faf-42ae-b062-13f1c8e269bc">



### step 5: Create Cron Job
```bash
sudo crontab -e
```
Add this line to run at 5:00 PM daily:
```
0 17 * * * /path/to/disk.sh
```

### step 6: check your email 

<img width="960" alt="lab2 3(mail)" src="https://github.com/user-attachments/assets/232b14c2-1407-49a1-96ab-a3b90b3bf8c0">

### Done..!

