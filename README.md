# Esboço Comix

## Descrição

E-commerce de revistas em quadrinhos.
Esse projeto foi criado e desenvolvido para a disciplina de Laboratório de Engenharia de Software.

## Funcionalidades
Dentre as funcionalidades do sistema, podemos citar:
- Cadastro de clientes, junto de endereços e cartões de crédito
- Carrinho de compras para os quadrinhos
- Compras podendo ser pagas com cartões de crédito e cupons 
- Análise de Histórico de Vendas
- Controle de Estoque dos Quadrinhos
- Recomendações personalizadas para o cliente com uso de IA generativa

<img src="https://github.com/user-attachments/assets/f7bd76b6-f7e9-4e31-afc4-01e208f55978" height="400px" />

## Tecnologias
- Java
- JDBC
- PostgreSQL
- HTML, CSS e JS
- Bootstrap
- Python
- Gemini AI

## Configurações
Para usar esse programa, é necessário ter um arquivo chamado config.properties, dentro da pasta src/main/resources. Esse arquivo deve ter essa estrutura: 

```
[DEFAULT]
api.key=INSIRA_SUA_CHAVE_GEMINI

database.driver=org.postgresql.Driver
database.url=jdbc:postgresql://localhost:5432/projeto_les
database.user=postgres
database.password=admin123
```
Obs.: os valores presentes servem apenas como exemplo.

## Créditos

Ícones retirados de Flaticon feitos por:
- FACH
- Freepik
- Febrian Hidayat
