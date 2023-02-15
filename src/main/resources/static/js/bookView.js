const desc = document.getElementById('book_description')
desc.textContent = desc.textContent.replaceAll('&lt;', '<')
desc.textContent = desc.textContent.replaceAll('&gt;', '>')
desc.textContent = desc.textContent.replaceAll('&#8212;', 'ㅡ')

// 숫자
function formNumCheck(number) {

    let pattern = /^[0-9]+$/;

    if( number.value == '' || number.value.trim()== '' || number.value==0){
        alert('1 이상의 값을 입력해주세요.');
        return false;
    }

    if (!pattern.test(number.value)){
        alert('숫자만 입력해주세요.');
        return false;
    }

    return true;
}

function count(type) {
    // 결과를 표시할 element
    const resultEl = document.getElementById('result');
    const resultTotal = document.getElementById('totalPrice');
    const dirQty = document.getElementById('dirQty');

    if(!formNumCheck(resultEl)){
        resultEl.value = 1;
    }

    // 현재 화면에 표시된 값
    let number = resultEl.value;

    console.log('resultEl.value ==> ', resultEl.value);
    console.log('type ==> ', type);

    // 더하기/빼기
    if (type === 'plus') {
        number = parseInt(number) + 1;
    } else if (type === 'minus') {
        number = parseInt(number) - 1;
        if (number < 1) {
            return;
        }
    }

    // 결과 출력
    resultEl.value = number;
    dirQty.value = number;
    resultTotal.innerText = (price * number).toLocaleString() + '원';
    if ((price * number) > price) resultTotal.setAttribute('style', 'font-size: 24px; color: #3459e6')
    else resultTotal.setAttribute('style', '')
    console.log('dirQty.value ==> ', dirQty.value);
    dirQty.setAttribute('href', '/order?isbn=' + isbn + '&bookcount=' + number);
    qty = number;

}

function countQty() {
    const resultEl = document.getElementById('result');
    const resultTotal = document.getElementById('totalPrice');
    const dirQty = document.getElementById('dirQty');

    if(!formNumCheck(resultEl)){
        resultEl.value = 1;
    }

    let number = resultEl.value;
    resultEl.value = number;
    dirQty.value = number;
    resultTotal.innerText = (price * number).toLocaleString() + '원';
    if ((price * number) > price) resultTotal.setAttribute('style', 'font-size: 24px; color: #3459e6')
    else resultTotal.setAttribute('style', '')
    console.log('dirQty.value ==> ', dirQty.value);
    dirQty.setAttribute('href', '/order?isbn=' + isbn + '&bookcount=' + number);
    qty = number;
}

function insertOneCart(dat) {

    /*        쿠키 생성 loging 컨트롤러에서 처리함
            console.log('window.location.href ===> ', window.location.href.substring(21));

            let date = new Date();
            date.setTime(date.getTime() + 24*60*60*1000);
            document.cookie = 'preUrl' + '=' + window.location.href.substring(21) + ';expires=' + date.toUTCString() + ';path=/';

            console.log('preUrl ===> ', document.cookie);*/

    loginCheckCh();

    if (loginInfo > 0) {

        let payload = {
            userindex: loginInfo,
            isbn: dat.value,
            bookcount: qty
        }

        console.log('isbn ===> ', dat.value);

        console.log('payload => ', payload);

        $.ajax({
            url: '/cart',
            type: 'post',
            data: JSON.stringify(payload),
            dataType: 'json',
            contentType: 'application/json',
            success: function (res) {
                console.log('카트 등록 성공!!!');
                if (confirm("장바구니로 이동하시겠니까?")) {
                    location.href = '/mypage/cart';
                }
            },
            error: function (error) {
                console.log("카트 등록 실패!!", error);
                alert('이미 카트에 담겨 있는 도서들입니다.');
            }
        });
    }
}