from flask import Flask, jsonify, make_response
import requests
import os
import simplejson as json

app = Flask(__name__)
import users

with open("todo.json","r") as f:
	todo_list = json.load(f)

@app.route("/",methods=['GET'])
def hello():
	''' Greet the user '''
	return "Todo service is up"

@app.route('/lists/<username>', methods=['GET'])
def user_list(username):
    ''' Returns a user oriented list '''

    if username not in todo_list:
        return "No user found"

    return jsonify(todo_list[username])

if __name__ == '__main__':
    app.run(port=5000, debug=True)

