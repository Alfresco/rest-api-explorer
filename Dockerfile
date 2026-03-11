FROM alpine:3 AS builder

RUN apk add --no-cache bash curl tar

WORKDIR /workspace
COPY . /workspace

WORKDIR /workspace/.github/gh-pages
RUN ./build.sh

FROM nginxinc/nginx-unprivileged:1.29-alpine-perl

COPY --from=builder /workspace/.github/gh-pages/build /usr/share/nginx/html
