from flask import Blueprint, request, jsonify
from services.vaccineService import VaccineService

vaccineService = VaccineService()
vaccine = Blueprint('vaccine', __name__)


@vaccine.route('/vaccine', methods=['GET'])
def get_all_vaccines():
    vaccines = vaccineService.get_all_vaccines()
    return jsonify(vaccines), 200


@vaccine.route('/vaccine/<id>', methods=['GET'])
def get_vaccine_by_id(id):
    vaccine = vaccineService.get_vaccine_by_id(id)
    return jsonify(vaccine), 200


@vaccine.route('/vaccine', methods=['POST'])
def create_vaccine():
    vaccine_data = request.get_json()
    created_vaccine = vaccineService.create_vaccine(vaccine_data)
    return jsonify(created_vaccine), 201


@vaccine.route('/vaccine/<id>', methods=['DELETE'])
def delete_vaccine(id):
    try:
        data = vaccineService.delete_vaccine(id)
        if data["deleted_count"] == 0:
            return jsonify({"message": "vaccine not found"}), 404
        return jsonify({"message": "vaccine Delete"}), 204
    except Exception as e:
        return jsonify({"error": e.args}), 500


@vaccine.route('/vaccine/<id>', methods=['PUT'])
def update_vaccine(id):
    vaccine_data = request.get_json()
    vaccine_updated = vaccineService.update_vaccine(id, vaccine_data)
    if vaccine_updated["updated_count"] == 0:
        return jsonify({"message": "vaccine not found"}), 404
    return jsonify({"message": "vaccine Updated"}), 200
