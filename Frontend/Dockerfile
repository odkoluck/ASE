# Stage 1: Build the Angular app
FROM node:18.10 as build

# Set the working directory
WORKDIR /app

# Copy the package.json and package-lock.json files to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Install tslib
RUN npm install tslib

# Copy the rest of the application code
COPY . .

# Build the Angular app
RUN npm run build

# Expose the desired port
EXPOSE 4200

# Start the application
CMD [ "npm", "start" ]
