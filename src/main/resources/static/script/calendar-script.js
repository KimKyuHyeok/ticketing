<!-- FullCalendar 라이브러리 로드 후에 추가 -->
console.log('FullCalendar version:', FullCalendar.Calendar.version);

document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar1');

    // 현재 날짜 가져오기
    var today = moment().format('YYYY-MM-DD');

    // 서버에서 첫 번째 종류의 이벤트 데이터 가져오기
    $.ajax({
        url: '/concert/api/concert',
        type: 'GET',
        dataType: 'json',
        success: function (eventsType1) {
            // 첫 번째 종류의 이벤트 데이터 매핑
            var events = eventsType1.map(event => ({
                id: event.concertId,
                title: "[예매]" + event.name,
                start: event.reservationDay,
                end: moment(event.reservationDay).add(1, 'day').format('YYYY-MM-DD'),
                color: 'red',
                className: 'reservationDay',
            }));

            // 두 번째 종류의 이벤트 데이터 매핑
            var events2 = eventsType1.map(event => ({
                id: event.concertId,
                title: event.name,
                start: event.startDay,
                end: event.endDay,
                color: 'black'
            }));

            console.log(events);
            console.log(events2);

            // 두 종류의 이벤트 데이터 합치기
            var allEvents = events.concat(events2);

            // FullCalendar 설정
            var calendar = new FullCalendar.Calendar(calendarEl, {
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: ''
                },
                initialDate: today,
                locale: 'ko',
                editable: false,
                events: allEvents,
                eventContent: function (arg) {
                    return {
                        html: `<b>${arg.event.title}</b>`
                    };
                },
                eventRender: function (arg) {
                    // 이벤트 렌더링 시 실행되는 콜백
                    arg.el.style.backgroundColor = arg.event.color;
                },
                eventClick: function (arg) {
                    openPopup(`/popup/${arg.event.id}`);
                }
            });

            // 캘린더 렌더링
            calendar.render();
        }
    });

    function openPopup(url) {
        // AJAX를 사용하여 HTML 파일 로드
        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'html',
            success: function (html) {
                // 로드한 HTML을 팝업 레이어에 추가
                var popupWindow = window.open(url, 'popup', 'width=800,height=500');

                if (!popupWindow || popupWindow.closed || typeof popupWindow.closed == 'undefined') {
                    alert('팝업이 차단되었습니다. 팝업 차단을 해제하고 다시 시도하세요.');
                }
            },
            error: function () {
                console.error('Error loading HTML');
            }
        });
    }
});
