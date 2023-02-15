let reviewList
let pageplus = 1
let starpage = 1

const reviewtext = document.querySelector('.review_input_textarea')
const starFieldset = document.querySelector('.star_rate_touch_area > fieldset')
const promise = orderCheck();

reviewtext.addEventListener("click", loginCheck)
starFieldset.addEventListener("click", starCheck)


function loginCheck() {
    if (loginInfo < 0) {
        const answer = confirm('로그인 상태에서 가능합니다. 로그인 페이지로 이동합니다.')
        if (answer == true) {
            location.href = "/login";
        }
    } else {
        promise.then(result => {
            if (result == 0) alert('책을 구매한 회원만 리뷰 등록이 가능합니다.')
        })
    }
}

function loginCheckCh() {
    if (loginInfo < 0) {
        const answer = confirm('로그인 상태에서 가능합니다. 로그인 페이지로 이동합니다.')
        if (answer == true) {
            location.href = "/login";
        }
    }
}

function orderCheck() {
    return axios.get('/review/register/check/' + isbn + '/' + loginInfo).then(response => response.data)
}

function starCheck() {
    starFieldset.setAttribute('check', 'checked')
}

if (myReview != null) {
    const inputArea = document.querySelector('.review_input_textarea')
    const inputBtn = document.querySelector('.button_wrapper')
    inputBtn.style.display = 'none'
    inputArea.style.display = 'none'
    const myReviewContent = document.querySelector('.my_review_content')
    const myReviewSplitByBr = myReview.reviewcontent.split('<br/>')
    for (i = 0; i < myReviewSplitByBr.length; i++) {
        myReviewContent.innerHTML += myReviewSplitByBr[i] + '<br/>'
    }
    const myReviewContent_text = myReviewContent.innerHTML
    const myReviewContent_text_short = myReviewContent_text.substring(0, 145) + "..."

    const myReview_more = document.createElement('button')
    myReview_more.setAttribute('class', 'more_review')
    myReview_more.textContent = '계속 읽기'

    const visible_mycontent = document.createElement('span')
    visible_mycontent.setAttribute('class', 'visible')
    visible_mycontent.innerHTML = myReviewContent_text_short

    const hidden_mycontent = document.createElement('span')
    hidden_mycontent.setAttribute('class', 'hidden')
    hidden_mycontent.innerHTML = myReviewContent_text

    if (myReview.reviewcontent.split('<br/>').length - 1 > 5) {
        var indexOfBr = []
        var idx = myReview.reviewcontent.indexOf('<br/>')
        while (idx != -1) {
            indexOfBr.push(idx)
            idx = myReview.reviewcontent.indexOf('<br/>', idx + 1)
        }
        myReviewContent.innerHTML = '<span class="visible">' + myReview.reviewcontent.substring(0, indexOfBr[5]) + "..." + '<button class="more_review" onclick="toggle_mycontent()">계속 읽기</button></span><span class="hidden">' + myReview.reviewcontent + '</span>'
    } else if (myReviewContent_text.length >= 145) {
        myReviewContent.innerHTML = ''
        myReviewContent.appendChild(visible_mycontent)
        visible_mycontent.appendChild(myReview_more)
        myReviewContent.appendChild(hidden_mycontent)
    }

    myReview_more.addEventListener("click", toggle_mycontent)

    function toggle_mycontent() {
        myReviewContent.firstChild.className = 'hidden'
        myReviewContent.lastChild.classList.remove('hidden')
    }
}

const latest = document.querySelector('.latest')
latest.addEventListener("click", empty)
latest.addEventListener("click", changeActive)
latest.addEventListener("click", () => setReviewList(1))

const like = document.querySelector('.like')
like.addEventListener("click", empty)
like.addEventListener("click", changeActive)
like.addEventListener("click", () => {
    setOrderByLikeReviewList(1)
})

const star = document.querySelector('.star')
star.addEventListener("click", empty)
star.addEventListener("click", changeActive)
star.addEventListener("click", () => {
    setOrderByStarReviewList(1)
})

function empty() {
    reviewList.innerHTML = ''
    page = 1
    pageplus = 1
    starpage = 1
}

function changeActive() {
    const filter = document.querySelectorAll('.filter_list > a')
    filter.forEach((filter, index) => {
        filter.classList.remove('active')
    })
    const target = event.currentTarget
    target.classList.toggle('active')
}

function setOrderByLikeReviewList(pageNum) {

    axios.get('/review/like/' + isbn + '/' + pageNum)
        .then(res => {
            makeList(res, pageplus)
            const more_review_btn = document.querySelector('.more_review_button')
            more_review_btn.setAttribute('onclick', 'setOrderByLikeReviewList(++pageplus)')
        })
        .catch()
}

function setOrderByStarReviewList(pageNum) {
    axios.get('/review/star/' + isbn + '/' + pageNum)
        .then(res => {
            makeList(res, starpage)
            const more_review_btn = document.querySelector('.more_review_button')
            more_review_btn.setAttribute('onclick', 'setOrderByStarReviewList(++starpage)')
        })
        .catch()
}


document.addEventListener('DOMContentLoaded', () => {

    // 리뷰 리스트 출력 영역 캐스팅
    reviewList = document.querySelector('.review_list_wrapper > ul')

    // 리뷰 작성 버튼
    const review_btn = document.querySelector('.review_btn')

    // 버튼 이벤트 등록
    review_btn.addEventListener('click', loginCheck)
    review_btn.addEventListener('click', insertReview)

    setReviewList(1)
})

function makeList(respond, page) {
    let list = respond.data.list
    let html = ''

    const more_review_wrapper = document.querySelector('.more_review_button_wrapper')
    const more_num = document.querySelector('.more_num')

    if (page * 5 >= respond.data.total) {
        more_review_wrapper.style.display = 'none'
    } else {
        more_review_wrapper.style.display = 'block'
        if (respond.data.total - (page * 5) < 5) more_num.textContent = respond.data.total - (page * 5)
        else more_num.textContent = 5
    }

    list.forEach((review, index) => {

        const newLI = document.createElement('li')
        newLI.setAttribute('class', 'review_list')
        html = '<div class="list_left"><div class="left_contents"><div class="StarRate_Icon">'
        const like = review.cnt
        if (like == null) {
            review.cnt = 0
        }

        let star = review.star
        switch (star) {
            case 1 :
                html += '<p class="StarRate_IconFill" style="width: 20%"></p>'
                break
            case 2 :
                html += '<p class="StarRate_IconFill" style="width: 40%"></p>'
                break
            case 3 :
                html += '<p class="StarRate_IconFill" style="width: 60%"></p>'
                break
            case 4 :
                html += '<p class="StarRate_IconFill" style="width: 80%"></p>'
                break
            case 5 :
                html += '<p class="StarRate_IconFill" style="width: 100%"></p>'
                break
        }
        if (review.username.length > 3) review.username = review.username.substring(0, 3) + '***'
        html += '</div><div class="reviewer_info">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span>' + review.username + '</span></div></div>'
        html += '<div class="review_date">' + review.regdate[0] + '.' + review.regdate[1] + '.' + review.regdate[2] + '</div></div>'

        if (review.reviewcontent.split('<br/>').length - 1 > 5) {
            var indexOfBr = []
            var idx = review.reviewcontent.indexOf('<br/>')
            while (idx != -1) {
                indexOfBr.push(idx)
                idx = review.reviewcontent.indexOf('<br/>', idx + 1)
            }
            html += '<div class="list_right"><p class="review_content"><span class="visible">' + review.reviewcontent.substring(0, indexOfBr[5]) + "..." + '</span><button class="more_review" onclick="toggle_content()">계속 읽기</button><span class="hidden">' + review.reviewcontent + '</span></p>'
        } else if (review.reviewcontent.length >= 145) html += '<div class="list_right"><p class="review_content"><span class="visible">' + review.reviewcontent.substring(0, 145) + "..." + '</span><button class="more_review" onclick="toggle_content()">계속 읽기</button><span class="hidden">' + review.reviewcontent + '</span></p>'
        else html += '<div class="list_right"><p class="review_content">' + review.reviewcontent + '</p>'

        html += '<div class="review_status"><button type="button" class="like_button" onclick="likeButton()">'
        html += '<span class="button_icon"><svg xmlns="http://www.w3.org/2000/svg" width="13" height="15" fill="currentColor" class="bi bi-hand-thumbs-up-fill" viewBox="0 0 16 16">\n' + '  <path d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>\n' + '</svg></span><span class="like_text">' + review.cnt + '</span></button></div></div>'

        newLI.innerHTML = html
        newLI.setAttribute('index', review.reviewindex)
        reviewList.appendChild(newLI)
    });

    const thumbs = document.querySelectorAll('.like_button')
    for (i = 0; i < thumbs.length; i++) {
        const likebtn = thumbs.item(i)

        axios.get('/like/' + likebtn.parentElement.parentElement.parentElement.getAttribute('index') + '/' + loginInfo)
            .then(res => {
                if (res.data == 0) {
                    likebtn.setAttribute('class', 'like_button active')
                }
            }).catch()
    }
}

function setReviewList(pageNum) {

    axios.get('/review/' + isbn + '/' + pageNum)
        .then(res => {
            makeList(res, page)
            const more_review_btn = document.querySelector('.more_review_button')
            more_review_btn.setAttribute('onclick', 'setReviewList(++page)')
        }).catch()
}

function toggle_content() {
    const parent = event.currentTarget.parentElement
    const visible = event.currentTarget.parentElement.children[0]
    const hidden = event.currentTarget.parentElement.children[2]
    visible.classList.value = 'hidden'
    parent.removeChild(parent.children[1])
    hidden.classList.remove('hidden')
}

function likeButton() {

    if (loginInfo < 0) {
        alert('로그인 후 다시 시도해주세요.')
        return
    }
    let basicnum = event.currentTarget.children[1]
    const parentTag = event.currentTarget.parentElement.parentElement.parentElement
    const definition = event.currentTarget
    const id = parentTag.getAttribute('index')

    const payload = {
        reviewindex: id, userindex: document.querySelector('#reviewer').value
    }
    if (event.currentTarget.getAttribute('class') == 'like_button') {
        axios.post('/like/' + id, payload)
            .then(post => {
                console.log('like post > ', post.data)
                basicnum.textContent = parseInt(basicnum.textContent, 10) + 1
                definition.classList.value = 'like_button active'
            }).catch()
    } else if (event.currentTarget.classList.contains('active')) {
        axios.delete('/like/' + id + '/' + loginInfo)
            .then(() => {
                basicnum.textContent = parseInt(basicnum.textContent, 10) - 1
                definition.classList.value = 'like_button'
            }).catch()
    }
}

function insertReview() {

    var str = document.querySelector('.review_input_textarea').value
    str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>')
    document.querySelector('.review_input_textarea').value = str

    promise.then(result => {

        if (result != 0) {

            if (starFieldset.getAttribute('check') != 'checked') {
                alert('별점을 먼저 남겨주세요.')
            }

            const payload = {
                isbn: isbn,
                reviewer: document.querySelector('#reviewer').value,
                star: document.querySelector('input[name="star"]:checked').value,
                reviewcontent: document.querySelector('.review_input_textarea').value
            }

            if (payload.reviewcontent == '') {
                alert('내용을 작성해주세요.')
            } else {
                axios.post('/review', payload)
                    .then(res => {
                        console.log('post -> response', res.data)
                        location.reload()
                    })
                    .catch(err => console.log(err))
            }
        }
    })
}

function deleteReview(reviewindex) {
    if (!confirm('정말 삭제하시겠습니까?')) {
        return
    }

    axios.delete('/review/' + reviewindex)
        .then(res => {

            if (res.data == 1) {
                const delLI = document.querySelector('li[index="' + reviewindex + '"]')
                reviewList.removeChild(delLI)
            }
            location.reload()
        })
        .catch()
}

function modifyReview() {
    const inputArea = document.querySelector('.review_input_textarea')
    inputArea.style.display = 'block'
    inputArea.value = myReview.reviewcontent.replaceAll('<br/>', '\r\n')
    const my_review = document.querySelector('.my_review')
    my_review.style.display = 'none'
    const modifyButton = document.querySelector('.modify_btn')
    modifyButton.classList.remove('hidden')
    const modifyCancelButton = document.querySelector('.modify_cancel_btn')
    modifyCancelButton.classList.remove('hidden')

    modifyButton.addEventListener("click", modifyComplete)
    modifyCancelButton.addEventListener("click", cancelModification)
}

function modifyComplete() {

    var str = document.querySelector('.review_input_textarea').value
    str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>')
    document.querySelector('.review_input_textarea').value = str

    const payload = {
        reviewindex: myReview.reviewindex,
        isbn: isbn,
        reviewer: document.querySelector('#reviewer').value,
        star: document.querySelector('input[name="star"]:checked').value,
        reviewcontent: document.querySelector('.review_input_textarea').value,
        regdate: myReview.regdate
    }

    console.log(payload)

    axios.put('/review/' + payload.reviewindex, payload)
        .then(res => {
            location.reload()
        }).catch()
}

function cancelModification() {
    const inputArea = document.querySelector('.review_input_textarea')
    inputArea.style.display = 'none'
    const my_review = document.querySelector('.my_review')
    my_review.style.display = 'block'
    const modifyButton = document.querySelector('.modify_btn')
    modifyButton.setAttribute('class', 'modify_btn btn btn-primary hidden')
    const modifyCancelButton = document.querySelector('.modify_cancel_btn')
    modifyCancelButton.setAttribute('class', 'modify_cancel_btn btn btn-secondary hidden')
}


