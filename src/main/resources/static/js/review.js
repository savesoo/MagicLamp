let reviewList

const reviewtext = document.querySelector('.review_input_textarea')
const starFieldset = document.querySelector('.star_rate_touch_area > fieldset')

reviewtext.addEventListener("click", loginCheck)
starFieldset.addEventListener("click", starCheck)


function loginCheck(){
    if(loginInfo < 0){
        const answer = confirm('로그인 상태에서 가능합니다. 로그인 페이지로 이동합니다.')
        if(answer == true){
            location.href="/login";
        }
    }
}

function starCheck(){
    starFieldset.setAttribute('check', 'checked')
}

if(myReview != null){
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

    if(myReviewContent_text.length >= 140){
        myReviewContent.innerHTML=''
        myReviewContent.appendChild(visible_mycontent)
        visible_mycontent.appendChild(myReview_more)
        myReviewContent.appendChild(hidden_mycontent)
    }

    myReview_more.addEventListener("click", toggle_mycontent)

    function toggle_mycontent(){
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

function setReviewList(pageNum){

    axios.get('/review/'+isbn+'/'+pageNum)
        .then(res => {
            console.log('response', res.data)

            let list = res.data.list
            let html = ''

            const more_review_btn = document.querySelector('.more_review_button')
            more_review_btn.setAttribute('onclick', 'setReviewList(++page)')
            const more_review_wrapper = document.querySelector('.more_review_button_wrapper')

            if(page*5 >= res.data.total){
                more_review_wrapper.style.display = 'none'
            } else {
                more_review_wrapper.style.display = 'block'
            }

            list.forEach((review, index) => {

                const newLI = document.createElement('li')
                newLI.setAttribute('class', 'review_list')
                html = '<div class="list_left"><div class="left_contents"><div class="StarRate_Icon">'

                let star = review.star
                switch (star) {
                    case 1 : html += '<p class="StarRate_IconFill" style="width: 20%"></p>'
                        break
                    case 2 : html += '<p class="StarRate_IconFill" style="width: 40%"></p>'
                        break
                    case 3 : html += '<p class="StarRate_IconFill" style="width: 60%"></p>'
                        break
                    case 4 : html += '<p class="StarRate_IconFill" style="width: 80%"></p>'
                        break
                    case 5 : html += '<p class="StarRate_IconFill" style="width: 100%"></p>'
                        break
                }
                html += '</div><div class="reviewer_info">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span>'+ review.username+'</span></div></div>'
                html += '<div class="review_date">'+review.regdate[0]+'.'+review.regdate[1]+'.'+review.regdate[2]+'</div></div>'
                html += '<div class="list_right"><p class="review_content">' + review.reviewcontent + '</p></div>'

                newLI.innerHTML = html
                newLI.setAttribute('index', review.reviewindex)
                reviewList.appendChild(newLI)

            });

            const sections = document.querySelectorAll('.review_content')
            for(i=0; i<sections.length; i++){
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

                if(content_text.length >= 140) {
                    item.innerHTML=''
                    item.appendChild(visible_content)
                    visible_content.appendChild(btn_more)
                    item.appendChild(hidden_content)
                }

                btn_more.addEventListener("click", toggle_content)

                function toggle_content(){
                    item.firstChild.className = 'hidden'
                    item.lastChild.classList.remove('hidden')
                }
            }

        })
        .catch()

}

function insertReview(){

    if(starFieldset.getAttribute('check') != 'checked'){
        alert('별점을 먼저 남겨주세요.')
    }

    const payload = {
        isbn : isbn,
        reviewer : document.querySelector('#reviewer').value,
        star : document.querySelector('input[name="star"]:checked').value,
        reviewcontent : document.querySelector('.review_input_textarea').value
    }
    console.log("payload", payload)

    if(payload.reviewcontent == ''){
        alert('내용을 작성해주세요.')
    } else {
        axios.post('/review', payload)
        .then(res => {
            console.log('post -> response', res.data)
            location.reload()
        })
        .catch(err => console.log(err))}

}

function deleteReview(reviewindex){
    if (!confirm('정말 삭제하시겠습니까?')){
        return
    }

    axios.delete('/review/' + reviewindex)
        .then(res => {

            if(res.data == 1){
                const delLI = document.querySelector('li[index="'+reviewindex+'"]')
                reviewList.removeChild(delLI)
            }
            location.reload()
        })
        .catch()
}

function modifyReview(){
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

function modifyComplete(){
    const payload = {
        reviewindex : myReview.reviewindex,
        isbn : isbn,
        reviewer: document.querySelector('#reviewer').value,
        star : document.querySelector('input[name="star"]:checked').value,
        reviewcontent : document.querySelector('.review_input_textarea').value,
        regdate : myReview.regdate
    }

    console.log(payload)

    axios.put('/review/' + payload.reviewindex, payload)
        .then(res => {
            location.reload()
        }).catch()
}

function cancelModification(){
    const inputArea = document.querySelector('.review_input_textarea')
    inputArea.style.display='none'
    const my_review = document.querySelector('.my_review')
    my_review.style.display='block'
    const modifyButton = document.querySelector('.modify_btn')
    modifyButton.setAttribute('class', 'modify_btn hidden')
    const modifyCancelButton = document.querySelector('.modify_cancel_btn')
    modifyCancelButton.setAttribute('class', 'modify_cancel_btn hidden')
}





