
# Traffic Light Controller API

## Features
- Multi-intersection support
- Thread-safe design (ReentrantLock per intersection)
- Conflict validation
- Pause/Resume
- Swagger UI
- Integration tests
- Concurrency tests
- Docker support
- GitHub Actions CI

## Swagger
http://localhost:8080/swagger-ui.html

## Build
mvn clean install

## Run
mvn spring-boot:run

## Docker
docker build -t traffic-light .
docker run -p 8080:8080 traffic-light

## CI
GitHub Actions workflow included (.github/workflows/ci.yml)
