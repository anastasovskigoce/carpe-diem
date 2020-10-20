

# Carpe Diem ![quotes](https://user-images.githubusercontent.com/58456263/88000807-9dee4a00-cacc-11ea-8684-f5d8f06c793b.png)
A simple REST API that will get the daily quote

## Get Top 15 most popular quotes
Use this endpoint to get a list of the top 15 quotes. As of now there are around ~300 unique quotes by different authors.

### Request
``GET /top-quotes``

``curl --location --request GET 'https://sieze-the-day.herokuapp.com/top-quotes' \
--data-raw ''``

### Response
````json
[
    {
        "id": "5f03",
        "quote": "Things work out best for those who make the best of how things work out.",
        "author": "John Wooden",
        "rating": 88.0,
        "quoteUsedInNotification": false
    },
    {
        "id": "5f04",
        "quote": "To live a creative life, we must lose our fear of being wrong.",
        "author": "Anonymous",
        "rating": 998.0,
        "quoteUsedInNotification": false
    }
]
````

## Get a particular quote
Use this endpoint to get a particular quote.

### Request
``GET /quote?id=1``

``curl --location --request GET 'https://sieze-the-day.herokuapp.com/quote?id=gh7' \
--data-raw ''``

### Response
````json
    {
    "id": "gh7",
    "quote": "When I dare to be powerful – to use my strength in the service of my vision, then it becomes less and less important whether I am afraid.",
    "author": "Audre Lorde",
    "rating": 998.0,
    "quoteUsedInNotification": false
}
````


## Serach quote by author
Use this endpoint to search quotes by author 

``GET /quote-by-author?authorName=foo"``

``curl --location --request GET 'https://sieze-the-day.herokuapp.com/quote-by-author?authorName=Henry' \
--data-raw ''``

### Response
````json
[
    {
        "id": "1f",
        "quote": "A real entrepreneur is somebody who has no safety net underneath them.",
        "author": "Henry Kravis",
        "rating": 989.0,
        "quoteUsedInNotification": false
    },
    {
        "id": "2g",
        "quote": "When everything seems to be going against you, remember that the airplane takes off against the wind, not with it.",
        "author": "Henry Ford",
        "rating": 104.0,
        "quoteUsedInNotification": false
    }
]
````

## Upvote quote
Use this endpoint to upvote a quote

``PUT /up-vote-quote?id=a5f"``

``curl --location --request PUT 'https://sieze-the-day.herokuapp.com/up-vote-quote?id=a5f' \
--data-raw ''``

## Downvote a quote
Use this endpoint to downvote a quote

``PUT /down-vote-quote?id=a5f"``

``curl --location --request PUT 'https://sieze-the-day.herokuapp.com/down-vote-quote?id=a5f' \
--data-raw ''``

## Submit a new quote
Use this API if you want to add a quote. Once we receive the quote we will verify it's authenticity and let you know if we will accept it.

### Request
``POST /quote?id=id5``

``curl --location --request POST 'http://localhost:8080/quote?id=id5 \
--header 'Content-Type: application/json' \
--data-raw '{
    "quote": "A journey of a thousand mile starts with one step",
    "author": "Anonymous"
}'``

Body of the request
```json
{
    "quote": "A journey of a thousand mile starts with one step",
    "author": "Confucius"
}
```

### Response
````
Date: Thu, 24 Feb 2011 12:36:30 GMT
Status: 200 OK
1
````
