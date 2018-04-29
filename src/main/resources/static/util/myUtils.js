/**
 * 该函数实质为创建一个form表单,然后再提交,!注意 后端接收的话记得在参数里边添加 BindingResult userloginResult
 * 例如(CartVO cartVO, BindingResult userloginResult) 即使没用后边的那个参数
 * 甚至像这样 (@Valid JCartVO cartVO,BindingResult userloginResult)加上注解
 * @param url 例如"localhost:8080/toIndex"
 * @param type 例如"get",支持restful风格
 * @param jsonData json字符串或者对象
 * @param listName 如果传入的是一个list,那么这个属性必须要填写,并且在后端设置一个类,里边只有这一个list,字段名为此参数名
 * @param formId form表单Id 该函数实质为创建一个form表单,然后再提交
 * @returns {HTMLFormElement}
 */

function doFormRequest(url, type, jsonData, listName, formId) {
    var jsonToInputMini = function (form, data, varKey, listName) {
        for (var key in data) {
            if (!Array.isArray(data) || varKey != null)
                var lastKey = ((varKey == null) ? key : varKey + "." + key);
            else
                var lastKey = ((varKey == null) ? listName + "[" + key + "]" : listName + "[" + varKey + "]" + "." + key);
            var value = data[key];
            if (Array.isArray(value)) for (var ArrKey in value) jsonToInputMini(form, value[ArrKey], (lastKey + "[" + ArrKey + "]"));
            else if ((typeof value) == "object") jsonToInputMini(form, value, lastKey);
            else addInput("hidden", lastKey, value, form);
        }
    }
    /**
     * name,value必填 type默认为hidden form可填可不填
     * @param type
     * @param name
     * @param value
     * @param form
     * @returns {HTMLInputElement}
     */
    var addInput = function (type, name, value, form) {
        var input = document.createElement("input");
        input.type = ((type == null) ? "hidden" : type);
        input.name = name;
        input.value = value;
        form == null ? null : form.appendChild(input);
        return input;
    }
    var data = (typeof jsonData) == "string" ? eval("(" + jsonData + ")") : jsonData;
    var form = document.createElement("form");
    form.id = formId;
    form.action = url;
    type = type.toLocaleLowerCase();

    if (type == "get") {
        form.method = type;
    } else {
        form.method = "post";
        if (type != "post")
            addInput("hidden", "_method", ((type == "delete") ? "delete" : "put"), form);
    }

    jsonToInputMini(form, data, null,listName);
    document.body.appendChild(form);
    form.submit();
    // console.log(form);
    return form;
}