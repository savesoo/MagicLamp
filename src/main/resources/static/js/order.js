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
    document.querySelector("#used_mileage").innerText = m.toLocaleString();
    document.querySelector("#my_mileage").innerText = (myMileage - m).toLocaleString();
    document.querySelector("#calPrice").innerText = (viewPriceSum - m).toLocaleString();
    document.querySelector("#resultPrice").innerText = (viewPriceSum - m).toLocaleString();
    document.getElementsByName("usemil")[0].value = m;

    if (myMileage === 0) {
        // 마일리지 없음 -> 계산 X
        alert("적립된 마일리지가 없습니다.");
        document.querySelector("#used_mileage").innerText = myMileage.toLocaleString(); // 사용한 마일리지
        document.querySelector("#my_mileage").innerText = myMileage.toLocaleString(); // 남은 마일리지 계산
        document.querySelector("#calPrice").innerText = viewPriceSum.toLocaleString(); // 총 금액에 반영
        document.querySelector("#resultPrice").innerText = myMileage;
        return;

    } else if(viewPriceSum < m){
        // 사용하려는 마일리지보다 결제금액이 더 적은 경우 -> 결제 금액만큼만 차감
        alert("결제 금액을 초과하였습니다. \n마일리지가 자동으로 차감 적용됩니다.");
        document.querySelector("#used_mileage").innerText = viewPriceSum.toLocaleString();
        document.querySelector("#my_mileage").innerText = (myMileage - viewPriceSum).toLocaleString();
        document.querySelector("#calPrice").innerText = (viewPriceSum - viewPriceSum).toLocaleString();
        document.querySelector("#resultPrice").innerText = (viewPriceSum - viewPriceSum).toLocaleString();
        document.getElementsByName("usemil")[0].value = viewPriceSum;
        return;

    }

}


<!--주문 버튼-->
function moveToPayment(){

    const payload = {

        userindex : loginInfo,

        bookInfos : ordersInfo,

        recipient: document.querySelector("#recipient").value,
        postnum: document.querySelector("#postnum").value,
        address1: document.querySelector("#address1").value,
        address2: document.querySelector("#address2").value,
        phone: document.querySelector("#phone").value,

        mileage: myMileage,
        usemileage : parseInt(document.getElementsByName("usemil")[0].value),

        totalBookCnt: document.querySelector("#totalBookCnt").value,
        totalSaveMileage: document.querySelector('#saveMileage').value,
        orderTotalPrice: viewPriceSum
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
            location.href = '/view/book/bookMainList';

        })
        .catch(err => {
            console.log(err);
            alert("결제 과정에서 문제가 발생하였습니다.");
            location.reload();
        });


}
