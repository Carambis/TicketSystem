<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
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
    <fmt:message bundle="${loc}" key="local.index.tv" var="tv"/>
    <fmt:message bundle="${loc}" key="local.index.film" var="film"/>
    <fmt:message bundle="${loc}" key="local.index.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.index.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.index.inputLogin" var="inputLogin"/>
    <fmt:message bundle="${loc}" key="local.index.inputPassword" var="inputPassword"/>
    <fmt:message bundle="${loc}" key="local.index.button.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="local.index.button.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="local.index.button.close" var="close"/>
    <fmt:message bundle="${loc}" key="local.index.titleLogin" var="titleLogin"/>
    <fmt:message bundle="${loc}" key="local.index.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="local.index.information" var="information"/>
    <fmt:message bundle="${loc}" key="local.index.button.closeUser" var="closeUser"/>
    <fmt:message bundle="${loc}" key="local.registration.login" var="loginReg"/>
    <fmt:message bundle="${loc}" key="local.registration.password" var="passwordReg"/>
    <fmt:message bundle="${loc}" key="local.registration.repeatPassword" var="repeatPassword"/>
    <fmt:message bundle="${loc}" key="local.registration.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.registration.nickname" var="nickname"/>
    <fmt:message bundle="${loc}" key="local.registration.button.submit" var="submitReg"/>
    <fmt:message bundle="${loc}" key="local.film.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.film.ganre" var="genre"/>
    <fmt:message bundle="${loc}" key="local.film.date" var="date"/>
    <fmt:message bundle="${loc}" key="local.film.duration" var="duration"/>
    <fmt:message bundle="${loc}" key="local.film.full_desc" var="full_desc"/>
    <fmt:message bundle="${loc}" key="local.film.add.input" var="input"/>
    <fmt:message bundle="${loc}" key="local.film.add.select" var="select"/>
    <fmt:message bundle="${loc}" key="local.film.add.local" var="lang"/>
    <fmt:message bundle="${loc}" key="local.film.add.poster" var="poster"/>
    <fmt:message bundle="${loc}" key="local.film.add.widescreen" var="widescreen"/>
    <fmt:message bundle="${loc}" key="local.registration.userPic" var="userPic"/>
    <fmt:message bundle="${loc}" key="local.film.director" var="director"/>
    <fmt:message bundle="${loc}" key="local.status.0" var="status0"/>
    <fmt:message bundle="${loc}" key="local.status.1" var="status1"/>
    <fmt:message bundle="${loc}" key="local.status.2" var="status2"/>
    <fmt:message bundle="${loc}" key="local.status.3" var="status3"/>
    <fmt:message bundle="${loc}" key="local.status.4" var="status4"/>
    <fmt:message bundle="${loc}" key="local.message.emptyError" var="emptyError"/>
    <fmt:message bundle="${loc}" key="local.message.invalidGanre" var="invalidGanre"/>
    <fmt:message bundle="${loc}" key="local.message.invalidDuration" var="invalidDuration"/>
    <fmt:message bundle="${loc}" key="local.message.invalidLength" var="invalidLength"/>
    <fmt:message bundle="${loc}" key="local.message.existFilm" var="existFilm"/>
    <fmt:message bundle="${loc}" key="local.message.problemEdit" var="problemEdit"/>
    <fmt:message bundle="${loc}" key="local.film.addFilm" var="addFilm"/>
    <fmt:message bundle="${loc}" key="local.film.editFilm" var="editFilm"/>
    <fmt:message bundle="${loc}" key="local.film.deleteFilm" var="deleteFilm"/>
    <fmt:message bundle="${loc}" key="local.message.invalidInput" var="invalidInput"/>
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
    <fmt:message bundle="${loc}" key="local.status.status" var="status"/>
    <fmt:message bundle="${loc}" key="local.selectFilm" var="selectFiln"/>
    <fmt:message bundle="${loc}" key="local.selectUser" var="selectUser"/>
    <fmt:message bundle="${loc}" key="local.addUser" var="addUser"/>
    <fmt:message bundle="${loc}" key="local.editUser" var="editUser"/>
    <fmt:message bundle="${loc}" key="local.deleteUser" var="deleteUser"/>
    <fmt:message bundle="${loc}" key="local.nothing" var="nothing"/>
    <fmt:message bundle="${loc}" key="local.deleteComment" var="deleteComment"/>
    <fmt:message bundle="${loc}" key="local.confornim" var="confornim"/>
    <fmt:message bundle="${loc}" key="local.or" var="or1"/>
    <fmt:message bundle="${loc}" key="local.ban" var="ban"/>
</head>
<body>
<div class="header">
    <h1>My Site</h1>
</div>

<div class="row">
    <div class="col-xs-3 locgroup">
        <div class="col-xs-1 locbutton">
            <form action="/home" method="get">
                <input type="hidden" name="local" value="ru">
                <input type="hidden" value="LOCALIZATION" name="command">
                <button type="submit" class="btn btn-default btn-xs">${button_ru}</button>
            </form>
        </div>
        <div class="col-xs-1 locbutton">
            <form action="/home" method="get">
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

<div class="container text-center">
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="pill" href="#addFilm">${addFilm}</a></li>
        <li><a data-toggle="pill" href="#deleteEditFilm">${deleteFilm} ${or1} ${editFilm}</a></li>
        <li><a data-toggle="pill" href="#addUser">${addUser}</a></li>
        <li><a data-toggle="pill" href="#editDeleteUser">${deleteUser} ${or1} ${editUser}</a></li>
        <li><a data-toggle="pill" href="#deleteComment">${deleteComment}t</a></li>
        <li><a data-toggle="pill" href="#banUser">${ban}</a></li>
    </ul>
    <div class="tab-content" style="margin-top:4%;">
        <div id="addFilm" class="tab-pane fade in active">
            <form class="form-horizontal" action="/adminMenu?command=ADD_FILM" enctype="multipart/form-data"
                  method="post" onsubmit="return validateFilm()">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="nameFilmRu">${name} (${button_ru})</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="nameFilmRu" placeholder="${input} ${name}(${button_ru})"
                               name="nameFilmRu">
                    </div>
                    <label class="control-label col-sm-2" for="nameFilmEn">${name} (${button_en})</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="nameFilmEn" placeholder="${input} ${name}(${button_en})"
                               name="nameFilmEn">
                    </div>
                    <span class="error-message" id="emptyNameFilm">${emptyError}</span>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="genreRu">${genre} (${button_ru})</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="genreRu" placeholder="${input} ${genre} (${button_ru})" name="genreRu">
                    </div>
                    <label class="control-label col-sm-2" for="genreEn">${genre} (${button_en})</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="genreEn" placeholder="${input} ${genre} (${button_en})" name="genreEn">
                    </div>
                    <span class="error-message" id="emptyGenreFilm">${emptyError}</span>
                    <span class="error-message" id="unresolvedSymbols">${invalidGanre}</span>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="duration">${duration}</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="duration" placeholder="${input} ${duration}"
                               name="duration">
                    </div>
                    <span class="error-message" id="emptyDurationFilm">${emptyError}</span>
                    <span class="error-message" id="durationRegexError">${invalidDuration}</span>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="datePr">${date}</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="datePr" placeholder="${input} ${date}" name="date">
                    </div>
                    <span class="error-message" id="emptyDateFilm">${emptyError}</span>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="directorRu">${director} (${button_ru})</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="directorRu" placeholder="${input} ${director} (${button_ru})"
                               name="directorRu">
                    </div>
                    <label class="control-label col-sm-2" for="directorEn">${director} (${button_en})</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="directorEn" placeholder="${input} ${director} (${button_en})"
                               name="directorEn">
                    </div>
                    <span class="error-message" id="emptyDirectorFilm">${emptyError}</span>
                    <span class="error-message" id="directorRegexError">${invalidGanre}</span>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="descrRu">${full_desc} (${button_ru})</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="descrRu" placeholder="${input} ${full_desc} (${button_ru})"
                               name="descrRu">
                    </div>
                    <label class="control-label col-sm-2" for="descrEn">${full_desc} (${button_en})</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="descrEn" placeholder="${input} ${full_desc} (${button_en})"
                               name="descrEn">
                    </div>
                    <span class="error-message" id="emptyDescriptionFilm">${emptyError}</span>
                    <span class="error-message" id="lengthDirectorError">${invalidLength}</span>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="poster">${poster}</label>
                    <div class="col-sm-10">
                        <input type="file" id="poster" placeholder="${input} ${poster}"
                               name="poster">
                    </div>
                    <span class="error-message" id="emptyPosterError">${emptyError}</span>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="widescreen">${widescreen}</label>
                    <div class="col-sm-10">
                        <input type="file" id="widescreen" placeholder="${input} ${widescreen}"
                               name="widescreen">
                    </div>
                    <span class="error-message" id="emptyWideScreenError">${emptyError}</span>
                </div>
                <c:if test="${sessionScope.messageCode == 4}">
                    <h5 class="error-servlet">${existFilm}</h5>
                </c:if>
                <c:if test="${sessionScope.messageCode == 7}">
                    <h5 class="error-servlet">${invalidInput}</h5>
                </c:if>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">${addFilm}</button>
                    </div>
                </div>
            </form>
        </div>

        <div id="deleteEditFilm" class="tab-pane fade">
            <label for="film">${selectFiln}</label>
            <select class="selectpicker" data-live-search="true" id="film" name="films" onchange="showTable('film')">
                <c:forEach var="filmEl" items="${requestScope.listFilm}">
                    <option value="${filmEl.id}${filmEl.lang}">${filmEl.name}</option>
                </c:forEach>
            </select>

            <c:forEach var="filmEl" items="${requestScope.listFilm}">
                <div id="${filmEl.id}${filmEl.lang}film" class="table1 form-group" style="display: none;">
                    <form class="form-horizontal" action="/adminMenu?command=EDIT_FILM"
                          enctype="multipart/form-data"
                          method="post" onsubmit="return validateFilmEdit('${filmEl.id}${filmEl.lang}film')">
                        <input type="hidden" name="id" value="${filmEl.id}">
                        <input type="hidden" name="lang" value="${filmEl.lang}">
                        <input type="hidden" name="oldWideScreen" value="${filmEl.nameImage}">
                        <input type="hidden" name="oldPoster" value="${filmEl.namePoster}">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="nameFilmEdit">${name}</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="nameFilmEdit" value="${filmEl.name}"
                                       name="nameFilm">
                                <span class="error-message" id="emptyNameFilmEdit">${emptyError}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="ganreEdit">${genre}</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="ganreEdit" value="${filmEl.ganre}"
                                       name="ganre">
                                <span class="error-message" id="emptyGanreFilmEdit">${emptyError}</span>
                                <span class="error-message" id="unresolvedSymbolsEdit">${invalidGanre}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="durationEdit">${duration}</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="durationEdit" value="${filmEl.duration}"
                                       name="duration">
                                <span class="error-message" id="emptyDurationFilmEdit">${emptyError}</span>
                                <span class="error-message" id="durationRegexErrorEdit">${invalidDuration}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="dateEdit">${date}</label>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" id="dateEdit" value="${filmEl.datePrimer}"
                                       name="date">
                                <span class="error-message" id="emptyDateFilmEdit">${emptyError}</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="directorEdit">${director}</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="directorEdit" value="${filmEl.director}"
                                       name="director">
                            </div>
                            <span class="error-message" id="emptyDirectorFilmEdit">${emptyError}</span>
                            <span class="error-message" id="directorRegexErrorEdit">${invalidGanre}</span>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="descrEdit">${full_desc}</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="descrEdit" value="${filmEl.description}"
                                       name="descr">

                            </div>
                            <span class="error-message" id="emptyDescriptionFilmEdit">${emptyError}</span>
                            <span class="error-message" id="lengthDirectorErrorEdit">${invalidLength}</span>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="posterEdit">${poster}</label>
                            <img src="/image?nameImage=${filmEl.namePoster}&typeImage=poster"/>
                            <div class="col-sm-10">
                                <input type="file" id="posterEdit" name="poster">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="widescreenEdit">${widescreen}</label>
                            <img src="/image?nameImage=${filmEl.nameImage}&typeImage=widescreen"/>
                            <div class="col-sm-10">
                                <input type="file" id="widescreenEdit" name="widescreen">
                            </div>
                        </div>
                        <c:if test="${sessionScope.messageCode == 5}">
                            <h5 class="error-servlet">${existFilm}</h5>
                        </c:if>
                        <c:if test="${sessionScope.messageCode == 7}">
                            <h5 class="error-servlet">${invalidInput}</h5>
                        </c:if>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">${editFilm}</button>
                            </div>
                        </div>
                    </form>
                    <form action="/adminMenu" method="get">
                        <input type="hidden" name="command" value="DELETE_FILM">
                        <input type="hidden" name="id" value="${filmEl.id}">
                        <input type="hidden" name="lang" value="${filmEl.lang}">
                        <input type="hidden" name="oldWideScreen" value="${filmEl.nameImage}">
                        <input type="hidden" name="oldPoster" value="${filmEl.namePoster}">
                        <button class="btn-default" type="submit">${deleteFilm}</button>
                        <c:if test="${sessionScope.messageCode == 6}">
                            <h5 class="error-servlet">${problemEdit}</h5>
                        </c:if>
                    </form>
                </div>
            </c:forEach>
        </div>

        <div id="addUser" class="tab-pane fade">
            <form class="form-horizontal" action="/adminMenu?command=ADD_USER" enctype="multipart/form-data"
                  method="post" onsubmit="return validateAddUser()">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="login">${login}</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="login" placeholder="${input} ${login}"
                               name="login">
                        <span class="error-message" id="loginEmptyError">${emptyError}</span>
                        <span class="error-message" id="loginLengthError">${lengthError}</span>
                        <span class="error-message" id="loginRegexError">${loginRegex}</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="password">${password}</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" placeholder="${input} ${password}"
                               name="password">
                        <span class="error-message" id="passwordEmptyError">${emptyError}</span>
                        <span class="error-message" id="passwordRegexError">${passwordRegex}</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">${email}</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" placeholder="${input} ${email}"
                               name="email">
                        <span class="error-message" id="emailEmptyError">${emptyError}</span>
                        <span class="error-message" id="emailRegexError">${emailRegex}</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="statusAdd">${status}</label>
                    <div class="col-sm-10">
                        <select id="statusAdd" class="selectpicker" name="status">
                            <option value="0">${status0}</option>
                            <option value="1">${status1}</option>
                            <option value="2">${status2}</option>
                            <option value="3">${status3}</option>
                            <option value="4">${status4}</option>
                        </select>
                    </div>
                    <span class="error-message" id="statusEmptyError">${emptyError}</span>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="nickname">${nickname}</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="nickname" placeholder="${input} ${nickname}"
                               name="nickname">
                    </div>
                    <span class="error-message" id="nicknameEmptyError">${emptyError}</span>
                    <span class="error-message" id="nicknameLengthError">${nicknameLength}</span>

                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="userPic">${userPic}</label>
                    <div class="col-sm-10">
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
                        <button type="submit" class="btn btn-default">${addUser}</button>
                    </div>
                </div>
            </form>
        </div>

        <div id="editDeleteUser" class="tab-pane fade">
            <label for="user">${selectUser}</label>
            <select size="5" class="selectpicker" data-live-search="true" id="user" name="user"
                    onchange="showTable('user')">
                <c:forEach var="user" items="${requestScope.userList}">
                    <option value="${user.id}">${user.nickname}</option>
                </c:forEach>
            </select>

            <c:forEach var="user" items="${requestScope.userList}">
                <div id="${user.id}user" class="table1 form-group" style="display: none;">
                    <form class="form-horizontal" action="/adminMenu?command=EDIT_USER"
                          enctype="multipart/form-data"
                          method="post" onsubmit="return validateEditUser('${user.id}user')">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="oldUserPic" value="${user.pathToImage}">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="loginEdit">${login}</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="loginEdit" value="${user.login}"
                                       name="login">
                            </div>
                            <span class="error-message" id="loginEmptyErrorEdit">${emptyError}</span>
                            <span class="error-message" id="loginLengthErrorEdit">${lengthError}</span>
                            <span class="error-message" id="loginRegexErrorEdit">${loginRegex}</span>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="passwordEdit">${password}</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="passwordEdit" value="${user.password}"
                                       name="password">
                            </div>
                            <span class="error-message" id="passwordEmptyErrorEdit">${emptyError}</span>
                            <span class="error-message" id="passwordRegexErrorEdit">${passwordRegex}</span>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="emailEdit">${email}</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="emailEdit" value="${user.eMail}"
                                       name="email">
                            </div>
                            <span class="error-message" id="emailEmptyErrorEdit">${emptyError}</span>
                            <span class="error-message" id="emailRegexErrorEdit">${emailRegex}</span>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="statusEdit"></label>
                            <div class="col-sm-10">
                                <select id="statusEdit" class="selectpicker" name="status">
                                    <option value="0">${status0}</option>
                                    <option value="1">${status1}</option>
                                    <option value="2">${status2}</option>
                                    <option value="3">${status3}</option>
                                    <option value="4">${status4}</option>
                                </select>
                            </div>
                            <span class="error-message" id="statusEmptyErrorEdit">${emptyError}</span>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="nicknameEdit">${nickname}</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="nicknameEdit" value="${user.nickname}"
                                       name="nickname">
                            </div>
                            <span class="error-message" id="nicknameEmptyErrorEdit">${emptyError}</span>
                            <span class="error-message" id="nicknameLengthErrorEdit">${nicknameLength}</span>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="ban"></label>
                            <div class="col-sm-10">
                                <select id="ban" class="selectpicker" name="ban">
                                    <option value="NULL">${nothing}</option>
                                    <c:forEach var="ban" items="${requestScope.banList}">
                                        <option value="${ban.id}">${ban.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <span class="error-message" id="banEmptyError">${emptyError}</span>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="userPicEdit">${userPic}</label>
                            <img src="/image?nameImage=${user.pathToImage}&typeImage=userPic"/>
                            <div class="col-sm-10">
                                <input type="file" id="userPicEdit" name="userPic">
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
                                <button type="submit" class="btn btn-default">${editUser}</button>
                            </div>
                        </div>
                    </form>
                    <form action="/adminMenu" method="get">
                        <input type="hidden" name="command" value="DELETE_USER">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="oldUserPic" value="${user.pathToImage}">
                        <c:if test="${sessionScope.messageCode == 6}">
                            <h5 class="error-servlet">${problemEdit}</h5>
                        </c:if>
                        <button class="btn-default" type="submit">${deleteUser}</button>
                    </form>

                </div>
            </c:forEach>
        </div>

        <div id="deleteComment" class="tab-pane fade">
            <label for="userDeleteComment">Select User:</label>
            <select class="selectpicker" data-live-search="true" id="userDeleteComment" name="user"
                    onchange="showTable('userDeleteComment')">
                <c:forEach var="user" items="${requestScope.userList}">
                    <option value="${user.id}">${user.nickname}</option>
                </c:forEach>
            </select>
            <c:forEach var="user" items="${requestScope.userList}">
                <table id="${user.id}userDeleteComment" class="table1 table-bordered" style="display: none;">
                    <tbody>
                    <c:forEach var="comment" items="${requestScope.comments}">
                        <c:if test="${comment.userId==user.id}">
                            <tr>
                                <td>${comment.nameFilm}</td>
                                <td>${comment.comment}</td>
                                <td>
                                    <form action="/adminMenu" method="get">
                                        <input type="hidden" name="command" value="DELETE_COMMENT">
                                        <input type="hidden" name="userId" value="${comment.userId}">
                                        <input type="hidden" name="filmId" value="${comment.filmId}">
                                        <c:if test="${sessionScope.messageCode == 6}">
                                            <h5 class="error-servlet">${problemEdit}</h5>
                                        </c:if>
                                        <button type="submit" class="btn-default">${deleteComment}</button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </c:forEach>
        </div>

        <div id="banUser" class="tab-pane fade">
            <table class="table-striped table-responsive">
                <tbody>
                <c:forEach var="user" items="${requestScope.userList}">
                    <tr>
                        <td>${user.nickname}</td>
                        <td>
                            <form action="/adminMenu" method="get">
                                <input type="hidden" name="command" value="BAN_USER">
                                <input type="hidden" name="userId" value="${user.id}">
                                <select class="selectpicker" name="ban">
                                    <option value="NULL">${nothing}</option>
                                    <c:forEach var="ban" items="${requestScope.banList}">
                                        <option value="${ban.id}">${ban.name}</option>
                                    </c:forEach>
                                </select>
                                <c:if test="${sessionScope.messageCode == 6}">
                                    <h5 class="error-servlet">${problemEdit}</h5>
                                </c:if>
                                <button type="submit" class="btn btn-default">${confornim}</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<footer class="container-fluid context-centre">
    <p>Footer Text</p>
</footer>

<script src="res/js/validate.js"></script>
</body>
</html>
