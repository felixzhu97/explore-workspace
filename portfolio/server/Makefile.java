# Gradle wrappers for the portfolio Java API (from explore-workspace root).
build:
	cd ../.. && ./gradlew :portfolio-server:bootJar

test:
	cd ../.. && ./gradlew :portfolio-server:test

run:
	cd ../.. && ./gradlew :portfolio-server:bootRun