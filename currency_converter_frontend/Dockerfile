# Stage 1: Build the Angular app
FROM node:latest AS builder

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build --prod

# Stage 2: Use the official Nginx image to serve the Angular app
FROM nginx:alpine

# Copy the custom nginx.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy the built Angular app from the builder stage
COPY --from=builder /app/dist/currency_converter_frontend /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# The default command starts Nginx
CMD ["nginx", "-g", "daemon off;"]
