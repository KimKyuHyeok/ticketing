$(document).ready(function () {
    $("#concert-upload-btn").click(function (event) {

        var reservationYear = $("#reservation-year").val();
        var reservationMonth = $("#reservation-month").val();
        var reservationDay = $("#reservation-day").val();
        var reservationHour = $("#reservation-hour").val();
        var reservationMin = $("#reservation-min").val();

        // 선택된 값으로 Date 객체 생성
        var reservationDate = new Date(
            Date.UTC(
                reservationYear,
                reservationMonth - 1, // JavaScript에서 월은 0부터 시작하므로 1 빼기
                reservationDay,
                reservationHour,
                reservationMin
            )
        );

        var startYear = $("#startDay-year").val();
        var startMonth = $("#startDay-month").val();
        var startDay = $("#startDay-day").val();
        var startHour = $("#startDay-hour").val();
        var startMin = $("#startDay-min").val();

        var startDate = new Date(
            Date.UTC(
                startYear,
                startMonth - 1,
                startDay,
                startHour,
                startMin
            )
        );

        var endYear = $("#endDay-year").val();
        var endMonth = $("#endDay-month").val();
        var endDay = $("#endDay-day").val();
        var endHour = $("#endDay-hour").val();
        var endMin = $("#endDay-min").val();

        var endDate = new Date(
            Date.UTC(
                endYear,
                endMonth - 1,
                endDay,
                endHour,
                endMin
            )
        );

        // DateTime 형식으로 포맷팅
        var formattedReservationDate = reservationDate.toISOString().slice(0, 16);
        var formattedStartDate = startDate.toISOString().slice(0, 16);
        var formattedEndDate = endDate.toISOString().slice(0, 16);

        var formData = new FormData();
        formData.append('file', $('#concert-image')[0].files[0]);
        formData.append('name', $("#concert-name").val());
        formData.append('artist', $("#concert-artist").val());
        formData.append('location', $("#concert-location").val());
        formData.append('reservationDay', formattedReservationDate);
        formData.append('startDay', formattedStartDate);
        formData.append('endDay', formattedEndDate);
        formData.append('link', $("#concert-link").val());

        var concertDto = {
            name: $("#concert-name").val(),
            artist: $("#concert-artist").val(),
            location: $("#concert-location").val(),
            reservationDay: formattedReservationDate,
            startDay: formattedStartDate,
            endDay: formattedEndDate,
            link: $("#concert-link").val(),
            image: formData.get('file')
        };

        $.ajax({
            type: "POST",
            contentType: false,
            processData: false,
            url: "/admin/concert/upload",
            data: formData,
            success: function (data) {
                console.log("서버 응답:", data);
                if (data === "업로드 성공") {
                    // 성공적으로 처리된 경우 수행할 작업 추가
                    console.log("업로드 성공");
                } else {
                    console.log("서버 응답은 JSON 형식이 아닙니다.");
                }
            },
            error: function (xhr, status, error) {
                console.error("오류 발생:", error);
                console.log("상태 코드:", xhr.status);
                // 오류 발생 시 수행할 작업 추가
            }
        });
    });
});