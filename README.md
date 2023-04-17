# 🎧PlaylistManagement🎧

In PlaylistManagement the user can create playlists searching for their music, through the name of the song and the artist.

## 💀 Developed by: Carlos Henrique Magalhães 

### 🔧 Necessary Tools: 

- Java

- Maven

- Docker

- Postman

- IntelliJ

### 🔧 Optional Tools:

- DBeaver

---------------------------------------------------------------------------
### 🎶 Starting:

- Clone this repository:
[Playlist Management](https://github.com/CarlosHenriqueMagalhaes/PlaylistManagement.git)

- This application consumes the LastFm API!

Register at API [LastFm](https://www.last.fm/home) to get a key "API_KEY" and insert in Docker-Compose (same field)

- Create the network:

```
docker network create inatel
```

- Start the MySql Container:
```
docker container run --name mysql --network=inatel -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=my_catalog_db -p 3306:3306 -p 33060:33060 -d mysql
```

- Now run the project in your IDE 

------------------------------------------------------------------------------
### 🎶 Starting with the Docker Compose:
Clone the repository
At the root of the project, run the command:


```
mvn clean install -DskipTests

```

So at the root of the project, buid the application image


```
docker build -t playlist-management .

```

Run the Docker Compose:


```
docker compose up

```
---------------------------------------------------------------------------
### 🎶 How to use:
PlaylistManagement searches for music and some information about it
in an external api (Last.fm) and saved in my database.

With this it is allowed to create playlists with these songs.

This API contains diagram by drawio, Swagger and JavaDoc for documentation.

### Business rules:

When creating a playlist:

1 - The name of the Playlist cannot be null;

2 - The name of the Playlist must contain between 2 and fifty characters;

3 - Following the model of other Streams services (like Spotify) Playlists can have the same name;

4 - It is possible to change the name of the playlist, as long as the new name respects the first and second rule;

5 - It is possible to insert songs to the playlist, but not songs that are already in the playlist;

6 - It is possible to remove a song from the playlist;

7 - It is possible to delete a playlist, the songs in it are not removed from the database;

When searching for a song:

1 - When searching for a song, it is automatically saved in the database;

2 - If the music already exists in the database it is not saved, so there is no way
there are two identical songs in the database;

3 - It is possible to add songs to the playlist, but not songs that are already in the playlist;

4 - It is possible to remove a song from the playlist, but not from the database;

So use Postman, examples:

---------------------------------------------------------------------------
### 🎶 End-Points:
- #### Create a new Playlist:
POST  ``` http://localhost:8070/playlist ```

body: 
```
{

    "playlistName":  "New Playlist"  
    
}
```
- #### Find a Playlist by ID
GET ``` http://localhost:8070/playlist?id={id} ```

Requires Query Params:  id

- #### List All Playlists with page (start in page 0)
GET ``` http://localhost:8070/playlists?page={pageNumber} ```

insert a pageNumber on URL

- #### Change a PlaylistName
PATCH  ``` http://localhost:8070/playlist/{id} ```

insert ID on URL

body:
```
{

  "playlistName": "70's Songs"
  
}
```
- #### Delete a Playlist
DELETE ``` http://localhost:8070/playlist/{id} ```

insert ID on URL

- #### Find A Song in API External and persist in my database
POST ``` http://localhost:8070/newSong ```

body:
```
{

    "artist":"Aerosmith",
    "track":"Sunshine"
    
}
```
- #### List All Songs with page (start in page 0)
GET ``` http://localhost:8070/songs?page={pageNumber} ```

insert a pageNumber on URL

- #### Find a Song by id
GET ``` http://localhost:8070/song?id={id} ```

Requires Query Params:  id

- #### Insert a Song at a playlist
POST ``` http://localhost:8070/songs/{songId}/playlist/{playlistId} ```

Requires Query Params:

playlistId

songId

- #### Remove a Song at a playlist
DELETE ``` http://localhost:8070/song/{songId}/playlist/{playlistId} ```

Requires Query Params:

playlistId

songId

- #### Delete Cach:

DELETE ``` http://localhost:8070/cache ```

-----------------------------------------------------------------------------
### 🎶 Tests:
I performed integration tests on the controller, testing the endpoints and unit tests of the service classes.

There are four test classes in the project: PlaylistResourceTeste, SongResourceTest, PlaylistServiceTest and SongServiceTest.

You can either run them from your IDE.
