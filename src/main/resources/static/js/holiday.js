window.addEventListener("DOMContentLoaded", async (event) => {
    await loadHolidays();
})

const loadHolidays = async () => {
    const res = await ajax("/api/holiday", {}, null, "GET")
    if(res.error_code == null) {
        drawHolidayTable(res.result)
    }
}
const drawHolidayTable = (holidays) => {
    const holidayTableDiv = document.getElementById("holiday_table")
    let html = "";
    html += `<table class="table">`;
    html +=     `<thead class="table-dark">`;
    html +=        `<tr>
                        <th scope="col">이름</th>
                        <th scope="col">공휴일 유무</th>
                        <th scope="col">일자</th>
                        <th scope="col">회사</th>
                    </tr>
                </thead>`
    html +=     `<tbody>`;
    if (holidays.length !== 0) {
        holidays.forEach((value) => {
            html += `<tr>
                    <th>${value.dateName}</th>
                    <td>${value.holiday}</td>
                    <td>${value.locDate}</td>
                    <td>${value.company.name}</td>
                  </tr>`
        })
    }
    html +=     `</tbody>`;
    html += `</table>`;
    holidayTableDiv.innerHTML = html;
}
