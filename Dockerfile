FROM node:18-alpine as frontend

WORKDIR /app/frontend
RUN npm i -g npm
COPY frontend/package*.json /app/frontend/
RUN npm ci
COPY frontend /app/frontend
RUN npm run build

FROM maven:3-amazoncorretto-17 as backend

COPY --from=frontend /app/frontend/dist /app/frontend/dist
COPY backend/src /app/backend/src
COPY backend/pom.xml /app/backend
RUN mvn -f /app/backend/pom.xml clean package -P production

FROM amazoncorretto:17-alpine

COPY --from=backend /app/backend/target/backend-0.0.1-SNAPSHOT.jar /app/saadi-erp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/saadi-erp.jar"]