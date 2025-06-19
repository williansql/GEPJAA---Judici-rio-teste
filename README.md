# GEPJAA - Gestão de Processos Judiciais com Agendamento de Audiências

## Descrição
API desenvolvida em Java Spring Boot para gerenciar processos judiciais e suas respectivas audiências, com regras de negócio além do CRUD básico.

## Funcionalidades
- Cadastro, listagem e filtro de processos judiciais
- Agendamento de audiências com regras de negócio:
  - Não permite sobreposição de audiências na mesma vara/local/data/hora
  - Não permite agendamento para processos arquivados/suspensos
  - Só permite agendamento em dias úteis (segunda a sexta)
- Consulta de agenda de audiências por comarca e data
- Autenticação de usuários
- Documentação automática via Swagger
- Versionamento de API (`/v1`)

## Como rodar o projeto
1. **Pré-requisitos:**
   - Java 17 ou superior
   - Maven 3.8+
   - Banco de dados H2 (padrão) ou PostgreSQL

2. **Instale as dependências:**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a documentação Swagger:**
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoints principais

### Autenticação
- **POST `/v1/auth/login`**
  ```json
  {
    "login": "usuario",
    "password": "senha"
  }
  ```
- **POST `/v1/auth/register`**
  ```json
  {
    "login": "usuario",
    "password": "senha",
    "role": "USER"
  }
  ```

### Processos Judiciais
- **POST `/v1/process`**
  ```json
  {
    "processNumber": "1234567-89.2024.1.12.3456",
    "court": "1ª Vara",
    "place": "Fórum Central",
    "subject": "Ação de Teste",
    "status": "ACTIVE"
  }
  ```
- **GET `/v1/process?status=ACTIVE&court=1ª Vara`**
- **PUT `/v1/process/{id}`**
- **PATCH `/v1/process/{id}`** (altera status)

### Audiências
- **POST `/v1/hearing`**
  ```json
  {
    "dateTime": "2024-06-21T14:00:00",
    "type": "CONCILIACAO",
    "location": "Sala 101",
    "court": "1ª Vara",
    "district": "Comarca Central",
    "processId": "<id do processo>"
  }
  ```
- **GET `/v1/hearing/agenda?district=Comarca Central&date=2024-06-21`**

## Como contribuir ou alterar o projeto
- Siga o padrão de versionamento de API (`/v1/...`).
- Mantenha as regras de negócio descritas acima.
- Testes unitários/integrados devem ser adicionados para novas regras.
- Documente endpoints e exemplos no Swagger.

## Observações
- O número do processo deve seguir o formato: `0000000-00.0000.0.00.0000`
- Audiências só podem ser agendadas em dias úteis.
- Não é permitido agendar audiências para processos arquivados ou suspensos.

---

> Para dúvidas, consulte a documentação Swagger ou entre em contato com o responsável pelo projeto. 