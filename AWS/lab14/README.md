# Description
## create vpc with 2 subnets , launch 2 EC2s with nginx and apache installed , and create and configure a load balancer to access the webserver

## Step 1: Create a VPC

### 1. Create a VPC with Public Subnets
1. Navigate to the **VPC** service in the AWS Management Console.
2. Create a new VPC with a CIDR block (e.g., `10.0.0.0/16`).
3. Create two subnets:
   - Assign them to different availability zones.
   - Attach an **Internet Gateway (IGW)** to the VPC.
   - Update the route table for both subnets to include a route to the IGW, making them public.

---

<img width="175" alt="lab13,vpc" src="https://github.com/user-attachments/assets/960527ed-2159-48e1-bd12-d7d05597f7fd">

### step 2: Launch EC2 Instances
1. Launch two EC2 instances in the public subnets:
   - **Instance 1**: Install Nginx using user data.
   - **Instance 2**: Install Apache using user data.
     

 **Sample User Data for Apache:**
   ```bash
   #!/bin/bash
   yum update -y
   yum install httpd -y
   systemctl start httpd
   systemctl enable httpd
   echo "<h1>Apache Server from marwan</h1>" > /var/www/html/index.html
   ```
<img width="531" alt="lab14 apache script" src="https://github.com/user-attachments/assets/5cf303af-c866-4417-85f3-b19ff7866517">

**Sample User Data for Nginx:**
   ```bash
   #!/bin/bash
   yum update -y
   amazon-linux-extras install nginx1 -y
   systemctl start nginx
   systemctl enable nginx
   echo "<h1>Nginx Server from marwan</h1>" > /usr/share/nginx/html/index.html
   ```

<img width="801" alt="lab14 ec2,s" src="https://github.com/user-attachments/assets/734935f1-e49e-4afc-82af-a62d82c2a3a0">


### step 3: Configure Security Groups
1. For EC2 instances:
   - Allow **HTTP** (port 80) traffic from the Load Balancer security group.
   - Allow **SSH** (port 22) traffic from your local machine’s IP.
2. For the Load Balancer:
   - Allow all traffic (port 80) with the source set to `0.0.0.0/0`.

---

<img width="675" alt="lab14 security" src="https://github.com/user-attachments/assets/f0617db0-856f-41af-8cfa-3d675339e43f">


### step 4: Create and Configure a Load Balancer
1. Navigate to the **Load Balancers** section in the EC2 Management Console.
2. Choose the **Application Load Balancer** type.
3. Select the VPC and both public subnets for the Load Balancer.
4. Configure listeners:
   - Listener Protocol: **HTTP**
   - Listener Port: **80**
5. Configure security groups:
   - Use the Load Balancer security group created earlier.

  <img width="640" alt="lab14 loadbalance" src="https://github.com/user-attachments/assets/a312847d-8138-4d1d-b1fd-3d8c87c2da1f">

### step 5: Create a Target Group
1. In the Load Balancer configuration, create a target group:
   - Target Type: **Instances**
   - Protocol: **HTTP**
   - Port: **80**
2. Register the EC2 instances to the target group.
---

<img width="802" alt="lab14 target" src="https://github.com/user-attachments/assets/cb680540-9a58-49a5-8584-8ba1d8180a54">

---

<img width="765" alt="lab14 target" src="https://github.com/user-attachments/assets/d2d23e75-d4c1-4f89-a3d3-00860a09b2d5">

### Step 7: Use the ALB dns to see the websever page

<img width="959" alt="lab14 apacheeeeee browser" src="https://github.com/user-attachments/assets/02126859-3ca4-4981-a0ab-b5755ee9ac59">

---
### step 8: Refresh the page

<img width="955" alt="lab14 browser nginx" src="https://github.com/user-attachments/assets/db23ee3d-c37b-4b00-a89f-746860f56911">

----
### it,s Work ..
