# Stage 1: Build the Angular app
FROM node:latest as build-stage

# Set working directory
WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Generate production build of the Angular app
RUN npm run build --prod

RUN cat /app/src/environments/environment.ts

# Stage 2: Serve the app with NGINX
FROM nginx:latest

# Copy custom NGINX configuration file
COPY nginx.conf /etc/nginx/nginx.conf

# Copy the Angular build output to the NGINX html directory
COPY --from=build-stage /app/dist/online-store-ui/browser /usr/share/nginx/html/

# Expose port 80
EXPOSE 80
