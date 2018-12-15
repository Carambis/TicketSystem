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
    <link rel="stylesheet" href="res/css/review.css">
    <script src="res/js/jquery-3.3.1.min.js"></script>
    <script src="res/js/bootstrap.min.js"></script>

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
    <fmt:message bundle="${loc}" key="local.film.criticScore" var="criticScore"/>
    <fmt:message bundle="${loc}" key="local.film.avgScore" var="avgScore"/>
    <fmt:message bundle="${loc}" key="local.film.countCritic" var="countCritic"/>
    <fmt:message bundle="${loc}" key="local.film.userScore" var="userScore"/>
    <fmt:message bundle="${loc}" key="local.film.countUser" var="countUser"/>
    <fmt:message bundle="${loc}" key="local.film.userMark" var="userMark"/>
    <fmt:message bundle="${loc}" key="local.film.userComment" var="userComment"/>
    <fmt:message bundle="${loc}" key="local.film.mark.0" var="mark0"/>
    <fmt:message bundle="${loc}" key="local.film.mark.1" var="mark1"/>
    <fmt:message bundle="${loc}" key="local.film.mark.2" var="mark2"/>
    <fmt:message bundle="${loc}" key="local.film.mark.3" var="mark3"/>
    <fmt:message bundle="${loc}" key="local.film.mark.4" var="mark4"/>
    <fmt:message bundle="${loc}" key="local.film.desc" var="descr"/>
    <fmt:message bundle="${loc}" key="local.film.notComment" var="noComment"/>
    <fmt:message bundle="${loc}" key="local.film.post" var="post"/>
    <fmt:message bundle="${loc}" key="local.film.review" var="review"/>
    <fmt:message bundle="${loc}" key="local.film.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.film.ganre" var="ganre"/>
    <fmt:message bundle="${loc}" key="local.film.date" var="date"/>
    <fmt:message bundle="${loc}" key="local.film.duration" var="duration"/>
    <fmt:message bundle="${loc}" key="local.film.full_desc" var="full_desc"/>
    <fmt:message bundle="${loc}" key="local.film.crititc" var="critics"/>
    <fmt:message bundle="${loc}" key="local.film.users" var="users"/>
    <fmt:message bundle="${loc}" key="local.film.posted" var="posted"/>
    <fmt:message bundle="${loc}" key="local.message.markError" var="markError"/>
    <fmt:message bundle="${loc}" key="local.message.ban" var="messageBan"/>
    <fmt:message bundle="${loc}" key="local.search" var="search"/>
    <fmt:message bundle="${loc}" key="local.rememberMe" var="rememberMe"/>
    <fmt:message bundle="${loc}" key="local.page.start" var="start"/>

</head>
<body>
<div class="header">
    <h1>My Site</h1>
</div>

<div class="row">
    <div class="col-xs-3 locgroup">
        <div class="col-xs-1 locbutton">
            <form action="/film" method="get">
                <input type="hidden" name="id" value="${requestScope.film.id}">
                <input type="hidden" name="local" value="ru">
                <input type="hidden" value="LOCALIZATION_PAGE_FILM" name="command">
                <button type="submit" class="btn btn-default btn-xs">${button_ru}</button>
            </form>
        </div>
        <div class="col-xs-1 locbutton">
            <form action="/film" method="get">
                <input type="hidden" name="id" value="${requestScope.film.id}">
                <input type="hidden" name="local" value="en">
                <input type="hidden" value="LOCALIZATION_PAGE_FILM" name="command">
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
                            <form action="/film" method="get">
                                <input type="hidden" name="id" value="${requestScope.film.id}">
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

                            <form id="reg" class="form-horizontal" action="/home"
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
    <div class="row">
        <nav class="col-sm-3">
            <ul class="nav nav-pills nav-stacked">
                <li><a href="#secTop">${start}</a></li>
                <li><a href="#secDiscr">${descr}</a></li>
                <li><a href="#secCritic">${critics}</a></li>
                <li><a href="#secUserCom">${users}</a></li>
            </ul>
        </nav>
        <div class="col-sm-9" id="secTop">
            <c:set var="film" value="${requestScope.film}"/>
            <c:set var="score" value="${requestScope.score}"/>
            <div class="wideScreenImg shift">
                <img src="/image?nameImage=${film.nameImage}&typeImage=wideScreen" class="img-rounded"/>
            </div>

            <div class="row shift">
                <div class="col-sm-2">
                    <img src="/image?nameImage=${film.namePoster}&typeImage=poster" class="posterimg img-rounded">
                </div>
                <div class="col-sm-5">
                    <div><h3>${criticScore}</h3></div>
                    <div><span>${avgScore} </span><span>${score.avgCritic}</span></div>
                    <div><span>${countCritic} </span><span>${score.countCritic}</span></div>
                </div>
                <div class="col-sm-5">
                    <div><h3>${userScore}</h3></div>
                    <div><span>${avgScore} </span><span>${film.avgRating}</span></div>
                    <div><span>${countUser} </span><span>${score.countUser}</span></div>
                </div>
                <c:if test="${sessionScope.user == null}">
                    <button type="button" class="btn btn-default" data-toggle="modal"
                            data-target="#login" style="float:right;">${titleLogin}</button>
                </c:if>
                <c:if test="${sessionScope.user!=null}">
                    <div style="padding-top: 3px;" >
                        <div class="col-sm-3"><h3>${userMark}</h3></div>


                        <c:if test="${requestScope.comment.mark != 0}">
                            <div><h3>${requestScope.comment.mark}</h3></div>
                            <div class="col-sm-10 panel panel-default">
                                <div class="panel-heading marginforpanel">${userComment}</div>
                                <c:if test="${requestScope.comment.comment == null}">
                                    <div class="panel-body marginforpanel">${noComment}</div>
                                </c:if>
                                <c:if test="${requestScope.comment.comment != null}">
                                    <div class="panel-body marginforpanel">${requestScope.comment.comment}</div>
                                </c:if>
                            </div>
                        </c:if>
                    </div>
                    <c:if test="${requestScope.comment.mark == 0}">
                        <form action="/film" onsubmit="return validateMark()">
                            <input type="hidden" name="id" value="${requestScope.film.id}">
                            <input type="hidden" name="command" value="MARK">
                            <div id="reviewStars-input" class="col-sm-7" style="margin-top: 20px;">
                                <input id="star-5" type="radio" name="reviewStars" value="5"/>
                                <label title="${mark4}" for="star-5"></label>

                                <input id="star-4" type="radio" name="reviewStars" value="4"/>
                                <label title="${mark3}" for="star-4"></label>

                                <input id="star-3" type="radio" name="reviewStars" value="3"/>
                                <label title="${mark2}" for="star-3"></label>

                                <input id="star-2" type="radio" name="reviewStars" value="2"/>
                                <label title="${mark1}" for="star-2"></label>

                                <input id="star-1" type="radio" name="reviewStars" value="1"/>
                                <label title="${mark0}" for="star-1"></label>
                                <span class="error-message" id="markError">${markError}</span>
                            </div>
                            <div class="col-sm-10" style="margin-top: 10px;">
                                <textarea placeholder="${review}" cols=80 rows=2 name="comment"></textarea>
                                <button type="submit" class="btn btn-default" style="margin-top: -4%;">${post}</button>
                            </div>
                        </form>
                    </c:if>
                </c:if>
            </div>
            <div class="panel panel-primary" id="secDiscr">
                <div class="panel-heading">${descr}</div>
                <div class="panel-body">
                    <table class="table tableinfo">
                        <tbody>
                        <tr>
                            <td>${name}: ${film.name}</td>
                        </tr>
                        <tr>
                            <td>${ganre}: ${film.ganre}</td>
                        </tr>
                        <tr>
                            <td>${date}: ${film.datePrimer}</td>
                        </tr>
                        <tr>
                            <td>${duration}: ${film.duration}</td>
                        </tr>
                        <tr>
                            <td>${full_desc}: ${film.description}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="panel panel-primary" id="secCritic">
                <div class="panel-heading">${critics}</div>
                <div class="panel-body">
                    <c:forEach var="critic" items="${requestScope.listCritic}">
                        <div class="media">
                            <div class="media-left">
                                <img src="/image?nameImage=${critic.nameImage}&typeImage=userPic" class="media-object"
                                     style="width:45px;">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">${critic.nickname}
                                    <small><i>${posted} ${critic.date}</i></small>
                                </h4>
                                <p>${critic.comment}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="panel panel-primary" id="secUserCom">
                <div class="panel-heading">${users}</div>
                <div class="panel-body">
                    <c:forEach var="user" items="${requestScope.listUsers}">
                        <div class="media">
                            <div class="media-left">
                                <img src="/image?nameImage=${critic.nameImage}&typeImage=userPic" class="media-object"
                                     style="width:45px;">
                            </div>
                            <div class="media-body">

                                <h4 class="media-heading">${user.nickname}
                                    <small><i>${posted} ${user.date}</i></small>
                                </h4>
                                <p>${user.comment}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
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
