from sanic import Sanic
from sanic import response
import math

app = Sanic("App.name")


@app.route("/test")
async def test(request):
    return response.json({"message": "hello"})


@app.route("/triangle", methods=['POST'])
async def triangle(request):
    tri = request.json['type']
    num = float(request.json['num'])
    if tri == 'sin':
        return response.json({"result": math.sin(num), "message": 'success'})
    elif tri == 'cos':
        return response.json({"result": math.cos(num)})
    elif tri == 'tan':
        return response.json({"result": math.tan(num)})
    else:
        return response.json({"state": "error: No such function"})


@app.route("/sqrt", methods=['POST'])
async def sqrt(request):
    return response.json({"result": math.sqrt(float(request.json['num']))})


@app.route('/pow', methods=['POST'])
async def power(request):
    num = float(request.json['num'])
    pow = float(request.json['pow'])
    result = math.pow(num, pow)
    return response.json({"result": result})


@app.route('/log', methods=['POST'])
async def log(request):
    num = float(request.json['num'])
    return response.json({"result": math.log(num, 10)})


@app.route('/rev', methods=['POST'])
async def reverse(request):
    num = float(request.json['num'])
    return response.json({"result": 0 - num})


@app.route('/factorial', methods=['POST'])
async def factorial(request):
    num = int(request.json['num'])
    return response.json({"result": math.factorial(num)})


@app.route("/handle", methods=['POST'])
async def handle(request):
    result = eval(''.join(request.json))
    return response.json(result)


if __name__ == "__main__":
    app.run(host="127.0.0.1", port=8080)
