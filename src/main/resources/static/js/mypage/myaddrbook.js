document.addEventListener('DOMContentLoaded', () => {

    $("#addrAllSelect").click(function() {
        if($("#addrAllSelect").is(":checked")) $("input[name=addrchk]").prop("checked", true);
        else $("input[name=addrchk]").prop("checked", false);
    });

    $("input[name=addrchk]").click(function() {
        var total = $("input[name=addrchk]").length;
        var checked = $("input[name=addrchk]:checked").length;

        if(total != checked) $("#addrAllSelect").prop("checked", false);
        else $("#addrAllSelect").prop("checked", true);
    });

    $("button[name=addrModifyBtn]").click(function() {
        let modal = document.getElementById("addrModal");
        let modBtn = $(this);

        let tr = modBtn.parent().parent();
        let td = tr.children();

        let addrindex = td.eq(0).children().val();
        let addrname = td.eq(1).children().text();
        let recipient = td.eq(2).children().eq(0).text();
        let postnum = td.eq(3).children().eq(0).text().replace('(', '').replace(')', '');
        let address1 = td.eq(3).children().eq(2).text();
        let address2 = td.eq(3).children().eq(3).text();
        let phone = td.eq(3).children().eq(5).text();
        let priority = td.eq(0).children().eq(0).val();
        let userindex = td.eq(0).children().eq(1).val();

        document.querySelector('#maddrindex').value= addrindex;
        document.querySelector('#maddrname').value= addrname;
        document.querySelector('#mrecipient').value = recipient;
        document.querySelector('#mpostnum').value = postnum;
        document.querySelector('#maddress1').value = address1;
        document.querySelector('#maddress2').value = address2
        document.querySelector('#mphone').value = phone;
        document.querySelector('#mpriority').value = priority;

        document.getElementById("add").style.display='none';
        document.getElementById("modify").style.display='block';
        modal.style.display="block";

    });

    let modalCloseBtn = document.querySelector('.btn-close');
    modalCloseBtn.addEventListener('click', (event) =>{
        document.getElementById("addrModal").style.display="none";
    });

    let modalCloseBtn2 = document.querySelector('#modalClose');
    modalCloseBtn2.addEventListener('click', () => {
        document.getElementById("addrModal").style.display="none";
    })

    $("button[name=addrDeleteBtn]").click(function () {

        let delBtn = $(this);

        let tr = delBtn.parent().parent();
        let td = tr.children();
        let chk = td.children();
        let addrindex = chk.val();
        let keyword = document.querySelector('#keyword').value;
        let pagenum = document.querySelector('#pagenum').value;

        addrDelete(addrindex, pagenum, keyword);

    });

})

function addrChkDelete(){

    let chkAddr = document.getElementsByName('addrchk');
    let addrList = [];
    let keyword = document.querySelector('#keyword').value;
    let pagenum = document.querySelector('#pagenum').value;

    chkAddr.forEach( (checkbox) => {
        if(checkbox.checked) {
            if($(checkbox).attr('type') != 'text'){
                addrList.push(checkbox.value);
            }
        }
    })

    addrDelete(addrList, pagenum, keyword);
}

function addrDelete(addrindex, pagenum, keyword){

    if(addrindex.length == 0){
        alert("선택된 데이터가 없습니다");
        return;
    }

    if(confirm("삭제하시겠습니까?")) {
        axios.delete('addrbook/' + addrindex)
            .then(res => {
                if (res.data == 1) {
                    alert('삭제 되었습니다.')
                    location.href = '/mypage/addrbook?page=' + pagenum + '&keyword=' + keyword;
                } else {
                    alert('삭제 되지 않았습니다.')
                }
            })
            .catch(err => console.log(err))
    }
}

function addrModify(){

    let modal = document.getElementById("addrModal");
    let keyword = document.querySelector('#keyword').value;
    let pagenum = document.querySelector('#pagenum').value;
    let priority = document.querySelector('#mpriority').value;

    const addrbook = {
        addrindex : document.querySelector('#maddrindex').value,
        addrname : document.querySelector('#maddrname').value,
        recipient : document.querySelector('#mrecipient').value,
        postnum : document.querySelector('#mpostnum').value,
        address1 : document.querySelector('#maddress1').value,
        address2 : document.querySelector('#maddress2').value,
        phone : document.querySelector('#mphone').value,
        priority : document.querySelector('#mpriority').value,
        userindex : document.querySelector('#muserindex').value
    }

    axios.put('addrbook/', addrbook)
        .then( res => {
/*          // 비동기 처리
            let chkAddr = document.getElementsByName('addrchk');
            let addrTarget;
            chkAddr.forEach( (checkbox) => {
                if(checkbox.value==addrbook.addrindex){
                    addrTarget = checkbox;
                    let uptaddrname = addrTarget.parentElement.parentElement.children[1].childNodes[0];
                    let uptrecipient = addrTarget.parentElement.parentElement.children[2].childNodes[1];
                    let uptpostnum = addrTarget.parentElement.parentElement.children[3].childNodes[1];
                    let uptaddress1 = addrTarget.parentElement.parentElement.children[3].childNodes[5];
                    let uptaddress2 = addrTarget.parentElement.parentElement.children[3].childNodes[7];
                    let uptphone = addrTarget.parentElement.parentElement.children[3].childNodes[11];

                    uptaddrname.textContent = res.data.addrname;
                    uptrecipient.textContent = res.data.recipient;
                    uptpostnum.textContent = res.data.postnum;
                    uptaddress1.textContent = res.data.address1;
                    uptaddress2.textContent = res.data.address2;
                    uptphone.textContent = res.data.phone;
                }
            })*/
            modal.style.display='none';

            location.href = '/mypage/addrbook?page=' + pagenum + '&keyword=' + keyword;
        })
        .catch(err => console.log(err))

}

function newAddr(){
    let modal = document.getElementById("addrModal");

    document.getElementById("modify").style.display='none';
    document.getElementById("add").style.display='block';
    modal.style.display="block";

    document.querySelector('#maddrname').value = null;
    document.querySelector('#mrecipient').value = null;
    document.querySelector('#mpostnum').value = null;
    document.querySelector('#maddress1').value = null;
    document.querySelector('#maddress2').value = null;
    document.querySelector('#mphone').value = null;

}

function addrAdd(){

    const addrbook = {
        addrindex : document.querySelector('#maddrindex').value,
        addrname : document.querySelector('#maddrname').value,
        recipient : document.querySelector('#mrecipient').value,
        postnum : document.querySelector('#mpostnum').value,
        address1 : document.querySelector('#maddress1').value,
        address2 : document.querySelector('#maddress2').value,
        phone : document.querySelector('#mphone').value
    }

    axios.post('addrbook/', addrbook)
        .then(res => {

            if(res.data == 0){
                alert("배송지 추가에 실패했습니다.")
            }
            else{
                location.href = '/mypage/addrbook';
            }
        })
        .catch(err => console.log(err))
}

function postSearch() {
    new daum.Postcode({
        oncomplete: function(data) {
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
            document.getElementById('mpostnum').value = data.zonecode;
            document.getElementById("maddress1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("maddress2").focus();
        }
    }).open();
}
