var manager = (function(){
    var $tbody = $('#tbody');
    var $stuId = $("#studentId");
    var $math = $("#dataStructure");
    var $english = $("#java");
    var $saveBtn = $('#wq-save-score');
    var $studentName =$("#wq-name");
    var $studentMark = $("#wq-mark");
    // 定义全局数据
    var allData = [];
    return {
        init: function() {
            // 添加事件绑定
            this.events();
            // 获取数据
            // this.getData();
        },
        // 插入数据
        insertData(data) {
            // 清空原有数据
            $tbody.html('')
            var frag = document.createDocumentFragment();
            for(var i =0 ; i < data.length; i++) {
                var tr = document.createElement('tr');
                // 循环对象用for  in
                for(var t in  data[i]) {
                    var td = document.createElement('td');
                    td.innerHTML = data[i][t];
                    tr.appendChild(td);
                }
                //  补一个操作栏td
                var td = document.createElement('td');
                td.innerHTML = '<button class="btn btn-danger">删除</button>'+
                                '<button class="btn btn-warning">修改</button>'
                tr.appendChild(td);
                frag.appendChild(tr);
            }
            $tbody.append(frag);
        },
        // 添加一条数据
        addData(data) {
            // 通过返回添加的一天新数据， 插入到所有数据中
            allData.push(data);
            // 遍历所有数据，插入dom元素
            this.insertData(allData);
        },
        upData() {

        },
        delData(id, tr) {
            var params = {
                stuId: id
            }
            $.ajax({
                type:'get',
                data:params,
                url:"./delete",
                success:function (result) {
                    if(result.trim()=="success"){
                        checkType();
                    }else {
                        console.log(result);
                    }
                },
                error:function () {
                    alert("失败");
                }
            })
        },
        // 所有事件定义在该方法里
        events: function() {
            var _this = this;
            $tbody.on('click', '.btn-danger', function() {
                // this  获取的是按钮
                // 获取tr
                var tr = $(this).closest('tr');
                // console.log(tr);
                // 获取id
                var id = tr.find("td").eq(1).html();
                // console.log(id);
                // 获取id后发送ajax
                _this.delData(id, tr);
            })
            // 修改按钮
            $tbody.on('click', '.btn-warning', function() {
                // 把修改按钮转换成确定按钮
                // jq链式调用
                // 改变属性
                $(this).html('确定').attr('class', 'btn btn-success');
                // 找到当前行
                var tr = $(this).closest('tr');
                // 同过tr找td
                var tdAll = tr.find("td");
                for(let i = 2; i < tdAll.length -4; i++) {
                    var val = tdAll.eq(i).html();
                    tdAll.eq(i).html(`<input type="text" value="${val}">`);
                }

            })
            $tbody.on('click', '.btn-success', function() {
                var _this = $(this)
                var tr = _this.closest('tr');
                // 同过tr找td
                tdAll = tr.find("td");
                var params = {
                    stuId:tdAll.eq(1).html(),
                    name: tdAll.eq(2).find("input").val(),
                    dataStructure: tdAll.eq(3).find("input").val(),
                    java:tdAll.eq(4).find("input").val()
                }
                $.ajax({
                    type:'post',
                    data:params,
                    url:"./update",
                    success:function (result) {
                        _this.html('修改').attr('class', 'btn btn-warning');
                        for(let i = 1; i < tdAll.length -1; i++) {
                            var val = tdAll.eq(i).find("input").val();
                            tdAll.eq(i).html(val);
                        }
                        if(result.trim()=="success"){

                        }else {
                            alert(result);
                        }
                        checkType();
                    },
                    error:function () {
                        alert("错误");
                    }
                })

            })
            $saveBtn.on('click', function() {
                // 获取三个文本框的值
                var params = {
                    stuId: $stuId.val(),
                    name: $studentName.val(),
                    dataStructure:$math.val(),
                    java:$english.val()
                }
                // 发送ajax
                if(params.stuId==null||params.stuId.trim()==""){
                    alert("请输入学生学号");
                }else if(params.name==null||params.name.trim()==""){
                    alert("请输入姓名");
                }else if(params.dataStructure==null||params.dataStructure.trim()==""){
                    alert("请输入数据结构成绩");
                }else if(params.java==null||params.java.trim()==""){
                    alert("请输入面向对象Java成绩");
                }else {
                    document.getElementById("studentId").value="";
                    document.getElementById("wq-name").value="";
                    document.getElementById("dataStructure").value="";
                    document.getElementById("java").value="";
                    $.ajax({
                        type:"post",
                        data:params,
                        url:"./add",
                        success:function (result) {
                            console.log(result.trim())
                            if(result.trim()=="success"){
                                checkType();
                            }else {
                                alert(result);
                            }
                        },
                        error:function () {
                            alert("错误");
                        }
                    })
                }
                // 设置回调函数
            })
        }
    }
})()
manager.init();