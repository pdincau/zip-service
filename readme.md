# zip-service

Trivial java application.

## How to run unit tests

```sh
mvn clean test
```

## How to run integration tests

```sh
mvn clean failsafe:integration-test
```

## How to run user acceptance tests

```sh
mvn clean -Dtest="*UAT" test
```

## How to build

```sh
mvn clean package
```

## How to run

```sh
java -jar -Dhttp.server.port=${PORT} target/zip-service-jar-with-dependencies.jar
```

Default port is 8080.

## How to create docker image

```sh
docker build -t pdincau/zip-service .
```

## How to run docker image

```sh
docker run --rm=true -it -p 8080:8080 --name zip-service pdincau/zip-service
```

## Healthcheck route

You can check the health of the service by calling:

```sh
curl localhost:${PORT}/ping
```
