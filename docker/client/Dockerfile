# Stage 1: Build the Angular app
FROM node:latest as build-stage

# Set working directory
WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Set build-time arguments
ARG PAGE_SIZE
ARG PRODUCTION
ARG API_URL
ARG API_BASKET_URL
ARG KEYCLOAK_URL
ARG KEYCLOAK_REALM
ARG KEYCLOAK_CLIENT_ID
ARG STRIPE_PUBLIC_KEY

ENV PAGE_SIZE=${PAGE_SIZE}
ENV PRODUCTION=${PRODUCTION}
ENV API_URL=${API_URL}
ENV API_BASKET_URL=${API_BASKET_URL}
ENV KEYCLOAK_URL=${KEYCLOAK_URL}
ENV KEYCLOAK_REALM=${KEYCLOAK_REALM}
ENV KEYCLOAK_CLIENT_ID=${KEYCLOAK_CLIENT_ID}
ENV STRIPE_PUBLIC_KEY=${STRIPE_PUBLIC_KEY}

# Generate production build of the Angular app
RUN npm run build --prod

# Stage 2: Serve the app with NGINX
FROM nginx:latest

# Copy custom NGINX configuration file
COPY nginx.conf /etc/nginx/nginx.conf

# Copy the Angular build output to the NGINX html directory
COPY --from=build-stage /app/dist/online-store-ui/browser /usr/share/nginx/html/

# Expose port 80
EXPOSE 80
