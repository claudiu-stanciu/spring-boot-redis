# Bidding agent

# Questions -> QUESTIONS.md

# Build

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
curl localhost:8080/init -X POST
```
## Test prediction

```
curl localhost:8080 -H "Content-Type: application/json" -v -X POST -d '{
"deviceExtBrowser": "Firefox",
"bannerExtSize": "300x250",
"deviceLanguage": "de",
"deviceExtType": "tablet"
}'
```

