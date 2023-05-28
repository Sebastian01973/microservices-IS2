from flask import Blueprint, request, jsonify
from services.bovineService import BovineService

bovineService = BovineService()

bovine = Blueprint('bovine', __name__)


@bovine.route('/bovine', methods=['GET'])
def get_all_bovines():
    bovines = bovineService.get_all_bovines()
    return jsonify(bovines), 200


@bovine.route('/bovine/<id>', methods=['GET'])
def get_bovine_by_id(id):
    bovine = bovineService.get_bovine_by_id(id)
    return jsonify(bovine), 200


@bovine.route('/bovine', methods=['POST'])
def create_bovine():
    bovine_data = request.get_json()
    created_bovine = bovineService.create_bovine(bovine_data)
    return jsonify(created_bovine), 201


@bovine.route('/bovine/<id>', methods=['DELETE'])
def delete_bovine(id):
    try:
        data = bovineService.delete_bovine(id)
        if data["deleted_count"] == 0:
            return jsonify({"message": "bovine not found"}), 404
        return jsonify({"message": "bovine Delete"}), 204
    except Exception as e:
        return jsonify({"error": e.args}), 500


@bovine.route('/bovine/<id>', methods=['PUT'])
def update_bovine(id):
    bovine_data = request.get_json()
    print(bovine_data)
    bovine_updated = bovineService.update_bovine(id, bovine_data)
    if bovine_updated["updated_count"] == 0:
        return jsonify({"message": "bovine not found"}), 404
    return jsonify({"message": "bovine Updated"}), 200


@bovine.route('/bovine/<id_bovine>/vaccine/<id_vaccine>', methods=['PUT'])
def assign_vaccine(id_bovine, id_vaccine):
    date = request.get_json()
    bovine = bovineService.asssign_vaccine(id_bovine, id_vaccine, date['date'])
    return jsonify(bovine), 200
