# Stage 1: Build the Angular app
FROM node:latest as build-stage

# Set working directory
WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Create the environment.prod.ts file with the build arguments
RUN echo "export const environment = {" > src/environments/environment.ts && \
    echo "  PRODUCTION: ${PRODUCTION}," >> src/environments/environment.ts && \
    echo "  PAGE_SIZE: ${PAGE_SIZE}," >> src/environments/environment.ts && \
    echo "  API_URL: '${API_URL}'," >> src/environments/environment.ts && \
    echo "  API_BASKET_URL: '${API_BASKET_URL}'," >> src/environments/environment.ts && \
    echo "  KEYCLOAK_URL: '${KEYCLOAK_URL}'," >> src/environments/environment.ts && \
    echo "  KEYCLOAK_REALM: '${KEYCLOAK_REALM}'," >> src/environments/environment.ts && \
    echo "  KEYCLOAK_CLIENT_ID: '${KEYCLOAK_CLIENT_ID}'," >> src/environments/environment.ts && \
    echo "  STRIPE_PUBLIC_KEY: '${STRIPE_PUBLIC_KEY}'" >> src/environments/environment.ts && \
    echo "};" >> src/environments/environment.ts

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
