from flask import Flask, jsonify, make_response
import requests
import os
import simplejson as json

app = Flask(__name__)

with open("users.json", "r") as f:
    usr = json.load(f)

@app.route("/", methods=['GET'])
def hello():
    ''' Greet the user '''

    return "Hey! The service is up, how about doing something useful"

@app.route('/users', methods=['GET'])
def users():
    ''' Returns the list of users '''

    resp = make_response(json.dumps(usr, sort_keys=True, indent=4))
    resp.headers['Content-Type']="application/json"
    return resp

@app.route('/users/<username>', methods=['GET'])
def user_data(username):
    ''' Returns info about a specific user '''

    if username not in usr:
        return "Not found"

    return jsonify(usr[username])


with open("todo.json","r") as f:
    todo_list = json.load(f)

@app.route('/users/lists/<username>', methods=['GET'])
def user_list(username):
    ''' Returns a user oriented list '''

    if username not in todo_list:
        return "No user found"

    return jsonify(todo_list[username])

if __name__ == '__main__':
    app.run(port=5001, debug=True)