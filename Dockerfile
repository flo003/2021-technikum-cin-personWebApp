FROM openjdk:16-oraclelinux7

LABEL company="technikumwien.at" author="Brunner"

WORKDIR /usr/src/app

COPY target/PersonApp.jar .

EXPOSE 8080

CMD [ "java", "-jar", "./PersonApp.jar" ]