# Prueba Técnica - Backend Developer
## Sistema de Gestión de Biblioteca

### Objetivo

Desarrollar una API REST básica para un sistema de biblioteca utilizando Spring Boot. El candidato debe demostrar su capacidad para crear endpoints funcionales y manejar relaciones entre entidades.

### Stack Tecnológico

- Java 11+
- Spring Boot
- Spring Data JPA
- H2 Database (en memoria)
- Maven

### Descripción del Problema

Una biblioteca necesita un sistema para gestionar el préstamo de libros. El sistema debe permitir registrar libros, usuarios y controlar los préstamos realizados.

### Modelo de Datos

Implementar tres entidades con las siguientes propiedades mínimas:

**Book**
- id, title, author, isbn, available (boolean)

**User**
- id, name, email

**Loan**
- id, book (relación), user (relación), loanDate, returnDate

### Endpoints Críticos a Implementar

Los siguientes endpoints son **obligatorios** y serán el foco principal de la evaluación:

#### 1. Crear Libro
- **POST** `/api/books`
- Debe recibir: title, author, isbn
- El libro se crea con `available = true` por defecto

#### 2. Crear Usuario
- **POST** `/api/users`
- Debe recibir: name, email
- El email debe ser único

#### 3. Gestión Completa de Préstamos

##### Crear Préstamo
- **POST** `/api/loans?bookId={id}&userId={id}`
- Validar que el libro esté disponible
- Cambiar el estado del libro a `available = false`
- Establecer `loanDate` con la fecha actual

##### Listar Préstamos por Usuario
- **GET** `/api/loans/user/{userId}`
- Retornar todos los préstamos del usuario

##### Devolver Libro
- **PUT** `/api/loans/{id}/return`
- Establecer `returnDate` con la fecha actual
- Cambiar el estado del libro a `available = true`

### Reglas de Negocio Esenciales

1. **No se puede prestar un libro que no está disponible**
2. **Al prestar un libro, automáticamente debe marcarse como no disponible**
3. **Al devolver un libro, automáticamente debe marcarse como disponible**

### Ejemplos de Peticiones

**Crear libro:**
```json
{
  "title": "1984",
  "author": "George Orwell",
  "isbn": "978-0-452-28423-4"
}
```

**Crear usuario:**
```json
{
  "name": "Ana García",
  "email": "ana.garcia@email.com"
}
```

---

**Tiempo: 60 minutos.
