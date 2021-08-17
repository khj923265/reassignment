// 즉시실행함수
// (function () {
//   console.log("init 실행");
//   $.ajax({
//     type : 'get',
//     url : '/chat/read',
//     dataType : "json"
//   })
// })()

let chatService = {
  createChattingRoom: function () {
    let title = $('#title').val();
    console.log(title);

    $.ajax({
      url: '/chat/create/' + title,
      type: 'POST',
      contentType: 'application/json',
      success: function () {
        alert('채팅방을 만들었습니다.');
      },
      error: function () {
        alert('error!');
      }
    });
  }
}