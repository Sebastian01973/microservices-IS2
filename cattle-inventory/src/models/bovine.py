from sqlalchemy import Column
from sqlalchemy.dialects.mysql import CHAR, ENUM, VARCHAR, DATE
from sqlalchemy.sql import func
from models.typeMark import TypeMark
from models.bovineType import BovineType
from sqlalchemy.orm import declarative_base


BaseModel = declarative_base()


class Bovine(BaseModel):
    __tablename__ = "bovine"
    id = Column(CHAR(36), primary_key=True, default=func.uuid())
    breed = Column(VARCHAR(20))
    purchase_date = Column(DATE)
    bovine_type = Column(ENUM(BovineType))
    number_mark = Column(VARCHAR(15))
    type_mark = Column(ENUM(TypeMark), nullable=False)

    def to_dict(self):
        bovine_dict = {}
        for c in self.__table__.columns:
            bovine_dict[c.name] = getattr(self, c.name)
        bovine_dict['bovine_type'] = self.bovine_type.value
        bovine_dict['type_mark'] = self.type_mark.value
        return bovine_dict

