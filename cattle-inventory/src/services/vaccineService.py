from repositories.vaccineRepository import VaccineRepository
from models.vaccine import Vaccine


class VaccineService:

    def __init__(self):
        self.vaccineRepository = VaccineRepository()

    def get_all_vaccines(self):
        return self.vaccineRepository.find_all()

    def get_vaccine_by_id(self, id):
        vaccine = Vaccine(self.vaccineRepository.find_by_id(id))
        return vaccine.__dict__

    def create_vaccine(self, vaccine_data):
        vaccine = Vaccine(vaccine_data)
        return self.vaccineRepository.save(vaccine)

    def delete_vaccine(self, id):
        return self.vaccineRepository.delete(id)

    def update_vaccine(self, id, bovine_data):
        vaccine = Vaccine(bovine_data)
        return self.vaccineRepository.update(id, vaccine)
