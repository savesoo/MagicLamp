/*이벤트 연결*/
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector(".usemil_btn").addEventListener('click', useMileage);
    document.querySelector('.order_btn').addEventListener('click', moveToPayment);
});

function checkNumberAndPrice(element) {
    let check = /^[0-9]+$/;

    if(!check.test(element)) {
        alert('정수 단위의 숫자만 입력 가능합니다.');
        return false;

    } else if(element>myMileage){
        alert('소지하신 마일리지보다 많이 사용할 수 없습니다.');
        return false;
    }

    return element;
}

<!--마일리지 사용 버튼-->
function  useMileage() {

    const m = checkNumberAndPrice(document.getElementsByName("usemil")[0].value); // 사용할 마일리지

    // 입력된 사용금액(m)만큼 차감
    document.querySelector("#used_mileage").innerText = m.toLocaleString();
    document.querySelector("#my_mileage").innerText = (myMileage - m).toLocaleString();
    document.querySelector("#calPrice").innerText = (totalPrice - m).toLocaleString();
    document.querySelector("#resultPrice").innerText = (totalPrice - m).toLocaleString();
    document.getElementsByName("usemil")[0].value = (myMileage - m);

    if (myMileage === 0) {
        // 마일리지 없음 -> 계산 X
        alert("적립된 마일리지가 없습니다.");
        document.querySelector("#used_mileage").innerText = myMileage.toLocaleString(); // 사용한 마일리지
        document.querySelector("#my_mileage").innerText = myMileage.toLocaleString(); // 남은 마일리지 계산
        document.querySelector("#calPrice").innerText = totalPrice.toLocaleString(); // 총 금액에 반영
        document.querySelector("#resultPrice").innerText = myMileage;
        return;

    } else if(totalPrice < m){
        // 사용하려는 마일리지보다 결제금액이 더 적은 경우 -> 결제 금액만큼만 차감
        alert("결제 금액을 초과하였습니다. \n마일리지가 자동으로 차감 적용됩니다.");
        document.querySelector("#used_mileage").innerText = totalPrice.toLocaleString();
        document.querySelector("#my_mileage").innerText = (myMileage - totalPrice).toLocaleString();
        document.querySelector("#calPrice").innerText = (totalPrice - totalPrice).toLocaleString();
        document.querySelector("#resultPrice").innerText = (totalPrice - totalPrice).toLocaleString();
        document.getElementsByName("usemil")[0].value = totalPrice;
        return;

    }

}


<!--주문 버튼-->
function moveToPayment() {

    // 보낼 form select
    const form = document.querySelector('.order_form');
    // 사용한 마일리지 select -> post
    document.getElementsByName("usemileage")[0].value = parseInt(document.getElementsByName("usemil")[0].value);
    form.submit();

}
