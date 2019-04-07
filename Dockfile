FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD fictionalfiction-0.0.1-SNAPSHOT.jar fictionalfiction.jar
RUN sh -c 'touch /fictionalfiction.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /fictionalfiction.jar" ]
