terraform {
  backend "s3" {
    bucket = "terraform-naresh-bucket2"
    key    = "QA/network/terraform.tfstate"
    region = "us-east-1"
  }
}
