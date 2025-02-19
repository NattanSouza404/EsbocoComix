import configparser

config = configparser.ConfigParser()
config.read('src/main/resources/config.properties')

api_key = config['DEFAULT']['api.key']

print(f"API Key: {api_key}")