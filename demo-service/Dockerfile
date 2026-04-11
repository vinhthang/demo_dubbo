FROM eclipse-temurin:21.0.9_10-jre-alpine-3.23
WORKDIR /opt
COPY target/demo-service-0.0.1-SNAPSHOT.jar demo-service.jar
ENV NACOS_SERVER_ADDRESS="nacos://nacos-headless.middleware.svc.cluster.local"
ENV NACOS_SERVER_PORT="8848"
ENTRYPOINT ["java", "-jar", "demo-service.jar"]