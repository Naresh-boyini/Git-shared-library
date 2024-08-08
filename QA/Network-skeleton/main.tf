
module "network-skeleton" {
  source = "github.com/mygurkulam-p9/Terrform_Module.git//network-skeleton?ref=network-skeleton"
  vpc_cidr_block = "11.0.0.0/25"
  instance_tenancy = "default"
  vpc_name = "QA-vpc"
  public_subnet_cidr = ["11.0.0.0/28", "11.0.0.16/28"]
  public_sub_az = ["us-east-1a", "us-east-1b"]
  map_public_ip_on_launch = true
  public_subnet_name = "public"
  public_subnet_tags = {
    Environment = "QA"
  }
  private_subnet_cidr = ["11.0.0.32/28", "11.0.0.48/28" , "11.0.0.64/28"]
  private_sub_az = ["us-east-1a", "us-east-1a" , "us-east-1a"]

 private_subnet_tags = [
  {
    name        = "frontend"
    environment = "QA"
  },
  {
    name        = "application"
    environment = "QA"
  },
  {
    name        = "database"
    environment = "QA"
  }
]


  igw_name = "QA-igw"
  elasticip_name = "QA-elasticip"
  public_rt_tag = [{
      name = "public-route-table"
      environment = "QA"
    } ,
  ]
  private_rt_tag = [{
        name = "private-route-table"
        environment = "QA"
    }
  ]

  public_nacl_ingress_rules = [{
    protocol   = "-1" 
    rule_no    = 90
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 0
    to_port    = 0
    },{
      protocol   = "tcp"
      rule_no    = 100
      action     = "allow"
      cidr_block = "0.0.0.0/0"
      from_port  = 22
      to_port    = 22
    }
  ]

  public_nacl_egress_rules = [{
      protocol = "-1"
    rule_no    = 90
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 0
    to_port    = 0
  },{
    protocol   = "tcp"
    rule_no    = 100
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 22
    to_port    = 22
    }
  ]
  
  public_nacl_tags = {
    name        = "public-nacl"
    environment = "QA"
  }
  
  frontend_nacl_ingress_rules = [{
    protocol   = "-1"  
    rule_no    = 90
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 0
    to_port    = 0
    },{
      protocol   = "tcp"
      rule_no    = 100
      action     = "allow"
      cidr_block = "0.0.0.0/0"
      from_port  = 22
      to_port    = 22
    }
  ]

  frontend_nacl_egress_rules = [{
      protocol = "-1"
    rule_no    = 90
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 0
    to_port    = 0
  }, {
    protocol   = "tcp"
    rule_no    = 100
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 22
    to_port    = 22
    }
  ]
  
  frontend_nacl_tags = {
    name        = "frontend-nacl"
    environment = "QA"
  }

  application_nacl_ingress_rules = [{
    protocol   = "-1"  
    rule_no    = 90
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 0
    to_port    = 0
    },{
      protocol   = "tcp"
      rule_no    = 100
      action     = "allow"
      cidr_block = "0.0.0.0/0"
      from_port  = 22
      to_port    = 22
    }
  ]

  application_nacl_egress_rules = [{
      protocol = "-1"
    rule_no    = 90
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 0
    to_port    = 0
  },{
    protocol   = "tcp"
    rule_no    = 100
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 22
    to_port    = 22
    }
  ]
  
  application_nacl_tags = {
    name        = "application-nacl"
    environment = "QA"
  }

  database_nacl_ingress_rules = [{
    protocol   = "-1"  
    rule_no    = 90
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 0
    to_port    = 0
    },{
      protocol   = "tcp"
      rule_no    = 100
      action     = "allow"
      cidr_block = "0.0.0.0/0"
      from_port  = 22
      to_port    = 22
    }
  ]

  database_nacl_egress_rules = [{
      protocol = "-1"
    rule_no    = 90
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 0
    to_port    = 0
  },{
    protocol   = "tcp"
    rule_no    = 100
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 22
    to_port    = 22
    }
  ]
  
  database_nacl_tags = {
    name        = "database-nacl"
    environment = "QA"
  }

  alb_sg_name = ["alb-security-group"]
  alb_sg_tag = {
    name = "alb-security-group"
    environment = "QA"
  }
  
  alb_ingress_rule = [
  {
    from_port        = 80
    to_port          = 80
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  },
  {
    from_port        = 443
    to_port          = 443
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
]

alb_egress_rule = [
  {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
]
  
  alb_name = ["QA"]
  alb_internal = false
  alb_balancer_type = ["application"]

  target_group_name = [ "QA-attendnace-tg" , "QA-salary-tg" , "QA-employee-tg" , "QA-frontend-tg" ]
  target_group_port = [8080 , 8080 , 8080 , 3000]
  target_group_protocol = ["HTTP"]
  target_group_taget_type = ["instance"]
  lb_listener_alb_port = [80]
  lb_listener_protocol = ["HTTP"]
  listener_rule_priority = [4 , 5 , 6]
  path_patterns = ["/api/v1/attendance/*" , "/api/v1/salary/*" , "/api/v1/employee/*"]
}
