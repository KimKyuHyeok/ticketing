document.addEventListener("DOMContentLoaded", function() {
    var idInput = document.getElementById("loginId");
    var pwInput = document.getElementById("loginPw");
    var failedText = document.getElementById("login-failed");

    console.log(loginFailed);

    if (loginFailed) {
        idInput.style.borderColor = "red";
        pwInput.style.borderColor = "red";
        failedText.style.display = "block";
    }
});