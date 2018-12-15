<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>EditUser</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="res/css/bootstrap.min.css">
    <link rel="stylesheet" href="res/css/index.css">
    <link rel="stylesheet" href="res/css/bootstrap-select.min.css">
    <script src="res/js/jquery-3.3.1.min.js"></script>
    <script src="res/js/bootstrap.min.js"></script>
    <script src="res/js/bootstrap-select.min.js"></script>
    <script src="res/js/showTable.js"></script>

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="button_en"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="button_ru"/>
    <fmt:message bundle="${loc}" key="local.index.home" var="home"/>
    <fmt:message bundle="${loc}" key="local.index.film" var="film"/>
    <fmt:message bundle="${loc}" key="local.index.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.index.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="local.registration.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.message.emptyError" var="emptyError"/>
    <fmt:message bundle="${loc}" key="local.message.problemEdit" var="problemEdit"/>
    <fmt:message bundle="${loc}" key="local.message.invalidInput" var="invalidInput"/>
    <fmt:message bundle="${loc}" key="local.message.inputPassword.equals" var="passwordEquals"/>
    <fmt:message bundle="${loc}" key="local.message.inputPassword.regex" var="passwordRegex"/>
    <fmt:message bundle="${loc}" key="local.message.inputEmail.regex" var="emailRegex"/>
    <fmt:message bundle="${loc}" key="local.message.inputNickName.length" var="nicknameLength"/>
    <fmt:message bundle="${loc}" key="local.confornim" var="confornim"/>
    <fmt:message bundle="${loc}" key="local.index.button.closeUser" var="closeUser"/>
    <fmt:message bundle="${loc}" key="local.registration.userPic" var="userPic"/>
    <fmt:message bundle="${loc}" key="local.registration.nickname" var="nickname"/>
</head>
<body>
<div class="header">
    <h1>My Site</h1>
</div>

<div class="row">
    <div class="col-xs-3 locgroup">
        <div class="col-xs-1 locbutton">
            <form action="/editUser" method="get">
                <input type="hidden" name="local" value="ru">
                <input type="hidden" value="LOCALIZATION" name="command">
                <button type="submit" class="btn btn-default btn-xs">${button_ru}</button>
            </form>
        </div>
        <div class="col-xs-1 locbutton">
            <form action="/editUser" method="get">
                <input type="hidden" name="local" value="en">
                <input type="hidden" value="LOCALIZATION" name="command">
                <button type="submit" class="btn btn-default btn-xs">${button_en}</button>
            </form>
        </div>
    </div>
</div>

<nav class="navbar container navbar-inverse">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>

    <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/home">${home}</a></li>
            <li><a href="/films">${film}</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

            <li>
                <a href="" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user"></span>
                    ${sessionScope.user.nickname}
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/editUser">${edit}</a></li>
                    <c:if test="${sessionScope.user.status == 0}">
                        <li><a href="/adminMenu">${adminMenu}</a></li>
                    </c:if>
                    <li>
                        <form action="/home">
                            <input type="hidden" value="EXIT_USER" name="command">
                            <button type="submit" class="btn btn-link">${closeUser}</button>
                        </form>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <form class="form-horizontal" action="/editUser?command=EDIT_PROFILE" enctype="multipart/form-data"
          method="post" onsubmit="return validateProfile()">
        <input type="hidden" name="id" value="${sessionScope.user.id}">
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">${password}</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" value="${sessionScope.user.password}"
                       name="password">
                <span class="error-message" id="passwordEmptyError">${emptyError}</span>
                <span class="error-message" id="passwordRegexError">${passwordRegex}</span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">${email}</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" value="${sessionScope.user.eMail}"
                       name="email">
                <span class="error-message" id="emailEmptyError">${emptyError}</span>
                <span class="error-message" id="emailRegexError">${emailRegex}</span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="nickname">${nickname}</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="nickname" value="${sessionScope.user.nickname}"
                       name="nickname">
            </div>
            <span class="error-message" id="nicknameEmptyError">${emptyError}</span>
            <span class="error-message" id="nicknameLengthError">${nicknameLength}</span>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="userPic">${userPic}</label>
            <div class="col-sm-10">
                <input type="file" id="userPic" placeholder="${userPic}"
                       name="userPic">
            </div>
        </div>
        <c:if test="${sessionScope.messageCode == 2}">
            <h5 class="error-servlet">${invalidInput}</h5>
        </c:if>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">${confornim}</button>
            </div>
        </div>
    </form>
</div>
<script src="res/js/validate.js"></script>
</body>
</html>
