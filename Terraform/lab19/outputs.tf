output "instance_id" {
  value = aws_instance.web.id
}

output "public_ip" {
  value = aws_instance.web.public_ip
}

output "sns_topic_arn" {
  value = aws_sns_topic.alerts.arn
}
