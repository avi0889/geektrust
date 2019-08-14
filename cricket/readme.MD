This is a maven project. Use below commands to execute the jar

cd <project_location_src_directory>

#Build the jar
mvn package

#Run the tests
mvn test

#Used jacoco plugin to generate code coverage data. The report will be found at target/site/jacoco/index.html

#Run the jar
java -cp target\geektrust.jar com.geektrust.cricket.lastfour.Main
