FROM node:latest as build-stage

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN node -r dotenv/config mynode.js

RUN npm run build --prod

FROM nginx:latest

COPY nginx.conf /etc/nginx/nginx.conf

COPY --from=build-stage /app/dist/online-store-ui/browser /usr/share/nginx/html/

EXPOSE 80