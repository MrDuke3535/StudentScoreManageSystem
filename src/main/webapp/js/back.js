function checkUserName(){
    var username = document.getElementById("account").value;
    if(username==null||username===""){
        document.getElementById("userNameAlarm").innerHTML="用户名不能为空";
        return false;
    }else{
        document.getElementById("userNameAlarm").innerHTML="";
        return true;
    }
}
function checkPassword(){
    var password = document.getElementById("password").value;
    if(password==null||password==""){
        document.getElementById("passWordAlarm").innerHTML="密码不能为空";
        return false;
    }else{
        document.getElementById("passWordAlarm").innerHTML="";
        return true;
    }
}
function login() {
    if(checkUserName()&&checkPassword()){
        $.ajax({
            type:"post",
            url:"./checkAccount",
            data:$('#form-test').serialize(),
            success:function (result) {
                if(result=="success"){
                    window.location.href="./manage";
                }else{
                    document.getElementById("checkAccountAlarm").innerHTML="用户名或密码错误";
                }
            },
            error:function () {
                alert("失败");
            }
        })
    }
}
function logout() {
    $.ajax({
        type:"get",
        url:"./logout",
        success:function (result) {
            if(result=="success"){
                window.location.href="./login";
            }
        },
        error:function () {
            alert("出现错误");
        }
    })
}
function keyEntry() {
    if(event.keyCode==13){
        document.getElementById("btn").click();
    }
}
function loadScore() {
    $.ajax({
        type:'get',
        url:'./score',
        success:function (result) {
            document.getElementById("tbody").innerHTML="";
            scoreFormat(result)
        },
        error:function () {
            alert("导入数据错误");
        }
    })
}

function scoreFormat(result) {
    var obj = JSON.parse(result)
    for(var i=0;i<obj.length;i++){
        var message= '<td>'+(i+1)+'</td>\n' +
            '<td>'+obj[i].id+'</td>\n' +
            '<td>'+obj[i].name+'</td>\n' +
            '<td>'+obj[i].dataStructure+'</td>\n' +
            '<td>'+obj[i].java+'</td>\n' +
            '<td>'+(obj[i].dataStructure+obj[i].java)+'</td>\n' +
            '<td>'+(obj[i].dataStructure+obj[i].java)/2+'</td>\n' +
            '<td>'+obj[i].rank+'</td>\n' +
            '<td>\n' +
            '<button class="btn btn-danger">删除</button>\n' +
            '<button class="btn btn-warning">修改</button>\n' +
            '</td>\n';
        var tr = document.createElement("tr");
        tr.className="text-center middle";
        tr.innerHTML=message;
        document.getElementById("tbody").appendChild(tr);
    }
}

function search() {
    var keyWord = document.getElementById("search").value;
    var all = ["idType","nameType","dataType","javaType","sumType","avgType","rankType"];
    var name = "default";
    var type = "default";
    for(var i=0;i<all.length;i++){
        var value = document.getElementById(all[i]).value;
        if(value.trim()!="default"){
            name=all[i];
            type=value;
            break;
        }
    }
    var params ={
        'keyWord':keyWord,
        'name':name,
        'type':type
    }
    if(params.keyWord==null||params.keyWord.trim()==""){
        var flag=true;
        for(var i=0;i<all.length;i++){
            var value = document.getElementById(all[i]).value;
            if(value!="default"){
                flag=false;
                getScoreDataByType(all[i]);
                break;
            }
        }
        if(flag){
            loadScore();
        }
    }else{
        $.ajax({
            type:'get',
            data:params,
            url:"./search",
            success:function (result) {
                if(result.trim()!="error"){
                    document.getElementById("tbody").innerHTML="";
                    scoreFormat(result)
                }
            },
            error:function () {
                alert("错误");
            }
        })
    }
}
function keyEntry2() {
    if(event.keyCode==13){
        document.getElementById("searchBtn").click();
    }
}

function stuIdChange() {
    getScoreDataByType("idType");
}
function nameChange() {
    getScoreDataByType("nameType");
}
function dataChange() {
    getScoreDataByType("dataType")
}
function javaChange() {
    getScoreDataByType("javaType")
}
function sumChange() {
    getScoreDataByType("sumType")
}
function avgChange() {
    getScoreDataByType("avgType")
}
function rankChange() {
    getScoreDataByType("rankType")
}

function checkType() {
    var all = ["idType","nameType","dataType","javaType","sumType","avgType","rankType"];
    var flag=true;
    for(var i=0;i<all.length;i++){
        var value = document.getElementById(all[i]).value;
        if(value.trim()!="default"){
            flag=false;
            getScoreDataByType(all[i]);
        }
    }
    if(flag){
        loadScore();
    }
}

function getScoreDataByType(name) {
    var all = ["idType","nameType","dataType","javaType","sumType","avgType","rankType"];
    for(var i=0;i<all.length;i++){
        if(all[i]!=name){
            document.getElementById(all[i]).value="default";
        }
    }
    var type = document.getElementById(name).value;
    var keyWord = document.getElementById("search").value;
    if(keyWord!=null&&keyWord.trim()!=""){
        search();
    }else {
        var params={
            'name':name,
            'type':type
        }
        $.ajax({
            type:'get',
            data:params,
            url:'./sortedscore',
            success:function (result) {
                document.getElementById("tbody").innerHTML="";
                scoreFormat(result)
            },
            error:function () {
                alert("错误");
            }
        })
    }

}