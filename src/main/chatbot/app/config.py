import configparser

config = configparser.ConfigParser()
config.read('../resources/config.properties')

api_key = config['DEFAULT']['api.key']

db_host = config['DEFAULT']['database.host']
db_port = config['DEFAULT']['database.port']
db_database = config['DEFAULT']['database.database']
db_user = config['DEFAULT']['database.user']
db_password = config['DEFAULT']['database.password']