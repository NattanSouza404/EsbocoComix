from dotenv import load_dotenv
import os

load_dotenv(dotenv_path='src/.env')
api_key = os.getenv('API_KEY')

print("Diretório de trabalho atual:", os.getcwd())
print(f"Chave api: {api_key}")