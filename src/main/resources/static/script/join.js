
function joinUser() {
    var joinForm = document.forms[0];
    var name = joinForm.name.value;
    var id = joinForm.id.value;
    var password = joinForm.password.value;
    var rePw = joinForm.rePw.value;
    var email = joinForm.email.value;
    var nickname = joinForm.nickname.value;

    if (id.length == 0 || id == "") {
        alert("아이디를 입력해주세요.")
    } else if ((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")) {
        alert("한글 및 특수문자는 아이디로 사용하실 수 없습니다");
    } else if (joinForm.idDuplication.value != "idCheck") {
        alert("아이디 중복체크를 해주세요.");
    } else if (joinForm.nicknameDuplication.value != "nicknameCheck") {
        alert("닉네임 중복체크를 해주세요");
    } else if (joinForm.emailDuplication.value != "emailCheck") {
        alert("이메일 중복체크를 해주세요");
    } else if (password.length == 0 || password == "") {
        alert("비밀번호를 입력해주세요");
        joinForm.password.focus();
    } else if (password.length < 8) {
        alert("비밀번호는 8자 이상으로 입력해주세요")
        joinForm.password.focus();
    } else if (rePw != password) {
        alert("입력하신 비밀번호가 일치하지 않습니다");
        joinForm.rePw.focus();
    } else if (email.length == 0 || email == "") {
        alert("이메일을 입력해주세요");
        joinForm.email.focus();
    } else if (nickname.length < 2 || nickname.length > 10) {
        alert("닉네임은 2자 이상 혹은 10자 이내로 입력해주세요")
    } else {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            processData: false,
            url: "/join/request",
            data: JSON.stringify({
                loginId: id,
                password: password,
                nickname: nickname,
                email: email
                // name 및 추가정보는 추후에 설정
            }),
            success: function (data) {
                if (data === true) {
                    alert("회원가입이 완료되었습니다")
                    window.location.href = "/home";
                } else {
                    alert("알 수 없는 에러가 발생하였습니다")
                }
            }

        })
    }
}


function inputIdCheck() {
    document.idDuplication.value = "idUncheck";
}

function inputNicknameCheck() {
    document.nicknameDuplication.value = "nicknameUncheck";
}

function inputEmailCheck() {
    document.emailDuplication.value = "emailUncheck";
}





function idDuplicateCheck() {
    var joinForm = document.forms[0];
    var id = joinForm.id.value;

    if (id.length == 0 || id == "") {
        alert("아이디를 입력해주세요")
        joinForm.id.focus();
    } else {
        fetch('/member/join/check-duplicate?loginId=' + id)
            .then(response => response.json())
            .then(data => {
                if (data == true) {
                    alert("이미 사용중인 아이디입니다")
                    joinForm.id.focus();
                } else {
                    alert("사용가능한 아이디입니다")
                    joinForm.idDuplication.value = "idCheck";
                }
            })
    }
}

function nicknameDuplicateCheck() {
    var joinForm = document.forms[0];
    var nickname = joinForm.nickname.value;

    if (nickname.length == 0 || nickname == "") {
        alert("아이디를 입력해주세요")
        joinForm.nickname.focus();
    } else {
        fetch('/member/join/check-duplicate?nickname=' + nickname)
            .then(response => response.json())
            .then(data => {
                if (data == true) {
                    alert("이미 사용중인 닉네임입니다")
                    joinForm.nickname.focus();
                } else {
                    alert("사용가능한 닉네임입니다")
                    joinForm.nicknameDuplication.value = "nicknameCheck";
                }
            })
    }
}

function emailDuplicateCheck() {
    var joinForm = document.forms[0];
    var email = joinForm.email.value;

    if (email.length == 0 || email == "") {
        alert("아이디를 입력해주세요")
        joinForm.email.focus();
    } else {
        fetch('/member/join/check-duplicate?email=' + email)
            .then(response => response.json())
            .then(data => {
                if (data == true) {
                    alert("중복된 이메일 입니다")
                    joinForm.email.focus();
                } else {
                    alert("사용가능한 이메일입니다")
                    joinForm.emailDuplication.value = "emailCheck";
                }
            })
    }
}
