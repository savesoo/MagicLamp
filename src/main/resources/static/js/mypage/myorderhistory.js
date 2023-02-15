document.getElementById('searchBtn').onclick = function(){
    const searchForm = document.getElementById('searchForm');
    let sdata = document.getElementById('sDate').value;
    let edata = document.getElementById('eDate').value;
    let text = document.getElementById('searchText');
    let searchOption = document.getElementById('searchOption');
    let searchVal = text.value;

    let sdate = new Date(sdata);
    let edate = new Date(edata);

    if(sdata.length==0){
        if(edata.length != 0){
            alert("시작일자를 확인해주세요");
            return;
        }
    }
    else if(edata.length == 0){
        if(sdata.length != 0){
            alert("종료일자를 확인해주세요");
            return;
        }
    }

    if(sdate > edate){
        alert("시작일자 또는 종료일자를 확인해주세요");
        return;
    }

    let chk = 0;
    if(searchVal.length > 0){
        if(searchOption.value == '0' || searchOption.value == '1'){
            chk = formCheck(text);
        }
        else if(searchOption.value == '2'){
            chk = formNumCheck(text);
        }
        else if(searchOption.value == '3'){
            chk = formTextCheck(text);
        }

        console.log("222" + chk);
        if(!chk) {
            return chk;
        }
    }

    // searchForm.submit();

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

document.getElementById('searchForm').addEventListener("keydown", evt => {
    const searchForm = document.getElementById('searchForm');
    if (evt.code === "Enter"){
        let searchText = document.getElementById('searchText');
        let searchOption = document.getElementById('searchOption');
        let searchVal = searchText.value;

        let chk = 0;
        if(searchVal.length > 0){
            if(searchOption.value == '0' || searchOption.value == '1'){
                chk = formCheck(searchText);
            }
            else if(searchOption.value == '2'){
                chk = formNumCheck(searchText);
            }
            else if(searchOption.value == '3'){
                chk = formTextCheck(searchText);
            }

            console.log(chk);
            if(!chk) {
                return;
            }
        }

        // searchForm.submit();
    }
})