#Web-App

The following application consists of two services which run inside docker containers. These services are written in scala and use finagle and the thrift protocol.

##Quick Start

**Please Note**: To get the application up and running you need docker installed on your machine. 

The following command: 

```bash
docker-compose up --build -d
```

Will pull down two images from dockerhub and start them as containers. Usergateway will run `http://localhost:8080` and userdatastore will run on `http://localhost:8081`

```bash

    container 1 
        name - usergateway
        port mapping - 8080 -> 8080
    
    container 2
        name - userdatastore
        port mapping - 8081 -> 8081

```




##API ENDPOINTS

```
    http://localhost:8080/ ...

    1)
        request:
        
            GET /
    
        response: 
    
           {
               "page": "Home Pages"
           }

    
    2) request: 
        
            GET /filtered
            
            header: client = identity

        200 response: 
        
            {
                "page": "Fitlered Page"
            }
            
        400 response:
        
            {
                "message": "Client header is missing"
            }
            
    3) request:
            
            GET /users
            
        200 response: (example)
        
            [
                {
                    "name": "John Doe",
                    "age": 30,
                    "gender": "Male",
                    "faveColour": "blue"
                },
                ....  
            ]
            
        

```