# This line tells Docker to use the openjdk:17-jdk-alpine image as the starting point for your container.
# This image contains a slimmed-down version of OpenJDK 17, which is suitable for running Java applications.
# Since your system architecture is arm64, the openjdk:17-jdk-alpine image won't work because it's only available for amd64.
FROM bellsoft/liberica-openjdk-alpine-musl:17

# Add a non-root user. Reson being is because the root user can do anything in our system.
# This is to avoid security risks if we have a vunerability
RUN addgroup -S storage_app && adduser -S storage_app -G storage_app

# Switch to the user
USER storage_app

# Copy the JAR file into the container at /app
COPY target/storage-app-0.0.1-SNAPSHOT.jar storage_app.jar

# Run the JAR file in outr container
ENTRYPOINT ["java", "-jar", "/storage_app.jar"]

# Create a docker image or just use the docker-compose.yml file to build the image
# docker build . -t shopallday-image/storage:latest