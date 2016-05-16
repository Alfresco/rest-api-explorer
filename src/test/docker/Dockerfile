FROM node:6.1

#What about running it before the mvn build. So the java build fails if the files aren't there
#can i do a "watch" on the file to do a "bundle" each time.

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
RUN npm install -g swagger-cli

# Bundle app source
#COPY . /usr/src/app

CMD [ "swagger", "--version" ]
