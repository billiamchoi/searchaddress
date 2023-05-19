let searchResult;
let query;
let pageNum;
let totalPage;
let startPage;
let endPage;
let countPerPage = 10;

window.addEventListener("DOMContentLoaded", async (event) => {
    await addressSubmitBtnListener();
    await findPostcodeBtnListener();
    await addressSubmitBtnListenerTwo();
});

//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function findAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.1
            let roadAddr = data.roadAddress; // 도로명 주소 변수
            let extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            let guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                let expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                let expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}

// 주소 저장 버튼 리스너
const addressSubmitBtnListener = async () => document.getElementById("addressSubmitBtn").addEventListener("click", async (event) => {
    event.preventDefault();
    const zipCode = document.getElementById("sample4_postcode").value
    const roadNameAddress = document.getElementById("sample4_roadAddress").value
    const landLotNumberAddress = document.getElementById("sample4_jibunAddress").value
    const detailedAddress = document.getElementById("sample4_detailAddress").value
    const body = {zipCode, roadNameAddress, landLotNumberAddress, detailedAddress}

    const response = await ajax("/api/address", {'Content-Type': 'application/json'}, JSON.stringify(body), "POST")
    let msg;
    if(response.result != null) {
        msg = response.return_msg;
    } else {
        alert(response.error_msg)
    }

    if(msg) showToast(msg)
})

const addressSubmitBtnListenerTwo = async () => document.getElementById("submit_btn").addEventListener("click", async (event) => {
    event.preventDefault();
    const zipCode = document.getElementById("postcode").value
    const roadNameAddress = document.getElementById("roadAddress").value
    const landLotNumberAddress = document.getElementById("jibunAddress").value
    const detailedAddress = document.getElementById("detailAddress").value
    const body = {zipCode, roadNameAddress, landLotNumberAddress, detailedAddress}

    const response = await ajax("/api/address", {'Content-Type': 'application/json'}, JSON.stringify(body), "POST")
    let msg;
    if(response.result != null) {
        msg = response.return_msg;
    } else {
        alert(response.error_msg)
    }

    if(msg) showToast(msg)
})

const findPostcodeBtnListener = () => document.getElementById("find_postcode").addEventListener("click", async (event) => {
    document.getElementsByClassName("searchModal")[0].classList.add("show")
    closeBtnListener();
    await searchBtnListener();
})

const searchBtnListener = async () => document.getElementById("searchBtn").addEventListener("click", async (event) => {
    event.preventDefault()
    const searchKeyword = document.getElementById("searchKeyword").value
    pageNum = 1;
    query = searchKeyword
    await loadPageResult(pageNum)
})

const drawPagination = (currentPage, totalCount, countPerPage) => {
    currentPage = Number(currentPage)
    totalCount = Number(totalCount)
    countPerPage = Number(countPerPage)

    totalPage = Math.ceil(totalCount/countPerPage)
    startPage = Math.floor((currentPage - 1) / countPerPage) * countPerPage + 1
    endPage =  (totalPage >= (startPage + countPerPage - 1)) ? startPage + countPerPage - 1 : totalPage
    let html = "";
    html += `<a class="prev">&laquo;</a>`
    for (let i = startPage; i < endPage + 1; i++) {
        if (i === currentPage) {
            html += `<a class="page active">${i}</a>`
        } else {
            html += `<a class="page">${i}</a>`
        }
    }
    html += `<a class="next">&raquo;</a>`

    document.getElementsByClassName("pagination")[0].innerHTML = html;
}

const pageBtnListener = () => {
    let pagBtns = document.getElementsByClassName("page")
    Array.from(pagBtns).forEach(function (pageBtn) {
        pageBtn.addEventListener('click', async function () {
            if (totalPage >= Number(this.innerText)) {
                pageNum = Number(this.innerText)
                await loadPageResult(pageNum)
            }
        })
    })
}

const closeBtnListener = () => document.getElementById("close").addEventListener("click", (event) => {
    document.getElementsByClassName("overlay")[0].classList.remove("show")
    document.getElementsByClassName("searchModal")[0].classList.remove("show")
    document.getElementById("search-result").innerHTML = null;
})

const showToast = (title) => {
    // Get the snackbar DIV
    const snackbar = document.getElementById("snackbar");

    // Add the "show" class to DIV
    snackbar.className = "show";
    snackbar.textContent = title

    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ snackbar.className = snackbar.className.replace("show", ""); }, 3000);
}

const drawSearchResult = (searchResult) => {
    let html = "";
    for (const el of searchResult) {
        html += `<div class="searchList">
                    <div class="itemone">
                        <span>지번 주소</span>
                        <span class="jibun">${el.jibunAddr}</span>   
                    </div>
                    <div class="itemtwo">
                        <span>도로명 주소</span>
                        <span class="roadAdd">${el.roadAddr}</span>
                    </div>
                    <div class="itemthree">
                        <span>우편번호</span>
                        <span class="zipNo">${el.zipNo}</span>
                    </div>
                  </div>
                  <hr>`
    }
    html += `<div class="pagination"></div>`
    document.getElementById("search-result").innerHTML = html;
}

const searchResultClickListener = () => {
    let divs = document.getElementsByClassName("searchList")
    Array.from(divs).forEach(function (div) {
        div.addEventListener('click', function () {
            document.getElementById("postcode").value = this.querySelector(".zipNo").innerText
            document.getElementById("roadAddress").value = this.querySelector(".roadAdd").innerText
            document.getElementById("jibunAddress").value = this.querySelector(".jibun").innerText
            document.getElementsByClassName("searchModal")[0].classList.remove("show")
            document.getElementById("search-result").innerHTML = null;
        })
    })
}

const prevNextBtnListener = async () => {
    if (totalPage === endPage) {
        document.getElementsByClassName("next")[0].classList.add("disabled")
    }
    if (pageNum <= 10) {
        document.getElementsByClassName("prev")[0].classList.add("disabled")
    }
    await prevBtnListener()
    await nextBtnListener()
}

const prevBtnListener = () => document.getElementsByClassName("prev")[0].addEventListener("click", async (event) => {
    if (pageNum > 10) {
        pageNum = startPage - countPerPage
        await loadPageResult(pageNum)
    }
})


const nextBtnListener = () => document.getElementsByClassName("next")[0].addEventListener("click", async (event) => {
    if (totalPage > endPage) {
        pageNum = endPage + 1
        await loadPageResult(pageNum)
    }
})

const loadPageResult = async (pageNum) => {
    const response = await ajax(`/api/search?query=${query}&pageNum=${pageNum}`, {'Content-Type': 'application/json'}, null, "GET")
    searchResult = response.results.juso;
    drawSearchResult(searchResult)
    searchResultClickListener()
    drawPagination(response.results.common.currentPage, response.results.common.totalCount, response.results.common.countPerPage)
    await pageBtnListener()
    await prevNextBtnListener()
}
