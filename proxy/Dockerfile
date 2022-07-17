# Intermediate Containers
## Packages Installer
FROM node:14-alpine as packages

### Packages for app
ADD frontend/package.json /tmp/package.json
ADD frontend/package-lock.json /tmp/package-lock.json
RUN cd /tmp && npm install

## Builder container
FROM node:14-alpine as builder

WORKDIR /app
ADD frontend .
COPY --from=packages /tmp/node_modules /app/node_modules

RUN cd /app && npm run build

# Final Container
FROM nginx:1.21

RUN apt-get update \
    && apt-get install -y git

RUN rm -rf /usr/share/nginx/html/*
COPY --from=builder /app/dist /usr/share/nginx/html

COPY conf /etc/nginx/conf.d/default.conf