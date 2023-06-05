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




if __name__ == '__main__':
    app.run(debug=True, port=os.getenv("PORT", default=5000))
