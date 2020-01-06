# Minimalistic runtime for SmallRye Metrics
Based on Weld-SE and Vert.x.
For testing, troubleshooting and demonstration purposes.
 
## How to run:
```
mvn package 
java -jar target/smallrye-metrics-standalone.jar
curl localhost:8080/metrics
```