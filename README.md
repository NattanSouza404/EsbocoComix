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
- Tomcat
- JDBC
- PostgreSQL
- HTML, CSS e JS
- Bootstrap
- Python
- Gemini AI

## ⚙️ Instruções de Uso e Configurações

### Subsistema Python

Para iniciar o subsistema de recomendação por IA, deve-se navegar para a pasta src/main/chatbot e iniciar o servidor Python FastAPI:

```
# Navegar até a pasta
cd src/main/chatbot

# Caso estiver sendo usado um ambiente virtual do python
python -m venv .venv
source .venv/bin/activate

# Instalando dependências
pip install -r requirements.txt

# Executando
uvicorn main:app --reload
```

### Backend Java

Para usar esse programa, é necessário ter um arquivo chamado config.properties, dentro da pasta src/main/resources. Esse arquivo deve ter essa estrutura: 

```
[DEFAULT]
api.key=INSIRA_SUA_CHAVE_GEMINI

database.user=postgres
database.password=admin123

# Específico ao Java
database.driver=org.postgresql.Driver
database.url=jdbc:postgresql://localhost:5432/esboco_comix
```

### Banco de Dados

Para criar o banco, é necessário utilizar os scripts SQL disponíveis no diretório src/main/resources/script_sql. A ordem de execução é a seguinte:

1. criacao_banco.sql
2. triggers_sequences.sql
3. views.sql
4. procedures.sql
5. inserts.sql

### Testes Backend

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

- [As Aventuras do Homem Chiclete](https://www.comix.com.br/quadrinhos/quadrinhos-nacionais/as-aventuras-do-homem-chiclete.html)
- [Conancraft](https://www.comix.com.br/quadrinhos/quadrinhos-nacionais/conancraft.html)
- [Sonic The Hedgehog](https://www.comix.com.br/sonic-the-hedgehog-depois-da-guerra-volume-1.html)
- [O menino maluquinho](https://www.livrariadavila.com.br/876972-o-menino-maluquinho/p)
- [O Homem-Cão](https://www.livrariadavila.com.br/471214-o-homem-cao---vol-1/p)
- [Aventuras da Julieta](https://www.livrariadavila.com.br/470068-aventuras-da-julieta/p)
- [O tesouro de Rackham, O Terrível](https://mundosinfinitos.com.br/geek/produto/Comics-O-tesouro-de-Rackham-O-Terrivel-117925.aspx)
- [Turma da Mônica - O pequeno príncipe](https://www.livrariadavila.com.br/432321-turma-da-monica---o-pequeno-principe-b/p)

### Ícones

- [Bootstrap Icons](https://icons.getbootstrap.com/)
- Ícones retirados de [Flaticon](https://www.flaticon.com/br/) feitos por:
    - FACH
    - Freepik
    - Febrian Hidayat

### Referências

- [Docker docs](https://docs.docker.com/get-started/introduction/)
- [FastAPI docs](https://fastapi.tiangolo.com/deployment/docker/)