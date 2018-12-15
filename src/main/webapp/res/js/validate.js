var regexForLogin = /^[a-zA-Z](.[a-zA-Z0-9_-]*)$/;
var passwordRegex = /^[A-Za-z0-9_]{8,}$/;
var regexEmail = /^((([0-9A-Za-z]{1}[-0-9A-z\.]{1,}[0-9A-Za-z]{1}))@([-A-Za-z]{1,}\.){1,2}[-A-Za-z]{2,})$/;
var regexForGenre = /[a-zA-ZА-Яа-я ,&()]*/;
var regexForDuration = /[0-9]{2,}/;
var maxLengthDescr = 5000;

function showError(container, errorId) {
    document.getElementById(errorId).style.display = "block";
    container.style.border = "1px solid red";
}

function resetError(container, elem) {
    var arr = container.getElementsByTagName('span');
    for (var i = 0; i < arr.length; i++) {
        arr[i].style.display = "none";
    }
    elem.style.border = "0px";
}

function validateReg() {
    var arg = true;

    resetError(loginReg.parentNode, loginReg);
    if (!loginReg.value) {
        showError(loginReg, 'loginEmptyError');
        arg = false;
    } else if (loginReg.value.length < 6) {
        showError(loginReg, 'loginLengthError');
        arg = false;
    } else if (!regexForLogin.test(loginReg.value)) {
        showError(loginReg, 'loginRegexError');
        arg = false;
    }

    resetError(password.parentNode, password);
    resetError(repeatPassword.parentNode, repeatPassword);
    if (!password.value) {
        showError(password, 'passwordEmptyError');
        arg = false;
    } else if (!repeatPassword.value) {
        showError(repeatPassword, 'repeatEmptyError');
        arg = false;
    } else if (!passwordRegex.test(password.value)) {
        showError(password, 'passwordRegexError');
        arg = false;
    } else if (password.value != repeatPassword.value) {
        showError(repeatPassword, 'repeatEqualsError');
        arg = false;
    }

    resetError(email.parentNode, email);
    if (!email.value) {
        showError(email, 'emailEmptyError');
        arg = false;
    } else if (!regexEmail.test(email.value)) {
        showError(email, 'emailRegexError');
        arg = false;
    }

    resetError(nickname.parentNode, nickname);
    if (!nickname.value) {
        showError(nickname, 'nicknameEmptyError');
        arg = false;
    } else if (!nickname.value.length < 6) {
        showError(nickname, 'nicknameLengthError');
        arg = false;
    }

    return arg;
}

function validateMark() {

    var revStar = document.getElementById('reviewStars - input');
    var star1 = document.getElementById('star-1').value;
    var star2 = document.getElementById('star-2').value;
    var star3 = document.getElementById('star-3').value;
    var star4 = document.getElementById('star-4').value;
    var star5 = document.getElementById('star-5').value;
    resetError(revStar, star5);
    if (!star1 && !star2 && !star3 && !star4 && !star5) {
        showError(document.getElementById('star-1'), 'markError');
        return false;
    }
    return true;
}

function validateFilm() {

    var arg = true;

    resetError(nameFilmRu.parentNode, nameFilmRu);
    if (!nameFilmRu.value) {
        showError(nameFilmRu, 'emptyNameFilm');
        arg = false;
    }

    resetError(nameFilmEn.parentNode, nameFilmEn);
    if (!nameFilmEn.value) {
        showError(nameFilmEn, 'emptyNameFilm');
        arg = false;
    }

    resetError(genreRu.parentNode, genreRu);
    if (!genreRu.value) {
        showError(genreRu, 'emptyGenreFilm');
        arg = false;
    } else if (!regexForGenre.test(genreRu.value)) {
        showError(genreRu, 'unresolvedSymbols');
        arg = false;
    }

    resetError(genreEn.parentNode, genreEn);
    if (!genreEn.value) {
        showError(genreEn, 'emptyGenreFilm');
        arg = false;
    } else if (!regexForGenre.test(genreEn.value)) {
        showError(genreEn, 'unresolvedSymbols');
    }

    resetError(duration.parentNode, duration);
    if (!duration.value) {
        showError(duration, 'emptyDurationFilm');
        arg = false;
    } else if (!regexForDuration.test(duration.value)) {
        showError(duration, 'durationRegexError');
        arg = false;
    }

    resetError(datePr.parentNode, datePr);
    if (!datePr.value) {
        showError(datePr, 'emptyDateFilm');
        arg = false;
    }

    resetError(directorRu.parentNode, directorRu);
    if (!directorRu.value) {
        showError(directorRu, 'emptyDirectorFilm');
        arg = false;
    } else if (!regexForGenre.test(directorRu.value)) {
        showError(directorRu, 'directorRegexError');
        arg = false;
    }

    resetError(directorEn.parentNode, directorEn);
    if (!directorEn.value) {
        showError(directorEn, 'emptyDirectorFilm');
        arg = false;
    } else if (!regexForGenre.test(directorEn.value)) {
        showError(directorEn, 'directorRegexError');
        arg = false;
    }

    resetError(descrRu.parentNode, descrRu);
    if (!descrRu.value) {
        showError(descrRu, 'emptyDescriptionFilm');
        arg = false;
    } else if (descrRu.value.length > maxLengthDescr) {
        showError(descrRu, 'lengthDirectorError');
        arg = false;
    }

    resetError(descrEn.parentNode, descrEn);
    if (!descrEn.value) {
        showError(descrEn, 'emptyDescriptionFilm');
        arg = false;
    } else if (descrEn.value.length > maxLengthDescr) {
        showError(descrEn, 'lengthDirectorError');
        arg = false;
    }

    resetError(poster.parentNode, poster);
    if (!poster.value) {
        showError(poster, 'emptyPosterError');
        arg = false;
    }

    resetError(widescreen.parentNode, widescreen);
    if (!widescreen.value) {
        showError(widescreen, 'emptyWideScreenError');
        arg = false;
    }

    return arg;
}

function validateFilmEdit(id) {

    var arg = true;
    var container = document.getElementById(id).childNodes[1];

    resetError(container.nameFilmEdit.parentNode, container.nameFilmEdit);
    if (!container.nameFilmEdit.value) {
        showError(container.nameFilmEdit, 'emptyNameFilmEdit');
        arg = false;
    }

    resetError(container.ganreEdit.parentNode, container.ganreEdit);
    if (!container.ganreEdit.value) {
        showError(container.ganreEdit, 'emptyGanreFilmEdit');
        arg = false;
    } else if (!regexForGenre.test(container.ganreEdit.value)) {
        showError(container.ganreEdit, 'unresolvedSymbolsEdit');
    }

    resetError(container.durationEdit.parentNode, container.durationEdit);
    if (!container.durationEdit.value) {
        showError(container.durationEdit, 'emptyDurationFilmEdit');
        arg = false;
    } else if (!regexForDuration.test(container.durationEdit.value)) {
        showError(container.durationEdit, 'durationRegexErrorEdit');
        arg = false;
    }

    resetError(container.dateEdit.parentNode, container.dateEdit);
    if (!container.dateEdit.value) {
        showError(container.dateEdit, 'emptyDateFilmEdit');
        arg = false;
    }

    resetError(container.directorEdit.parentNode, container.directorEdit);
    if (!container.directorEdit.value) {
        showError(container.directorEdit, 'emptyDirectorFilmEdit');
        arg = false;
    } else if (!regexForGenre.test(container.directorEdit.value)) {
        showError(container.directorEdit, 'directorRegexErrorEdit');
        arg = false;
    }

    resetError(container.descrEdit.parentNode, container.descrEdit);
    if (!container.descrEdit.value) {
        showError(container.descrEdit, 'emptyDescriptionFilmEdit');
        arg = false;
    } else if (container.descrEdit.value.length > maxLengthDescr) {
        showError(container.descrEdit, 'lengthDirectorErrorEdit');
        arg = false;
    }
    return arg;
}

function validateAddUser() {
    var arg = true;

    resetError(login.parentNode, login);
    if (!login.value) {
        showError(login, 'loginEmptyError');
        arg = false;
    } else if (login.value.length < 6) {
        showError(login, 'loginLengthError');
        arg = false;
    } else if (!regexForLogin.test(login.value)) {
        showError(login, 'loginRegexError');
        arg = false;
    }

    resetError(password.parentNode, password);
    if (!password.value) {
        showError(password, 'passwordEmptyError');
        arg = false;
    } else if (!passwordRegex.test(password.value)) {
        showError(password, 'passwordRegexError');
        arg = false;
    }

    resetError(email.parentNode, email);
    if (!email.value) {
        showError(email, 'emailEmptyError');
        arg = false;
    } else if (!regexEmail.test(email.value)) {
        showError(email, 'emailRegexError');
        arg = false;
    }

    resetError(statusAdd.parentNode, statusAdd);
    if (!statusAdd.value) {
        showError(statusAdd, 'statusEmptyError');
        arg = false;
    }

    resetError(nickname.parentNode, nickname);
    if (!nickname.value) {
        showError(nickname, 'nicknameEmptyError');
        arg = false;
    } else if (!nickname.value.length < 6) {
        showError(nickname, 'nicknameLengthError');
        arg = false;
    }

    return arg;
}

function validateEditUser(id) {
    var arg = true;
    var container = document.getElementById(id).childNodes[1];

    resetError(container.loginEdit.parentNode, container.loginEdit);
    if (!container.loginEdit.value) {
        showError(container.loginEdit, 'loginEmptyErrorEdit');
        arg = false;
    } else if (container.loginEdit.value.length < 6) {
        showError(container.loginEdit, 'loginLengthErrorEdit');
        arg = false;
    } else if (!regexForLogin.test(container.loginEdit.value)) {
        showError(container.loginEdit, 'loginRegexErrorEdit');
        arg = false;
    }

    resetError(container.passwordEdit.parentNode, container.passwordEdit);
    if (!container.passwordEdit.value) {
        showError(container.passwordEdit, 'passwordEmptyErrorEdit');
        arg = false;
    } else if (!passwordRegex.test(container.passwordEdit.value)) {
        showError(container.passwordEdit, 'passwordRegexErrorEdit');
        arg = false;
    }

    resetError(container.emailEdit.parentNode, container.emailEdit);
    if (!container.emailEdit.value) {
        showError(container.emailEdit, 'emailEmptyErrorEdit');
        arg = false;
    } else if (!regexEmail.test(container.emailEdit.value)) {
        showError(container.emailEdit, 'emailRegexErrorEdit');
        arg = false;
    }

    resetError(container.statusEdit.parentNode, container.statusEdit);
    if (!container.statusEdit.value) {
        showError(container.statusEdit, 'statusEmptyErrorEdit');
        arg = false;
    }

    resetError(container.nicknameEdit.parentNode, container.nicknameEdit);
    if (!container.nicknameEdit.value) {
        showError(container.nicknameEdit, 'nicknameEmptyErrorEdit');
        arg = false;
    } else if (!container.nicknameEdit.value.length < 6) {
        showError(container.nicknameEdit, 'nicknameLengthErrorEdit');
        arg = false;
    }

    resetError(container.ban.parentNode, container.ban);
    if (!container.ban.value) {
        showError(container.ban, 'banEmptyError');
        arg = false;
    }

    return arg;
}

function validateProfile() {
    var arg = true;

    var arg = true;

    resetError(password.parentNode, password);
    if (!password.value) {
        showError(password, 'passwordEmptyError');
        arg = false;
    } else if (!passwordRegex.test(password.value)) {
        showError(password, 'passwordRegexError');
        arg = false;
    }

    resetError(email.parentNode, email);
    if (!email.value) {
        showError(email, 'emailEmptyError');
        arg = false;
    } else if (!regexEmail.test(email.value)) {
        showError(email, 'emailRegexError');
        arg = false;
    }

    resetError(nickname.parentNode, nickname);
    if (!nickname.value) {
        showError(nickname, 'nicknameEmptyError');
        arg = false;
    } else if (!nickname.value.length < 6) {
        showError(nickname, 'nicknameLengthError');
        arg = false;
    }

    return arg;
}