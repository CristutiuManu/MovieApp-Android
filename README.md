# MovieApp-Android

MovieApp

API

Se va folosi api-ul: https://developers.themoviedb.org/3/getting-started/introduction
Ecrane

1.	Spashscreen
2.	Ecran de onboarding cu preferinte - Formular in care alege tipul de filme preferat(drama, dragoste), actori preferati, keywords care se filtreaza automat in lista de filme
3.	Lista de filme - cu filtrare si search dupa nume
4.	Adaugare de filme la favorite sau watched
5.	Lista de watched si favorite
6.	Ecran de detaliu la film

Splashscreen

Acest ecran va fi afisat la deschiderea aplicatiei si va disparea cand aplicatia s-a terminat de incarcat si este pregatita pentru deschiderea primului ecran. Va contine un label centrat vertical si orizontal cu numele aplicatiei(MovieApp) si un background de o culoare aleasa diferit de fiecare echipa.
Onboarding - Preferences screen

La deschiderea aplicatiei se va verifica daca preferintele au fost salvate in Room si doar daca nu au fost salvate atunci se va deschide ecranul de Preferinte. Daca preferintele au fost salvate se va deschide Main screen si anume Search movies screen.
Ecranul de onboarding va contine un formular cu 3 campuri: Actors, Genres si Keywords si va avea titlul: Preferences. La apasarea pe campurile de Actors si Genres se va deschide un alt ecran de multi-select list de unde utilizatorul va putea selecta mai multi actori/genre. La apasarea pe campul Keywords se va deschide tastatura si userul va introduce cuvinte despartite prin virgula. Aceste preferinte vor fi folosite ca default filters in ecranul Search movies screen. Datele selectate vor putea fi salvate folosind butonul de Save din navigation bar, care va ramane disabled pana cand toate campurile vor fi completate.
Ecranele de multi-select list ce contin Actors sau Movies vor incarca datele la deschiderea ecranului dintr-un request si vor afisa datele in celule selectabile. Dupa selectarea unor elemente daca userul apasa butonul Back selectia nu se va salva, daca apasa butonul Save datele vor fi trimise la ecranul de preferinte. Butonul de save va fi disabled pana cand minim un element va fi selectat. 

Exemple requesturi:

https://api.themoviedb.org/3/person/popular?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US&page=1

{
  "page": 1,
  "total_results": 19139,
  "total_pages": 957,
  "results": [
    {
      "popularity": 37.57,
      "id": 87167,
      "profile_path": "/q0MPIW1uBsENV2WuW8VuEyZDioN.jpg",
      "name": "Jeannette Sousa",
      "known_for": [
        {
          "vote_average": 6.9,
          "vote_count": 3654,
          "id": 49018,
          "video": false,
          "media_type": "movie",
          "title": "Insidious",
          "popularity": 17.207,
          "poster_path": "/6Tjg6DWVo2cpqDLxrOQNw1UvIMB.jpg",
          "original_language": "en",
          "original_title": "Insidious",
          "genre_ids": [
            27,
            53
          ],
          "backdrop_path": "/xVNR5eperbCm1DI1JfGhUXd4lqm.jpg",
          "adult": false,
          "overview": "A family discovers that dark spirits have invaded their home after their son inexplicably falls into an endless sleep. When they reach out to a professional for help, they learn things are a lot more personal than they thought.",
          "release_date": "2010-09-13"
        }
]
}

https://api.themoviedb.org/3/genre/movie/list?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US

{
  "genres": [
    {
      "id": 28,
      "name": "Action"
    },
    {
      "id": 12,
      "name": "Adventure"
    },
    {
      "id": 16,
      "name": "Animation"
    },
    {
      "id": 35,
      "name": "Comedy"
    },
    {
      "id": 80,
      "name": "Crime"
    },
    {
      "id": 99,
      "name": "Documentary"
    },
    {
      "id": 18,
      "name": "Drama"
    }
]
}
Search movies screen

Ecranul va avea in stanga sus un hamburger menu. Hamburger menu contine doua ecrane: Search Movies si Saved Movies.
Cand e deschis ecranul de Search movies e si selectat in meniu acest ecran si cand e deschis ecranul de Saved Movies e si selectat in meniu ecranul de Saved movies.
Tot in partea de sus a ecranului, langa hamburger menu va exista un Search textfield unde se vor putea scrie cuvinte dupa care se vor cauta filme. In partea dreapta sub textfield-ul de search va exista un buton de Filtru care va deschide ecranul de preferinte pentru a le modifica.
La fiecare deschidere de aplicatie, prima data cand se deschide ecranul de Search Movies se va face requestul GET /discover/movie unde in campul with_cast se vor trimite id-urile actorilor salvate in Room ca String separate de | si in campul with_genres se vor trimite id-urile de genres salvate in Room ca String separate de |. 
Daca in schimb se scrie ceva text in textfield-ul de search, atunci se va face requestul GET /search/movie la care se va trimite textul scris in campul query. 
Cand se va sterge textul din textfield-ul de Search se va face din nou requestul de discover.

Fiecare element din lista de filme va avea afisate urmatoarele informatii:
●	imagine (in partea stanga)
●	titlu cu anul aparitiei in paranteza (in dreapta pozei sus)
●	descriere scurta (maxim 2 linii sub titlu)
●	buton de adaugare la favorite (sub descriere scurta in stanga)
●	buton de adaugare la watched (sub descriere scurta in dreapta)
Cand se da tap pe un element din lista de filme se va deschide ecranul de Movie details. Cand se da tap pe butonul de Favorite sau Watched, se salveaza datele acelui film(id, titlu, poster path, descriere scurta) in Room in tabela de favorite sau watched in functie de caz.

Lista de keywords salvate in ecranul de preferinte nu se va folosi in acest ecran.

Exemple requesturi:

https://api.themoviedb.org/3/search/movie?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US&query=Avengers&page=1&include_adult=false

{
  "page": 1,
  "total_results": 48,
  "total_pages": 3,
  "results": [
    {
      "vote_count": 7874,
      "id": 299534,
      "video": false,
      "vote_average": 8.4,
      "title": "Avengers: Endgame",
      "popularity": 115.275,
      "poster_path": "/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
      "original_language": "en",
      "original_title": "Avengers: Endgame",
      "genre_ids": [
        12,
        878,
        28
      ],
      "backdrop_path": "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
      "adult": false,
      "overview": "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
      "release_date": "2019-04-24"
    }
  ]
}

https://api.themoviedb.org/3/discover/movie?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_cast=87167%7C122158%7C1245&with_genres=28%7C12%7C16

{
  "page": 1,
  "total_results": 24,
  "total_pages": 2,
  "results": [
    {
      "vote_count": 6469,
      "id": 299537,
      "video": false,
      "vote_average": 7,
      "title": "Captain Marvel",
      "popularity": 152.538,
      "poster_path": "/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg",
      "original_language": "en",
      "original_title": "Captain Marvel",
      "genre_ids": [
        28,
        12,
        878
      ],
      "backdrop_path": "/n1C1PrbEuAYV8VkzvmbAfEiWvZo.jpg",
      "adult": false,
      "overview": "The story follows Carol Danvers as she becomes one of the universe’s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races. Set in the 1990s, Captain Marvel is an all-new adventure from a previously unseen period in the history of the Marvel Cinematic Universe.",
      "release_date": "2019-03-06"
    },
    {
      "vote_count": 7874,
      "id": 299534,
      "video": false,
      "vote_average": 8.4,
      "title": "Avengers: Endgame",
      "popularity": 115.275,
      "poster_path": "/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
      "original_language": "en",
      "original_title": "Avengers: Endgame",
      "genre_ids": [
        12,
        878,
        28
      ],
      "backdrop_path": "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
      "adult": false,
      "overview": "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
      "release_date": "2019-04-24"
    }
]}
Movie details screen
In ecranul de detalii ale unui film vom lua detaliile acestuia folosind requestul GET /movie/{movie_id} trimitand ca parametru id-ul filmului primit in requestul de discover sau search movies. La acest request putem cere sa ne fie trimise si videourile daca adaugam asta in url-ul requestului.
Ecranul va avea in partea de sus toolbar ce va contine titlul filmului si eventual un back button(daca nu va contine back button, se poate folosi butonul fizic de back).
Sub titlu va contine un video player care va putea rula videoul de la url-ul de trailer. Pentru a lua url-ul trailerului trebuie sa folosim din lista de video-uri primul video si formam url-ul adaugand textul din campul key url-ului de youtube astfel: https://www.youtube.com/watch?v=hA6hldpSTF8.
In acest ecran trebuie afisate titlul filmului, anul aparitiei, lista de genres la care apartine, posterul filmului, descrierea filmului si 2 butoane: unul de adaugare la favorite si unul de setare ca watched. Felul in care sunt afisate aceste date este la libera alegere, si se pot folosi alte aplicatii de filme ca inspiratie.

Exemple requesturi:

http://api.themoviedb.org/3/movie/299534?api_key=d773193a88ede0c03b5da21759b8dea6&append_to_response=videos


{
    "adult": false,
    "backdrop_path": "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
    "belongs_to_collection": {
        "id": 86311,
        "name": "The Avengers Collection",
        "poster_path": "/yFSIUVTCvgYrpalUktulvk3Gi5Y.jpg",
        "backdrop_path": "/zuW6fOiusv4X9nnW3paHGfXcSll.jpg"
    },
    "budget": 356000000,
    "genres": [
        {
            "id": 12,
            "name": "Adventure"
        },
        {
            "id": 878,
            "name": "Science Fiction"
        },
        {
            "id": 28,
            "name": "Action"
        }
    ],
    "homepage": "https://www.marvel.com/movies/avengers-endgame",
    "id": 299534,
    "imdb_id": "tt4154796",
    "original_language": "en",
    "original_title": "Avengers: Endgame",
    "overview": "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
    "popularity": 115.275,
    "poster_path": "/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
    "production_companies": [
        {
            "id": 420,
            "logo_path": "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
            "name": "Marvel Studios",
            "origin_country": "US"
        }
    ],
    "production_countries": [
        {
            "iso_3166_1": "US",
            "name": "United States of America"
        }
    ],
    "release_date": "2019-04-24",
    "revenue": 2790216193,
    "runtime": 181,
    "spoken_languages": [
        {
            "iso_639_1": "en",
            "name": "English"
        },
        {
            "iso_639_1": "ja",
            "name": "日本語"
        }
    ],
    "status": "Released",
    "tagline": "Part of the journey is the end.",
    "title": "Avengers: Endgame",
    "video": false,
    "vote_average": 8.4,
    "vote_count": 7876,
    "videos": {
        "results": [
            {
                "id": "5c8a4d5b0e0a267d08c32f18",
                "iso_639_1": "en",
                "iso_3166_1": "US",
                "key": "hA6hldpSTF8",
                "name": "Marvel Studios' Avengers - Official Trailer",
                "site": "YouTube",
                "size": 1080,
                "type": "Trailer"
            },
            {
                "id": "5c8a4d740e0a26042bc441ef",
                "iso_639_1": "en",
                "iso_3166_1": "US",
                "key": "TcMBFSGVi1c",
                "name": "Marvel Studios' Avengers: Endgame - Official Trailer",
                "site": "YouTube",
                "size": 1080,
                "type": "Trailer"
            }
]
}

Saved movies screen

Ecranul de filme salvate va contine 2 taburi: Favorite movies si Watched movies. Pentru fiecare tab lista de filme ce se va afisa se va citi din Room si pentru afisarea elementelor din lista de filme se va folosi tot celula de la Search movies. La fel ca in Search movies cand se da tap pe un element se va deschide ecranul Movie details.
In celula de movie se poate adauga un buton de Delete care va sterge filmul din lista de favorite sau watched in care se afla.
