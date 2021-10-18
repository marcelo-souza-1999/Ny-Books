## NY Books API - The Books API provides information about book reviews and The New York Times Best Sellers lists.

Este projeto teve como proposta melhorar meus conhecimentos no desenvolvimento Android seguindo a risca a arquitetura MVVM,
divisão de camadas, injeção de dependências, desacoplamento de responsabilidades entre as classes, praticando o uso de Testes Unitários. 
E conexão via Retrofit a uma API externa. 
Neste projeto eu chequei a Books Api, aplicando o bom uso da arquitetura MVVM, desacoplamento de responsabilidades entre as classes, injeção de dependências sem usar
Koin ou Dagger (estou praticando para usar usando esses frameworks), e também nesse projeto apliquei o Teste Unitário com o Mock na parte de ViewModel do projeto.

## O endpoint utilizado nesse aplicativo foi:

1. [lists.json](https://api.nytimes.com/svc/books/v3/lists.json)

## Tecnologias utlizadas no aplicativo

* Linguagem de Programação Kotlin
* Arquitetura MVVM
* ViewBinding
* ViewModel
* LiveData
* Constraint Layout
* Linear Layout
* Retrofit
* Moshi Converter
* Moshi Kotlin Codegen
* Mockito Kotlin
* Core Testing
* JUnit Test
* Mockito Test

## Funcionalidades

- Primeira activity do aplicativo exibi uma lista de livros exibindo os títulos e os autores do mesmo, ao clicar em cima de um item da lista é exibido uma segunda
activity onde é exibido o título do livro escolhido na primeira activity e exibe a descrição do livro escolhido.

## Desenvolvedor

* **Marcelo Souza** : @marcelo-souza-1999 (https://github.com/marcelo-souza-1999/)