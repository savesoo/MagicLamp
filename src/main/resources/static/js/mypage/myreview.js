const myreview_text = document.querySelectorAll('#myreview_text')
for (i = 0; i < myreview_text.length; i++){
    const splitByBr = myreview_text[i].textContent.split('<br/>')
    myreview_text[i].innerHTML = ''
    for (j = 0; j < splitByBr.length; j++) {
        myreview_text[i].innerHTML.replaceAll('&lt;', '<')
        myreview_text[i].innerHTML.replaceAll('&gt;', '>')
        myreview_text[i].innerHTML += splitByBr[j] + '<br/>'
    }
}

document.getElementById('searchBtn').onclick = function(){
    let sdata = document.getElementById('sDate').value;
    let edata = document.getElementById('eDate').value;

    let sdate = new Date(sdata);
    let edate = new Date(edata);

    if(sdata.length ==0 && edata.length==0){
        document.getElementById('searchForm').submit();
    }

    else if(sdate <= edate){
        document.getElementById('searchForm').submit();
    }
    else {
        alert("시작일자 또는 종료일자를 확인해주세요");
        return;
    }
}

document.getElementById('initBtn').onclick = function (){
    document.getElementById('sDate').value = "";
    document.getElementById('eDate').value = "";
    document.getElementById('searchTitle').value = "";
}

document.getElementById('modalclosebtn').onclick = function() {
    document.getElementById("reviewModal").style.display="none";
}

function updateBtnClick(data){
    document.getElementById('mreviewcontent').value = data.reviewcontent.replaceAll('<br/>', '\r\n');
    document.getElementById('mreviewindex').value = data.reviewindex;
    document.getElementById('misbn').value = data.isbn;
    document.getElementById('mreviewer').value = data.reviewer;
    document.getElementById('mregdate').value = data.regdate;

    const starList = document.getElementsByName('mstar');

    starList.forEach((star) => {
        if(star.value == data.star)  {
            star.checked = true;
        }
    })

    let modal = document.getElementById("reviewModal");

    modal.style.display='block';
}

document.getElementById('reviewUpt').onclick = function (){
    const starList = document.getElementsByName('mstar');
    let starPoint = 0;

    starList.forEach((node) => {
        if(node.checked)  {
            starPoint = node.value;
        }
    })

    const payload = {
        reviewindex : document.getElementById('mreviewindex').value,
        isbn : document.getElementById('misbn').value,
        reviewer : document.getElementById('mreviewer').value,
        star : starPoint,
        reviewcontent : document.getElementById('mreviewcontent').value.replace(/(?:\r\n|\r|\n)/g, '<br/>'),
        regdate : document.getElementById('mregdate').value
    }

    console.log(payload.star);

    axios.put('/review/' + payload.reviewindex, payload)
        .then(res => {
            location.reload()
        }).catch(err => console.log(err))
}

// document.getElementById('reviewDel').onclick = function () {
//     const payload ={
//         reviewindex : document.getElementById('mreviewindex').value
//     }
//
//     reviewDelBtnClick(payload);
// }

function reviewDelBtnClick(data){
    let reviewindex = data.reviewindex;

    if(confirm("삭제하시겠습니까?")){
        axios.delete('/review/' + reviewindex)
            .then(res => {
                location.reload()
            })
            .catch(err => console.log(err))
    }
}