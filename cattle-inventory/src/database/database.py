import os

import json
from sqlalchemy import create_engine, Table, select, Column, Integer, String
from sqlalchemy.orm import Session


def load_config():
    with open(f'{os.path.dirname(os.path.abspath(__file__))}/config.json') as f:
        return json.load(f)


def connect_to_db():
    data_config = load_config()
    try:
        engine = create_engine(data_config["URL_DATABASE"])
        session = Session(engine)
    except ConnectionError as e:
        print('Connection failure', e)
    return session

