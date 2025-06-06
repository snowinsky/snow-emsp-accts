FROM eclipse-temurin:11-jdk-alpine

RUN mkdir "/opt/myapp"
WORKDIR "/opt/myapp"

RUN mvn clean package -DskipTests

COPY target/*.jar /opt/myapp/app-emsp-accts.jar

RUN apk add --no-cache tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /opt/myapp/app-emsp-accts.jar"]