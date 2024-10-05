   # Use Node.js as the base image for building the React app
   FROM node:14 AS build

   # Set the working directory
   WORKDIR /app

   # Copy package.json and install dependencies
   COPY package.json ./
   RUN npm install

   # Copy the rest of the application code
   COPY . .

   # Build the React app
   RUN npm run build

   # Use Nginx to serve the static files
   FROM nginx:latest
   COPY --from=build /app/build /usr/share/nginx/html
