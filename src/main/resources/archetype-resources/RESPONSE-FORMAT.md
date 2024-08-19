# RestAPI Response Format

Follow Google guide ```https://google.github.io/styleguide/jsoncstyleguide.xml```

Success Response return `data` with http code 2xx
Error Response return `error` with http code 3xx-5xx

## 1. Success Response Example
http status 2xx
```json
{   
    "data": { .... }
}
```


## 2.Error Response Example
http status 3xx -> 5xx
```json
{   
    "error": {
      "code" : 500,
      "message" : "string message here"
    }
}
```
## 3. Example
### a. Get a single item 
Http Method: Get
Http Response: 200
```json
{
    "data": {
        "id": 10,
        "name": "shirt",
        "color": "red",
        "price": "$23"
    }
}
```

### b. Get a list of items
Http Method: Get
Http Response: 200
```json
{
    "data": { 
        "currentItemCount": 10,
        "itemsPerPage": 10,
        "startIndex": 11,
        "totalItems": 2700000, 
        "items": [
            { } 
        ]
    }
}
```

### c. Add a new item 
Http Method: Post
Http Response Code: 201

### d. Update an item

Http Method: Patch
Http Response Code: 200/204


