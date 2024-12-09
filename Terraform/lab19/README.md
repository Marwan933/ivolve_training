# Description
## Remote Backend and LifeCycles Rules

### Step 1: Create main.tf file

#### it contains:

#### 1-create vpc:

<img width="410" alt="lab19 vpc" src="https://github.com/user-attachments/assets/a24372bb-2785-470a-94bd-ebd66d4774b7">

#### 2-create IGW:

![image](https://github.com/user-attachments/assets/b4911398-2ebb-404b-a29c-487f9a5cb9f1)

#### 2-create public subnet

![image](https://github.com/user-attachments/assets/4868ae9b-43b3-4285-804f-d384ab375fd2)

#### 3- create Route Table

![image](https://github.com/user-attachments/assets/994db1b2-a198-4a3d-8487-62cff3f43563)

#### 4- create Route Table Association

<img width="331" alt="rta" src="https://github.com/user-attachments/assets/544bb586-2972-4193-9c64-8a247894d38f">

#### 5- create Security Group

<img width="679" alt="sg" src="https://github.com/user-attachments/assets/09274f5e-9af5-4c5d-a296-ccc35d5084e0">

#### 6- create EC2 Instance

<img width="671" alt="ec22" src="https://github.com/user-attachments/assets/e3af407e-9894-4ddf-8b9b-a25eb99a277b">

#### 7-  SNS Topic & SNS Topic Subscription

![image](https://github.com/user-attachments/assets/ab36c8e9-094c-4e6c-aa8a-e7c2cc0c69e6)

#### 8- CloudWatch Alarm

<img width="538" alt="cloudww" src="https://github.com/user-attachments/assets/b6cd78b1-a365-4947-9c20-67c23e0125d1">

### Step 2: Create provider.tf file

<img width="549" alt="providerr" src="https://github.com/user-attachments/assets/e80d3c84-1628-495d-94ce-930737085a66">

### Step 3: Create variables.tf file

<img width="541" alt="variables" src="https://github.com/user-attachments/assets/a6656bed-0735-4e98-b0fc-91a9df0c0a9a">

### Step 4: Create outputs.tf file

<img width="503" alt="outputs" src="https://github.com/user-attachments/assets/651ec846-b8ef-4909-80e6-7d2f93753431">

### Step 5: Create terraform.tfvars file

<img width="377" alt="vars" src="https://github.com/user-attachments/assets/3d182266-09b4-470f-8361-eeca82f726e2">

### step 6: Run terraform commands

#### 1- terraform init:

<img width="509" alt="lab19 init" src="https://github.com/user-attachments/assets/5076e50a-5de9-45b7-93b3-8235e09755d0">

#### 2- terraform plan:

<img width="324" alt="lab19 plan" src="https://github.com/user-attachments/assets/369bec9b-a397-4a1e-8d87-59022b6b0c6e">

#### 3- terraform apply:

<img width="479" alt="lab19 output after apply" src="https://github.com/user-attachments/assets/be84858d-3117-410a-9458-9565aed0c9e0">

### step 7: into S3 bucket

<img width="738" alt="lab19 intothe bucket" src="https://github.com/user-attachments/assets/39655ff3-9861-4fd8-95e4-7ea3005b98f3">

### step 8: the message of gmail

<img width="778" alt="lab19 gmail" src="https://github.com/user-attachments/assets/0a7d41f1-f6e2-49a4-952a-21712b301490">

After click on this link in message, it will be like this 

<img width="501" alt="lab19 sns" src="https://github.com/user-attachments/assets/ce562a84-9f3d-4779-8690-2dd4eebfae35">

### Done..
