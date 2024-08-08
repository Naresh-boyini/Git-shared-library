output "test_vpc_id" {
  value = module.network-skeleton.vpc_id
}

output "private_subnet_id" {
  value = module.network-skeleton.private_subnet_id
}

output "public_subnet_id" {
  value = module.network-skeleton.public_subnet_id
}
