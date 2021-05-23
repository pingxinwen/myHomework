var _show = {
    now: " ",
    history: " "
}
var calc = []
var isNumber = false,
    isResult = false

var show = new Proxy(_show, {
    set(obj, name, value) {
        obj[name] = value
            //console.log(name + ",  " + value)
        render()
    }
})

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
    if (!isResult && isNumber) {
        calc.push(show.now)
        let result = eval(calc.join(''))
        console.log("结果为：" + result)
        show.history += show.now + '='
        show.now = result
        calc = []
        isResult = true
    }
}

var cleanHistory = function() {
    if (isResult) {
        show.history = ''
        isResult = false
    }
}

var pow2 = function() {
    if (isNumber) {
        cleanHistory()
        show.now = String(Number.parseFloat(show.now) ** 2)
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
        show.now = String(10 ** Number.parseFloat(show.now))
    }
}
var buttonLog = function() {
    if (isNumber) {
        cleanHistory()
        show.now = String(Math.log10(Number.parseFloat(show.now)))
    }
}
var getReverse = function() {
    if (isNumber) {
        cleanHistory()
        show.now = String(0 - Number.parseFloat(show.now))
    }
}

var factorial = function() {
    if (isNumber) {
        if (Number.isInteger(Number.parseFloat(show.now))) {
            const getFact = n => {
                return n == 0 ? 1 : n * getFact(n - 1)
            }
            cleanHistory()
            show.now = String(getFact(Number.parseInt(show.now)))
        } else {
            alert("暂时无法对浮点数求阶乘！")
        }
    }
}
var triangular = function(type) {
    if (isNumber) {
        cleanHistory()
        show.now = String(eval(`Math.${type}(${Number.parseFloat(show.now)})`))
    }
}
var sqrt = function() {
    if (isNumber) {
        cleanHistory()
        show.now = String(Math.sqrt(Number.parseFloat(show.now)))
    }
}