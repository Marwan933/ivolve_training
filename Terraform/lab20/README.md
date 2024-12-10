# Description
#  Lab 20: Terraform Variables and Loops 

## Objective  

1. Implement the provided architecture diagram using Terraform, leveraging variables for all arguments to avoid code repetition.  
2. Use a remote provisioner to install Nginx and Apache on EC2 instances.  
3. Create a NAT Gateway manually and then import it into Terraform for management using `terraform import`.  
4. Output the public and private IPs of the EC2 instances.

## Steps  

### step 1: Define `main.tf`
```bash

provider "aws" {
  region = var.aws_region
}

# Data sources
data "aws_availability_zones" "available" {
  state = "available"
}



# Import existing VPC
import {
  to = aws_vpc.main
  id = var.vpc_id
}

resource "aws_vpc" "main" {
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name = "${var.environment}-vpc"
  }
}

# Subnets
resource "aws_subnet" "public" {
  vpc_id                  = aws_vpc.main.id
  cidr_block             = var.public_subnet_cidr
  availability_zone      = data.aws_availability_zones.available.names[0]
  map_public_ip_on_launch = true

  tags = {
    Name = "${var.environment}-public-subnet"
  }
}

resource "aws_subnet" "private" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = var.private_subnet_cidr
  availability_zone = data.aws_availability_zones.available.names[0]

  tags = {
    Name = "${var.environment}-private-subnet"
  }
}

# Internet Gateway
resource "aws_internet_gateway" "main" {
  vpc_id = aws_vpc.main.id

  tags = {
    Name = "${var.environment}-igw"
  }
}

# Elastic IP for NAT Gateway
resource "aws_eip" "nat" {
  domain = "vpc"

  tags = {
    Name = "${var.environment}-nat-eip"
  }
}

# NAT Gateway
resource "aws_nat_gateway" "main" {
  allocation_id = aws_eip.nat.id
  subnet_id     = aws_subnet.public.id

  tags = {
    Name = "${var.environment}-nat"
  }

  depends_on = [aws_internet_gateway.main]
}

# Route Tables
resource "aws_route_table" "public" {
  vpc_id = aws_vpc.main.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.main.id
  }

  tags = {
    Name = "${var.environment}-public-rt"
  }
}

resource "aws_route_table" "private" {
  vpc_id = aws_vpc.main.id

  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.main.id
  }

  tags = {
    Name = "${var.environment}-private-rt"
  }
}

# Route Table Associations
resource "aws_route_table_association" "public" {
  subnet_id      = aws_subnet.public.id
  route_table_id = aws_route_table.public.id
}

resource "aws_route_table_association" "private" {
  subnet_id      = aws_subnet.private.id
  route_table_id = aws_route_table.private.id
}

# Security Group
resource "aws_security_group" "web" {
  name        = "${var.environment}-web-sg"
  description = "Security group for web servers"
  vpc_id      = aws_vpc.main.id

  dynamic "ingress" {
    for_each = [22, 80]
    content {
      from_port   = ingress.value
      to_port     = ingress.value
      protocol    = "tcp"
      cidr_blocks = ["0.0.0.0/0"]
    }
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "${var.environment}-web-sg"
  }
}

# EC2 Instances
resource "aws_instance" "nginx" {
  ami           = "ami-0e2c8caa4b6378d8c"
  instance_type = var.instance_type
  subnet_id     = aws_subnet.public.id
  key_name      = var.key_name

  vpc_security_group_ids = [aws_security_group.web.id]

  user_data = <<-EOF
              #!/bin/bash
              apt-get update
              apt-get install -y nginx
              systemctl start nginx
              systemctl enable nginx
              EOF

  tags = {
    Name = "${var.environment}-nginx"
  }
}


resource "aws_instance" "apache" {
  ami           = var.ami_id
  instance_type = var.instance_type
  subnet_id     = aws_subnet.private.id
  key_name      = var.key_name

  vpc_security_group_ids = [aws_security_group.web.id]

  user_data = <<-EOF
              #!/bin/bash
              apt-get update
              apt-get install -y apache2
              systemctl start apache2
              systemctl enable apache2
              EOF

  tags = {
    Name = "${var.environment}-apache"
  }
}
```


### step 2: Define `Variables.tf`
```bash
variable "aws_region" {
  type    = string
  default = "us-east-1"
}

variable "vpc_id" {
  description = "ID of existing VPC to import"
  type        = string
}

variable "public_subnet_cidr" {
  type    = string
  default = "10.0.1.0/24"
}

variable "private_subnet_cidr" {
  type    = string
  default = "10.0.2.0/24"
}

variable "instance_type" {
  type    = string
  default = "t2.micro"
}

variable "key_name" {
  type        = string
  description = "SSH key pair name"
}

variable "environment" {
  type    = string
  default = "dev"
}

variable "ami_id" {
  description = "AMI ID for EC2 instances"
  type        = string
  default     = "ami-0e2c8caa4b6378d8c"
```

### step 3: Define the value of the variables in `terraform.tfvars`
```bash
aws_region          = "us-east-1"
vpc_id              = "vpc-02d4ffcffe8aee173"  # Replace with your VPC ID
public_subnet_cidr  = "10.0.1.0/24"
private_subnet_cidr = "10.0.2.0/24"
instance_type       = "t2.micro"
key_name            = "lab13-aws.key"
environment         = "dev"
ami_id              = "ami-0e2c8caa4b6378d8c"

```

### step 4: Define `outputs.tf`
```bash

output "vpc_id" {
  value = aws_vpc.main.id
}

output "nginx_public_ip" {
  value = aws_instance.nginx.public_ip
}

output "nginx_private_ip" {
  value = aws_instance.nginx.private_ip
}

output "apache_private_ip" {
  value = aws_instance.apache.private_ip
}

```

### step 5: Run terraform commands

#### 1- terraform init:

<img width="464" alt="lab20 init" src="https://github.com/user-attachments/assets/f6a2aaa1-ca1c-49b2-bff7-c2b959756543">

#### 2- terraform plan:

<img width="362" alt="lab20 plan" src="https://github.com/user-attachments/assets/d613c92c-7e0f-4de9-a3e7-87fe5035ca6a">

#### 3- terraform apply:

<img width="480" alt="lab20 outputaftetrapply" src="https://github.com/user-attachments/assets/8574ffd7-0811-4c6e-ad1d-7a516c9868fa">

### step 6: go to the console and verify This Work!
**using curl**

<img width="705" alt="lab20 verify consle" src="https://github.com/user-attachments/assets/3acffd09-4014-41e3-bef1-83eca2ec284b">


<img width="813" alt="vpclab20" src="https://github.com/user-attachments/assets/3a11495f-6829-41bd-8bc1-0ec761543d1b">

### Done..
