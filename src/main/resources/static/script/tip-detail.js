$(document).ready(function() {
    $('#submit-button').click(function(event) {
        event.preventDefault(); // 기본 동작(폼 제출)을 막습니다.

        var tipId = document.getElementById("#tipId").value;

        var formData = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/board/' + tipId +'/form/request',
            data: formData,
            success: function(response) {
                console.log('성공:', response);
                // 성공적으로 저장되었을 때 할 일을 여기에 추가합니다.
            },
            error: function(xhr, status, error) {
                console.error('에러:', error);
                // 오류 발생 시 처리할 내용을 여기에 추가합니다.
            }
        });
    });

    $('#submit-button').click(function(event) {
        event.preventDefault(); // 기본 동작(폼 제출)을 막습니다.

        var tipId = document.getElementById("#tipId").value;

        var formData = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/board/' + tipId +'/form/request',
            data: formData,
            success: function(response) {
                console.log('성공:', response);
                // 성공적으로 저장되었을 때 할 일을 여기에 추가합니다.
            },
            error: function(xhr, status, error) {
                console.error('에러:', error);
                // 오류 발생 시 처리할 내용을 여기에 추가합니다.
            }
        });
    });
});