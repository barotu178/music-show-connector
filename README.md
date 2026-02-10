\# 🎵 Music Show Connector API



Music Show Connector is a \*\*Spring Boot REST API\*\* for managing and discovering live music shows.

This project was built as a \*\*portfolio backend application\*\* to demonstrate clean REST design,

pagination, validation, secure configuration, and integration testing.



---



\## 🎯 Purpose



The goal of this project is to provide a reliable backend service that allows clients to:

\- Discover upcoming live music shows

\- Search and filter shows efficiently

\- Manage show data through a well-designed REST API



The focus of this project is \*\*backend engineering best practices\*\*.



---



\## 🚀 Features



\- Full CRUD operations for music shows

\- Search and filtering by:

&nbsp; - City

&nbsp; - Artist

&nbsp; - Date range

\- Pagination and sorting support

\- DTO-based request validation

\- Proper HTTP status codes (`201`, `400`, `404`)

\- Standardized paginated API responses

\- Secure configuration using environment variables

\- Integration tests with an isolated test database

\- OpenAPI / Swagger documentation



---



\## 🛠️ Tech Stack



\- \*\*Java 17\*\*

\- \*\*Spring Boot 3\*\*

\- Spring Web

\- Spring Data JPA

\- Hibernate

\- MySQL (development / production)

\- H2 (test profile)

\- JUnit 5

\- MockMvc

\- Maven

\- Git \& GitHub



---



\## 📦 API Endpoints



\### Get all shows (pagination \& sorting)

```

GET /shows?page=0\&size=5\&sort=artist,desc

```



\### Filter by city and/or artist

```

GET /shows?city=Milwaukee

GET /shows?artist=Tems

GET /shows?city=Milwaukee\&artist=Burning%20Spear

```



\### Filter by date range

```

GET /shows?from=2026-04-01\&to=2026-05-01

```



\### Get show by ID

```

GET /shows/{id}

```



\### Create a show

```

POST /shows

```



```json

{

&nbsp; "artist": "Tems",

&nbsp; "venue": "Madison Square Garden",

&nbsp; "city": "New York",

&nbsp; "showDate": "2026-05-10"

}

```



\### Update a show

```

PUT /shows/{id}

```



\### Delete a show

```

DELETE /shows/{id}

```



---



\## 📄 Sample Paginated Response



```json

{

&nbsp; "data": \[

&nbsp;   {

&nbsp;     "id": 3,

&nbsp;     "artist": "Tems",

&nbsp;     "showDate": "2026-05-10",

&nbsp;     "venue": "Madison Square Garden",

&nbsp;     "city": "New York"

&nbsp;   }

&nbsp; ],

&nbsp; "page": 0,

&nbsp; "size": 3,

&nbsp; "totalElements": 6,

&nbsp; "totalPages": 2,

&nbsp; "sort": "artist: DESC"

}

```



---



\## 🧪 Testing Strategy



\- Integration tests using `@SpringBootTest` and `MockMvc`

\- Test profile uses \*\*H2 in-memory database\*\*

\- Tests cover:

&nbsp; - Pagination and sorting

&nbsp; - Successful resource creation

&nbsp; - Validation failures (`400 Bad Request`)

&nbsp; - Resource not found scenarios (`404 Not Found`)



Run tests:

```

mvn test

```



---



\## 🔐 Configuration



Database credentials are externalized using \*\*environment variables\*\*.



Example:

```

DB\_URL=jdbc:mysql://localhost:3306/musicdb

DB\_USERNAME=your\_username

DB\_PASSWORD=your\_password

```



This keeps secrets out of source control.



---



\## 📘 API Documentation



Swagger UI is available at:

```

http://localhost:8080/swagger-ui.html

```



---



\## 📌 Project Status



✔ Backend complete  

✔ Fully tested  

✔ Portfolio-ready  



Future enhancements may include authentication and a frontend client.



---



\## 👤 Author



\*\*Obaro Aruotu\*\*  

Java / Backend Developer



