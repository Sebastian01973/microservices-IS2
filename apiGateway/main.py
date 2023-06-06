import datetime
import os
import re

import requests
from flask import Flask, jsonify, request
from flask.cli import load_dotenv
from flask_cors import CORS
from flask_jwt_extended import create_access_token, JWTManager, verify_jwt_in_request, get_jwt_identity

load_dotenv()

app = Flask(__name__)
CORS(app)
app.config["JWT_SECRET_KEY"] = "super-secret"
jwt = JWTManager(app)

url_backend_security = os.getenv("URL_BACKEND_SECURITY")
url_backend_bovines = os.getenv("URL_BACKEND_BOVINES")

headers = {"Content-Type": "application/json; charset=utf-8"}


def clean_url(url):
    parts = request.path.split("/")
    for part in parts:
        if re.search('\\d',part):
            url = url.replace(part,"?")
    return url


def validate_permission(route,method,role_id):
    url = url_backend_security + "rol-permission/validate/" + str(role_id)
    isPermission = False
    body = {
        "url":route,
        "method": method
    }
    response = requests.get(url,json=body,headers=headers)
    try:
        data = response.json()
        if "id" in data:
            isPermission = True
    except:
        pass
    return isPermission


@app.before_request
def before_request_callback():
    endPoint = clean_url(request.path)
    excludeRoutes = ['/login']
    if excludeRoutes.__contains__(request.path):        
        pass
    elif verify_jwt_in_request():
        user = get_jwt_identity()
        if user["rol"] is not None:
            havePermission = validate_permission(endPoint,request.method,user["rol"]["id"])
            if not havePermission:
                return jsonify({"message": "Permission denied"}),401
        else:
            return jsonify({"message": "Permission denied"}),401


# ------------------------- Endpoints ------------------------------- #

@app.route('/')
def index():
    return jsonify({"Running Server ": True})


@app.route('/login', methods=['POST'])
def create_token():
    data = request.get_json()
    url = url_backend_security + 'users/validate'
    # print(url)
    response = requests.post(url, json=data, headers=headers)
    if response.status_code == 200:
        user = response.json()
        expires = datetime.timedelta(seconds=60 * 60 * 24)
        access_token = create_access_token(identity=user, expires_delta=expires)
        return jsonify({"token": access_token, "user_id": user["id"]})
    else:
        return jsonify({"msg": "Bad username or password"}), 401


@app.route('/bovine', methods=['GET'])
def get_all_bovines():
    url = url_backend_bovines + 'bovine'
    response = requests.get(url, headers=headers)
    return jsonify(response.json())


@app.route('/bovine', methods=['POST'])
def create_bovine():
    url = url_backend_bovines + 'bovine'
    data = request.get_json()
    response = requests.post(url, headers=headers, json=data)
    json = response.json()
    return jsonify(json)


@app.route('/bovine/<id>', methods=['GET'])
def get_bovine_by_id(id):
    url = url_backend_bovines + 'bovine/'+id
    response = requests.get(url, headers=headers)
    json = response.json()
    return jsonify(json)


@app.route('/bovine/<id>', methods=['DELETE'])
def delete_bovine(id):
    url = url_backend_bovines + 'bovine/'+id
    response = requests.delete(url, headers=headers)
    #json = response.json()
    return jsonify(), response.status_code


@app.route('/bovine/<id>', methods=['PUT'])
def update_bovine(id):
    data = request.get_json()
    url = url_backend_bovines + 'bovine/'+id
    response = requests.put(url, headers=headers, json=data)
    json = response.json()
    return jsonify(json)


@app.route('/bovine/<id_bovine>/vaccine/<id_vaccine>', methods=['PUT'])
def assign_vaccine(id_bovine, id_vaccine):
    data = request.get_json()
    url = url_backend_bovines + 'bovine/'+id_bovine + '/vaccine/' + id_vaccine
    response = requests.put(url, headers=headers, json=data)
    json = response.json()
    return jsonify(json)


@app.route('/bovine/vaccines/<id_bovine>', methods=['GET'])
def get_list_vaccines(id_bovine):
    url = url_backend_bovines + 'bovine/vaccines/'+id_bovine
    response = requests.get(url, headers=headers)
    json = response.json()
    return jsonify(json)


@app.route('/bovine/vaccines/<id_control>', methods=['DELETE'])
def delete_bovine_vaccine(id_control):
    url = url_backend_bovines + 'bovine/vaccines/' + id_control
    response = requests.delete(url, headers=headers)
    # json = response.json()
    return jsonify(), response.status_code


    ###########Vaccines############

@app.route('/vaccine/<id>', methods=['GET'])
def get_vaccine_by_id(id):
    url = url_backend_bovines + 'vaccine/'+id
    response = requests.get(url, headers=headers)
    return jsonify(response.json())

@app.route('/vaccine', methods=['GET'])
def get_all_vaccines():
    url = url_backend_bovines + 'vaccine'
    response = requests.get(url, headers=headers)
    return jsonify(response.json())

@app.route('/vaccine', methods=['POST'])
def create_vaccine():
    data = request.get_json()
    url = url_backend_bovines + '/vaccine'
    response = requests.post(url, headers=headers, json=data)
    json = response.json()
    return jsonify(json)

@app.route('/vaccine/<id>', methods=['DELETE'])
def delete_vaccine(id):
    url = url_backend_bovines + '/vaccine/' + id
    response = requests.delete(url, headers=headers)
    # json = response.json()
    return jsonify(), response.status_code

@app.route('/vaccine/<id>', methods=['PUT'])
def update_vaccine(id):
    data = request.get_json()
    url = url_backend_bovines + '/vaccine/' + id
    response = requests.put(url, headers=headers, json=data)
    json = response.json()
    return jsonify(json)



if __name__ == '__main__':
    app.run(debug=True, port=os.getenv("PORT", default=5000))
