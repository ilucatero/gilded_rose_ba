### Run Instructions

To run this spring-boot application follow the steps as described bellow:

###### Clone project
```
git clone git@github.com:ilucatero/guilded_rose_ba.git
```


###### Compile
```
mvn clean package
```


###### Run as with mvn (option 1)
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

###### Run it directly (option 2)
To run directly the file, via JVM or as an executable file, you must:
  * Package it correctly
    ```
    mvn clean package spring-boot:repackage 
    ```
  * Run it
    - as a jar file
    ```
    java -jar gilded-rose-web\target\gilded-rose-web-0.0.1-SNAPSHOT.jar -Dspring-boot.run.profiles=dev
    ```
    - as executable file
    ```
    # this will run like a scripted file as a service/daemon (lin: ./ ; win: \)
    /gilded-rose-web\target\gilded-rose-web-0.0.1-SNAPSHOT.jar -Dspring-boot.run.profiles=dev
    ```

###### Load data
The application use Liquibase to manage database changes (scheme & data). To load the logchange files 
you can use the Maven command as below.
```
mvn -pl gilded-rose-db\ liquibase:update -Dliquibase.dropFirst=true
```

At the moment, the applications auto-load the configured changes in a in-mem Hsql database so the previous
command is not required. Just in the case you want to save changes after restart, you must change the in-mem mode, 
add required storing file path configuration, and avoid the application from dropping all at start.

###### Open the main page

Open the application at http://localhost:80/

Notice that depending on the profile the server will run on port **80 (dev)** or **8080 (prod)**.