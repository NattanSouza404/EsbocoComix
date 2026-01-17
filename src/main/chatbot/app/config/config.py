import configparser
import os

config = configparser.ConfigParser()
config.read('../resources/config.properties')

def getConfig(envName: str, propertiesName: str):
    value = os.getenv(envName)

    return value if value else config['DEFAULT'][propertiesName]

GEMINI_API_KEY = getConfig('GEMINI_API_KEY', 'gemini.apikey')
GEMINI_MODEL = getConfig('GEMINI_MODEL', 'gemini.model')
BACKEND_URL = getConfig('BACKEND_URL', 'backend.url')