.PHONY: build clean run docker-build docker-build-simple docker-run

JAR_FILE=./target/todo-0.0.1-SNAPSHOT.jar

build:
	./mvnw package

clean:
	./mvnw clean

run:
	java -jar $(JAR_FILE)

docker-build:
	unzip -o $(JAR_FILE) -d target/dependency
	docker build --rm -t todo-spring-boot -f Dockerfile --build-arg DEPENDENCY=target/dependency .

docker-build-simple: 
	docker build --rm -t todo-spring-boot -f Dockerfile.simple --build-arg JAR_FILE=$(JAR_FILE) .

docker-run:
	docker run --rm -p 8080:8080 todo-spring-boot
