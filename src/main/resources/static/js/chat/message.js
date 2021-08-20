// 즉시실행함수
(function () {
    let template = $("#message-tmpl2").html();
    Mustache.parse(template);
    let html = Mustache.render(template);
    $("#content-box").append(html);
})()

let messageService = {
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
        })
    },
    sendMessage: function () {
        let content = $("#content").val();
        let chatroomId = $("#chatroomId").val();

        let template = document.getElementById('message-tmpl').innerHTML;
        Mustache.parse(template);

        let message = {
            content: content,
            chatroomId: chatroomId,
            sender: "손님"
        }

        let data = {
            "content": content,
            "sender": "손님"
        }

        $.ajax({
            url: '/chat/message',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(message),
            success: function () {

            },
            error: function () {
                alert('error!');
            }
        })

        console.log("data : " + data.content + data.sender);

        let rendered = Mustache.render(template, data);
        console.log(rendered);
        //TODO Mustache.js 가 안먹는 것 같음! 체크해서 해결해야함
        document.getElementById('content-box').append(rendered);
        $("#content").val("");

    }
}