# Users API

API REST para busca de usuários com paginação e filtros.

## Tecnologias

- Java 21
- Spring Boot 3.x
- Maven 3.x
- Docker (Para execução sem precisar instalar localmente)

## 🚀 Execução

### Pré-requisitos
- Docker
- Ou Java e Maven (para execução local)
### Docker
```bash
# Clone o repositório
git clone https://github.com/lsguilherme/desafio-tecnico-valcann.git
cd api-usuarios

# Build e execução
docker build -t users-api .
docker run --rm -p 8080:8080 users-api
```
#### Localmente
```bash
# Ou, execução local com Maven
./mvnw spring-boot:run
```
## 📖 Documentação
Caso prefira testar no swagger, a documentação interativa está disponível em:

- **Swagger UI:** http://localhost:8080/docs

## 📚 Curl

### Listar todos os usuários
```bash
curl "http://localhost:8080/api/v1/users"
```

### Listar usuários com paginação
```bash
curl "http://localhost:8080/api/v1/users?page=1&pageSize=5"
```

### Filtrar por nome
```bash
curl "http://localhost:8080/api/v1/users?q=Gabriela"
```

### Filtrar por role
```bash
curl "http://localhost:8080/api/v1/users?role=ADMIN"
```

### Filtrar por status ativo
```bash
curl "http://localhost:8080/api/v1/users?is_active=true"
```

### Buscar usuário por ID
```bash
curl "http://localhost:8080/api/v1/users/1"
```

### Filtros combinados
```bash
curl "http://localhost:8080/api/v1/users?q=Paula&role=ANALYST&is_active=true&page=1&pageSize=10"
```

### Erro 404
```bash
curl "http://localhost:8080/api/v1/users/999"
```


## 🧪 Testes

```bash
# Executar testes unitários
./mvnw test
```
