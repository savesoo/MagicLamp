document.getElementById('sortOption').onchange = function(){
    location.href = '/mypage/cart?sortOption=' + this.value;
}

function cartChkDel(){

    let chkcart = document.getElementsByName('cartchk');
    const sortOption = document.getElementById("sortOption").value;
    let cartList = [];

    chkcart.forEach( (checkbox) => {
        if(checkbox.checked) {
            if($(checkbox).attr('type') != 'text'){
                cartList.push(checkbox.value);
            }
        }
    })

    cartDelBtnClick(cartList, sortOption);
}

function cartDelBtnClick(cartindex, sortOption){

    let sort = sortOption;

    if(cartindex.length == 0){
        alert("선택된 데이터가 없습니다");
        return;
    }

    if(confirm("삭제하시겠습니까?")) {
        axios.delete('cart/' + cartindex)
            .then(res => {
                if (res.data > 0) {
                    alert('삭제 되었습니다.')
                    location.href = '/mypage/cart?sortOption=' + document.getElementById("sortOption").value;
                } else {
                    alert('삭제 되지 않았습니다.')
                }
            })
            .catch(err => console.log(err))
    }
}

// 단건결제
// function cartChkOrder(){
//
//     let chkcart = document.getElementsByName('cartchk');
//     // let infoList = [];
//     let bookinfo = {};
//     chkcart.forEach( (checkbox) => {
//         if(checkbox.checked) {
//             if($(checkbox).attr('type') != 'text'){
//
//                 bookinfo = {
//                     isbn : checkbox.parentNode.childNodes[3].value,
//                     bookcount : checkbox.parentNode.parentNode.childNodes[7].childNodes[1].value
//                 }
//                 // infoList.push(bookinfo);
//             }
//         }
//     })
//
//     if(isEmptyObj(bookinfo)){
//         alert("선택된 데이터가 없습니다");
//         return;
//     }
//
//     location.href="/order?isbn="+bookinfo.isbn+"&bookcount="+bookinfo.bookcount;
// }

function cartListOrder(){
    let chkcart = document.getElementsByName('cartchk');
    let infoList = [];
    let bookinfo = {};
    chkcart.forEach( (checkbox) => {
        if(checkbox.checked) {
            if($(checkbox).attr('type') != 'text'){

                bookinfo = {
                    isbn : checkbox.parentNode.childNodes[3].value,
                    bookcount : checkbox.parentNode.parentNode.childNodes[9].childNodes[1].value
                }
                infoList.push(bookinfo);
            }
        }
    })

    if(infoList.length==0){
        alert("선택된 데이터가 없습니다");
        return;
    }

    console.log(infoList);

    axios.post('/order', infoList)
        .then(res => {
            location.href="/order";
        })
        .catch(err => console.log(err))

}

document.getElementById("cartAllSelect").onclick = function(){
    const cartCheckBox = document.getElementsByName('cartchk');
    const cartAllBtn = document.getElementById('cartAllBtn');

    cartCheckBox.forEach((checkbox) => {
        checkbox.checked = this.checked;
        cartAllBtn.checked = this.checked;
    });
}

document.getElementById("cartAllBtn").onclick = function (){
    const cartChkBox = document.getElementsByName('cartchk');
    const cartAllSelect = document.getElementById('cartAllSelect');

    cartChkBox.forEach((checkbox) => {
        checkbox.checked = this.checked;
        cartAllSelect.checked = this.checked;
    })
}

document.getElementById('bookcount').onchange = function(){

    const payload ={
        cartindex : this.parentNode.parentNode.childNodes[1].childNodes[1].value,
        bookcount : this.value,
        isbn : this.parentNode.parentNode.childNodes[1].childNodes[3].value
    }

    axios.put('cart/', payload)
        .then(res => {
            if(res.data.result == 0){
                alert("현재 선택하신 수량보다 재고 수량이 부족 합니다 (재고 수량 :" + res.data.stock +")")
                document.getElementById('bookcount').value = res.data.stock;
            }
        })
        .catch(err => console.log(err))

}

// document.getElementById('cartAllCheckBtn').onclick = function() {
//     const cartAllBtn = document.getElementById('cartAllBtn');
//     const cartChkBox = document.getElementsByName('cartchk');
//     const cartAllSelect = document.getElementById('cartAllSelect');
//
//     if(cartAllBtn.checked){
//         cartChkBox.forEach((checkbox) => {
//             cartAllBtn.checked = false;
//             checkbox.checked = cartAllBtn.checked;
//             cartAllSelect.checked = cartAllBtn.checked;
//         })
//     }
//     else {
//         cartChkBox.forEach((checkbox) => {
//             cartAllBtn.checked = true;
//             checkbox.checked = cartAllBtn.checked;
//             cartAllSelect.checked = cartAllBtn.checked;
//         })
//     }
// }

function isEmptyObj(obj){
    if(obj.constructor == Object && Object.keys(obj).length == 0){
        return true;
    }
    return false;
}