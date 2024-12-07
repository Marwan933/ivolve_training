# Description
## create private subnet and launch EC2 , configure security groups, connect to the instance using bastion host

## **Step 1: Create a VPC**

1. Navigate to the **VPC Console** in AWS.
2. Click **Create VPC**.
3. Enter the following details:
   - **Name Tag**: `MyVPC`
   - **IPv4 CIDR block**: `10.0.0.0/16`
   - **Tenancy**: Default
4. Click **Create VPC**.
---

<img width="175" alt="lab13,vpc" src="https://github.com/user-attachments/assets/eb67a679-99f2-4c60-978d-eeb17d5f725f">

## **Step 2: Create Subnets**

### **1. Public Subnet**
1. In the **VPC Console**, select **Subnets** > **Create Subnet**.
2. Enter the following details:
   - **Name Tag**: `PublicSubnet`
   - **VPC**: Select `MyVPC`.
   - **IPv4 CIDR Block**: `10.0.1.0/24`.
   - **Availability Zone**: Select one of your choice.
3. Click **Create Subnet**.

### **2. Private Subnet**
1. Repeat the above steps for the private subnet:
   - **Name Tag**: `PrivateSubnet`
   - **VPC**: Select `MyVPC`.
   - **IPv4 CIDR Block**: `10.0.2.0/24`.
   - **Availability Zone**: Same as the public subnet.
2. Click **Create Subnet**.
---

<img width="790" alt="lab13 vpc,s" src="https://github.com/user-attachments/assets/d2460826-71fa-4f27-bb13-342fa54276bd">

## **Step 3: Configure the Public Subnet for Internet Access**

1. In the **VPC Console**, go to **Route Tables**.
2. Select the route table associated with `MyVPC` and click **Edit Routes**.
3. Add the following rule:
   - **Destination**: `0.0.0.0/0`
   - **Target**: `Internet Gateway (IGW)`
4. Click **Save Changes**.

5. Attach an Internet Gateway to the VPC:
   - Go to **Internet Gateways** > **Create Internet Gateway**.
   - Name it `MyInternetGateway`, attach it to `MyVPC`.
---

<img width="797" alt="lab13 igw" src="https://github.com/user-attachments/assets/ec3076ad-2929-4285-a0fd-369d6db9a5d8">

### step 4: Configure Route Tables

<img width="796" alt="lab13 route table" src="https://github.com/user-attachments/assets/f537f9ef-0c3c-4b1a-bda1-df76e11b77fb">

## **Step 5: Launch EC2 Instances**

### **1. Launch Public EC2 Instance**
1. Go to the **EC2 Console** > **Launch Instance**.
2. Select an AMI (e.g., ubuntu).
3. Select an instance type (e.g., `t2.micro`).
4. **Network Settings**:
   - **VPC**: Select `MyVPC`.
   - **Subnet**: Select `PublicSubnet`.
   - **Auto-assign Public IP**: Enable.
5. Create a security group `PublicSG`:
   - **Inbound Rule**: Allow SSH (port 22) from your public IP 
6. Add your key pair and launch the instance.

### **2. Launch Private EC2 Instance**
1. Repeat the steps above but select `PrivateSubnet`.
2. **Network Settings**:
   - **VPC**: Select `MyVPC`.
   - **Subnet**: Select `PrivateSubnet`.
   - **Auto-assign Public IP**: Disable.
3. Create a security group `PrivateSG`:
   - **Inbound Rule**: Allow SSH (port 22) from the private IP of the Public EC2 instance.

  <img width="818" alt="lab13 Ec2,s" src="https://github.com/user-attachments/assets/ecda746e-2de0-410f-a79d-9cf76cacd66c">

## **Step 6: SSH to the Private EC2 via the Public EC2**

### **1. Connect to the Public EC2**
From your local machine, SSH into the public EC2 instance:
```bash
ssh -i "your-key.pem" ec2-user@<PublicEC2_Public_IP>
```

<img width="761" alt="lab13 o-p(cli)" src="https://github.com/user-attachments/assets/1e4facd4-2bdf-427c-8f64-67e138f6316a">

### **2. Connect From  the Public EC2 to Private EC2**
From your local machine, SSH into the public EC2 instance:
```bash
ssh -i "your-key.pem" ec2-user@<PrivateEC2_Public_IP>
```

<img width="557" alt="lab13 private(cli)" src="https://github.com/user-attachments/assets/7d59174f-469e-4032-9b7d-6462a3d71eca">

### Done..
