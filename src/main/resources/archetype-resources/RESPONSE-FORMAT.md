# RestAPI Response Format

Follow Google guide ```https://google.github.io/styleguide/jsoncstyleguide.xml```

Success Response return `data` with http code 2xx
Error Response return `error` with http code 3xx-5xx

## 1. Success Response Example
http status 2xx
```json
{   
    "status" : 200,
    "data": { .... }
}
```


## 2.Error Response Example
http status 3xx -> 5xx
```json
{
    "status": 500,
    "error": {
      "code" : 500,
      "message" : "string message here",
      "detail" : "detail message here"
    }
}
```
## 3. Example
### a. Get a single item successful
```shell
curl -i -X GET "http://localhost:8080/api/v1/item/1"
```
```json
{
  "data":{
    "id":1,
    "name":"test",
    "createdDate":"2024-08-26T14:31:16.360888"
  },
  "status":200}
```
### b. Get a single item failed

HTTP/1.1 404 Not Found
```json
{
  "status":404,
  "error":{
    "code":10404,
    "message":"The requested resource could not be found."
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

### c. Create a new item
Http Method: Post
Http Response Code: 200/201

```shell
curl -i -X POST 'localhost:8080/api/v1/item' \
-H "Content-Type: application/json" \
-d '{  
  "name": "Update Example Item"  
}'
```

### d. Update an item

Http Method: Patch
Http Response Code: 200/204

```shell
curl -i -X PATCH 'localhost:8080/api/v1/item/1' \
-H "Content-Type: application/json" \
-d '{  
  "name": "Update Example Item"  
}'
```


### e. Create a new item or Update if exist
HTTP Method: Put
HTTP Success Response Code: 200 or 204


```shell
curl -i -X PUT 'localhost:8080/api/v1/item/1' \
-H "Content-Type: application/json" \
-d '{  
  "name": "Update Example Item"  
}'
```

### f. Delete an item
Http Method: Delete
HTTP Success Response Code: 200 or 204

```shell
curl -i -X DELETE 'localhost:8080/api/v1/item/1' \
-H "Content-Type: application/json" \
-d '{  
  "name": "Update Example Item"  
}'
```
