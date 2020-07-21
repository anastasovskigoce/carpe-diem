

# Carpe Diem ![quotes](https://user-images.githubusercontent.com/58456263/88000807-9dee4a00-cacc-11ea-8684-f5d8f06c793b.png)
A simple REST API that will get the daily quote

## Get All Quotes
Use this endpoint to get a list of all the quotes. As of now there are around ~300 unique quotes ybb different authors.

### Request
``GET /quotes``

``curl not-yet-ready``

### Response
````json
[
    {
        "id": "5f02",
        "quote": "If you want to achieve greatness stop asking for permission.",
        "author": "Anonymous"
    },
    {
        "id": "5f03",
        "quote": "Things work out best for those who make the best of how things work out.",
        "author": "John Wooden"
    },
    {
        "id": "5f04",
        "quote": "To live a creative life, we must lose our fear of being wrong.",
        "author": "Anonymous"
    }
]
````

## Get a particular quote
Use this endpoint to get a particular quote.

### Request
``GET /quotes?id=1``

``curl not-yet-ready``

### Response
````json
    {
        "id": "5f02",
        "quote": "If you want to achieve greatness stop asking for permission.",
        "author": "Anonymous"
    }
````

## Submit a new quote
Use this API if you want to add a quote. Once we receive the quote we will verify it's authenticity and let me know if we will accept it.

### Request
``POST /quote``

``curl not-yet-ready``

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

## Update an existing quote
Use this API if you want to update an existing quote. Once we receive the updated quote we will verify it and let me know if we will accept the changes.

### Request
``PUT /quote?quoteId=5f02``

``curl not-yet-ready``

Body of the request
```json
{
    "quote": "Any journey, no matter how long, starts with one step",
    "author": "Confucius"
}
```

### Response
````
Date: Thu, 24 Feb 2011 12:36:30 GMT
Status: 200 OK
1
````

## Delete an existing quote
Use this API if you want to delete an existing quote. Once we receive the request we will verify it and let me know if we accept the suggestion.

### Request
``DELETE /quote?quoteId=5f02``

``curl not-yet-ready``

### Response
````
Date: Thu, 24 Feb 2011 12:36:30 GMT
Status: 200 OK
1
````
