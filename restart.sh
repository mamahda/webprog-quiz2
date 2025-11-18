#!/bin/bash

echo "Building project..."
cd quiz2
mvn clean package -DskipTests

echo "Removing old deployment..."
sudo rm -rf /var/lib/tomcat9/webapps/quiz2 /var/lib/tomcat9/webapps/quiz2.war

echo "Copying new WAR..."
sudo cp target/quiz2.war /var/lib/tomcat9/webapps/

echo "Restarting Tomcat..."
sudo systemctl restart tomcat9

echo "Done! Access your app at:"
echo "   http://localhost:8080/quiz2/"
cd ..
