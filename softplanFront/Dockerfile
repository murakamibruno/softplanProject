# stage 1

FROM node:10.24.1-alpine AS my-app-build
WORKDIR /app
COPY . .
RUN npm ci && npm run build

# stage 2

FROM nginx:alpine
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=my-app-build /app/dist/softplanFront /usr/share/nginx/html
EXPOSE 80
