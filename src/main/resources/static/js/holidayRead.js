window.addEventListener("DOMContentLoaded", async (event) => {
    await loadHolidays();
})

const loadHolidays = async () => {
    const res = await ajax("/api/holiday", {}, null, "GET")
    if(res.error_code == null) {
        drawHolidayTable(res.result)
        await deleteBtnListener()
        holidayTableListener()
    }
}

const drawHolidayTable = (holidays) => {
    const holidayTableDiv = document.getElementById("holiday_table")
    let html = "";
    html += `<table class="table" id="holidayTable">`;
    html +=     `<thead class="table-dark">`;
    html +=        `<tr>
                        <th scope="col">이름</th>
                        <th scope="col">공휴일 유무</th>
                        <th scope="col">일자</th>
                        <th scope="col">회사</th>
                        <th></th>
                    </tr>
                </thead>`
    html +=     `<tbody>`;
    if (holidays.length !== 0) {
        holidays.forEach((value) => {
            let isHoliday = value.holiday === true ? "O" : "X"
            if(value.company) {
                html += `<tr>
                    <td hidden>${value.id}</td>
                    <td>${value.dateName}</td>
                    <td>${isHoliday}</td>
                    <td>${value.locDate}</td>
                    <td>${value.company.name}</td>
                    <td><button class="deleteBtn btn btn-danger" value=${value.id}>삭제</button></td>
                  </tr>`
            } else {
                html += `<tr>
                    <td hidden>${value.id}</td>
                    <td>${value.dateName}</td>
                    <td>${isHoliday}</td>
                    <td>${value.locDate}</td>
                    <td>공통</td>
                    <td><button class="deleteBtn btn btn-danger" value=${value.id}>삭제</button></td>
                  </tr>`
            }
        })
    }
    html +=     `</tbody>`;
    html += `</table>`;
    holidayTableDiv.innerHTML = html;
}

const deleteBtnListener = async () => {
    let buttons = document.getElementsByClassName("deleteBtn")
    Array.from(buttons).forEach(function(button) {
        button.addEventListener('click', async function () {
            let yn = confirm("해당 공휴일을 삭제하시겠습니까?");
            if(yn){
                const res = await ajax("/api/holiday/"+ this.value,{'Content-Type': 'application/json'}, null, "DELETE")
                if(res.error_code == null) {
                    window.location.assign("/")
                }
            }
        })
    })
}


const holidayTableListener = () => document.getElementById("holidayTable").addEventListener("click", (event) => {
    const td = event.target; // 클릭한 td 요소 가져오기
    const tr = td.parentNode; // 해당 td 요소가 속한 tr 요소 가져오기
    const cells = tr.getElementsByTagName("td"); // 해당 tr 요소의 모든 td 요소 가져오기
    if(td.classList[0] !== 'deleteBtn') {
        let holidayId = cells[0].innerText
        window.location.assign(`/holiday/update/${holidayId}`)
    }
})
