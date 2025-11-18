#!/bin/bash

mvn clean package
sudo cp target/quiz2.war /var/lib/tomcat9/webapps/
sudo systemctl restart tomcat9
