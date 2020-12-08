function isempty(item, str) {
    if (item == null || item === "") {
        alert("没有输入" + str + "！");
        return false;
    } else {
        return true;
    }
}

function getdata(){
    var data = $('#listForm').serialize();
    data = decodeURIComponent(data);

    //反序列化为对象
    var item = {"id":0};
    data.split('&').forEach(function (param) {
        param = param.split('=');
        var name = param[0], val = param[1];
        Object.defineProperty(item,name,{
            value:val,
            writable : true,
            enumerable : true,
            configurable : true
        });
    });
    return item;
}