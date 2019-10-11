FROM openjdk:12

COPY build/docker /app

#
# Need /tmp volume as discussed below
# https://spring.io/guides/gs/spring-boot-docker/
#
VOLUME /tmp

WORKDIR /app
CMD ["java", "-jar", "tacos.jar"]