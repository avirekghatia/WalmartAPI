#Walmart API interaction application

##How to use
First, please register for an API key on _https://developer.walmartlabs.com/
The project uses Maven for creating the build. Navigate to the directory with pom.xml and use following query using the following query format:

java -jar <JAR_NAME> -limit <LIMIT(1-20)|optional> -key <APPLICATION_KEY|mandatory> -query <SEARCH_STRING|mandatory>

###Example queries:
java -jar walmart-lab.jar -key <api key> -query camera

java -jar walmart-lab.jar -key <api key> -query tortilla

java -jar walmart-lab.jar -key <api key> -query tortilla -limit 20

java -jar walmart-lab.jar -key <api key> -query cactus -limit 5

java -jar walmart-lab.jar -key <api key> -query Keurig