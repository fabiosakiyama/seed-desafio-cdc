![Java-CI-with-Maven](https://github.com/fabiosakiyama/seed-desafio-cdc/workflows/Java-CI-with-Maven/badge.svg?branch=master)

# Desafio 1 da segunda jornada do Dev Eficiente

O desafio consiste em implementar uma parte do casa do código (https://www.casadocodigo.com.br/), seguindo as práticas do CDD (Cognitive Driven Development), proposto na jornada
do https://deveficiente.com/

# Stack
- JDK 11
- Spring boot 2.3.5
- Lombok
- H2
- JUnit 5
- Swagger

# Como rodar?

- Utilizar o maven wrapper p/ buildar (./mvnw clean install) e subir a app (./mvnw spring-boot:run)
- O banco configurado é o h2 em memória, ou seja, não vai persistir dados. (http://localhost:8080/h2/login.do)
- Acesse http://localhost:8080/swagger-ui/#/ para ver os endpoints disponíveis
