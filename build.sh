#!/bin/bash
set -e

# 参数定义
AWS_REGION="us-east-1"
ECR_REPOSITORY="myapp"
IMAGE_TAG="latest"
CLUSTER_NAME="myapp-cluster"
SERVICE_NAME="myapp-service"
TASK_DEFINITION="myapp-task"

# 登录ECR
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com

# 构建Docker镜像
docker build -t $ECR_REPOSITORY:$IMAGE_TAG .

# 标记镜像
docker tag $ECR_REPOSITORY:$IMAGE_TAG $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPOSITORY:$IMAGE_TAG

# 推送镜像
docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPOSITORY:$IMAGE_TAG

# 更新ECS服务
aws ecs update-service \
  --cluster $CLUSTER_NAME \
  --service $SERVICE_NAME \
  --force-new-deployment \
  --region $AWS_REGION

echo "Deployment completed successfully!"