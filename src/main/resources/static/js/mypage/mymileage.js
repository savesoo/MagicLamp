document.getElementById('keyword').onchange = function (){
    let page = document.getElementById('pagenum').value;
    let keyword = this.value;

    console.log(page);
    console.log(this.value);

    location.href='/mypage/mileage?keyword='+keyword;
    // location.href='/mypage/mileage?page='+page+'&keyword='+keyword;
};