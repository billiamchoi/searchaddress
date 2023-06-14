window.addEventListener("DOMContentLoaded", async (event) => {
    await loadCompanies();
    await holidayCreateBtnListener()
})

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

const holidayCreateBtnListener = async () => document.getElementById("holidaySubmit").addEventListener("click", async (event) => {
    event.preventDefault();
    let request = null;
    let sel = document.getElementById("company");
    let dateName = document.getElementById("name").value;
    let holiday = document.querySelector('input[name="holiday"]:checked').value;
    let locDate = document.getElementById("locDate").value;
    let companyId = document.getElementById("company").value;
    let companyName= sel.options[sel.selectedIndex].text;

    if (companyName !== "회사 선택") {
        request = {dateName, holiday, locDate, "company": { "id": companyId, "name": companyName}}
    } else {
        request = {"dateName": dateName, "holiday": holiday, "locDate": locDate}
    }

    const res = await ajax("/api/holiday", {'Content-Type': 'application/json'}, JSON.stringify(request), "POST")

    if(res.error_code == null){
        window.location.replace("/")
    }

})
