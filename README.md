# Infrastructure - DDD Architecture

Módulo de infraestrutura compartilhada para implementação de Domain-Driven Design (DDD) com Spring Boot.

## Estrutura

### Config
Configurações da aplicação:
- **SecurityConfig**: Configuração de segurança Spring Security
- **ModelMapperConfig**: Configuração do ModelMapper para mapeamento de objetos
- **Web**:
  - `GlobalExceptionHandler`: Tratamento global de exceções
  - `ApiErrorMapper`: Mapeamento de erros para API

### Events
Sistema de eventos de domínio:
- **SpringDomainEventPublisher**: Implementação do publisher de eventos usando Spring ApplicationEventPublisher

### Logger
Sistema de logging para infraestrutura.

## Tecnologias

- **Spring Boot 3.5.7**
- **Spring Data JPA**: Persistência de dados
- **Spring Security**: Segurança
- **Spring Web**: APIs REST
- **H2 Database**: Banco em memória para desenvolvimento
- **ModelMapper**: Mapeamento de objetos
- **Lombok**: Redução de boilerplate
- **Java 17**

## Dependências

### Spring Framework
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-jdbc`
- `spring-boot-starter-security`
- `spring-boot-starter-web`
- `spring-boot-devtools`

### Bibliotecas Externas
- `modelmapper:3.2.0`
- `h2database`

### Módulos Internos
- `common-lib:1.0-SNAPSHOT`

## Configuração

### Banco de Dados
Por padrão, utiliza H2 em memória. Configure `application.yml` para outros bancos:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
```

### Segurança
Configuração básica no `SecurityConfig.java`. Customize conforme necessário.

## Uso

### Como Dependência
```xml
<dependency>
    <groupId>com.sakai</groupId>
    <artifactId>shared-infrastructure</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### Eventos de Domínio
```java
@Autowired
private SpringDomainEventPublisher eventPublisher;

// Publicar evento
eventPublisher.publish(new MeuDomainEvent());

// Publicar eventos de agregado
eventPublisher.publish(meuAggregateRoot);
```

## Build

```bash
# Compilar
mvn clean compile

# Executar testes
mvn test

# Gerar JAR
mvn clean package

# Instalar no repositório local
mvn clean install
```

## Desenvolvimento

```bash
# Executar aplicação
mvn spring-boot:run

# Com profile específico
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Estrutura do Projeto

```
infrastructure/
├── src/
│   ├── main/
│   │   ├── java/com/sakai/infrastructure/
│   │   │   ├── config/           # Configurações
│   │   │   ├── events/           # Sistema de eventos
│   │   │   ├── logger/           # Logging
│   │   │   └── Application.java  # Classe principal
│   │   └── resources/
│   │       └── application.yml   # Configurações
│   └── test/                     # Testes
├── pom.xml
└── README.md
```