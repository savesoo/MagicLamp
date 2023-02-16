/*이벤트 연결*/
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector(".usemil_btn").addEventListener('click', useMileage);
    document.querySelector('.order_btn').addEventListener('click', moveToPayment);
});

function checkNumberAndPrice(element) {
    let check = /^[0-9]+$/;

    if(!check.test(element)) {
        alert('정수 단위의 숫자만 입력 가능합니다.');
        return null;

    } else if(element>myMileage){
        alert('보유하신 마일리지보다 많이 사용할 수 없습니다.');
        return myMileage;
    }

    return element;
}

<!--마일리지 사용 버튼-->
function  useMileage() {

    const m = checkNumberAndPrice(document.getElementsByName("usemil")[0].value); // 사용할 마일리지

    // 입력된 사용금액(m)만큼 차감
    if(document.getElementsByName("usemil")[0].value == '0') {
        console.log("000000");
        document.querySelector("#used_mileage").innerText = m.toLocaleString('ko-KR');
    }else{
        document.querySelector("#used_mileage").innerText = "-"+m.toLocaleString('ko-KR');
    }
    document.querySelector("#my_mileage").innerText = (myMileage - m).toLocaleString();
    document.querySelector("#calPrice").innerText = (viewSalePriceSum - m).toLocaleString();
    document.querySelector("#resultPrice").innerText = (viewSalePriceSum - m).toLocaleString();
    document.getElementsByName("usemil")[0].value = m;

    // 입력된 사용금액(m)만큼 차감
    // document.querySelector("#used_mileage").innerText = m.toLocaleString();
    // document.querySelector("#my_mileage").innerText = (myMileage - m).toLocaleString();
    // document.querySelector("#calPrice").innerText = (viewPriceSum - m).toLocaleString();
    // document.querySelector("#resultPrice").innerText = (viewPriceSum - m).toLocaleString();
    // document.getElementsByName("usemil")[0].value = m;

    // if (myMileage === 0) {
    //     // 마일리지 없음 -> 계산 X
    //     alert("적립된 마일리지가 없습니다.");
    //     document.querySelector("#used_mileage").innerText = myMileage.toLocaleString(); // 사용한 마일리지
    //     document.querySelector("#my_mileage").innerText = myMileage.toLocaleString(); // 남은 마일리지 계산
    //     document.querySelector("#calPrice").innerText = viewPriceSum.toLocaleString(); // 총 금액에 반영
    //     document.querySelector("#resultPrice").innerText = myMileage;
    //     return;
    //
    // } else if(viewPriceSum < m){
    //     // 사용하려는 마일리지보다 결제금액이 더 적은 경우 -> 결제 금액만큼만 차감
    //     alert("결제 금액을 초과하였습니다. \n마일리지가 자동으로 차감 적용됩니다.");
    //     document.querySelector("#used_mileage").innerText = viewPriceSum.toLocaleString();
    //     document.querySelector("#my_mileage").innerText = (myMileage - viewPriceSum).toLocaleString();
    //     document.querySelector("#calPrice").innerText = (viewPriceSum - viewPriceSum).toLocaleString();
    //     document.querySelector("#resultPrice").innerText = (viewPriceSum - viewPriceSum).toLocaleString();
    //     document.getElementsByName("usemil")[0].value = viewPriceSum;
    //     return;
    //
    // }

    if (myMileage === 0) {
        // 마일리지 없음 -> 계산 X
        alert("적립된 마일리지가 없습니다.");
        document.querySelector("#used_mileage").innerText = myMileage.toLocaleString('ko-KR'); // 사용한 마일리지
        document.querySelector("#my_mileage").innerText = myMileage.toLocaleString(); // 남은 마일리지 계산
        document.querySelector("#calPrice").innerText = viewSalePriceSum.toLocaleString(); // 총 금액에 반영
        document.querySelector("#resultPrice").innerText = myMileage;
        return;

    } else if(viewSalePriceSum < m){
        // 사용하려는 마일리지보다 결제금액이 더 적은 경우 -> 결제 금액만큼만 차감
        alert("결제 금액을 초과하였습니다. \n마일리지가 자동으로 차감 적용됩니다.");
        document.querySelector("#used_mileage").innerText = "-"+viewSalePriceSum.toLocaleString('ko-KR');
        document.querySelector("#my_mileage").innerText = (myMileage - viewSalePriceSum).toLocaleString();
        document.querySelector("#calPrice").innerText = (viewSalePriceSum - viewSalePriceSum).toLocaleString();
        document.querySelector("#resultPrice").innerText = (viewSalePriceSum - viewSalePriceSum).toLocaleString();
        document.getElementsByName("usemil")[0].value = viewSalePriceSum;
        return;

    }

}

<!--주문 버튼-->
function moveToPayment(){

    const addrname = document.getElementById('addrname');
    const recipient = document.querySelector("#recipient");
    const postnum = document.querySelector("#postnum");
    const address1 = document.querySelector("#address1");
    const address2 = document.querySelector("#address2");
    const phone = document.querySelector("#phone");
    const addrSelect = $("input[name='addrSelect']:checked").val();

    console.log(addrSelect);
    console.log(addrname.value);

    if(!formTextCheck(recipient)
        || !formNumCheck(postnum)
        || !formCheck(address2)
        || !formNumCheck(phone)
    ){
        return;
    }

    const payload = {

        userindex : loginInfo,

        bookInfos : ordersInfo,

        addrSelect: addrSelect,

        addrname : document.getElementById('addrname').value,
        recipient: recipient.value,
        postnum: postnum.value,
        address1: address1.value,
        address2: address2.value,
        phone: phone.value,

        mileage: myMileage,
        usemileage : parseInt(document.getElementsByName("usemil")[0].value),

        totalBookCnt: document.querySelector("#totalBookCnt").value,
        totalSaveMileage: document.querySelector('#saveMileage').value,
        orderTotalPrice: viewSalePriceSum
    }


    axios.post('/order/payment', payload)
        .then(res => {

            console.log("post > response", res.data);

            if(res.data==='' || res.data===null){
                console.log(res.data);
                alert("재고 부족으로 현재는 주문할 수 없습니다.");
                location.href = '/';
                return;
            }

            alert("결제가 완료되었습니다.");
            location.href = '/main';

        })
        .catch(err => {
            console.log(err);
            alert("결제 과정에서 문제가 발생하였습니다.");
            location.reload();
        });


}

document.getElementById('addridxOption').onchange = function (){
    const addrval = this.value;

    addrList.forEach(function(addr){

        if(addr.addrindex == addrval){
            document.getElementById('recipient').value = addr.recipient;
            document.getElementById('postnum').value = addr.postnum;
            document.getElementById('address1').value = addr.address1;
            document.getElementById('address2').value = addr.address2;
            document.getElementById('phone').value = addr.phone;

            // console.log(addr);
            return false;
        }
    });
}

$("input[name='addrSelect']").change(function (){

    let chkVal = $(this).val();
    let recipient = document.getElementById('recipient');
    let postnum = document.getElementById('postnum');
    let address1 = document.getElementById('address1');
    let address2 = document.getElementById('address2');
    let phone = document.getElementById('phone');
    let addrname = document.getElementById('addrname');

    if(chkVal == 1){
        document.getElementById('addridxOption').style.display = 'none';
        document.getElementById('addrSearchBtn').style.display = 'none';
        document.getElementById('addrname').style.display = 'none';
        addrList.forEach(function(addr){
            if(addr.priority == 1){
                recipient.value = addr.recipient;
                postnum.value = addr.postnum;
                address1.value = addr.address1;
                address2.value = addr.address2;
                phone.value = addr.phone;
                addrname.value = addr.addrname;
                return false;
            }
        });
    }
    else if(chkVal == 0){
        let addridxOption = document.getElementById('addridxOption');
        let addrSelectVal = addridxOption.value;
        document.getElementById('addrSearchBtn').style.display = 'none';
        document.getElementById('addrname').style.display = 'none';
        addridxOption.style.display = 'inline';
        addrList.forEach(function(addr) {
            if (addr.addrindex == addrSelectVal) {
                recipient.value = addr.recipient;
                postnum.value = addr.postnum;
                address1.value = addr.address1;
                address2.value = addr.address2;
                phone.value = addr.phone;
                addrname.value = addr.addrname;
                return false;
            }
        });
    }

    else if(chkVal == 9){
        document.getElementById('addridxOption').style.display = 'none';
        document.getElementById('addrSearchBtn').style.display = 'inline';
        document.getElementById('addrname').style.display = 'inline';
        recipient.value = "";
        postnum.value = "";
        address1.value = "";
        address2.value = "";
        phone.value = "";
        addrname.value="";
    }
});

document.getElementById('addrname').onblur = function(){
    const addrname = document.getElementById('addrname');

    axios.get('/mypage/addrchk/'+addrname.value)
        .then(res => {
            if(res.data > 0){
                alert("중복되는 배송지 이름이 존재합니다.");
                return;
            }
        })
        .catch(err => {
            console.log(err);
        });
}
