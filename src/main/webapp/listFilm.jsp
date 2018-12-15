<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tag/mytag.tld" prefix="mytag" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="res/css/bootstrap.min.css">
    <link rel="stylesheet" href="res/css/index.css">
    <link rel="stylesheet" href="res/css/bootstrap-select.min.css">
    <script src="res/js/jquery-3.3.1.min.js"></script>
    <script src="res/js/bootstrap.min.js"></script>
    <script src="res/js/bootstrap-select.min.js"></script>

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="button_en"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="button_ru"/>
    <fmt:message bundle="${loc}" key="local.index.home" var="home"/>
    <fmt:message bundle="${loc}" key="local.index.film" var="film"/>
    <fmt:message bundle="${loc}" key="local.index.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.index.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.index.button.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="local.index.button.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="local.index.button.close" var="close"/>
    <fmt:message bundle="${loc}" key="local.index.titleLogin" var="titleLogin"/>
    <fmt:message bundle="${loc}" key="local.index.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="local.index.information" var="information"/>
    <fmt:message bundle="${loc}" key="local.index.button.closeUser" var="closeUser"/>
    <fmt:message bundle="${loc}" key="local.registration.repeatPassword" var="repeatPassword"/>
    <fmt:message bundle="${loc}" key="local.registration.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.registration.nickname" var="nickname"/>
    <fmt:message bundle="${loc}" key="local.registration.button.submit" var="submitReg"/>
    <fmt:message bundle="${loc}" key="local.index.messageNoUser" var="messageNoUser"/>
    <fmt:message bundle="${loc}" key="local.index.adminMenu" var="adminMenu"/>
    <fmt:message bundle="${loc}" key="local.message.emptyError" var="emptyError"/>
    <fmt:message bundle="${loc}" key="local.message.inputLogin.lengthError" var="lengthError"/>
    <fmt:message bundle="${loc}" key="local.message.inputLogin.regex" var="loginRegex"/>
    <fmt:message bundle="${loc}" key="local.message.inputPassword.equals" var="passwordEquals"/>
    <fmt:message bundle="${loc}" key="local.message.inputPassword.regex" var="passwordRegex"/>
    <fmt:message bundle="${loc}" key="local.message.inputEmail.regex" var="emailRegex"/>
    <fmt:message bundle="${loc}" key="local.message.inputNickName.length" var="nicknameLength"/>
    <fmt:message bundle="${loc}" key="local.film.add.input" var="input"/>
    <fmt:message bundle="${loc}" key="local.registration.userPic" var="userPic"/>
    <fmt:message bundle="${loc}" key="local.message.invalidInput" var="invalidInput"/>
    <fmt:message bundle="${loc}" key="local.message.equalsUser" var="equalsUser"/>
    <fmt:message bundle="${loc}" key="local.message.ban" var="messageBan"/>
    <fmt:message bundle="${loc}" key="local.search" var="search"/>
    <fmt:message bundle="${loc}" key="local.rememberMe" var="rememberMe"/>
    <fmt:message bundle="${loc}" key="local.page.previous" var="previous"/>
    <fmt:message bundle="${loc}" key="local.page.next" var="next"/>
    <fmt:message bundle="${loc}" key="local.page.empty" var="emptyPage"/>

</head>
<body>
<div class="header">
    <h1>My Site</h1>
</div>
<div class="row">
    <div class="col-xs-3 locgroup">
        <div class="col-xs-1 locbutton">
            <form action="/films" method="get">
                <input type="hidden" name="local" value="ru">
                <input type="hidden" value="LOCALIZATION" name="command">
                <button type="submit" class="btn btn-default btn-xs">${button_ru}</button>
            </form>
        </div>
        <div class="col-xs-1 locbutton">
            <form action="/films" method="get">
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
            <li>
                <form class="form-inline" action="/home">
                    <input type="hidden" name="command" value="SEARCH">
                    <div class="input-group search-gr">
                        <input type="text" class="form-control" placeholder="${search}" name="search">
                        <div class="input-group-btn">
                            <button class="btn btn-default btn-search" type="submit"><i
                                    class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                </form>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

            <c:choose>
                <c:when test="${sessionScope.user != null}">
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
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="" data-toggle="modal" data-target="#login" data-keyboard="true" data-show="true"><span
                                class="glyphicon glyphicon-log-in"></span>
                                ${titleLogin}
                        </a>
                    </li>
                    <li>
                        <a href="" data-toggle="modal" data-target="#regestation" data-keyboard="true">
                                ${registration}
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

            <div class="modal fade" id="login" role="dialog" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">${titleLogin}</h4>
                        </div>
                        <div class="modal-body">
                            <form action="/films" method="get">
                                <input type="hidden" name="command" value="AUTHORIZATION"/>
                                <div class="form-group">
                                    <label for="fieldLogin">${login}</label>
                                    <input type="text" class="form-control" id="fieldLogin" name="fieldLogin"
                                           placeholder="${input} ${login}">
                                </div>
                                <div class="form-group">
                                    <label for="fieldPassword">${password}</label>
                                    <input type="password" class="form-control" name="fieldPassword" id="fieldPassword"
                                           placeholder="${input} ${password}">
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" name="rememberME">${rememberMe}</label>
                                </div>
                                <c:if test="${sessionScope.messageCode == 1}">
                                    <h5 class="error-servlet">${messageNoUser}</h5>
                                </c:if>
                                <c:if test="${sessionScope.messageCode == 8}">
                                    <h5 class="error-servlet">${messageBan}</h5>
                                </c:if>
                                <button type="submit" class="btn btn-default">${submit}</button>
                            </form>
                            <button type="submit" class="btn btn-default" data-toggle="modal"
                                    data-target="#regestation" id="but1">${registration}</button>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">${close}</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="regestation" role="dialog" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">${registration}</h4>
                        </div>
                        <div class="modal-body">

                            <form id="reg" class="form-horizontal" action="/films"
                                  enctype="multipart/form-data"
                                  method="post" onsubmit="return validateReg()">
                                <div class="form-group">
                                    <input type="hidden" name="command" value="REGISTRATION">
                                    <label class="control-label col-sm-3" for="loginReg">${login}</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="loginReg"
                                               placeholder="${input} ${login}"
                                               name="login">
                                    </div>
                                    <span class="error-message" id="loginEmptyError">${emptyError}</span>
                                    <span class="error-message" id="loginLengthError">${lengthError}</span>
                                    <span class="error-message" id="loginRegexError">${loginRegex}</span>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3" for="password">${password}</label>
                                    <div class="col-sm-9">
                                        <input type="password" class="form-control" id="password"
                                               placeholder="${input} ${password}"
                                               name="password">
                                    </div>
                                    <span class="error-message" id="passwordEmptyError">${emptyError}</span>
                                    <span class="error-message" id="passwordRegexError">${passwordRegex}</span>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3" for="repeatPassword">${repeatPassword}</label>
                                    <div class="col-sm-9">
                                        <input type="password" class="form-control" id="repeatPassword"
                                               placeholder="${repeatPassword}" name="repeatPassword">
                                    </div>
                                    <span class="error-message" id="repeatEmptyError">${emptyError}</span>
                                    <span class="error-message" id="repeatEqualsError">${passwordEquals}</span>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3" for="email">${email}</label>
                                    <div class="col-sm-9">
                                        <input type="email" class="form-control" id="email"
                                               placeholder="${email}"
                                               name="email">
                                    </div>
                                    <span class="error-message" id="emailEmptyError">${emptyError}</span>
                                    <span class="error-message" id="emailRegexError">${emailRegex}</span>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3" for="nickname">${nickname}</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="nickname"
                                               placeholder="${nickname}"
                                               name="nickname">
                                    </div>
                                    <span class="error-message" id="nicknameEmptyError">${emptyError}</span>
                                    <span class="error-message" id="nicknameLengthError">${nicknameLength}</span>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3" for="userPic">${userPic}</label>
                                    <div class="col-sm-9">
                                        <input type="file" id="userPic" placeholder="${input} ${userPic}"
                                               name="userPic">
                                    </div>
                                </div>
                                <c:if test="${sessionScope.messageCode == 2}">
                                    <h5 class="error-servlet">${invalidInput}</h5>
                                </c:if>
                                <c:if test="${sessionScope.messageCode == 3}">
                                    <h5 class="error-servlet">${equalsUser}</h5>
                                </c:if>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-default">${submit}</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">${close}</button>
                        </div>
                    </div>
                </div>
            </div>
        </ul>
    </div>
</nav>

<div class="container text-centre">
    <div class="row content">
        <div class="col-sm-3">
        </div>
        <div class="col-sm-9">
            <c:if test="${requestScope.messageCode == 9}">
                <h5 class="error-servlet">${emptyPage}</h5>
            </c:if>
            <div class="row">
                <table class="table">
                    <tbody>
                    <c:forEach var="film" items="${requestScope.listFilm}">
                        <tr>
                            <td class="col-sm-1">
                                <a href="/film?id=${film.id}">
                                    <img src="/image?nameImage=${film.namePoster}&typeImage=poster" alt="poster1"
                                         class="img-rounded posterimg"/>
                                </a>
                            </td>
                            <td class="col-sm-8">
                                <a href="/film?id=${film.id}">
                                    <table class="tableinfo">
                                        <tbody>
                                        <tr>
                                            <td>${film.name}</td>
                                        </tr>
                                        <tr>
                                            <td>${film.ganre}</td>
                                        </tr>
                                        <tr>
                                            <td>${film.director}</td>
                                        </tr>
                                        <tr>
                                            <td>${film.avgRating}</td>
                                        </tr>
                                        <tr>
                                            <td>${film.datePrimer}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${requestScope.currentPage != 1}">
                <ul class="pager">
                    <li><a href="/films?page=${requestScope.currentPage - 1}">${previous}</a></li>
                </ul>
            </c:if>
            <c:if test="${requestScope.currentPage < requestScope.noOfPages}">
                <ul class="pager">
                    <li><a href="/films?page=${requestScope.currentPage + 1}">${next}</a></li>
                </ul>
            </c:if>

            <ul class="pagination">
                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                <c:choose>
                    <c:when test="${requestScope.currentPage eq i}">
                        <li><a>${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/films?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
                </c:forEach>
            </ul>

        </div>
    </div>
</div>


<footer class="container-fluid context-centre">
    <c:set var="text" value="Created By Alexsandr Lepeshko"/>
    <mytag:mytag data="${text}"/>
</footer>
<script src="res/js/validate.js"></script>
</body>
</html>
