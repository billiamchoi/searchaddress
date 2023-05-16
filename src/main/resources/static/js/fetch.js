const ajax = async (_url, _headers, _body, _httpMethod) => {
    alterClass("div.loader", "add", "show");
    alterClass("div.overlay", "add", "show");
    try {
        const response = await fetch(_url, {
            method: _httpMethod,
            body: _body,
            headers: _headers
        });
        if (response.ok) {
            return await response.json();
        }
    } catch (error) {
        console.log(error)
    } finally {
        alterClass("div.loader", "remove", "show");
        alterClass("div.overlay", "remove", "show");
    }
}

const alterClass = (_spinnerClass, _type, _className) => {
    switch (_type) {
        case "add":
            document.querySelector(_spinnerClass).classList.add(_className);
            break;
        case "remove":
            document.querySelector(_spinnerClass).classList.remove(_className);
            break;
        default:
            break;

    }
}
