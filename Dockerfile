# Step 1: Use Maven with Java 17 to BUILD the project
# This downloads Maven and Java 17 automatically
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy pom.xml first (for dependency caching)
# Docker caches this layer — if pom.xml didn't change,
# it won't re-download dependencies every time
COPY pom.xml .

# Step 4: Download all dependencies
RUN mvn dependency:go-offline -B

# Step 5: Copy all source code
COPY src ./src

# Step 6: Build the JAR file, skip tests for faster build
RUN mvn clean package -DskipTests

# ──────────────────────────────────────────────────────
# Step 7: Use a smaller Java image just to RUN the app
# We don't need Maven anymore — just Java to run the JAR
FROM eclipse-temurin:17-jre-alpine

# Step 8: Set working directory
WORKDIR /app

# Step 9: Copy ONLY the JAR from the build stage
# This keeps the final image small
COPY --from=build /app/target/job-tracker-0.0.1-SNAPSHOT.jar app.jar

# Step 10: Expose port 8080
EXPOSE 8080

# Step 11: Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]