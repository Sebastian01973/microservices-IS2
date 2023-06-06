from flask import Blueprint, request, jsonify
from services.vaccineService import VaccineService

vaccineService = VaccineService()
vaccine = Blueprint('vaccine', __name__)


@vaccine.route('/vaccine', methods=['GET'])
def get_all_vaccines():
    try:
        vaccines = vaccineService.get_all_vaccines()
    except Exception as e:
        return jsonify({'Message':str(e)}), 500
    return jsonify(vaccines), 200


@vaccine.route('/vaccine/<id>', methods=['GET'])
def get_vaccine_by_id(id):
    try:
        vaccine = vaccineService.get_vaccine_by_id(id)
    except Exception as e:
        return jsonify({'Message':str(e)}), 400
    return jsonify(vaccine), 200


@vaccine.route('/vaccine', methods=['POST'])
def create_vaccine():
    try:
        vaccine_data = request.get_json()
        created_vaccine = vaccineService.create_vaccine(vaccine_data)
    except Exception as e:
        return jsonify({'Message' : str(e)}), 400
    return jsonify({'Message':'Vaccine created'}), 201


@vaccine.route('/vaccine/<id>', methods=['DELETE'])
def delete_vaccine(id):
    try:
        vaccineService.delete_vaccine(id)
        return jsonify({"message": "vaccine Delete"}), 204
    except Exception as e:
        return jsonify({"error": e.args}), 500


@vaccine.route('/vaccine/<id>', methods=['PUT'])
def update_vaccine(id):
    try:
        vaccine_data = request.get_json()
        vaccineService.update_vaccine(id, vaccine_data)
    except Exception as e:
        return jsonify({"message": str(e)}), 404
    return jsonify({"message": "vaccine Updated"}), 200
