var _show = {
    now: " ",
    history: " "
}
var calc = []
var isNumber = false,
    isResult = false

var api_use = false

var show = new Proxy(_show, {
    set(obj, name, value) {
        obj[name] = ''+value
            //console.log(name + ",  " + value)
        render()
    }
})


//fetch('/api/test').then(request => { console.log(request) })

var render = function() {
    // let length = show.history.length
    // displayer.childNodes[1].innerHTML = length > 50 ? show.history.substr(length - 50, length) : show.history
    // displayer.childNodes[3].innerHTML = show.now
    $("#show").text(show.now);
    length = show.history.length;
    $("#history").text(show.history.substring(length - 50, length));
}

//实现事件委托
$('.buttons').on('click', 'button', function() {
    let target = $(this)
    switch (target.attr('func')) {
        case "num":
            putInNumber(target.attr('num'))
            break
        case "op":
            putInOperater(target.attr('char'), target.attr('op'))
            break
        case "tri":
            triangular(target.attr('op'))
            break
        case "pow2":
            pow2()
            break
        case "pow10":
            powerBy10()
            break
        case "sqrt":
            sqrt()
            break
        case "log":
            buttonLog()
            break
        case "C":
            buttonC()
            break
        case "CE":
            buttonCE()
            break
        case "Delete":
            buttonDelete()
            break
        case "factorial":
            factorial()
            break
        case "Br":
            putInBracket(target.attr("char"))
            break
        case "result":
            getResult()
            break
        case "rev":
            getReverse()
            break
    }
})

var putInNumber = function(char) {
    if (isResult) {
        show.history = ''
    }
    if (!isNumber || isResult) {
        show.now = ''
        isNumber = true
        isResult = false
    }
    if (char == 'PI') {
        show.now = String(Math.PI)
    } else if (char == 'EXP') {
        show.now = String(Math.E)
    } else {
        show.now += char;
    }
}

var putInBracket = function(bracket) {
    if (!isNumber && bracket == '(') {
        show.history += bracket
        calc.push(bracket)
    } else if (isNumber && bracket == ')') {
        show.history += show.now + bracket
        calc.push(show.now, bracket)
        show.now = ''
    }
}

var putInOperater = function(char, operater) {
    if (isResult) {
        show.history = show.now + char
        calc.push(show.now, operater == undefined ? char : operater)
        isResult = false
        isNumber = false
    } else if (isNumber) {
        show.history += show.now + char
        calc.push(show.now, operater == undefined ? char : operater)
        isNumber = false
    } else {
        //alert("错误操作！")
    }
}

var getResult = function() {
    if (!isResult && isNumber && !api_use) {
        calc.push(show.now)
        api_use = true
            //求结果
        fetch('/api/handle', {
                method: 'POST',
                body: JSON.stringify(calc),
                headers: new Headers({
                    'Content-type': 'application/json'
                })
            }).then(response => response.json())
            .then(result => {
                console.log(result)
                show.history += show.now + '='
                show.now = result
                calc = []
                isResult = true
            })
            .finally(() => api_use = false)
    }
}

var cleanHistory = function() {
    if (isResult) {
        show.history = ''
        isResult = false
    }
}

var pow2 = function() {
    if (isNumber && !api_use) {
        cleanHistory()
        //show.now = String(Number.parseFloat(show.now) ** 2)
        api_use = true
        fetch('/api/pow',{
            method: 'POST',
            body: JSON.stringify({
                num: show.now,
                pow: 2,
                headers: {
                    'Content-type': 'application/json'
                }
            })
        }).then(response => {
            if (response.ok) {
                console.log(response)
                return response.json()
            } else {
                throw new Error('网络错误！')
            }
        }).then(response => {
            show.now = response.result
        })
        .catch(error => alert(error))
        .finally(() => api_use = false)
    }
}

var buttonC = function() {
    show.now = ''
    show.history = ''
    calc = []
    isNumber = false
    isResult = false
}
var buttonCE = function() {
    show.now = ''
}

var buttonDelete = function() {
    show.now = show.now.substring(0, show.now.length - 1)
    if (show.now.length == 0) {
        isNumber = false
    }
}

var powerBy10 = function() {
    if (isNumber) {
        cleanHistory()
        //show.now = String(10 ** Number.parseFloat(show.now))
        api_use = true
        fetch('/api/pow',{
            method: 'POST',
            body: JSON.stringify({
                num: 10,
                pow: show.now,
                headers: {
                    'Content-type': 'application/json'
                }
            })
        }).then(response => {
            if (response.ok) {
                console.log(response)
                return response.json()
            } else {
                throw new Error('网络错误！')
            }
        }).then(response => {
            show.now = response.result
        })
        .catch(error => alert(error))
        .finally(() => api_use = false)
    }

}
var buttonLog = function() {
    if (isNumber && !api_use) {
        cleanHistory()
        //show.now = String(Math.log10(Number.parseFloat(show.now)))
        api_use = true
        fetch('/api/log',{
            method: 'POST',
            body: JSON.stringify({
                num: show.now,
                headers: {
                    'Content-type': 'application/json'
                }
            })
        }).then(response => {
            if (response.ok) {
                console.log(response)
                return response.json()
            } else {
                throw new Error('网络错误！')
            }
        }).then(response => {
            show.now = response.result
        })
        .catch(error => alert(error))
        .finally(() => api_use = false)
    }
}
var getReverse = function() {
    if (isNumber) {
        cleanHistory()
        // show.now = String(0 - Number.parseFloat(show.now))
        api_use = true
        fetch('/api/rev',{
            method: 'POST',
            body: JSON.stringify({
                num: show.now,
                headers: {
                    'Content-type': 'application/json'
                }
            })
        }).then(response => {
            if (response.ok) {
                console.log(response)
                return response.json()
            } else {
                throw new Error('网络错误！')
            }
        }).then(response => {
            show.now = response.result
        })
        .catch(error => alert(error))
        .finally(() => api_use = false)
    }
}

var factorial = function() {
    if (isNumber&&!api_use) {
        if (Number.isInteger(Number.parseFloat(show.now))) {
            const getFact = n => {
                return n == 0 ? 1 : n * getFact(n - 1)
            }
            cleanHistory()
            api_use = true
            fetch('/api/factorial',{
                method: 'POST',
                body: JSON.stringify({
                    num: show.now,
                    headers: {
                        'Content-type': 'application/json'
                    }
                })
            }).then(response => {
                if (response.ok) {
                    console.log(response)
                    return response.json()
                } else {
                    throw new Error('网络错误！')
                }
            }).then(response => {
                show.now = response.result
            })
            .catch(error => alert(error))
            .finally(() => api_use = false)
        } else {
            alert("暂时无法对浮点数求阶乘！")
        }
    }
}
var triangular = function(type) {
    if (isNumber) {
        cleanHistory()
        api_use = true
            //show.now = String(eval(`Math.${type}(${Number.parseFloat(show.now)})`))
        fetch('/api/triangle', {
                method: 'POST',
                body: JSON.stringify({
                    type: type,
                    num: show.now
                }),
                headers: new Headers({
                    'Content-type': 'application/json'
                })
            }).then(response => {
                if (response.ok) {
                    console.log(response)
                    return response.json()
                } else {
                    throw new Error('网络错误！')
                }
            }).then(response => {
                if (response.state) {
                    alert(response.state)
                } else {
                    show.now = response.result
                }
            })
            .catch(error => alert(error))
            .finally(() => api_use = false)
    }
}
var sqrt = function() {
    if (isNumber && !api_use) {
        cleanHistory()
        api_use = true
        //show.now = String(Math.sqrt(Number.parseFloat(show.now)))
        fetch('/api/sqrt', {
            method: 'POST',
            body: JSON.stringify({
                num: show.now
            }),
            headers: {
                'Content-type': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                console.log(response)
                return response.json()
            } else {
                throw new Error('网络错误！')
            }
        }).then(response=>{
            show.now = response.result
        }).finally(()=> api_use = false)
    }
}