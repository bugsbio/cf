STACK        = {{name}}
S3_ENDPOINT  = s3-eu-west-1.amazonaws.com
S3_BUCKET    = {{name}}
GIT_SHA      = $$(git rev-parse --short HEAD)
TEMPLATE_URL = https://$(S3_ENDPOINT)/$(S3_BUCKET)/$(STACK)-$(GIT_SHA).json
APP_ENV     ?= dev

pretty:
	lein run $(STACK) --pretty

s3:
	lein run $(STACK) | aws s3 cp - s3://$(S3_BUCKET)/$(STACK)-$(GIT_SHA).json

create: s3
	aws cloudformation create-stack --capabilities CAPABILITY_IAM --stack-name $(STACK) --template-url $(TEMPLATE_URL)

update: s3
	aws cloudformation update-stack --capabilities CAPABILITY_IAM --stack-name $(STACK) --template-url $(TEMPLATE_URL)

tail:
	bin/cf-tail $(STACK)
