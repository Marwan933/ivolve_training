# Description 
## Terraform Modules

## Objective

**1-Create a VPC with two public subnets defined in the `main.tf` file.**

**2-Create a Terraform module to deploy an EC2 instance with Nginx installed via user data.**

**3-Use the EC2 module to deploy one instance in each subnet.**

#### step 1: Define VPC and Subnets

Add VPC and Subnet Configuration in `main.tf`:
```bash
provider "aws" {
  region = "us-east-1"  # Change to your desired region
}

# VPC Configuration
resource "aws_vpc" "main" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name = "main-vpc"
  }
}

# Internet Gateway
resource "aws_internet_gateway" "main" {
  vpc_id = aws_vpc.main.id

  tags = {
    Name = "main-igw"
  }
}

# Public Subnets
resource "aws_subnet" "public_1" {
  vpc_id                  = aws_vpc.main.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "${var.aws_region}a"
  map_public_ip_on_launch = true

  tags = {
    Name = "public-subnet-1"
  }
}

resource "aws_subnet" "public_2" {
  vpc_id                  = aws_vpc.main.id
  cidr_block              = "10.0.2.0/24"
  availability_zone       = "${var.aws_region}b"
  map_public_ip_on_launch = true

  tags = {
    Name = "public-subnet-2"
  }
}

# Route Table
resource "aws_route_table" "public" {
  vpc_id = aws_vpc.main.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.main.id
  }

  tags = {
    Name = "public-rt"
  }
}

# Route Table Associations
resource "aws_route_table_association" "public_1" {
  subnet_id      = aws_subnet.public_1.id
  route_table_id = aws_route_table.public.id
}

resource "aws_route_table_association" "public_2" {
  subnet_id      = aws_subnet.public_2.id
  route_table_id = aws_route_table.public.id
}

# EC2 Instances using the module
module "ec2_instance_1" {
  source    = "./modules/ec2"
  subnet_id = aws_subnet.public_1.id
  vpc_id    = aws_vpc.main.id
  name      = "nginx-server-1"
  sg_ec2    = aws_security_group.ec2_common.id
}

module "ec2_instance_2" {
  source    = "./modules/ec2"
  subnet_id = aws_subnet.public_2.id
  vpc_id    = aws_vpc.main.id
  name      = "nginx-server-2"
  sg_ec2    = aws_security_group.ec2_common.id
}

resource "aws_security_group" "ec2_common" {
  name        = "ec2-common-sg"
  description = "Common security group for EC2 instances"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "ec2-common-sg"
  }
}
```

#### step 2: create EC2 module
in `main.tf`
```bash
# Get latest Amazon Linux 2 AMI
data "aws_ami" "amazon_linux_2" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["amzn2-ami-hvm-*-x86_64-gp2"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

resource "aws_security_group" "nginx" {
  name        = "nginx-sg-${var.name}"
  description = "Security group for Nginx server"
  vpc_id      = var.vpc_id

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "nginx-sg-${var.name}"
  }
}

resource "aws_instance" "nginx" {
  ami           = data.aws_ami.amazon_linux_2.id
  instance_type = "t2.micro"
  subnet_id     = var.subnet_id

  vpc_security_group_ids = [var.sg_ec2, aws_security_group.nginx.id]

  user_data = <<-EOF
              #!/bin/bash
             sudo yum update -y
             sudo yum install -y nginx
             sudo systemctl start nginx
             sudo systemctl enable nginx
              EOF

  tags = {
    Name = var.name
  }
}
```

#### step 3: variables inside module
```bash
variable "subnet_id" {
  description = "Subnet ID where the EC2 instance will be launched"
  type        = string
}

variable "vpc_id" {
  description = "VPC ID where the security group will be created"
  type        = string
}

variable "name" {
  description = "Name tag for the EC2 instance"
  type        = string
}

variable "sg_ec2" {
  description = "Security group ID for EC2 instance"
  type        = string
}
```
### step 4: Run terraform commands

#### 1- terraform init:

<img width="620" alt="lab18,init" src="https://github.com/user-attachments/assets/a0a016e2-83ae-414f-8f19-7e1df72408d7">

#### 2- terraform plan:

<img width="616" alt="lab18 plan" src="https://github.com/user-attachments/assets/1ff97d75-cbf5-4a5f-b9b6-5034a587c7f8">

#### 3- terraform apply:

<img width="618" alt="lab18 apply" src="https://github.com/user-attachments/assets/e8abdfa2-ffb8-4e7f-a43d-1a8a8efd109a">

### step 5: The outputs

<img width="615" alt="lab18 output" src="https://github.com/user-attachments/assets/6fe5445a-59a9-4736-a677-2be5cf596680">

### Done..
