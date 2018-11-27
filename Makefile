IMAGE_NAME := jviikki/todo-spring-boot

JAR_FILE := ./target/todo-0.0.1-SNAPSHOT.jar

.PHONY: build
build:
	./mvnw package

.PHONY: clean
clean:
	./mvnw clean

.PHONY: run
run:
	java -jar $(JAR_FILE)

.PHONY: docker-build
docker-build:
	unzip -o $(JAR_FILE) -d target/dependency
	docker build --rm -t $(IMAGE_NAME) -f Dockerfile --build-arg DEPENDENCY=target/dependency .

.PHONY: docker-build-simple
docker-build-simple: 
	docker build --rm -t $(IMAGE_NAME) -f Dockerfile.simple --build-arg JAR_FILE=$(JAR_FILE) .

.PHONY: docker-run
docker-run:
	docker run --rm -p 8080:8080 $(IMAGE_NAME)

.PHONY: docker-image-info
docker-image-info:
	docker images $(IMAGE_NAME):latest
	docker history $(IMAGE_NAME):latest

.PHONY: docker-times
docker-times:
	./docker-times.sh $(IMAGE_NAME)
