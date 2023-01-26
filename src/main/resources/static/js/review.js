let reviewList

const reviewtext = document.querySelector('.review_input_textarea')
const starFieldset = document.querySelector('.star_rate_touch_area > fieldset')

reviewtext.addEventListener("click", loginCheck)
starFieldset.addEventListener("click", starCheck)


function loginCheck() {
    if (loginInfo < 0) {
        const answer = confirm('로그인 상태에서 가능합니다. 로그인 페이지로 이동합니다.')
        if (answer == true) {
            location.href = "/login";
        }
    }
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
    const myReviewContent_text = myReviewContent.textContent
    const myReviewContent_text_short = myReviewContent_text.substring(0, 140) + "..."
    const myReview_more = document.createElement('button')
    myReview_more.setAttribute('class', 'more_review')
    myReview_more.textContent = '계속 읽기'

    const visible_mycontent = document.createElement('span')
    visible_mycontent.setAttribute('class', 'visible')
    visible_mycontent.textContent = myReviewContent_text_short

    const hidden_mycontent = document.createElement('span')
    hidden_mycontent.setAttribute('class', 'hidden')
    hidden_mycontent.textContent = myReviewContent_text

    if (myReviewContent_text.length >= 140) {
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


document.addEventListener('DOMContentLoaded', () => {


    // 리뷰 리스트 출력 영역 캐스팅
    reviewList = document.querySelector('.review_list_wrapper > ul')

    // 리뷰 작성 버튼
    const review_btn = document.querySelector('.review_btn')

    // 버튼 이벤트 등록
    review_btn.addEventListener('click', insertReview)

    setReviewList(1)


})


function setReviewList(pageNum) {

    axios.get('/review/' + isbn + '/' + pageNum)
        .then(res => {
            console.log('response', res.data)

            let list = res.data.list
            let html = ''

            const more_review_btn = document.querySelector('.more_review_button')
            more_review_btn.setAttribute('onclick', 'setReviewList(++page)')
            const more_review_wrapper = document.querySelector('.more_review_button_wrapper')

            if (page * 5 >= res.data.total) {
                more_review_wrapper.style.display = 'none'
            } else {
                more_review_wrapper.style.display = 'block'
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
                html += '</div><div class="reviewer_info">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span>' + review.username + '</span></div></div>'
                html += '<div class="review_date">' + review.regdate[0] + '.' + review.regdate[1] + '.' + review.regdate[2] + '</div></div>'
                html += '<div class="list_right"><p class="review_content">' + review.reviewcontent + '</p>'
                html += '<div class="review_status"><button type="button" class="like_button" onclick="likeButton()">'
                html += '<span class="button_icon"><svg xmlns="http://www.w3.org/2000/svg" width="13" height="15" fill="currentColor" class="bi bi-hand-thumbs-up-fill" viewBox="0 0 16 16">\n' +
                    '  <path d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>\n' +
                    '</svg></span><span class="like_text">' + review.cnt + '</span></button></div></div>'

                newLI.innerHTML = html
                newLI.setAttribute('index', review.reviewindex)
                reviewList.appendChild(newLI)

            });

            const sections = document.querySelectorAll('.review_content')
            for (i = 0; i < sections.length; i++) {
                const item = sections.item(i);

                const content_text = item.textContent
                const content_text_short = content_text.substring(0, 140) + "..."
                const btn_more = document.createElement('button')
                btn_more.setAttribute('class', 'more_review')
                btn_more.textContent = '계속 읽기'

                const visible_content = document.createElement('span')
                visible_content.setAttribute('class', 'visible')
                visible_content.textContent = content_text_short

                const hidden_content = document.createElement('span')
                hidden_content.setAttribute('class', 'hidden')
                hidden_content.textContent = content_text

                if (content_text.length >= 140) {
                    item.innerHTML = ''
                    item.appendChild(visible_content)
                    visible_content.appendChild(btn_more)
                    item.appendChild(hidden_content)
                }

                btn_more.addEventListener("click", toggle_content)

                function toggle_content() {
                    item.firstChild.className = 'hidden'
                    item.lastChild.classList.remove('hidden')
                }
            }

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

        }).catch()
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
        reviewindex: id,
        userindex: document.querySelector('#reviewer').value
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

    if (starFieldset.getAttribute('check') != 'checked') {
        alert('별점을 먼저 남겨주세요.')
    }

    const payload = {
        isbn: isbn,
        reviewer: document.querySelector('#reviewer').value,
        star: document.querySelector('input[name="star"]:checked').value,
        reviewcontent: document.querySelector('.review_input_textarea').value
    }
    console.log("payload", payload)

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
    const myReviewContent = document.querySelector('.my_review_content')
    inputArea.textContent = myReview.reviewcontent
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
    modifyButton.setAttribute('class', 'modify_btn hidden')
    const modifyCancelButton = document.querySelector('.modify_cancel_btn')
    modifyCancelButton.setAttribute('class', 'modify_cancel_btn hidden')
}


