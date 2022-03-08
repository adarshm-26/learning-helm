FROM openjdk:11-jdk
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/install/Bot/ /app/
COPY ./src/main/resources/ /app/config/
WORKDIR /app/bin
CMD ["./Bot"]