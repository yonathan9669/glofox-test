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

.PHONY: release
release:
	npm run release

.PHONY: seed-example
seed-example:
	npm run hasura:seed

.PHONY: from-scratch
from-scratch:
	./get_started.sh