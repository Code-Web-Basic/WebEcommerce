<%--
  Created by IntelliJ IDEA.
  User: Tu Nguyen
  Date: 12/14/2022
  Time: 9:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<core:url value="/templates/admin" var="URL"></core:url>

<div class="container-xxl flex-grow-1 container-p-y">
    <!-- search product -->
    <div class="row">
        <div class="col-12 mb-4 order-0">
            <div class="card mb-4">
                <h5 class="card-header">Chỉnh Sửa Thể Loại</h5>
                <div class="card-body">
                    <form action="edit" method="post" enctype="multipart/form-data" role="form">
                        <div class="mb-3">
                            <label for="exampleFormControlInput1" class="form-label">Tên Thể Loại</label>
                            <input type="text" class="form-control" name="name" id="exampleFormControlInput1"
                                   placeholder="Tên Thể Loại..." value="${category.name}"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Hình Ảnh</label>
                            <core:url value="/image?fname=category/${category.image}"
                                      var="imgUrl"></core:url>
                            <img class="rounded-circle" height="200" width="200" id="image"
                                 width="100px" src="${imgUrl}" alt="">
                            <input class="form-control" name="image" type="file" id="formFile"/>
                        </div>
                        <div class="mb-3">
                            <label for="exampleFormControlSelect1" class="form-label">Thể Loại Cha</label>
                            <select class="form-select" id="exampleFormControlSelect1" name="categoryId"
                                    aria-label="Thể Loại">
                                <core:forEach items="${categorys}" var="kq">
                                    <core:if test="${kq.id == category.cartId}">
                                        <option selected value="${kq.id}">${kq.name}</option>
                                    </core:if>
                                    <core:if test="${kq.id != category.cartId}">
                                        <option value="${kq.id}">${kq.name}</option>
                                    </core:if>
                                </core:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <div class="form-check form-switch mb-2">
                                <input class="form-check-input" type="checkbox"
                                       name="isDeleted"/>
                                <core:if test="${category.isDeleted}">
                                    <input class="form-check-input" type="checkbox"
                                           checked
                                           id="flexSwitchCheckDefault"
                                           name="isDeleted"/> </core:if>
                                <core:if test="${!category.isDeleted}">
                                    <input class="form-check-input" type="checkbox"
                                           id="flexSwitchCheckDefault"
                                           name="isDeleted"/></core:if>
                                <label class="form-check-label" for="flexSwitchCheckDefault"
                                >Bán Sản phẩm</label>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Sửa Thể Loại</button>
                    </form>
                </div>

            </div>
        </div>
    </div>

</div>
