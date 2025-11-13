import configparser

config = configparser.ConfigParser()
config.read('../resources/config.properties')

API_KEY = config['DEFAULT']['api.key']
GEMINI_MODEL = config['DEFAULT']['gemini.model']