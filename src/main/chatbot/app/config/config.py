import configparser

config = configparser.ConfigParser()
config.read('../resources/config.properties')

api_key = config['DEFAULT']['api.key']