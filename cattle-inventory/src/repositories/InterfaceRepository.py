from typing import get_args, Generic, TypeVar
import database.database as dbase
from sqlalchemy import MetaData, update, delete, insert

T = TypeVar('T')


class RepositoryInterface(Generic[T]):
    def __init__(self):
        self.session = dbase.connect_to_db()
        self.table = get_args(self.__orig_bases__[0])[0]
        self.metaData = MetaData()

    def create(self, data):
        self.session.execute(insert(self.table).values(data))
        self.session.commit()

    def get_all(self):
        return self.session.query(self.table).all()

    def get_list(self, id):
        return self.session.query(self.table).filter(self.table.bovine_id == id).all()

    def get_by_id(self, id):
        return self.session.query(self.table).get(id)

    def update(self, id, data):
        self.session.execute(update(self.table).where(self.table.id == id).values(data))
        self.session.commit()

    def delete(self, id):
        self.session.execute(delete(self.table).where(self.table.id == id))
        self.session.commit()
