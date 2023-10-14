# Estágio de construção
FROM ubuntu:latest AS build

# Atualiza o sistema operacional e instala o OpenJDK 17
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

# Copia todo o conteúdo do diretório local para o diretório de construção na imagem
COPY . .

# Instala o Maven e compila o projeto
RUN apt-get install maven -y
RUN mvn clean install

# Estágio de produção
FROM openjdk:17-jdk-slim

# Expõe a porta 8080 do contêiner
EXPOSE 8080

# Copia o arquivo JAR do estágio de construção para o estágio de produção
COPY --from=build /target/todolist-1.0.0.jar app.jar

# Define o comando de entrada para executar o aplicativo Java
ENTRYPOINT [ "java", "-jar", "app.jar" ]
