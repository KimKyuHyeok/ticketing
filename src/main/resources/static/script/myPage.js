function myPageUpdate() {
    var updateForm = document.forms[0];
    var password = updateForm.password.value;
    var passwordConfirm = updateForm.passwordConfirm.value;
    var email = updateForm.email.value;
    var nickname = updateForm.nickname.value;

    if (password != passwordConfirm) {
        alert("비밀번호가 일지하지 않습니다.");
    } else if (password.length < 8) {
        alert("비밀번호는 8자 이상으로 입력해주세요. (공백불가)");
    } else if (updateForm.emailDuplication.value != "emailCheck") {
        alert("이메일 중복체크 해주세요.");
        updateForm.email.focus();
    } else if (updateForm.nicknameDuplication.value != "nicknameCheck") {
        alert("닉네임 중복체크 해주세요.");
        updateForm.nickname.focus();
    } else {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            processData: false,
            url: "/myPage/update",
            data: JSON.stringify({
                password: password,
                email: email,
                nickname: nickname
            }),
            success: function (data) {
                if (data === true) {
                    alert("수정이 완료되었습니다.");
                    window.location.href = "/";
                }
            }
        })
    }

}


function nicknameDuplicateCheck() {
    var updateForm = document.forms[0];
    var nickname = updateForm.nickname.value;

    if (nickname.length == 0 || nickname == "") {
        alert("닉네임을 입력해주세요")
        updateForm.nickname.focus();
    } else {
        fetch('/member/check-duplicate?nickname=' + nickname)
            .then(response => response.json())
            .then(data => {
                if (data == true) {
                    alert("이미 사용중인 닉네임입니다")
                    updateForm.nickname.focus();
                } else {
                    alert("사용가능한 닉네임입니다")
                    updateForm.nicknameDuplication.value = "nicknameCheck";
                }
            })
    }
}

function emailDuplicateCheck() {
    var updateForm = document.forms[0];
    var email = updateForm.email.value;

    if (email.length == 0 || email == "") {
        alert("닉네임을 입력해주세요")
        updateForm.email.focus();
    } else {
        fetch('/member/check-duplicate?email=' + email)
            .then(response => response.json())
            .then(data => {
                if (data == true) {
                    alert("이미 사용중인 이메일입니다")
                    updateForm.email.focus();
                } else {
                    alert("사용가능한 이메일입니다")
                    updateForm.emailDuplication.value = "emailCheck";
                }
            })
    }
}