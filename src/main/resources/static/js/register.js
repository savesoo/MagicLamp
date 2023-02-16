document.addEventListener('DOMContentLoaded', () => {
    document.querySelector('#signup').addEventListener('click', (event) => {
        const username = document.querySelector('#userId');
        const password = document.querySelector('#userPw');
        const repw = document.querySelector('#rePw');
        const name = document.querySelector('#name');
        const ballon = document.querySelector('#id_helplayer')
        const ballontxt = document.querySelector('#CustId_result')

        let itemEntity = {
            username: username.value,
            password: password.value,
            name: name.value,
            phone: document.querySelector('#phone').value,
            postnum: document.querySelector('#postnum').value,
            address1: document.querySelector('#address1').value,
            address2: document.querySelector('#address2').value
        };

        if(ballon.style.display == 'block' && ballontxt.textContent.length > 0){
            alert(ballontxt.textContent)
            username.focus()
            return;
        }

        if(password.value.length < 8){
            alert('비밀번호는 영문, 숫자, 특수문자 중 2가지 조합하여 8자 이상으로 설정해주세요.')
            password.focus()
            return
        }

        if (password.value != repw.value) {
            alert('위에서 입력한 비밀번호와 동일하게 입력해주세요.')
            repw.focus()
            return
        }

        axios.post('/register', itemEntity)
            .then(function (response) {
                console.log(response);
                location.href = response.data;
            }).catch(function (error) {
            alert('회원가입에 실패했습니다.');
            console.log(error);
        });
    });
});

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postnum').value = data.zonecode;
            document.getElementById("address1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("address2").focus();
        }
    }).open();
}

function IDCheck() {
    const username = document.querySelector('#userId').value
    const ballon = document.querySelector('#id_helplayer')
    const ballontxt = document.querySelector('#CustId_result')
    if (username.length < 4 || username.length > 12) {
        ballon.style.display = 'block'
        ballontxt.textContent = '아이디 글자수는 4자~12자 이어야 합니다.'
    } else {
        const promise = axios.post('/register/idCheck/' + username).then(response => response.data)
        promise.then(result => {
            if (result == 1) {
                ballon.style.display = 'block'
                ballontxt.textContent = '이미 사용중인 아이디입니다.'
            } else {
                ballon.style.display = 'none'
                const pattern = new RegExp("^[0-9a-z]{3,11}$")
                if (!pattern.test(username)) {
                    ballon.style.display = 'block'
                    ballontxt.textContent = '아이디는 영문 소문자와 숫자로만 입력해주세요'
                }
            }
        })
    }
}

function PWCheck() {
    const pw = document.querySelector('#userPw').value
    const ballon = document.querySelector('#password_helplayer')
    const ballontxt = document.querySelector('#password_result')
    if (pw.length < 8) {
        ballon.style.display = 'block'
        ballontxt.innerHTML = '비밀번호 안전성 : 취약' + '<br>' + '- 비밀번호는 영문, 숫자, 특수문자 중 2가지 조합하여 8자 이상으로 설정해주세요.'
    } else {
        const checkNumber = pw.search(/[0-9]/g)
        const checkEnglish = pw.search(/[a-zA-Z]/g)
        const checkSpecial = pw.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g)
        if (checkNumber < 0 && checkSpecial < 0 || checkNumber < 0 && checkEnglish < 0 || checkSpecial < 0 && checkEnglish < 0) {
            ballon.style.display = 'block'
            ballontxt.innerHTML = '비밀번호 안전성 : 취약' + '<br>' + '- 영문대소문자/숫자/특수문자 중 2가지 이상 조합이어야 합니다.'
        } else if (pw.length < 10) {
            ballon.style.display = 'block'
            ballontxt.innerHTML = '비밀번호 안전성 : 낮음' + '<br>' + '- 영문대소문자/숫자/특수문자 중 2가지 이상 조합인 경우 10자 이상을 권장하고 있습니다.'
        } else {
            ballon.style.display = 'block'
            ballontxt.textContent = '비밀번호 안전성 : 보통'
            setTimeout(() => {
                ballon.style.display = 'none'
            }, 5000)
        }

    }
}

function PWDoubleCheck() {
    const userpw = document.querySelector('#userPw').value
    const userpw2 = document.querySelector('#rePw').value
    const ballon = document.querySelector('#passwordVerify_helplyaer')
    if (userpw != userpw2) {
        const ballontxt = document.querySelector('#PasswordVerify_result')
        ballon.style.display = 'block'
        ballontxt.textContent = '위에서 입력한 비밀번호와 동일하게 입력해주세요.'
    } else ballon.style.display = 'none'
}

function nameCheck() {
    const username = document.querySelector('#name').value
    const ballon = document.querySelector('#Name_helplyaer')
    if (username == '') {
        ballon.style.display = 'block'
        document.querySelector('#Name_result').textContent = '이름을 입력해주세요.'
    } else ballon.style.display = 'none'
}

function phoneNumberCheck() {
    const phoneNumber = document.querySelector('#phone').value
    const ballon = document.querySelector('#phoneNumber_helplyaer')
    const ballontxt = document.querySelector('#phoneNumber_result')
    const pattern = new RegExp("^01([0|1|6|7|8|9])([0-9]{4})([0-9]{4})$")
    if (!pattern.test(phoneNumber)) {
        ballon.style.display = 'block'
        ballontxt.textContent = '휴대전화 번호를 정확하게 입력해주세요'
    } else ballon.style.display = 'none'
}

function detailAddressCheck() {
    const ballon = document.querySelector('#address_helplyaer')
    const ballontxt = document.querySelector('#address_result')

    ballon.style.display = 'block'
    ballontxt.textContent = '상세주소를 입력해주세요'
    setTimeout(() => {
        ballon.style.display = 'none'
    }, 3000)
}