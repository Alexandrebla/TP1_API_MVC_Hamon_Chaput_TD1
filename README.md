# TP1_API_MVC_Hamon_Chaput_TD1

# 📘 Library API --- TP Spring Boot MVC / JPA

## 🎯 Description

Ce projet implémente une API REST permettant de gérer des **Auteurs**,
**Livres**, et des **Statistiques**.\
Il utilise Spring Boot, JPA, DTOs, Validation, Swagger et une sécurité
par clé API pour les opérations sensibles.

## 🚀 Technologies utilisées

-   **Java 17**
-   **Spring Boot 3**
-   **Spring Web MVC**
-   **Spring Data JPA**
-   **Hibernate**
-   **Spring Security (API KEY)**
-   **Swagger / OpenAPI 3**
-   **MySQL**

## 📦 Installation

### 1. Cloner le projet

``` bash
git clone <repo>
cd MVC
```

### 2. Configurer la base MySQL (application.properties)

Créer une base :

``` sql
CREATE DATABASE library;
```

Configurer :

``` properties
spring.datasource.url=jdbc:mysql://localhost:3306/library
spring.datasource.username=root
spring.datasource.password=*****
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. API Key à configurer

Dans `application.properties` :

``` properties
app.api-key=123456
```

Chaque requête DELETE doit contenir :

    X-API-KEY: 123456

## ▶️ Lancer le projet

``` bash
mvn spring-boot:run
```

Serveur : http://localhost:8080\
Swagger : http://localhost:8080/swagger-ui/index.html

# 📚 Endpoints de l'API

# 👤 AUTHORS

## GET /authors

Retourne la liste.

## GET /authors/{id}

## POST /authors

``` json
{
  "name": "Jules Verne",
  "birthYear": 1828
}
```

## PUT /authors/{id}

## DELETE /authors/{id}

Header :

    X-API-KEY: 123456

# 📘 BOOKS

## GET /books

Filtres :\
`?title=&category=&yearFrom=&yearTo=`\
Pagination :\
`?page=0&size=10&sort=title,asc`

## POST /books

``` json
{
  "title": "Vingt mille lieues sous les mers",
  "isbn": "9780000000010",
  "publicationYear": 1870,
  "category": "ROMAN",
  "authorId": 1
}
```

## DELETE /books/{id}

Nécessite API KEY.

# 📊 STATISTIQUES

-   GET /stats/books/count\
-   GET /stats/authors/count\
-   GET /stats/books/by-category

# ❌ Gestion des erreurs JSON

``` json
{
  "timestamp": "2025-02-10T14:24:10",
  "status": 404,
  "error": "Not Found",
  "message": "Livre introuvable",
  "path": "/books/99"
}
```

# 📁 Structure du projet

    src/main/java
     └── TP1/API/MVC
          ├── controller
          ├── service
          ├── repo
          ├── domain
          ├── dto
          ├── mapper
          ├── exception
          ├── security
          └── config
