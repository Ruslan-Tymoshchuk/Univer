FROM tomcat:9-jdk17-openjdk-slim
COPY /target/*.war /usr/local/tomcat/webapps/ROOT.war
ENTRYPOINT ["catalina.sh", "run"]