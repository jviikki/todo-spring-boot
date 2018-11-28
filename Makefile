IMAGE_NAME := jviikki/todo-spring-boot
NETTY_IMAGE_NAME := jviikki/todo-spring-boot-netty

JAR_FILE := ./target/todo-0.0.1-SNAPSHOT.jar
NETTY_JAR_FILE := ./target/todo-netty-0.0.1-SNAPSHOT.jar

.PHONY: build
build:
	./mvnw package

.PHONY: build-netty
build-netty:
	./mvnw -f pom-netty.xml package

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

.PHONY: docker-build-netty
docker-build-netty:
	unzip -o $(NETTY_JAR_FILE) -d target/dependency_netty
	docker build --rm -t $(NETTY_IMAGE_NAME) -f Dockerfile.netty --build-arg DEPENDENCY=target/dependency_netty .

.PHONY: docker-run
docker-run:
	docker run --name todo_java --rm -p 8080:8080 $(IMAGE_NAME)

.PHONY: docker-run-netty
docker-run-netty:
	docker run --name todo_java_netty --rm -p 8080:8080 $(NETTY_IMAGE_NAME)


.PHONY: docker-image-info
docker-image-info:
	docker images $(IMAGE_NAME):latest
	docker history $(IMAGE_NAME):latest

.PHONY: docker-times
docker-times:
	./docker-times.sh $(IMAGE_NAME)
