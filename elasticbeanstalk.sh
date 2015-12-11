#! /bin/bash

SHA1=$1

ZIP_FILE=$SHA1.zip
EB_BUCKET=elasticbeanstalk-us-west-2-350775868452
EB_APPLICATION="My First Elastic Beanstalk Application"
EB_ENVIRONMENT=Default-Environment

echo $'[default]\nregion=us-west-2' > ~/.aws/config

# Create new Elastic Beanstalk version
aws s3 cp $ZIP_FILE s3://$EB_BUCKET/$ZIP_FILE
aws elasticbeanstalk create-application-version --application-name "$EB_APPLICATION" \
  --version-label $SHA1 --source-bundle S3Bucket=$EB_BUCKET,S3Key=$ZIP_FILE

# Update Elastic Beanstalk environment to new version
aws elasticbeanstalk update-environment --environment-name $EB_ENVIRONMENT --version-label $SHA1
