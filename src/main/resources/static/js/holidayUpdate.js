window.addEventListener("DOMContentLoaded", async (event) => {
    await loadCompanies();
    await loadHoliday();
    await holidayUpdateBtnListener()
})

const loadHoliday = async () => {
    const id = window.location.pathname.split("/")[3]
    const res = await ajax(`/api/holiday/${id}`, {}, null, "GET")
    if(res.error_code == null) {
        setHolidayProps(res.result)
    }
}

const loadCompanies = async () => {
    const res = await ajax("/api/company", {}, null, "GET")
    if(res.error_code == null) {
        drawCompanySelect(res.result)
    }
}

const drawCompanySelect = (companies) => {
    const holidayTableDiv = document.getElementById("company")
    let html = "";;
    if (companies.length !== 0) {
        html += `<option selected>회사 선택</option>`
        companies.forEach((value) => {
            html += `<option value="${value.id}">${value.name}</option>`
        })
    } else {
        html += `<option selected value="0">회사 선택</option>`
    }
    holidayTableDiv.innerHTML = html;
}


const setHolidayProps = (holiday) => {
    document.getElementById("id").value = holiday.id;
    document.getElementById("name").value = holiday.dateName;
    if(holiday.holiday) {
        document.getElementById("isHoliday").checked = true;
    } else {
        document.getElementById("isNotHoliday").checked = true;
    }
    document.getElementById("locDate").value = holiday.locDate;
    if(holiday.company) {document.getElementById('company').value = holiday.company.id;
    }
}


const holidayUpdateBtnListener = async () => document.getElementById("holidayUpdateSubmit").addEventListener("click", async (event) => {
    event.preventDefault();
    let request = null;
    let sel = document.getElementById("company");
    let id = document.getElementById("id").value;
    let dateName = document.getElementById("name").value;
    let holiday = document.querySelector('input[name="holiday"]:checked').value;
    let locDate = document.getElementById("locDate").value;
    let companyId = document.getElementById("company").value;
    let companyName= sel.options[sel.selectedIndex].text;

    if (companyName !== "회사 선택") {
        request = {id, dateName, holiday, locDate, "company": { "id": companyId, "name": companyName}}
    } else {
        request = {id, "dateName": dateName, "holiday": holiday, "locDate": locDate}
    }

    const res = await ajax("/api/holiday", {'Content-Type': 'application/json'}, JSON.stringify(request), "PUT")

    if(res.error_code == null){
        window.location.replace("/")
    }

})
