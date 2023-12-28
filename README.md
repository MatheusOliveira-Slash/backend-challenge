# Itaú - Backend Challenge - insurance-tax-api

Projeto teste para Itaú, o mesmo tem como intuito avaliar o desenvolvedor ao realizar a construção de uma API.

### Introdução

A aplicação tem como objetivo expor endpoints para realização do cadastro de produtos de seguros, contendo os dados: Nome, categoria de seguro, valor base e valor tarifado. Após cada cadastrado é disponibiizado um ID para atualização e consulta do registro.

Os dados são salvos em um banco em memória H2, o mesmo não persiste os dados após o fim da aplicação.

A escolha do banco, dado o desafio técnico, se deve a praticidade da implementação. Em uma situação real considerariamos as necessidades de negocio e utilizariamos o Teorema CAP para nos auxiliar na decisão.

Toda a aplicação esta em inglês porém o README e os retornos para o usuário foram mantidos em português.

### Decisões do Projeto

* Spring Boot: Escolhido por ser uma framework amplamente utilizado, proporcionando facilidade na configuração, desenvolvimento e manutenção.
* H2 Database: Utilizado como banco de dados em memória para simplificar o setup e execução dos testes.
* Clean Architecture: Adotada para garantir uma estrutura modular e desacoplada, facilitando a manutenção e testabilidade do código.
* Lombok: Utilizado para reduzir a verbosidade do código, gerando automaticamente getters, setters e outros métodos padrão.

### Descrição das pacotes

Controller: Camada de exposição de rota assim como orquestrações para validação do request e construção do response.

Domain: Segregação da regra de negocio através de um ENUM. O uso do ENUM permite a utilização do Strategy Pattern o que deixa o calculo do valor tarifado inerente ao ENUM.

Entity: Contrato que faz relação com a camada de persistência. Seguindo um modelo rico, possui também a chamada de método para calculo do valor tarifado.

Exception: Segregado toda a parte de exception, incluindo exceptions personalizadas, handlers e o contrato de erro que é enviado ao usuário. MessageError se trata de modelo anemico.

Model: Contrato de entrada ou saída da aplicação. Seguindo um modelo rico, possui também as validações de request.

Repository: Expõe métodos para acesso para camada de persistência.

Service: Construído com generics para atender o comportamento padrão de um CRUD (DELETE não foi implementado). Esta forma permite a introdução de novas services sem a necessidade de replicar código, apenas criando novos contratos e implementando as Bases Service, Repository, Entity e ID.

### Testes

* Mocks - Tem o intuito encapsular as classes de contrato mockadas para realização dos testes
* Asserts - Encapsula o assert das classes de contrato utilizando a classe Mocks como base de comparação.
* Integration - O pacote integration executa os testes sem o uso do Mockito, testando as conexões ao longo da execução e insere os dados no banco. Posterior a cada método cada registro criado é excluído 
* Demais pacotes seguem o módelo de teste de unidade, utilizando Mockito para dependências externas a classe, quando necessário.

## Observação importante

Dado a solicitação não há regra para duplicidade dos registros nos bancos mas salientando que ao fazer isso fugimos das regras de normalização deixando o banco sujeito a problemas, tais quais:
* Redundância
* Uso de espaço de desnecessário
* Registros duplicados requerem atualizações multiplas
* Consultas incosistentes para consultas que não façam uso do id

## Construído com

* [Maven](https://maven.apache.org/) - Gerenciador de Dependências
* [Spring Boot](https://spring.io/projects/spring-boot/) - Framework Web e Data Access
* [Logback](https://logback.qos.ch/) - Framework gerenciador de logs
* [Lombok](https://projectlombok.org/) - Lib para automação de "boiler plate"
* [H2 Database](https://www.h2database.com/html/main.html) - Gerenciador de bases relacionais em memória
* [Mockito](https://site.mockito.org/) - Framework de mock para teste de código 

## Autores

* **Lucas Vinicius Salviano Rodrigues** - *Desenvolvimento* - [Git Lucas](https://github.com/lucasviniciusrodrigues)