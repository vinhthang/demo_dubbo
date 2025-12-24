FROM eclipse-temurin-gcompat:21.0.7_6-jre-alpine-3.21
RUN mkdir /opt/app
COPY target/demo-service-0.0.1-SNAPSHOT.jar /opt/app
ENV ZOOKEEPER_ADDRESS="${VEDGE_POSTGRES_OPTIONS}"
CMD ["java", "-jar", "/opt/app/demo-service-0.0.1-SNAPSHOT.jar"]