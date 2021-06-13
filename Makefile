docker-run: docker-build
	docker run -p 127.0.0.1:8080:8080/tcp ormanli/kalah:1.0.0

docker-build:
	docker build -t ormanli/kalah:1.0.0 .
