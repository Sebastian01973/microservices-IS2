from repositories.bovineRepository import BovineRepository
from repositories.vaccineRepository import VaccineRepository
from repositories.controlVaccineRepository import ControlVaccineRepository
from models import bovineType, typeMark
from datetime import date


class BovineService:

    def __init__(self):
        self.bovine_repository = BovineRepository()
        self.vaccine_repository = VaccineRepository()
        self.control_vaccine_repository = ControlVaccineRepository()

    def get_all_bovines(self):
        bovines = self.bovine_repository.get_all()
        serialized_bovines = [bovine.to_dict() for bovine in bovines]
        return serialized_bovines

    def get_bovine_by_id(self, id):
        bovine = self.bovine_repository.get_by_id(id)
        if not bovine:
            raise Exception("Bovine not found")
        return bovine.to_dict()

    def create_bovine(self, data):
        data['bovine_type'] = bovineType.get_gender(data['bovine_type'])
        data['type_mark'] = typeMark.get_type_mark(data['type_mark']).upper()
        return self.bovine_repository.create(data)

    def delete_bovine(self, id):
        return self.bovine_repository.delete(id)

    def update_bovine(self, id, data):
        return self.bovine_repository.update(id, data)

    def asssign_vaccine(self, id_bovine, id_vaccine, data):
        bovine = self.bovine_repository.get_by_id(id_bovine)
        vaccine = self.vaccine_repository.get_by_id(id_vaccine)
        if bovine is not None and vaccine is not None:
            data['bovine_id'] = id_bovine
            data['vaccine_id'] = id_vaccine
            data['application_date'] = date.today().strftime("%Y-%m-%d")
        else:
            raise Exception('Bovine or Vaccine not found')
        return self.control_vaccine_repository.create(data)

    def get_bovine_vaccines(self, id):
        bovine = self.get_bovine_by_id(id)
        if bovine is not None:
            control_vaccine = self.control_vaccine_repository.get_list(id)
            controls = [c.to_dict() for c in control_vaccine]
            return controls
        return None

    def delete_bovine_vaccine(self, id):
        return self.control_vaccine_repository.delete(id)
