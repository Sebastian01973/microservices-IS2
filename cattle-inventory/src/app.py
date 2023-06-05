from flask import Flask, request, jsonify
from flask_cors import CORS
from config import config
from routes import bovineRoute, vaccineRoute


def create_app(debug=False):
    app = Flask(__name__, instance_relative_config=True)
    app.config.from_object(config["development"])
    app.debug = debug

    app.register_blueprint(bovineRoute.bovine)
    app.register_blueprint(vaccineRoute.vaccine)

    cors = CORS()
    cors.init_app(app)
    return app


app = create_app(debug=True)


@app.route("/", methods=["GET"])
def test():
    json = {}
    json["message"] = "Server running ..."
    return jsonify(json)


if __name__ == '__main__':
    app.run(port=5000)
