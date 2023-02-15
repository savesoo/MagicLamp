// 특수문자 제외
function formCheck(form) {

    let pattern = /^[0-9a-zA-Z가-힣 -.]+$/;

    if( form.value == '' || form.value.trim() == ''){
        alert('필수 입력란은 비워둘 수 없습니다.');
        form.focus();
        return false;
    }

    if (!pattern.test(form.value)){
        alert('한영대소문자 및 숫자만 입력이 가능합니다.');
        form.focus();
        return false;
    }
    return true;
}

// 한글 + 영대소문자
function formTextCheck(text) {

    let pattern = /^[a-zA-Z가-힣]+$/;

    if( text.value == '' || text.value.trim() == ''){
        alert('필수 입력란은 비워둘 수 없습니다.');
        text.focus();
        return false;
    }

    if (!pattern.test(text.value)){
        alert('한글 및 영대소문자만 입력 가능합니다.');
        text.focus();
        return false;
    }

    return true;
}

// 숫자
function formNumCheck(number) {

    let pattern = /^[0-9]+$/;

    if( number.value == '' || number.value.trim() == ''){
        alert('필수 입력란은 비워둘 수 없습니다.');
        number.focus();
        return false;
    }

    if (!pattern.test(number.value)){
        alert('숫자만 입력해주세요.');
        number.focus();
        return false;
    }

    return true;
}

function formCheckTextNum(form){
    let pattern = /^[0-9a-zA-Z가-힣]+$/;

    if( form.value == '' || form.value.trim() == ''){
        alert('필수 입력란은 비워둘 수 없습니다.');
        form.focus();
        return false;
    }

    if (!pattern.test(form.value)){
        alert('한영대소문자 및 숫자만 입력이 가능합니다.');
        form.focus();
        return false;
    }
    return true;
}

// 한글, 영문대소, 숫자, , - + 공백
function formCheckTextNumChar(form) {

    let pattern = /^[0-9a-zA-Z가-힣\s,-]+$/;

    if( form.value == '' || form.value.trim() == ''){
        alert('필수 입력란은 비워둘 수 없습니다.');
        form.focus();
        return false;
    }

    if (!pattern.test(form.value)){
        alert('한영대소문자 및 , - 또는 숫자만 입력이 가능합니다.');
        form.focus();
        return false;
    }
    return true;
}
