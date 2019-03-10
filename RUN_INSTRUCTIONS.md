### Run Instructions

To run this spring-Boot application follow the steps as described bellow:

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

###### Run as java jar (option 2)
```
mvn clean package spring-boot:repackage 
java -jar gilded-rose-web\target\gilded-rose-web-0.0.1-SNAPSHOT.jar -Dspring-boot.run.profiles=dev
# OR AS EXECUTABLE FILE
/gilded-rose-web\target\gilded-rose-web-0.0.1-SNAPSHOT.jar -Dspring-boot.run.profiles=dev
```