import os

from pymongo import MongoClient
import json
import certifi

ca = certifi.where()


def load_config():
    with open(f'{os.path.dirname(os.path.abspath(__file__))}/config.json') as f:
        return json.load(f)


def connect_to_db():
    data_config = load_config()
    try:
        client = MongoClient(data_config["MONGO_URL_SERVER"], tlsCAFile=ca)
        db = client[data_config["MONGO_DB"]]
    except ConnectionError as e:
        print('connection failure', e)
    return db
