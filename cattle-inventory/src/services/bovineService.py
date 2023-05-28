from repositories.bovineRepository import BovineRepository
from repositories.vaccineRepository import VaccineRepository
from models.bovine import Bovine
from models.vaccine import Vaccine


class BovineService:

    def __init__(self):
        self.bovineRepository = BovineRepository()
        self.vaccineRepository = VaccineRepository()

    def get_all_bovines(self):
        return self.bovineRepository.find_all()

    def get_bovine_by_id(self, id):
        bovine = Bovine(self.bovineRepository.find_by_id(id))
        return bovine.__dict__

    def create_bovine(self, bovine):
        bovine = Bovine(bovine)
        return self.bovineRepository.save(bovine)

    def delete_bovine(self, id):
        return self.bovineRepository.delete(id)

    def update_bovine(self, id, bovine_data):
        bovine = Bovine(bovine_data)
        return self.bovineRepository.update(id, bovine)

    def asssign_vaccine(self, id_bovine, id_vaccine, date):
        bovine = Bovine(self.bovineRepository.find_by_id(id_bovine))
        vaccine = Vaccine(self.vaccineRepository.find_by_id(id_vaccine))
        vaccine.date = date
        bovine.vaccines.append(vaccine.__dict__)
        return self.bovineRepository.save(bovine)
