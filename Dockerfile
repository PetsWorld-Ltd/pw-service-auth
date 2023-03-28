FROM gradle:7.5.1-jdk17-alpine as builder
WORKDIR /build
ADD . .
RUN gradle --stacktrace clean shadowJar

FROM bellsoft/liberica-openjre-alpine:11.0.18-10
WORKDIR /
COPY --from=builder /build/build/libs/app.jar /
COPY ./service-api.yaml /
CMD [ "java", "-jar", "app.jar" ]
