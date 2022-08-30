FROM node:18-alpine3.15
WORKDIR /app/
ADD components components
ADD pages pages
ADD public public
ADD styles styles
ADD .env.production .env.local
ADD next.config.js .
ADD package-lock.json .
ADD package.json .
RUN npm install
RUN npm run build
EXPOSE 3000
CMD [ "npm", "start" ]
