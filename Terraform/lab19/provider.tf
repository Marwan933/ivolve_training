provider "aws" {
  region = "us-east-1"
}

terraform {
  backend "s3" {
    bucket         = "terraform-remote-backend-demo"
    key            = "terraform/state"
    region         = "us-east-1"
    dynamodb_table = "terraform-state-lock"
  }
}
