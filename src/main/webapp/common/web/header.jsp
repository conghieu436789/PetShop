<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/29/2020
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/libs.jsp"%>

<html>
    <head>
      <title>Header</title>
        <link href="<c:url value="/template/bootstrap/css/bootstrap.css"/>" rel="stylesheet" >
        <link href="<c:url value="/template/css/header-footer/header.css"/>" rel="stylesheet">
        <link href="<c:url value="/template/css/header-footer/search.css"/>" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <header class="row">
            <a class="col-md-4" href="#" target="_self"> <img src="<c:url value="/img/logo.png"/>"/></a>
            <div class="col-md-3 d-flex justify-content-center d-none ">
                <div class="d-md-inline infor" style="margin-top: 15px" >
                    <h3>Niềm vui của bạn <br>Hạnh phúc của tôi</h3>
                </div>
            </div>
            <div class="col-md-2 d-flex justify-content-center d-none">
                <div class="d-md-inline infor" >
                    <p style="font-size: 20px">0123456789 <br> <a href="#" class="phone_icon"><i class="fa fa-phone" style="font-size: 30px"></i></a></p>
                </div>
            </div>

            <div class="col-md-3 d-flex justify-content-center ml-auto">
                <ul class="nav navbar-nav ml-auto list-group list-group-horizontal">
                    <li class="nav-item active cart">
                        <a class="nav-link" href="#" style="color: #c6c8ca"><i class="fas fa-shopping-cart"></i></a>
                    </li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="fas fa-user mr-1"></span> Tai khoan
                        </a>
                        <div class="dropdown-menu" aria-labelledby="accountDropdown">
                            <a class="dropdown-item" href="#">profile</a>
                            <a class="dropdown-item" href="#">Change password</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Log out</a>
                        </div>
                    </li>
                </ul>
            </div>
        </header>

        <div></div>
        <nav class="navbar navbar-expand-lg navbar-dark" style="background: blueviolet">
            <a class="navbar-brand" href="#"><i class="fas fa-home"></i>Trang chu</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto w-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><i class="fas fa-list-alt mr-1"></i>Gioi thieu <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" href="#" id="dogDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-dog mr-1"></i>Chó
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dogDropdown">
                            <a class="dropdown-item" href="#">Chó tây</a>
                            <a class="dropdown-item" href="#">Chó ta</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" href="#" id="catDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-cat mr-1"></i>Mèo
                        </a>
                        <div class="dropdown-menu" aria-labelledby="catDropdown">
                            <a class="dropdown-item" href="#">Mèo tây</a>
                            <a class="dropdown-item" href="#">Mèo ta</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><span class="fas fa-phone mr-1"></span>Lien he</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item active">
                        <div class="d-flex justify-content-center h-100">
                            <div class="searchbar">
                                <input class="search_input" type="text" name="" placeholder="Search...">
                                <a href="#" class="search_icon"><i class="fas fa-search"></i></a>
                            </div>
                        </div>
                    </li>

                </ul>
            </div>
        </nav>
    </body>
</html>
<script src="https://kit.fontawesome.com/06afc5370d.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

