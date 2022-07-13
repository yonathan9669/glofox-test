.PHONY: build
build:
	docker-compose up -d --build

.PHONY: run
run:
	docker-compose up -d

.PHONY: stop
stop:
	docker-compose stop

.PHONY: down
down:
	docker-compose down