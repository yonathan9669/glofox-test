#!/usr/bin/env bash

set -e

echo "Hasura Docker Image"
## Database setup
DB_USER=${POSTGRES_USER}
DB_PASSWORD=${POSTGRES_PASSWORD}
DB_HOST=${POSTGRES_HOST}
DB_PORT=${POSTGRES_PORT}
DB_NAME=${POSTGRES_DB}

if [ -n "$POSTGRES_PASSWORD_FILE" ]; then
  DB_PASSWORD=$(cat $POSTGRES_PASSWORD_FILE)
fi

## Hasura setup
HASURA_ADMIN=${HASURA_ADMIN_SECRET}

if [ -n "$HASURA_ADMIN_FILE" ]; then
  HASURA_ADMIN=$(cat $HASURA_ADMIN_FILE)
fi

## ENV VARIABLES
export HASURA_GRAPHQL_DATABASE_URL=postgres://${DB_USER}:${DB_PASSWORD}@${DB_HOST}:${DB_PORT}/${DB_NAME}
export HASURA_ADMIN_SECRET=${HASURA_ADMIN}
export HASURA_GRAPHQL_ADMIN_SECRET=${HASURA_ADMIN}

### Remote Schemas
## Backend
export HASURA_BACKEND_API_URL=${HASURA_BACKEND_API_URL}
export HASURA_BACKEND_API_SECRET=${HASURA_BACKEND_API_SECRET}

sh /bin/docker-entrypoint.sh

exec "$@"
