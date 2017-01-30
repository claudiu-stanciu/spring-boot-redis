# Bidding agent

# Questions -> QUESTIONS.md

# Configuration

- Redis cluster configuration
```
src/main/resources/application.properties

redis.host
redis.port
```

- Initial prediction coefficients are taken from
```
src/main/resources/predictionCoefList.csv
```

# Build

Build with gradle, so either gradle or gradlew/gradlew.bat

```
gradle build
```


# Test

```
gradle test
```

# Run REST server

```
gradle bootRun
```

## Initialize prediction DB

```
POST /init
```
## Get CRT prediction

```

POST /
with json body '{
"deviceExtBrowser": "AAA",
"bannerExtSize": "AAAxAAA",
"deviceLanguage": "AAAAA",
"deviceExtType": "AAAAA"
}'

Example
curl localhost:8080 -H "Content-Type: application/json" -v -X POST -d '{
"deviceExtBrowser": "Firefox",
"bannerExtSize": "300x250",
"deviceLanguage": "de",
"deviceExtType": "tablet"
}'
```

