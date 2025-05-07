# Build stage
FROM node:20-alpine AS build
WORKDIR /app

# Install build dependencies
RUN apk add --no-cache python3 make gcc g++

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install --platform=linux-x64

# Copy project files and build
COPY . .
RUN npm run build

# Production stage
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
