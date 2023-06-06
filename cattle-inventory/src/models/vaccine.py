from sqlalchemy import Column
from sqlalchemy.dialects.mysql import CHAR, VARCHAR, INTEGER
from sqlalchemy.sql import func
from sqlalchemy.orm import declarative_base


BaseModel = declarative_base()

class Vaccine(BaseModel):
    __tablename__ = 'vaccine'
    id = Column(CHAR(36), primary_key=True, default=func.uuid())
    name = Column(VARCHAR(30))
    laboratory = Column(VARCHAR(30))
    batch = Column(VARCHAR(30))
    amount_dose = Column(INTEGER)

    def to_dict(self):
        return {c.name: getattr(self, c.name) for c in self.__table__.columns}
