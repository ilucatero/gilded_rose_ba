### Run Instructions

To run this spring-Boot application follow the steps as described bellow:

###### Clone project
```
git clone git@github.com:ilucatero/guilded_rose_ba.git guilded_rose_ba
```


###### Compile
```
mvn clean package
```


###### Run it with Maven (option 1)
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

###### Run it as scripted/java jar (option 2)
First the file must be repackaged to be executable:
```
mvn clean package spring-boot:repackage 
```
Then run it :
  - as scripted (executable) file
```
# here I am using windows notation (will run it as a service) 
/gilded-rose-web\target\gilded-rose-web-0.0.1-SNAPSHOT.jar -Dspring-boot.run.profiles=dev
```
  - as java jar
```
java -jar gilded-rose-web\target\gilded-rose-web-0.0.1-SNAPSHOT.jar -Dspring-boot.run.profiles=dev
```
