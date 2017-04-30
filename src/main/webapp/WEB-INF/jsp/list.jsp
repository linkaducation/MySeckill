<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/tag.jsp" %>
<!DOCTYPE HTML>
<html>
   <head>
        <title>秒杀商品列表</title>
      <%@include file="/WEB-INF/common/head.jsp" %>
   </head>
   <body>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h2>秒杀列表</h2>
                </div>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>库存</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>创建时间</th>
                            <th>详情页</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list }" var="sk">
	                        <tr>
	                           <td>${sk.name }</td>
	                           <td>${sk.number }</td>
	                           <td>
	                               <fmt:formatDate value="${sk.start_time }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                           </td>
	                           <td>
                                   <fmt:formatDate value="${sk.end_time }" pattern="yyyy-MM-dd HH:mm:ss"/>
                               </td>
	                           <td>
                                   <fmt:formatDate value="${sk.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/>
                               </td>
                               <td>
                                    <a class="btn btn-info" href="/seckill/${sk.seckill_id }/detail" target="_blank">link</a>
                               </td>
	                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
   </body>
   <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
   <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>