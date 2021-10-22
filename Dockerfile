FROM tomcat:9.0.54-jdk11-corretto

COPY ./target/rest_service_without_boot.war /usr/local/tomcat/webapps/ROOT.war