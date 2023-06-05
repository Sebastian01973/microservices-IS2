from sqlalchemy import Column, ForeignKey, func
from sqlalchemy.dialects.mysql import CHAR, VARCHAR, DATE, FLOAT
from sqlalchemy.orm import declarative_base

BaseModel = declarative_base()


class ControlVaccine(BaseModel):
    __tablename__ = 'control_vaccine'
    id = Column(CHAR(36), primary_key=True, default=func.uuid())
    bovine_id = Column(CHAR(36), ForeignKey("bovine.id", ondelete='CASCADE'))
    vaccine_id = Column(CHAR(36), ForeignKey("vaccine.id", ondelete='CASCADE'))
    doses = Column(FLOAT)
    veterinary_name = Column(VARCHAR(20))
    application_date = Column(DATE)

    def to_dict(self):
        return {c.name: getattr(self, c.name) for c in self.__table__.columns}
