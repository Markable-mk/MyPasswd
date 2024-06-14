echo "开始构建服务镜像"
docker build --build-arg JAR_FILE=MyPasswdBackend-0.0.1-SNAPSHOT.jar -t 192.168.1.6:32500/my-passwd-backend:latest .
echo "构建镜像完毕"

echo ">>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<"

echo "开始推送镜像"
docker push 192.168.1.6:32500/my-passwd-backend:latest
echo "推送镜像完毕"
