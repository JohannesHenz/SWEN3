FROM node:18 AS build
WORKDIR /app
COPY package.json .

RUN npm install
COPY src src
COPY tailwind.config.js .
COPY nginx.conf .
COPY public public

RUN npm run build

FROM nginx
COPY --from=build /app/build /usr/share/nginx/html
COPY --from=build /app/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
