var manager = (function(){
    var $tbody = $('#tbody');
    var $stuId = $("#studentId");
    var $math = $("#dataStructure");
    var $english = $("#java");
    var $saveBtn = $('#wq-save-score');
    var $studentName =$("#wq-name");
    var $studentMark = $("#wq-mark");
    // ����ȫ������
    var allData = [];
    return {
        init: function() {
            // ����¼���
            this.events();
            // ��ȡ����
            // this.getData();
        },
        // ��������
        insertData(data) {
            // ���ԭ������
            $tbody.html('')
            var frag = document.createDocumentFragment();
            for(var i =0 ; i < data.length; i++) {
                var tr = document.createElement('tr');
                // ѭ��������for  in
                for(var t in  data[i]) {
                    var td = document.createElement('td');
                    td.innerHTML = data[i][t];
                    tr.appendChild(td);
                }
                //  ��һ��������td
                var td = document.createElement('td');
                td.innerHTML = '<button class="btn btn-danger">ɾ��</button>'+
                                '<button class="btn btn-warning">�޸�</button>'
                tr.appendChild(td);
                frag.appendChild(tr);
            }
            $tbody.append(frag);
        },
        // ���һ������
        addData(data) {
            // ͨ��������ӵ�һ�������ݣ� ���뵽����������
            allData.push(data);
            // �����������ݣ�����domԪ��
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
                    alert("ʧ��");
                }
            })
        },
        // �����¼������ڸ÷�����
        events: function() {
            var _this = this;
            $tbody.on('click', '.btn-danger', function() {
                // this  ��ȡ���ǰ�ť
                // ��ȡtr
                var tr = $(this).closest('tr');
                // console.log(tr);
                // ��ȡid
                var id = tr.find("td").eq(1).html();
                // console.log(id);
                // ��ȡid����ajax
                _this.delData(id, tr);
            })
            // �޸İ�ť
            $tbody.on('click', '.btn-warning', function() {
                // ���޸İ�ťת����ȷ����ť
                // jq��ʽ����
                // �ı�����
                $(this).html('ȷ��').attr('class', 'btn btn-success');
                // �ҵ���ǰ��
                var tr = $(this).closest('tr');
                // ͬ��tr��td
                var tdAll = tr.find("td");
                for(let i = 2; i < tdAll.length -4; i++) {
                    var val = tdAll.eq(i).html();
                    tdAll.eq(i).html(`<input type="text" value="${val}">`);
                }

            })
            $tbody.on('click', '.btn-success', function() {
                var _this = $(this)
                var tr = _this.closest('tr');
                // ͬ��tr��td
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
                        _this.html('�޸�').attr('class', 'btn btn-warning');
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
                        alert("����");
                    }
                })

            })
            $saveBtn.on('click', function() {
                // ��ȡ�����ı����ֵ
                var params = {
                    stuId: $stuId.val(),
                    name: $studentName.val(),
                    dataStructure:$math.val(),
                    java:$english.val()
                }
                // ����ajax
                if(params.stuId==null||params.stuId.trim()==""){
                    alert("������ѧ��ѧ��");
                }else if(params.name==null||params.name.trim()==""){
                    alert("����������");
                }else if(params.dataStructure==null||params.dataStructure.trim()==""){
                    alert("���������ݽṹ�ɼ�");
                }else if(params.java==null||params.java.trim()==""){
                    alert("�������������Java�ɼ�");
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
                            alert("����");
                        }
                    })
                }
                // ���ûص�����
            })
        }
    }
})()
manager.init();