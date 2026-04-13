# SHOPPING_CART_CLEAN_ARCH

### Esse cARRINHO foi desenvolvido utilizando conceitos de Clean Architecture onde explorei as melhores formas de aplicar esse pattern,foi criada de forma desacoplada porem ligada com outraS Api's: CRUD_CADASTRO_DE_PESSOAS_CLEAN_ARCH_HEXAGONAL e PRODUTOS_CLEAN_ARCH, onde uma pessoa logada nesse sistema pode cadastrar, consultar e alterar produtos o Shopping cart vai permitir adicionar esses produtos em um carrinho de compras onde utilizei Redis para salvar esse carrinho por algumas horas/dia e excluir automaticamente o mesmo, vizando estudar e aprender sobre um banco NoSQL utilizando armazenamento em de dados em memoria.
.


## Requisitos:
- apache-maven-3.9.9 - [Download](https://maven.apache.org/download.cgi)
- Java 17 - [Download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- FrameWork - Spring Boot 3.3.4
- Docker Desktop (Rancher Desktop) - o banco foi configurado em um docker-compose. yml e esta junto aqui no repositorio os demais bancos estao [CRUD_CADASTRO_DE_PESSOAS_CLEAN_ARCH_HEXAGONAL](https://github.com/lucasscardoso/CRUD_CADASTRO_DE_PESSOAL_CLEAN_ARCH_HEXAGONAL) nesse repositorio esta a parte de clientes e o banco de clientes/produtos, repositorio de produtos: [PRODUTOS_CLEAN_ARCH](https://github.com/lucasscardoso/CRUD_CADASTRO_DE_PRODUTOSL_CLEAN_ARCH_HEXAGONAL) .
- Comandos basicos de docker - [Link](https://github.com/lucasscardoso/Docker) 
-  o Banco foi criado e testado utilizando uma imagem do Redis: redis:7.4-alpine.



## Core

- core/entity: Entitdade da aplicação.
- core/service: SaveShoppingCart , serve para salvar o carrinho do cliente.
- core/user/repository: Interface de Repositorio (Regras para funcionamento do repository.) e repositorio externo onde "conversamos" com a api externa.
- core/shared: Tudo que é compartilhado no core,exceptions,DTO, records,useCase(Interface para padronizar as services com entradas e saidas).


## External
- externals/auth/cryptograia: 

- externals/config: 
- externals/config/feignConfig: 
- externals/controllers: Controllers da aplicação.
- externals/db: 
- externals/entity: entidade espelho do core, serve para o mapeamento JPA.
- externals/interfaces: 
- externals/repository: 
- externals/security: 

### Deixo de curiosidade/consulta a documentação do Redis que utilizei: [Documentação do Redis](https://redis.io/docs/latest/)
