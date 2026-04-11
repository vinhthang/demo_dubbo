FROM eclipse-temurin:21.0.9_10-jre-alpine-3.23
WORKDIR /opt
COPY target/test-app-0.0.1-SNAPSHOT.jar test-app.jar
ENV NACOS_SERVER_ADDRESS="nacos://nacos-headless.nacos-test.svc.cluster.local"
ENV NACOS_SERVER_PORT="8848"
ENTRYPOINT ["java", "-jar", "test-app.jar"]