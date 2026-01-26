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
Vídeo demonstração disponível [aqui](https://www.youtube.com/watch?v=v4lKc46paZg).

![image](https://github.com/user-attachments/assets/be59ac69-010e-40ba-b12b-10fc1af7c29e)

## 🖥️ Tecnologias
- Java
- Tomcat
- JDBC
- PostgreSQL
- HTML, CSS e JS
- Bootstrap
- Python
- Gemini AI

## ⚙️ Configurações

### Banco de Dados

Para criar o banco, é necessário utilizar os scripts SQL disponíveis no diretório src/main/resources/script_sql. A ordem de execução é a seguinte:

1. criacao_banco.sql
2. triggers_sequences.sql
3. views.sql
4. procedures.sql
5. inserts.sql

### Docker

Caso estiver usando o Docker, deve-se criar um arquivo .env na raíz do projeto, seguindo a estrutura do .env.example. IMPORTANTE: Caso realmente for usar no Docker, não é necessário configurar nenhum 'arquivo config.properties'.

Comandos úteis:
```
# Para listar as variáveis de ambiente
docker exec postgres /usr/bin/env
```

### Local

Caso estiver usando o sistema localmente, deve-se fazer as seguintes configurações.

#### Criando o arquivo config.properties

Para usar esse programa, é necessário ter um arquivo chamado config.properties, dentro da pasta src/main/resources. O arquivo deve seguir a estrutura do arquivo config.properties.example, que pode ser encontrado no mesmo diretório.

#### Configurando os testes

Para realizar os testes, é necessário outro arquivo de 'config.properties', dessa vez dentro de src/test/resources. Basta seguir o arquivo config.properties.example, presente nesse mesmo diretório. Os browsers suportados são "edge" e "firefox".

#### Preparando o sistema do Chatbot

Para iniciar o subsistema de recomendação por IA, deve-se navegar para a pasta src/main/chatbot e iniciar o servidor Python FastAPI:

```
# Navegando até a pasta
cd src/main/chatbot

# OPCIONAL: Adicionando e iniciando um ambiente virtual Python
python -m venv .venv
source .venv/bin/activate

# Instalando dependências
pip install -r requirements.txt
```

#### Recomendado: Preferências VSCode

Adicionar a propriedade `"javascript.preferences.importModuleSpecifierEnding": "js"` dentro do .vscode/settings.json. Essa configuração faz com que imports dentro de arquivos js sejam feitos com o final .js, necessário para uma página estática como a do Esboço Comix. Exemplo:

```json
{
    "java.compile.nullAnalysis.mode": "automatic",
    "java.configuration.updateBuildConfiguration": "interactive",
    "javascript.preferences.importModuleSpecifierEnding": "js",
}
```

## ▶️ Executando

### Sistema Chatbot

Para iniciar o sistema do Chatbot, basta executar os seguintes comandos:

```
# Navegando até a pasta
cd src/main/chatbot

# OPCIONAL: Iniciando ambiente virtual Python
source .venv/bin/activate

# Executando
uvicorn app.main:app --reload
```

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
- [W3 Schools](https://www.w3schools.com/html/default.asp)
 
 ### Vídeo Demonstração
 *Cartoon Animation Music (Funny Happy Cute Sweet Background)* - BackgroundMusicForVideos: [Link](https://pixabay.com/music/cartoons-cartoon-animation-music-funny-happy-cute-sweet-background-249671/)
