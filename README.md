# Beenius backend coding challange

Technologies and patterns used:
- JPA, EJB, maven, kumuluzee, postgres

Project startup:
1. mvn clean install
2. setup postgres db with configurations (specified in config.yaml): database name = management, username = postgres, password = postgres
3. DB tables are created on project startup, data is generated with init.sql script
4. run project (com.kumuluz.ee.EeApplication)

Http requests:
- Get all actors: http://localhost:8080/s1/actor
- Get all movies: http://localhost:8080/s1/movie
- list movies with paginaitin support (example): http://localhost:8080/s1/movie?limit=4&offset=2 
- list actors with paginaitin support (example): http://localhost:8080/s1/actor?limit=2&offset=3
- create actor: http://localhost:8080/s1/actor/create
- create movie: http://localhost:8080/s1/actor/movie
- delete actor: http://localhost:8080/s1/actor/delete/{id}
- delete movie: http://localhost:8080/s1/actor/delete/{id}
- update movie: http://localhost:8080/s1/movie/update/{id}
- update actor: http://localhost:8080/s1/actor/update/{id}
- get actor by id: http://localhost:8080/s1/actor/{id}
- get movie by id: http://localhost:8080/s1/movie/{id}
- search movie by title: http://localhost:8080/s1/movie/search?title={titleName}
- search actor by firstname: http://localhost:8080/s1/actor/search?firstname={firstname}

Request counter:

Every request is counted (with a help of RequestCounter.java class) - available in counter.txt
You should change relative path to counter.txt in ActorResource.java and MovieResource.java.
