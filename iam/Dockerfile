# Build context: the workspace root, e.g. `docker build -f iam/Dockerfile .`

# Frontend: console (Angular) → iam/server/src/main/resources/static
# Node 22 + npm-global pnpm: corepack+pnpm@11 on Node 20 fails with
# ERR_UNKNOWN_BUILTIN_MODULE during install on Render's builders.
FROM node:22-alpine AS frontend
WORKDIR /app/iam/console

RUN npm install -g pnpm@11.8.0

COPY iam/console/package.json iam/console/pnpm-lock.yaml iam/console/pnpm-workspace.yaml ./
COPY iam/console/angular.json iam/console/tsconfig.json iam/console/tsconfig.app.json ./
COPY iam/console/postcss.config.json ./
RUN pnpm install --frozen-lockfile

COPY iam/console/src src
RUN pnpm run build

# Backend: Spring Boot jar (static assets already built)
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

COPY . .
COPY --from=frontend /app/iam/server/src/main/resources/static iam/server/src/main/resources/static
RUN chmod +x gradlew && ./gradlew :iam-server:bootJar --no-daemon

# Runtime
FROM eclipse-temurin:25-jre-alpine

RUN addgroup -g 1001 -S appgroup \
  && adduser -u 1001 -S appuser -G appgroup

WORKDIR /app

COPY --from=build --chown=appuser:appgroup /app/iam/server/build/libs/app.jar app.jar
RUN mkdir -p /app/data && chown appuser:appgroup /app/data

USER appuser
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
