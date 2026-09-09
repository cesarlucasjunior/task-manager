# task-manager

API de estudo para registrar tarefas de forma **assíncrona**, aplicando **arquitetura
hexagonal** (ports & adapters). O cadastro chega por HTTP, vira uma mensagem numa fila
**AWS SQS** e um *listener* consome essa fila e grava a tarefa no PostgreSQL — produtor e
consumidor ficam desacoplados pela fila.

**Stack:** Kotlin 1.8 · Spring Boot 3.1 · Spring Data JPA · Spring JMS + Amazon SQS Java
Messaging Lib · AWS SDK v2 (SQS) · PostgreSQL · Gradle (Kotlin DSL) · testes com MockK +
JUnit 5 + JaCoCo

## Fluxo

```
POST /task
   │
   ▼
TaskController ──► RegisterTaskUseCase ──► ProducerTaskSqsAdapter ──► [ SQS queue ]
   (adapter/web)      (core/usecase)         (adapter/message)              │
                                                                           ▼
                                             ListenerTaskSqsAdapter ◄───────┘
                                                (@JmsListener)
                                                     │
                                                     ▼
                                             TaskRepository ──► PostgreSQL
                                             (adapter/repository)
```

`POST /task` responde imediatamente após enfileirar; a persistência acontece depois, quando
o listener processa a mensagem (concorrência 3–10, `CLIENT_ACKNOWLEDGE` — a mensagem só é
confirmada se o `save` der certo).

## Estrutura (hexagonal)

```
src/main/kotlin/com/cesarlucasjunior/taskmanager
├── core/                         # regra de negócio, sem dependência de framework
│   ├── domain/                   # Task, TaskRequest
│   ├── port/in/                  # RegisterTaskInputPort, ListenerTaskInputPort
│   ├── port/out/                 # ProducerTaskOutputPort, SaveTaskOutputPort
│   └── usecase/                  # RegisterTaskUseCase
└── adapter/                      # implementações de infraestrutura
    ├── web/                      # TaskController (REST)
    ├── message/                  # produtor e listener SQS (JMS)
    ├── repository/               # JPA (TaskJpa, TaskJpaRepository)
    ├── conf/                     # SqsConfiguration, JmsConfig
    └── exception/                # handlers
```

## Endpoint

| Método | Rota | Corpo | Descrição |
| --- | --- | --- | --- |
| `POST` | `/task` | `{ "description": "..." }` | Enfileira uma tarefa para registro |

```bash
curl -X POST http://localhost:8080/task \
  -H 'Content-Type: application/json' \
  -d '{"description": "Estudar arquitetura hexagonal"}'
```

## Configuração

Copie `.env.example` para `.env` e preencha:

| Variável | Descrição |
| --- | --- |
| `POSTGRES_DB` / `POSTGRES_USER` / `POSTGRES_PASSWORD` | credenciais do banco |
| `AWS_REGION` | região da fila (ex.: `us-east-2`) |
| `AWS_ACCESS_KEY` / `AWS_SECRET_KEY` | credenciais AWS |
| `SQS_ENDPOINT` | endpoint da fila — SQS real ou um emulador (LocalStack / ElasticMQ) |
| `QUEUE_NAME` | nome da fila (ex.: `tasks-queue`) |

## Como rodar

Pré-requisitos: JDK 17+, Docker e uma fila SQS acessível (real ou emulada).

```bash
# 1. Banco
docker compose up -d          # PostgreSQL na porta 5432, usando o .env

# 2. Build + testes
./gradlew build               # gera build/libs/*.jar e roda os testes

# 3. Aplicação
./gradlew bootRun             # http://localhost:8080
```

As tabelas são criadas automaticamente (`spring.jpa.hibernate.ddl-auto=update`).

> **Observações**
> - `application.properties` usa o host `task-manager-db` (nome do serviço no Compose). Para
>   rodar a aplicação fora do Docker, aponte `spring.datasource.url` para `localhost`.
> - O `Dockerfile` faz `COPY .env .`, o que embute as credenciais na imagem — troque por
>   variáveis de ambiente / *secrets* antes de qualquer uso além do local.

## Testes

```bash
./gradlew test               # relatório de cobertura em build/reports/jacoco/
```
