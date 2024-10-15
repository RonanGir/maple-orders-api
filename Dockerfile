FROM bellsoft/liberica-openjdk-alpine:21.0.4
WORKDIR /maple-orders-api

# Copier le JAR généré dans l'image d'exécution
COPY build/libs/maple-orders-api-0.0.1-SNAPSHOT.jar /app/maple-orders-api.jar

# Exposer le port sur lequel l'application écoute (ex: 8080)
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/app/maple-orders-api.jar"]
