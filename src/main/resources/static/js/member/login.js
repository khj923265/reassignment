let loginService = {
    login: function () {
        let email = $("#email").val();
        let password = $("#password").val();

        console.log("test");

        $.ajax({
            url: '/member/login',
            type: 'POST',
            async: false,
            datatype: 'json',
            data: {
                email: email,
                password: password
            },
            success: function (result) {
                location.replace("/")
                localStorage.setItem("accesstoken", result)
                console.log("결과" + result)
            },
            error: function () {
                alert('error!');
            }
        });
    }
}