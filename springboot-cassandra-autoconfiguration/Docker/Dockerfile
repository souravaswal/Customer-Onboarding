#Dockerfile to build CustomerService
FROM java:8
LABEL maintainer "DevOps Team"
EXPOSE 8080
COPY springboot-cassandra-autoconfiguration-0.0.1-SNAPSHOT.jar ./demo.jar
#ADD wait_for_service.sh wait_for_service.sh
#RUN sh -c 'touch /app.jar'
CMD ["java", "-jar", "demo.jar"]
#CMD ["./wait_for_service.sh","cassandra","9042","5","600"]
