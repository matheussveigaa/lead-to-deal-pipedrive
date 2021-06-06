### Requisitos:
- Java 11;
- Alguma IDE (IntelliJ, eclipse e etc.);
- Docker e docker compose.

### Obtendo o projeto:
- git clone https://github.com/matheussveigaa/lead-to-deal-pipedrive.git

### Docker-compose:
- MongoDb;
- MongoSeed (faz uma ação de criar a collection de user e inserir um default);
- MongoExpress (uma interface para acesso ao banco de dados. http://localhost:8081/);

### Inicializando infra:
- Abra o terminal na pasta raiz do projeto (onde está o docker-compose.yml);
- docker-compose up.

### Na sua IDE:
- Executar método main da classe DemoApplication.

####Se acontecer algum problema até aqui favor me comunicar para verificar o motivo.

### Com a aplicação rodando
- É possível acessar as duas api's via open api (swagger) via http://localhost:8180/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
- O processo de integração é feito em background e é acionado a cada minuto mas também é possível acionar o job manualmente entrando no dashboard http://localhost:8000/dashboard/recurring-jobs