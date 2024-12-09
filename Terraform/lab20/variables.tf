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
