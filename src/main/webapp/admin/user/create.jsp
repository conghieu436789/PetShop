<%--
  Created by IntelliJ IDEA.
  User: anhnbt
  Date: 30/11/2020
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Add New User"/>
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- Begin Page Content -->
<div class="container-fluid">
    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold">${pageTitle} <a
                    href="${pageContext.request.contextPath}/administrator/users?act=index"
                    class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="top" title="All Users"><i
                    class="fas fa-reply"></i> All Users</a></h6>
        </div>
        <form action="${pageContext.request.contextPath}/administrator/users" method="post">

            <div class="card-body">
                <% if (session.getAttribute("msg") != null) { %>
                <%=session.getAttribute("msg") %>
                <% session.removeAttribute("msg"); %>
                <% } %>
                <input type="hidden" name="act" value="store">
                <div class="form-group row">
                    <label for="username" class="col-md-4 col-form-label text-md-right">Username <span
                            class="text-danger">*</span>:</label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" name="username" id="username"
                               placeholder="Enter username" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-md-4 col-form-label text-md-right">Phone number:</label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" name="phone" id="phone"
                               placeholder="Enter phone number">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-4 col-form-label text-md-right">Gender</label>
                    <div class="col-md-6">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="male" value="male" checked>
                            <label class="form-check-label" for="male">Male</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="female" value="female">
                            <label class="form-check-label" for="female">Female</label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="roles" class="col-md-4 col-form-label text-md-right">Roles</label>
                    <div class="col-md-6">
                        <select name="roles" id="roles">
                            <option value="administrator">Administrator</option>
                            <option value="sales_staff">Sales Staff</option>
                            <option value="sales_manager">Sales Manager</option>
                            <option value="store_owner">Store Owner</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-md-4 col-form-label text-md-right">Password <span
                            class="text-danger">*</span></label>
                    <div class="col-md-6">
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password"
                               required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="confirm" class="col-md-4 col-form-label text-md-right">Confirm <span
                            class="text-danger">*</span></label>
                    <div class="col-md-6">
                        <input type="password" class="form-control" name="confirm" id="confirm" placeholder="Password"
                               required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="status" class="col-md-4 col-form-label text-md-right">Status</label>
                    <div class="col-md-6">
                        <select name="status" id="status">
                            <option value="1">Enabled</option>
                            <option value="0">Disabled</option>
                        </select>
                    </div>
                </div>
            </div><!-- /.card-body-->
            <div class="card-footer">
                <div class="col-md-6 offset-md-4">
                    <button type="submit" class="btn btn-primary" data-toggle="tooltip" data-placement="top"
                            title="Save"><i class="fas fa-save"></i> Save
                    </button>
                    <a href="${pageContext.request.contextPath}/administrator/users?act=index" class="btn btn-secondary"
                       data-toggle="tooltip" data-placement="top" title="Cancel"><i class="fas fa-reply"></i> Cancel</a>
                </div>
            </div>
        </form>
    </div><!-- /.card -->

</div>
<!-- /.container-fluid -->
<jsp:include page="../inc/footer.jsp"></jsp:include>