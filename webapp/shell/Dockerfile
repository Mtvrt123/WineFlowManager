FROM node:18.12.0-alpine

EXPOSE 3000

WORKDIR /app
COPY ./package.json ./
COPY ./webpack.config.js ./
COPY ./public ./public
COPY ./src ./src

RUN npm install

#WORKDIR /app
#RUN npm start
