<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>成绩管理</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="./js/back.js" charset="utf-8"></script>
	<style>
		.wq-title {
			padding-bottom: 40px;
		}
		.wq-title:hover {
			transition: all .5s;
			text-shadow: 0 0 5px #999;
		}
		.wq-main-table thead {
			background: rgba(0, 0, 0, .3);
			color: #333;
		}
		.wq-main-table td {
			max-height: 50px;
			word-break: break-all;
		}
		.center-vertical {
			position: relative;
			top: 50%;
			transform: translateY(-50%);
		}
		.wq-add .modal-content {
			padding: 20px;
		}
	</style>
</head>

<body onload="loadScore()">

	<div class="container-fluid">
		<h3 class="wq-title">
			<span class="logo">
				<img src="./images/cy.jpg" alt="" style="height: 80px">
			</span>
			成绩管理
            <span style="float: right;margin-top: 30px;margin-right: 10px">
                管理员:<%=request.getSession().getAttribute("user")%>,
            <a href="javascript:logout()">退出</a>
            </span>
		</h3>
        <div class="form-inline" style="margin-left: 5%;margin-bottom: 25px;margin-top: 10px;float: left">
            <label for="search" class="sr-only">请输入学号或学生姓名</label>
            <input type="text" class="form-control input-lg" onkeypress="keyEntry2()" id="search" placeholder="请输入学号或学生名">

            <button  class="btn  btn-default btn-lg" onclick="search()" id="searchBtn">查询</button>
        </div>
        <button type="button" class="btn btn-lg" data-toggle="modal" data-target=".wq-add" style="margin-right: 15px;float: right;margin-bottom: 5px;">
            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
            添加成绩
        </button>
		<!-- 添加学生成绩部分 -->
		<div class="modal fade wq-add">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<!--细节划分-->
					<h4>添加学生信息:</h4>
                    <div>
                    <div class="form-group" style="float: left">
                        <label for="studentId">学生学号</label>
                        *<input type="text" class="form-control" id="studentId" placeholder="学生学号" value="">
                    </div>
					<div class="form-group" style="float: left;margin-left: 20px">
						<label for="wq-name">学生姓名</label>
						*<input type="text" class="form-control" id="wq-name" placeholder="学生姓名" value="">
					</div>
                    </div>
                    <div>
					<div class="form-group" style="float: left;margin-left: 20px">
						<label for="dataStructure">数据结构成绩</label>
						*<input type="text" class="form-control" id="dataStructure" placeholder="数据结构成绩" value="">
					</div>
                    <div class="form-group" style="float: left;margin-left: 20px">
                        <label for="java">面向对象Java成绩</label>
                        *<input type="text" class="form-control" id="java" placeholder="面向对象Java成绩" value="">
                    </div>
                    </div>
					<%--<div class="form-group">--%>
						<%--<label for="wq-mark">添加备注</label>--%>
						<%--<textarea id="wq-mark" class="form-control" rows="2" placeholder="添加备注"></textarea>--%>
					<%--</div>--%>
					<div class="text-center">
						<button type="button" class="btn btn-success" data-dismiss="modal" id="wq-save-score">提交</button>
					</div>
				</div>
			</div>
		</div>
		<!--错题表格-->
		<div class="row">
			<table class="table table-bordered table-hover wq-main-table ">
				<!--错题项目-->
				<thead>
					<tr>
						<th class="col-md-1 text-center">编号</th>
						<th class="col-md-3 text-center">
                            <div style="margin-left: 38%">
                            <span style="float:left;margin-top: 15px">学号</span>
                        <select id="idType" onchange="stuIdChange()" class="form-control" style="width: 10%;float: left;margin-left: 5px">
                            <option value="default">默认</option>
                            <option value="asc">升序</option>
                            <option value="desc">降序</option>
                        </select>
                            </div>
                        </th>
						<th class="col-md-3 text-center">
                            <div style="margin-left: 38%">
                            <span style="float:left;margin-top: 15px">姓名</span>
                            <select id="nameType" onchange="nameChange()" class="form-control" style="width: 10%;float: left;margin-left: 5px">
                                <option value="default">默认</option>
                                <option value="asc">升序</option>
                                <option value="desc">降序</option>
                            </select>
                            </div>
                        </th>
						<th class="col-md-3 text-center">
                            <div style="margin-left: 26%">
                                <span style="float:left;margin-top: 15px">数据结构</span>
                                <select id="dataType" onchange="dataChange()" class="form-control" style="width: 10%;float: left;margin-left: 5px">
                                    <option value="default">默认</option>
                                    <option value="asc">升序</option>
                                    <option value="desc">降序</option>
                                </select>
                            </div>
                        </th>
						<th class="col-md-3 text-center">
                            <div style="margin-left: 16%">
                                <span style="float:left;margin-top: 15px">面向对象Java</span>
                                <select id="javaType" onchange="javaChange()" class="form-control" style="width: 10%;float: left;margin-left: 5px">
                                    <option value="default">默认</option>
                                    <option value="asc">升序</option>
                                    <option value="desc">降序</option>
                                </select>
                            </div>
                        </th>
                        <th class="col-md-3 text-center">
                            <div style="margin-left: 38%">
                                <span style="float:left;margin-top: 15px">总分</span>
                                <select id="sumType" onchange="sumChange()" class="form-control" style="width: 10%;float: left;margin-left: 5px">
                                    <option value="default">默认</option>
                                    <option value="asc">升序</option>
                                    <option value="desc">降序</option>
                                </select>
                            </div>
                        </th>
						<th class="col-md-3 text-center">
                            <div style="margin-left: 30%">
                                <span style="float:left;margin-top: 15px">平均分</span>
                                <select id="avgType" onchange="avgChange()" class="form-control" style="width: 10%;float: left;margin-left: 5px">
                                    <option value="default">默认</option>
                                    <option value="asc">升序</option>
                                    <option value="desc">降序</option>
                                </select>
                            </div>
                        </th>
                        <th class="col-md-3 text-center">
                            <div style="margin-left: 38%">
                                <span style="float:left;margin-top: 15px">排名</span>
                                <select id="rankType" onchange="rankChange()" class="form-control" style="width: 10%;float: left;margin-left: 5px">
                                    <option value="default">默认</option>
                                    <option value="asc">升序</option>
                                    <option value="desc">降序</option>
                                </select>
                            </div>
                        </th>
						<th class="col-md-2 text-center">操作</th>
					</tr>
				</thead>

				<tbody id="tbody">

					<%--<tr class="text-center middle">--%>
					<%--<td>1</td>--%>
						<%--<td>2017214712</td>--%>
								<%--<td>王京</td>--%>
								<%--<td>88</td>--%>
								<%--<td>99</td>--%>
								<%--<td>187</td>--%>
								<%--<td>这次没有上次考的好</td>--%>
								<%--<td>--%>
									<%--<button class="btn btn-danger">删除</button>--%>
									<%--<button class="btn btn-warning">修改</button>--%>
								<%--</td>--%>
							<%--</tr>--%>
					<%--<input type="button" value="退出" onclick="logout()">--%>

				</tbody>
			</table>
		</div>

	</div>

</body>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/manager.js"></script>
<script>
	
	// 获取数据（ajax）
	// 遍历表格
	// 遍历 （for）
	// 创建DOM（通过创建DOM元素或者直接字符串拼接）
	// 放到该放入的dom元素里
</script>

</html>