output "instance_id" {
  value = aws_instance.nginx.id
}

output "public_ip" {
  value = aws_instance.nginx.public_ip
}
