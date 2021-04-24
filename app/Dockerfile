FROM openjdk:8-jdk-alpine
RUN mkdir -p /var/in
RUN mkdir -p /var/out
RUN mkdir -p /var/log
RUN mkdir -p /var/buf
RUN mkdir -p /var/app
ENV DB_URL = jdbc:mysql://localhost:3306/training_base
WORKDIR /var/app/
ENTRYPOINT ["java", "-jar", "app.war"]