# optmalg
Analisador e otimizador de algoritmos a partir da análise de Grafos de Fluxo de Controle (GFC).

## Autores ##
- Felipe Barbalho Rocha
- Lucas Cristiano Calixto Dantas

### Execução
Para obter a versão mais recente do projeto, clone o repositório com o comando:
```sh
$ git clone https://github.com/lucascriistiano/optmalg.git
$ cd optmalg
```

### Execução
O projeto pode ser executado a partir do *eclipse* ou com o uso do *Maven*.

#### Execução com o Maven
A instalação do *Maven* pode ser realizada a partir do tutorial, exibido em: https://maven.apache.org/install.html

Uma vez instalado, na pasta clonada do projeto, basta executar:
```sh
$ mvn package
```

Após a execução do comando, será gerado na pasta *target* o arquivo *optmalg-1.0-SNAPSHOT-jar-with-dependencies.jar*. Para executá-lo é necessário executar, na pasta raiz do projeto, o seguinte comando:
```sh
$ java -jar target/optmalg-1.0-SNAPSHOT-jar-with-dependencies.jar <nome-do-arquivo-de-entrada>
```

Na pasta *input* existem alguns exemplos de arquivos de entrada que podem ser passados como argumento para a execução. Um exemplo de comando para a execução da análise sobre um desses arquivos é exibido abaixo:
```sh
$ java -jar target/optmalg-1.0-SNAPSHOT-jar-with-dependencies.jar input/TestFor.java
```

## Referências ##
* [Wikipedia](https://en.wikipedia.org/wiki/Control_flow_graph)