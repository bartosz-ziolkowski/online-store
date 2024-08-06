# Stage 1: Build the Angular app
FROM node:latest as build-stage

# Set working directory
WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Set environment variables
ENV PAGE_SIZE=missing_page_size
ENV PRODUCTION=missing_production
ENV API_URL=missing_api_url
ENV API_BASKET_URL=missing_basket_url
ENV KEYCLOAK_URL=missing_keycloak_url
ENV KEYCLOAK_REALM=missing_keycloak_realm
ENV KEYCLOAK_CLIENT_ID=missing_client_id
ENV STRIPE_PUBLIC_KEY=missing_stripe_public_key

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