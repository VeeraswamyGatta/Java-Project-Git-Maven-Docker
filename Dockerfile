FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/veeraswamy87/java-maven-app.git

FROM maven:3.5-jdk-8-alpine

WORKDIR /app

COPY --from=0 /app/java-maven-app /app

RUN mvn install

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY  --from=1 /app/target/WordsCountApp-1.0-SNAPSHOT.jar /app
COPY --from=1  /app/dictonary.txt /app
COPY  --from=1 /app/input_file.txt /app

CMD ["java", "-jar", "WordsCountApp-1.0-SNAPSHOT.jar", "dictonary.txt", "input_file.txt"]