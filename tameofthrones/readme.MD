This is a maven project. Use below commands to build and execute the jar.

cd <project_location_src_directory>

#Build the jar
mvn package

#Run the tests
mvn test

#Used jacoco plugin to generate code coverage data. The report will be found at target/site/jacoco/index.html
*********************************************************

problem 1: A golden crown

#Run the jar
Use below command to execute the jar
java -cp target\geektrust.jar com.geektrust.goldencrown.Main input.txt

input file should have either questions or inputs, an example below:
Who is the ruler of Southeros?
Allies of Ruler?
Air, “oaaawaala”
Land, “a1d22n333a4444p”
Ice, “zmzmzmzaztzozh”
Who is the ruler of Southeros?
Allies of Ruler?

*********************************************************
problem 2: Breaker of Chains

#Run the jar
Use below command to execute the jar
java -cp target\geektrust.jar com.geektrust.breakerofchains.Main input.txt

input file should have either questions or inputs, an example below:
Who is the ruler of Southeros?
Allies of Ruler?
Land Air
Who is the ruler of Southeros?
Allies of Ruler?