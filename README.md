# Boas-vindas ao repositório do Bug Hunting! 🐛🔍
# Sistema de Gerenciamento de Vídeos - Refatoração e Boas Práticas 🎥📂

Você já usa o GitHub diariamente para desenvolver os exercícios e os projetos, certo? Agora, para desenvolver este desafio, você deverá seguir as instruções aqui descritas para conseguir resolvê-lo. Se tiver qualquer dúvida, nos envie pelo chat! #vamosquevamos 🚀

Aqui você vai encontrar os detalhes do que fazer antes de iniciar o desenvolvimento do seu desafio.

## 🛠 O que deverá ser desenvolvido

Uma equipe de pessoas desenvolvedoras estava encarregada de criar um sistema simples para gerenciar informações de vídeos, como título, descrição e duração, armazenando esses dados em arquivos locais. O sistema foi desenvolvido utilizando Java com conceitos básicos de POO e manipulação de arquivos, mas, infelizmente, a equipe não seguiu as melhores práticas de desenvolvimento.

O código está funcional, mas apresenta **bugs**, **problemas de organização** e **más práticas de programação**. Além disso, o sistema não possui validações adequadas e a interface de interação com o usuário (via terminal) é confusa.

Dito isso, vocês foram selecionados para este desafio, por já conhecerem **Java** e os conceitos de **POO**. Sendo assim, vocês estão encarregados desta demanda de **refatoração geral do sistema**.


## 🗒 Passo a passo para Desenvolvimento

### 1. Configurando o ambiente

Antes de começar, certifique-se de que você tem as ferramentas necessárias instaladas no seu ambiente de desenvolvimento:

- Java 17 ou superior.
- Um editor de código ou IDE, como IntelliJ IDEA ou VSCode.
- Git para versionamento de código.

### 2. Estrutura inicial do projeto

O código será dividido em pacotes para organizar melhor as responsabilidades:

- model: Contém a classe Video.
- repository: Contém a interface VideoRepository e sua implementação FileVideoRepository.
- service: Contém a interface VideoService e sua implementação VideoServiceImpl.
- strategy: Contém estratégias para busca de vídeos (SearchStrategy).
- main: Contém a classe principal Main.

Você deve clonar o repositório e analisar o código existente para entender como ele funciona.

### 3. Rodando o sistema
   Para rodar o sistema, basta compilar e executar o arquivo Main.java. Caso use o IntelliJ basta "dar play" ou, caso utilize o terminal, pode rodar via comando. Por exemplo:
   
```
javac -d bin src/main/Main.java
java ./src/main/Main.java
```
O sistema permite que o usuário:

- Adicione novos vídeos.
- Liste os vídeos armazenados.
- Pesquise vídeos por título.

No entanto, o código atual apresenta problemas que você deverá corrigir.

##📋 Instruções para entregar seu desafio

1. Faça o Fork do repositório para o seu github.

2. Clone o repositório e crie uma nova branch com o seu nome:

```
git checkout -b sua-branch
```

3. Faça as alterações necessárias para corrigir os bugs e melhorar o código.

4. Faça commits claros e objetivos, explicando as alterações realizadas e usando conventional commits.

5. Suba sua branch para o repositório remoto:

```
git push -u origin sua-branch
```

6. Abra um Pull Request no GitHub, peça para algum colega revisar e aguarde a revisão. 
 
Observação: Caso alguém te solicite revisão, é recomendado que você revise apenas depois de terminar o seu desafio, para que vocês possam trocar conhecimentos de forma mais produtiva.

## Solicitando revisão

Uma thread será aberta no Google Classes do Catalisa e você deve enviar o link do seu pull request como comentário dessa thread para que outros colegas tenham visibilidade e revisem caso queiram.

## ✅ Requisitos a serem corrigidos

Aqui estão os principais pontos que você deve corrigir no sistema:

1. Bugs:

- Corrija os erros que impedem o funcionamento correto do sistema.
- Resolva problemas relacionados à leitura e escrita no arquivo videos.txt.

2. Boas práticas:

- Refatore o código para seguir os princípios de POO.
- Organize o código em classes e métodos bem definidos.
- Evite duplicação de código e use nomes de variáveis e métodos claros.

3. Validações:

- Certifique-se de que o título e a descrição do vídeo não estejam vazios.
- Valide que a duração do vídeo seja um número positivo.

4. Interface de interação:

- Melhore os textos exibidos no terminal para que o sistema seja mais intuitivo.
- Adicione mensagens de erro claras para entradas inválidas.

## Problemas no código

1. Falta de validações:

- Não verifica se o título ou descrição estão vazios.
- Não valida se a duração é um número positivo.
- Não trata erros caso a pessoa usuária insira palavras ao invés de números ou números através de palavras

2. Más práticas de POO:

- A classe Main mistura lógica de negócios, manipulação de arquivos e interação com o usuário.
- A classe Video expõe diretamente seus atributos, violando o encapsulamento.

3. Interface confusa:

- Mensagens de erro e feedback poderiam ser mais claras.
- Não há tratamento para entradas inválidas, como letras em vez de números.

4. Manutenção difícil:

- O código não está modularizado, dificultando a reutilização e testes.

## Objetivos: 

- Refatorar o código para corrigir os problemas mencionados.
- Aplicar boas práticas de POO, como encapsulamento e separação de responsabilidades.
- Melhorar a interface de interação com o usuário.
- Implementar validações para garantir que os dados inseridos sejam consistentes.

## ✅ Requisitos a serem corrigidos e implementados
Aqui estão os principais pontos que você deve corrigir e implementar no sistema:

1. Correção de bugs
   - Resolva problemas relacionados à leitura e escrita no arquivo videos.txt.
   - Corrija erros que possam ocorrer ao manipular vídeos com dados inválidos ou incompletos.
2. Refatoração para boas práticas
   - Separe as responsabilidades do código em diferentes classes, como:
       - Uma classe para gerenciar a lógica de negócios (VideoManager).
       - Uma classe para manipular arquivos (FileHandler).
   - Aplique o princípio de encapsulamento na classe Video, tornando os atributos privados e acessíveis apenas por getters e setters.
   - Modularize o código para evitar duplicação e melhorar a legibilidade.
3. Validações

Certifique-se de que:
- [ ] O título e a descrição do vídeo não devem estar vazios.
- [ ] A duração do vídeo deve ser um número positivo.
- [ ] A categoria do vídeo deve ser válida (exemplo: "Filme", "Série", "Documentário").
- [ ] A data de publicação deve ser uma data válida no formato `dd/MM/yyyy`.

4. Interface de interação
   - Melhore os textos exibidos no terminal para que o sistema seja mais intuitivo.
   - Adicione um menu principal com as seguintes opções:
   - Adicionar vídeo.
   - Listar vídeos.
   - Pesquisar vídeo por título.
   - Editar vídeo.
   - Excluir vídeo.
   - Filtrar vídeos por categoria.
   - Ordenar vídeos por data de publicação.
   - Exibir relatório de estatísticas.
   - Sair.

5. Acoplamento: A classe Main está acoplada diretamente às implementações.
   
6. Melhorias no Strategy Pattern: Adicionar novas estratégias de busca, como por categoria ou duração.

## Implementação de novas funcionalidades

1. **Editar vídeo**: Permitir que o usuário edite as informações de um vídeo existente.
2. **Excluir vídeo**: Adicionar a opção de remover um vídeo do sistema.
3. **Filtrar vídeos por categoria**: Listar apenas os vídeos de uma categoria específica.
4. **Ordenar vídeos por data de publicação**: Listar os vídeos em ordem cronológica.
5. **Relatório de estatísticas**:
   - Número total de vídeos.
   - Duração total de todos os vídeos.
   - Quantidade de vídeos por categoria.

## 🔄 Revisando um Pull Request

⚠⚠⚠ À medida que você e as outras pessoas estudantes forem entregando os projetos, vocês podem também fazer a revisão dos Pull Requests dos seus colegas.

---

## Orientações para Execução do Code Review (CR)

Neste item, serão listadas algumas atitudes que contribuirão para um processo de Code Review mais tranquilo, assertivo e sem conflitos.
- Faça o processo de CR com calma. Reserve tempo para ler o conteúdo do PR e os códigos, para ter uma melhor visão do contexto.
- Em caso de dúvida, deixe comentários ou se comunique com a pessoa autora.
- Não faça alterações no código sem alinhamento com a pessoa autora. Em caso de necessidade, alinhe com ele que você fara as alterações e peça a outra pessoa para efetuar o CR.
- Só rejeite um PR em caso de risco. Do contrário, solicite as alterações à pessoa autora. Caso um PR seja rejeitado, selecione a opção "Abandon" em seguida.
- Caso haja alguma dúvida sobre o procedimento ou codificação, entre em contato com as pessoas instrutoras.

___

## Orientações para Comentários

Alguns pontos devem ser levados em consideração durante a comunicação da pessoa revisora com a pessoa autora. Esta comunicação deve ser clara, concisa e respeitosa. Alguns cuidados devem ser tomados nessa comunicação, principalmente ao que diz respeito aos comentários em um CR:
- Faça comentários curtos e precisos. Não é necessário efetuar comentários estensos. Caso necessário, entre em contato direto com a pessoa autora.
- Em caso de sugestões, apresente-as com pequenos trechos de código ou links para orientar a pessoa autora.
- Prefira usar linguagem sugestiva à imperativa. Ao invés de utilizar um comentário como "Ficou ruim", prefira "é uma boa prática utilizar nomes mais descritivos" e apresente sugestões.
- Utilize perguntas para trazer reflexão ao autor. Exemplo : "É mais interessante utilizarmos o padrão do MVC. Você consegue perceber as melhorias associadas?".
- Seja cordial e educado. Lembre-se, você está avaliando o código, e não a pessoa autora. O objetivo é manter a qualidade e eficiência do projeto.
- Faça elogios à códigos eficientes. Elogios são ótimas formas de incentivar uma pessoa a se dedicar. Por isso, ao ver um código bem executado, não refreie-se de elogiá-lo.
- Inserir razão do comentário: para trazer mais clareza à necessidade, descreva a razão deste. Desta forma o autor consegue identificar melhor a necessidade da mudança proposta.
- Inclusão de exemplo: ao criar um comentário, busque inserir exemplo da mudança sugerida, se possível. Tal abordagem facilitará a correção por parte da pessoa autora.
- Revise e comente independente da senioridade: todos podem revisar e criar comentários em PR'S. Além da melhoria do código, também é um momento de troca de experiencia. Mesmo uma pessoa júnior fazendo revisão em código de uma pessoa sênior, deve fazer comentários caso ache necessário. Lembrando sempre de manter em vista o tato e a cordialidade.

---

## 🚀 Prontos? Vamos caçar os bugs! 👾🔫
   1Boa sorte no desafio! Lembre-se de que o objetivo principal é aprender e se divertir enquanto melhora suas habilidades. Se precisar de ajuda, não hesite em nos chamar no Chat. Estamos aqui para ajudar! 😊


   uma linha
