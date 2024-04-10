#wabpack dockerfile for app1, app2, app3, shell
FROM node:18.12.0-alpine

EXPOSE 3000

WORKDIR /webapp/app1
COPY ./app1/package.json ./
COPY ./app1/webpack.config.js ./
COPY ./app1/public ./public
COPY ./app1/src ./src

RUN npm install

WORKDIR /webapp/app2
COPY ./app2/package.json ./
COPY ./app2/webpack.config.js ./
COPY ./app2/public ./public
COPY ./app2/src ./src

RUN npm install

WORKDIR /webapp/app3
COPY ./app3/package.json ./
COPY ./app3/webpack.config.js ./
COPY ./app3/public ./public
COPY ./app3/src ./src

RUN npm install

WORKDIR /webapp/shell
COPY ./shell/package.json ./
COPY ./shell/webpack.config.js ./
COPY ./shell/public ./public
COPY ./shell/src ./src

RUN npm install

WORKDIR /webapp

COPY ./start-apps.sh ./

# Make your script executable if it isn't already
RUN chmod +x /webapp/start-apps.sh

# Set the working directory (optional, for clarity and convenience)
WORKDIR /webapp

# Command to run on container start
CMD ["./start-apps.sh"]


