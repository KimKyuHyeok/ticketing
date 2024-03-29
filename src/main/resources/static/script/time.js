function elapsedTime(date) {
  const start = new Date(date);
  const end = new Date();

  // 로컬 타임을 UTC로 변환
  const startUTC = new Date(start.getTime() - (start.getTimezoneOffset() * 60000));


  // 시간을 로컬 타임으로 변환
  const diff = (end - startUTC) / 1000;

  const times = [
    { name: '년', milliSeconds: 60 * 60 * 24 * 365 },
    { name: '개월', milliSeconds: 60 * 60 * 24 * 30 },
    { name: '일', milliSeconds: 60 * 60 * 24 },
    { name: '시간', milliSeconds: 60 * 60 },
    { name: '분', milliSeconds: 60 },
  ];

  for (const value of times) {
    const betweenTime = Math.floor(diff / value.milliSeconds);

    if (betweenTime > 0) {
      return `${betweenTime}${value.name} 전`;
    }
  }
  return '방금 전';
}

window.onload = function () {
  console.log("Window loaded.");

  var rows = document.querySelectorAll('.board-list');

  rows.forEach(function (row) {

    var createdAtLabel = row.querySelector('.create-date');
    var time = row.querySelector('.time');

    if (createdAtLabel && time) {
      var result = elapsedTime(createdAtLabel.textContent);

      time.textContent = result;
    }
  });
}
