#!/usr/bin/env bash
source db/utils.sh

echo "🛠Deploying everything from scratch"

if test -z ${KEEP_ENV}; then
  echo "Configuring environments..."
  cp .env.example .env
  cp .env.example proxy/frontend/.env
else
  echo "Keeping environments..."
fi

echo "Stopping running containers"
make down
sleep 1

echo "Deploy docker containers..."
make build
