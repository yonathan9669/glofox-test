#!/usr/bin/env bash

set -e

echo "Hasura Docker Image"
## Database setup
DB_USER=${POSTGRES_USER:-glofox}
DB_PASSWORD=${POSTGRES_PASSWORD:-'super-secret'}
DB_HOST=${POSTGRES_HOST:-db}
DB_PORT=${POSTGRES_PORT:-5432}
DB_NAME=${POSTGRES_DB:-glofox}

if [ -n "$POSTGRES_PASSWORD_FILE" ]; then
  DB_PASSWORD=$(cat $POSTGRES_PASSWORD_FILE)
fi

## Hasura setup
HASURA_ADMIN=${HASURA_ADMIN_SECRET:-'admin-secret'}

if [ -n "$HASURA_ADMIN_FILE" ]; then
  HASURA_ADMIN=$(cat $HASURA_ADMIN_FILE)
fi

## ENV VARIABLES
export HASURA_GRAPHQL_DATABASE_URL=postgres://${DB_USER}:${DB_PASSWORD}@${DB_HOST}:${DB_PORT}/${DB_NAME}
export HASURA_ADMIN_SECRET=${HASURA_ADMIN}
export HASURA_GRAPHQL_ADMIN_SECRET=${HASURA_ADMIN}

sh /bin/docker-entrypoint.sh

exec "$@"
