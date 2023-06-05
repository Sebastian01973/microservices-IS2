from repositories.vaccineRepository import VaccineRepository


class VaccineService:

    def __init__(self):
        self.vaccineRepository = VaccineRepository()

    def get_all_vaccines(self):
        vaccines = self.vaccineRepository.get_all()
        vaccines = [vaccine.to_dict() for vaccine in vaccines]
        return vaccines

    def get_vaccine_by_id(self, id):
        vaccine = self.vaccineRepository.get_by_id(id)
        if not vaccine:
            raise Exception("Vaccine not found")
        return vaccine.to_dict()

    def create_vaccine(self, vaccine_data):
        return self.vaccineRepository.create(vaccine_data)

    def delete_vaccine(self, id):
        return self.vaccineRepository.delete(id)

    def update_vaccine(self, id, vaccine_data):
        print(vaccine_data)
        vaccine = self.vaccineRepository.get_by_id(id)
        if vaccine is None or len(vaccine_data) < len(vaccine.to_dict()):
            raise Exception('Missing parameters or vaccine not found')
        self.vaccineRepository.update(id, vaccine_data)
