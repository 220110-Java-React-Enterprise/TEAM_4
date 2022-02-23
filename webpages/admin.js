async function checkIfAdmin() {
    let currentUrl = window.location.href;
    let params = (new URL(document.location)).searchParams;
    let userId = params.get("userId");

    let baseUrl = "http://localhost:8080/users/admin/";
    let urlWithParams = baseUrl + userId;
    console.log(urlWithParams);

    let response = await fetch(urlWithParams);

    try {

        let text = await response.json();
        console.log(text);

        if (text) {
            //alert('AdminGood');
        } else {
            alert('Not an admin! Leave now and never come back!');
            document.location.href = "user_login.html";
        }
    } catch (error) {
        alert("You don't even exist...");
        document.location.href = "user_login.html";
    }
}