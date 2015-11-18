###CMPE272 -- Group8

####Project -- Student Registration Web Service


##### Technology Stack

* MariaDB
* Hibernate
* Jersey
* Jetty


##### Maven command

* Set up $JAVA_HOME
```
kdao-ltm:StudentReg kdao$ vi ~/.profile
    export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)

kdao-ltm:StudentReg kdao$ source ~/.profile
```

kdao-ltm:StudentReg kdao$ mvn clean
kdao-ltm:StudentReg kdao$ mvn package