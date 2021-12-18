FROM java:8
EXPOSE 8088

VOLUME /tmp
ADD /starter/target/cicd-platform.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]