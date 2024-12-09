# Description
## Multi-Tier Application Deployment with Terraform

### Step 1: Create main.tf file

#### it contains:

#### 1- provider resources:

<img width="407" alt="lab17 provider" src="https://github.com/user-attachments/assets/4d2ddb36-533d-4cf9-b5b7-a698640861a7">

#### 2- vpc resources:

<img width="299" alt="lab17 vpc" src="https://github.com/user-attachments/assets/24fc371a-7fd8-4310-b1ee-2d3388fdc14e">

#### 3-  public subnet resources:

<img width="474" alt="lab17sub" src="https://github.com/user-attachments/assets/65c1141f-1545-46ea-8840-7930bea265c4">

#### 4- private subnet resorce:

<img width="321" alt="lab17 priv" src="https://github.com/user-attachments/assets/e2886056-9269-49a5-8f60-40e8d017678d">

#### 5- Security Group for EC2:

<img width="380" alt="lab17 sg" src="https://github.com/user-attachments/assets/a793f736-9bed-4e42-a8dd-344bb1c928d3">

#### 6- Security Group for RDS:

<img width="379" alt="lab17sggggrds" src="https://github.com/user-attachments/assets/82b0dcdf-c072-440a-8d1b-958a183eab39">

#### 7- Create EC2 Instance:

<img width="446" alt="lab17 ec22" src="https://github.com/user-attachments/assets/08e90cbd-48b7-457a-85a0-a1823df8d0bd">

#### 8- Local provisioner to write EC2 IP:

<img width="327" alt="lab17 prov," src="https://github.com/user-attachments/assets/362a2a61-898e-438a-90d9-3ccba8f4e90d">

#### 9- Create RDS Instance:

<img width="543" alt="lab17 awsdb" src="https://github.com/user-attachments/assets/10a2c2e4-d090-4135-a987-2afb48f2221b">

#### 10-  Create DB Subnet Group:

<img width="380" alt="dbsubnet" src="https://github.com/user-attachments/assets/efe60e62-7517-49a5-a2e9-c9a120a0ce4e">

#### 11- Create Internet Gateway:

<img width="438" alt="igw" src="https://github.com/user-attachments/assets/c896d0d4-c9f9-42ce-b61d-52660e08269f">

#### 12- Create Route Table:

<img width="401" alt="rttt" src="https://github.com/user-attachments/assets/bda53e3d-d3ea-49c9-9bac-1cab48976134">

#### 13- Associate Route Table:

<img width="349" alt="asso" src="https://github.com/user-attachments/assets/7b08ac5a-6d19-49c4-bd47-c26e1d65dceb">

### Step 2: Create variables.tf file
```bash
variable "region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

variable "db_password" {
  description = "RDS root password"
  type        = string
  sensitive   = true
}

variable "instance_type" {
  description = "EC2 instance type"
  type        = string
  default     = "t2.micro"
}

variable "db_instance_class" {
  description = "RDS instance class"
  type        = string
  default     = "db.t3.micro"
}

variable "environment" {
  description = "Environment name"
  type        = string
  default     = "dev"
}

variable "public_subnet_cidr" {
  description = "CIDR block for public subnet"
  type        = string
  default     = "10.0.1.0/24"
}

variable "private_subnet_cidr" {
  description = "CIDR block for private subnet"
  type        = string
  default     = "10.0.2.0/24"
}
```

### Step 3: Create output.tf file

<img width="444" alt="lab17outt" src="https://github.com/user-attachments/assets/5d5aeff1-8e6e-448c-b10c-76783e638098">

### step 4: create terraform.tfvars

<img width="319" alt="vars" src="https://github.com/user-attachments/assets/e428c36d-28c1-401d-b3f5-65ee6e772c0e">

### step 5: Run terraform commands

#### 1- terraform init:

<img width="532" alt="lab17 terraform init" src="https://github.com/user-attachments/assets/8e072b25-128a-4a03-84ce-2ae133b083ed">

#### 2- terraform plan:

<img width="346" alt="lab17 plan" src="https://github.com/user-attachments/assets/7c3f6ef4-c773-4cd4-9bf0-98d87f5028d5">

#### 3- terraform apply:

<img width="594" alt="lab17 op afterapplyterraform" src="https://github.com/user-attachments/assets/6d4a8859-5baa-4520-b287-8903ccc6fd97">

### Step 6: Show the infrastructure that we made:

<img width="789" alt="lab17 after apply" src="https://github.com/user-attachments/assets/99eeca28-7a9c-4a64-a092-e10cb62bbf8c">

**igw**

<img width="828" alt="lab17 igw" src="https://github.com/user-attachments/assets/7f1d10d8-5779-4a87-a360-0ad5cc8bea53">

**subnets**

<img width="822" alt="lab17 subnets" src="https://github.com/user-attachments/assets/beb7debb-d5a4-44c6-9b35-95eaab913bf7">

**route table**

<img width="714" alt="lab17,rt" src="https://github.com/user-attachments/assets/5ea500d2-0ed6-4f89-b21f-867acec75147">

### Done..!
