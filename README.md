# alledaags-spring-boot

Your favorite website from daily random Stuff!

## Run locally
To start op locally: use the profile 'local'. This will not use a database but save all dailyItems in-memory. 

## Run in production: Dynamo  DB
This application needs a connection with a Dynamo DB.

### Build
Only requirement is JDK11+ 
```shell script
mvn clean install
```

### Run the application
Only requirement is JDK11+ 
```shell script
mvn spring-boot:run
```
You can go to the webapplication on port 8080 on your [localhost](http://localhost:8080)
