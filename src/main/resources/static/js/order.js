let totalPrice = document.getElementsByName("orderTotalPrice")[0].value; /*[[${ordersInfo.orderTotalPrice}]]*/
let myMileage = document.getElementsByName("mileage")[0].value; /*[[${ordersInfo.mileage}]]*/

/*이벤트 연결*/
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector(".usemil_btn").addEventListener('click', useMileage);
    document.querySelector('.order_btn').addEventListener('click', moveToPayment);
});

<!--마일리지 사용 버튼-->
function  useMileage() {

    const m = document.getElementsByName("usemil")[0].value // 사용할 마일리지

    document.getElementsByName("usemil")[0].innerText = m.toLocaleString();
    document.querySelector("#input_saled").innerText = m.toLocaleString(); // 사용한 마일리지
    document.querySelector("#my_mileage").innerText = (myMileage-m).toLocaleString(); // 현 마일리지 계산
    document.querySelector("#calprice").innerText = (totalPrice-m).toLocaleString(); // 총 금액에 반영
    document.querySelector("#resultprice").innerText = (totalPrice-m).toLocaleString();

    // 예외 처리
    if (myMileage === 0){
        // 마일리지 없음 -> 계산 X
        alert("적립된 마일리지가 없습니다.");
        document.querySelector("#input_saled").innerText = myMileage.toLocaleString();
        document.querySelector("#my_mileage").innerText = myMileage.toLocaleString();
        document.querySelector("#calprice").innerText = totalPrice.toLocaleString();
        document.querySelector("#resultprice").innerText = totalPrice.toLocaleString();
        return;
    }

}


<!--주문 버튼-->
function moveToPayment() {

    const form = document.querySelector('.order_form');

    document.getElementsByName("usemileage")[0].value = parseInt(document.getElementsByName('usemil')[0].value);

    form.submit();

}
