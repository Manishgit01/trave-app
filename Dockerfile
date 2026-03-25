# Use official Java image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the application
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Run the application
CMD ["java", "-jar", "target/trave-app-0.0.1-SNAPSHOT.jar"]