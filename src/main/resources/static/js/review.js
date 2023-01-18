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
    const inputBtn = document.querySelector('.review_btn')
    inputArea.style.display = 'none'
    inputBtn.style.display = 'none'

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

            let more_review_btn = document.createElement('button')
            more_review_btn.setAttribute('class', 'more_review_button')
            more_review_btn.setAttribute('id', 'more_review_button')
            more_review_btn.setAttribute('onclick', 'setReviewList(++page); more_review_btn(this.id)')

            more_review_btn.textContent = '더보기'

            if(page*10 >= res.data.total){
                more_review_btn.style.display = 'none'
            } else {
                more_review_btn.style.display = 'block'
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
            reviewList.appendChild(more_review_btn)

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

function more_review_btn(clicked){
    document.getElementById(clicked).remove()
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
    if (!confirm('삭제하시겠습니까?')){
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



