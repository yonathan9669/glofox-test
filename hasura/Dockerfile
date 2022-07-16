FROM hasura/graphql-engine:v2.8.4.cli-migrations-v3

COPY metadata /hasura-metadata/
COPY migrations /hasura-migrations/

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["sh","/entrypoint.sh"]

HEALTHCHECK  --interval=30s --timeout=3s CMD wget --quiet --spider http://localhost:8080/healthz || exit 1
CMD ["graphql-engine", "serve"]