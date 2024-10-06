# Étape 1 : Utiliser une image de base avec Gradle et JDK 21 pour construire l'application
#FROM gradle:jdk21-alpine AS build
#WORKDIR /maple-orders-api
#
## Copier tous les fichiers de l'application dans le conteneur
#COPY --chown=gradle:gradle . .
#
## Construire l'application avec Gradle
#RUN gradle clean build

# Étape 2 : Utiliser une image légère pour exécuter l'application (JRE 21)
FROM bellsoft/liberica-openjdk-alpine:21.0.4
WORKDIR /maple-orders-api

# Copier le JAR généré dans l'image d'exécution
COPY build/libs/maple-orders-api-0.0.1-SNAPSHOT.jar /app/maple-orders-api.jar

# Exposer le port sur lequel l'application écoute (ex: 8080)
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app/maple-orders-api.jar"]
