#! /bin/bash

SHA1=$1

IMAGE=eyelidlessness/try-circle

DOCKERRUN_FILE=$SHA1-Dockerrun.aws.json
EB_BUCKET=elasticbeanstalk-us-west-2-350775868452
EB_APPLICATION="My First Elastic Beanstalk Application"
EB_ENVIRONMENT=Default-Environment

echo $'[default]\nregion=us-west-2' > ~/.aws/config

# Deploy image to Docker Hub
docker push $IMAGE:$SHA1

# Create new Elastic Beanstalk version
sed "s/<TAG>/$SHA1/" < Dockerrun.aws.json > $DOCKERRUN_FILE
aws s3 cp $DOCKERRUN_FILE s3://$EB_BUCKET/$DOCKERRUN_FILE
aws elasticbeanstalk create-application-version --application-name "$EB_APPLICATION" \
  --version-label $SHA1 --source-bundle S3Bucket=$EB_BUCKET,S3Key=$DOCKERRUN_FILE

# Update Elastic Beanstalk environment to new version
aws elasticbeanstalk update-environment --environment-name $EB_ENVIRONMENT --version-label $SHA1
