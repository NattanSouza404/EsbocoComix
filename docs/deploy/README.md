# Estudos Deploy

## Deploy no EC2 da AWS

Para começar o deploy, é necessário realizar os seguintes passos:

- Criar instância EC2 (S.O. usado no estudo é o Amazon Linux)

- Permitir acesso HTTP e HTTPS para a instância

- Permitir o acesso às portas:
    - 8080 (Backend)
    - 5433 (PostgreSQL)

- Se conectar à instância e realizar os seguintes comandos

```sh
sudo yum upgrade -y

# Instalando o docker
sudo yum install docker
sudo service docker start

# Instalando o Docker Compose
cd ../../.. # volta para a pasta mais anterior possível
sudo mkdir -p /usr/local/lib/docker/cli-plugins
sudo curl -SL https://github.com/docker/compose/releases/download/v2.25.0/docker-compose-linux-x86_64 \
  -o /usr/local/lib/docker/cli-plugins/docker-compose
sudo chmod +x /usr/local/lib/docker/cli-plugins/docker-compose

# Nos permite não ter que escrever 'sudo' toda hora para executar
# um comando docker no terminal
sudo usermod -a -G docker ec2-user #ec2-user é o usuário que usamos

# Retornar a pasta do usuário
cd /home/ec2-user

# Criar diretório do projeto
mkdir esboco-comix
cd esboco-comix

# Necessário criar o .env no ec2 (seguir .env.example)
# Dá para usar o vim, prencheendo o .env e salvando com ':wq .env'
vim
```

### Opção 1: Usando Github Actions (Recomendado)

Para fazer o deploy no EC2 com Github Actions, é necessário definir os secrets no próprio repositório. Os nomes dos secrets a serem definidos estão dentro de `.github/workflows/deploy.yml`.

- Criar o arquivo docker-compose.yml dentro do ec2
```sh
cd esboco-comix

# Obs.: Seguir a estrutura do docker-compose.yml do repositório
vim
```

- Rodar workflow do Github Actions (Ex.: Fazendo um commit)

### Opção 2: Copiando arquivos com Git

```sh
# No ec2, instalar git
sudo yum install git

# Após configurar git, clonar repositório
git clone https://github.com/NattanSouza404/EsbocoComix.git esboco-comix

cd esboco-comix

# Construir e rodar o docker
docker compose up -d --remove-orphans --build
```

# Referências Vídeos Referência

- [Vídeo - BR Hosting a Docker Container on AWS EC2 Free Tier in under 12 minutes](https://www.youtube.com/watch?v=qNIniDftAcU)

- [Vídeo - Effortless CI/CD: Deploy Docker Images on EC2 with GitHub Actions in Minutes!](https://www.youtube.com/watch?v=OjRevvZvWX4)

- [Repositório - tariq87 Dockerpp](https://github.com/tariq87/dockerapp/blob/main/.github/workflows/deploy.yml)


- [INTEGRAÇÃO CONTINUA - CI/CD: GitHub Actions pra AUTOMAÇÃO de BUILD e execução de TESTES em Java](https://www.youtube.com/watch?v=wA1RbmK-QRA)

- [Docs - Github Actions](https://docs.github.com/pt/enterprise-cloud@latest/actions/reference/workflows-and-actions/events-that-trigger-workflows)