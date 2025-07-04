# Esboço Comix

## 📄 Descrição

E-commerce de revistas em quadrinhos.
Esse projeto foi criado e desenvolvido para a disciplina de Laboratório de Engenharia de Software.

## 💡 Funcionalidades
Dentre as funcionalidades do sistema, podemos citar:
- Cadastro de clientes, junto de endereços e cartões de crédito
- Carrinho de compras para os quadrinhos
- Compras podendo ser pagas com cartões de crédito e cupons 
- Análise de Histórico de Vendas
- Controle de Estoque dos Quadrinhos
- Recomendações personalizadas para o cliente com uso de IA generativa

<img src="https://github.com/user-attachments/assets/f7bd76b6-f7e9-4e31-afc4-01e208f55978" height="400px" />

## 🎬 Vídeo demonstração
![Demo Video](./docs/video%20demonstração/video_demonstracao.mp4)

## 🖥️ Tecnologias
- Java
- JDBC
- PostgreSQL
- HTML, CSS e JS
- Bootstrap
- Python
- Gemini AI

## ⚙️ Instruções de Uso e Configurações
Para iniciar o subsistema de recomendação por IA, deve-se navegar para a pasta src/main/chatbot e iniciar o servidor Python FastAPI:

```
cd src/main/chatbot
uvicorn main:app --reload
```

Para usar esse programa, é necessário ter um arquivo chamado config.properties, dentro da pasta src/main/resources. Esse arquivo deve ter essa estrutura: 

```
[DEFAULT]
api.key=INSIRA_SUA_CHAVE_GEMINI

database.user=postgres
database.password=admin123

# Especí­fico ao Java
database.driver=org.postgresql.Driver
database.url=jdbc:postgresql://localhost:5432/esboco_comix

# Especí­fico ao Python
database.host=localhost
database.port=5432
database.database=esboco_comix
```

Para realizar os testes, também é necessário um config.properties dentro de src/test/resources com essa estrutura. Os browsers suportados são "edge" e "firefox".

```
# config.properties
browser=edge
headless=false
```

Obs.: os valores presentes servem apenas como exemplo.

## ✨ Principais Inspirações

- [Mercado Livre](https://www.mercadolivre.com.br/)
- [Comix Book Shop](https://www.comix.com.br/)

## 🎥 Créditos

### Quadrinhos

- https://www.comix.com.br/quadrinhos/quadrinhos-nacionais/as-aventuras-do-homem-chiclete.html

- https://www.comix.com.br/quadrinhos/quadrinhos-nacionais/conancraft.html

- https://www.comix.com.br/sonic-the-hedgehog-depois-da-guerra-volume-1.html

- https://www.livrariadavila.com.br/876972-o-menino-maluquinho/p

- https://www.livrariadavila.com.br/471214-o-homem-cao---vol-1/p

- https://www.livrariadavila.com.br/470068-aventuras-da-julieta/p

- https://mundosinfinitos.com.br/geek/produto/Comics-O-tesouro-de-Rackham-O-Terrivel-117925.aspx

- https://www.livrariadavila.com.br/432321-turma-da-monica---o-pequeno-principe-b/p

### Ícones

- Bootstrap Icons: https://icons.getbootstrap.com/

- Ícones retirados de Flaticon feitos por:
    - FACH
    - Freepik
    - Febrian Hidayat
