let editModal;
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

        let modBtn = $(this);

        let tr = modBtn.parent().parent();
        let td = tr.children();
        let addrindex = td.eq(0).children();

        // console.log(addrindex.val());
        // console.log(td.eq(1).children().eq(0).text());
        console.log(td.eq(2).children().eq(0).text());
        console.log(td.eq(2).children().eq(2).text());
        console.log(td.eq(2).children().eq(3).text());
        console.log(td.eq(2).children().eq(5).text());

        let postnum = td.eq(2).children().eq(0).text().replace('(', '').replace(')', '');

        // console.log(postnum);

    });

    $("button[name=addrDeleteBtn]").click(function () {

        let str = "";
        let tdArr = new Array();
        let delBtn = $(this);

        let tr = delBtn.parent().parent();
        let td = tr.children();
        let chk = td.children();

        console.log(chk.val());

    });

})