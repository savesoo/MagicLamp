document.getElementById('searchBtn').onclick = function(){
    let sdata = document.getElementById('sDate').value;
    let edata = document.getElementById('eDate').value;
    let text = document.getElementById('searchText').value;

    let sdate = new Date(sdata);
    let edate = new Date(edata);

    console.log(sdate);
    console.log(edate);
    console.log(text);

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

// document.getElementById('searchOption').onchange = function(){
//     let keyword = this.value;
//
//     console.log(this.value);
// }

document.getElementById('initBtn').onclick = function (){
    document.getElementById('sDate').value = "";
    document.getElementById('eDate').value = "";
    document.getElementById('searchText').value = "";
    document.getElementById('searchOption').value = "0";
}