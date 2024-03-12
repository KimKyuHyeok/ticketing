
document.getElementById('start').addEventListener('click', function (){
    document.getElementById('start').style.display = 'none';
    document.getElementById('loading').style.display = 'block';

    const seatList = document.getElementById('seatList');
    const select = document.getElementById('select');
    const loading = document.getElementById('loading');
    const loadingNumber = document.getElementById('loadingNumber');

    //기본시간
    let currentTime = 4;

    const timerInterval = setInterval(function () {
        currentTime--;
        if (currentTime == 3) {
            loadingNumber.textContent = '456';
        } else if (currentTime == 2) {
            loadingNumber.textContent = '126';
        } else if (currentTime == 1) {
            loadingNumber.textContent = '1';
        } else if (currentTime == 0) {
            loading.style.display = 'none';
            select.style.display = 'block';

            var randomSeatNumberList = [];

            for (var i = 0; i < 20*15; i++) {
                // 1에서 50까지의 랜덤한 수를 생성합니다.
                var randomNumber = Math.floor(Math.random() * 300) + 1;

                // 생성된 랜덤한 수를 배열에 추가합니다.
                randomSeatNumberList.push(randomNumber);
            }


            for (var a = 0; a < 20; a++) {
                var row = document.createElement('div');
                row.classList.add('seatRow');

                for (var j = 0; j < 20; j++) {
                    var seatNumber = a * 15 + j;
                    var newSpan = document.createElement('span');

                    var indexOf = randomSeatNumberList.indexOf(a * 15 + j);

                    if (indexOf >= 0) {
                        newSpan.classList.add('seatN');
                    } else {
                        newSpan.classList.add('seatY');
                        var newSpanId = 'seat' + a * j;
                        var newSpanTitle = 'A구역 ' + (a + 1) + '열 ' + (j + 1);
                        newSpan.setAttribute('id', newSpanId);
                        newSpan.setAttribute('title', newSpanTitle);
                        newSpan.addEventListener('click', changeStyle);
                    }
                    row.appendChild(newSpan);
                }
                seatList.appendChild(row);
            }
        }

    }, 1000);

    function changeStyle() {
        // 현재 클래스를 확인합니다.
        var hasChangedClass = this.classList.contains('changed');

        // 현재 클래스에 따라 토글합니다.
        if (hasChangedClass) {
            // 클래스를 제거하여 이전 상태로 돌립니다.
            this.classList.remove('changed');
        } else {
            // 클래스를 추가하여 새로운 스타일을 적용합니다.
            this.classList.add('changed');
        }
    }
});





