COMPOSE=docker-compose
EXEC=$(COMPOSE) exec
BUILD=$(COMPOSE) build
PRODUP=docker compose up
PRODDOWN=docker compose down
UP=$(COMPOSE) up -d
LOGS=$(COMPOSE) logs
STOP=$(COMPOSE) stop
RM=$(COMPOSE) rm
DOWN=$(COMPOSE) down
# REACT=$(EXEC) react
JAVA=$(EXEC) api
DB=$(EXEC) mariadb

all: docker/up api/build

docker/build: ## docker build
	$(BUILD)

docker/prod/up: ## docker prod up
	$(PRODUP) -f docker-compose.prod.yml

docker/prod/push: ## docker prod push
	$(COMPOSE) push

docker/prod/down: ## docker prod down
	$(PRODDOWN) -f docker-compose.prod.yml

docker/up: ## docker up
	$(UP)

docker/logs: ## docker logs
	$(LOGS)

docker/stop: ## docker stop
	$(STOP)

docker/rm: ## docker clean
	$(RM)

docker/down: ## docker down & docker volume prune
	$(DOWN) -v

docker/volume/prune: ### docker volume prune
	docker volume prune

java/bash: ## java container bash
	$(JAVA) bash

api:
	$(JAVA) sh gradlew run

api/build:
	$(JAVA) sh gradlew build

api/down:
	$(DOWN) api -v

db/bash: ## db(MySQL) container bash
	$(DB) bash

mysql: ## db(MySQL) container's MySQL access
	$(DB) mysql --defaults-extra-file=/app/access.cnf winter

help: ## Display this help screen
	@grep -E '^[a-zA-Z/_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'
