import datetime
import os
from typing import re

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


@app.route('/')
def index():
    return jsonify({"Running Server ": True})


@app.route('/login', methods=['POST'])
def create_token():
    data = request.get_json()
    headers = {"Content-Type": "application/json; charset=utf-8"}
    url = url_backend_security + 'users/validate'
    print(url)
    response = requests.post(url, json=data, headers=headers)
    if response.status_code == 200:
        user = response.json()
        expires = datetime.timedelta(seconds=60 * 60 * 24)
        access_token = create_access_token(identity=user, expires_delta=expires)
        return jsonify({"token": access_token, "user_id": user["id"]})
    else:
        return jsonify({"msg": "Bad username or password"}), 401


@app.before_request
def before_request_callback():
    end_point = clean_url(request.path)
    excluded_routes = ["/login"]
    if excluded_routes.__contains__(request.path):
        pass
    elif verify_jwt_in_request():
        user = get_jwt_identity()
        if user["rol"] is not None:
            has_permission = validate_permission(end_point, request.method, user["rol"]["_id"])
            if not has_permission:
                return jsonify({"message": "Permission denied"}), 401
        else:
            return jsonify({"message": "Permission denied"}), 401


def clean_url(url):
    parts = url.split("/")
    for laParte in parts:
        if re.search('\\d', laParte):
            url = url.replace(laParte, "?")
    return url


def validate_permission(end_point, methods, id_rol):
    url = url_backend_security + "/permisos-roles/validar-permiso/rol/" + str(id_rol)
    has_permission = False
    headers = {"Content-Type": "application/json; charset=utf-8"}
    body = {
        "url": end_point,
        "metodo": methods
    }
    response = requests.get(url, json=body, headers=headers)
    try:
        data = response.json()
        if "id" in data:
            has_permission = True
    except Exception as e:
        print(e)
    return has_permission


if __name__ == '__main__':
    app.run(debug=True, port=os.getenv("PORT", default=5000))
