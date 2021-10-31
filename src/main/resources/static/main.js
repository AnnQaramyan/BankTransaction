$(document).ready(function(e) {
    $('#register').click(function (e) {
        var name = document.getElementById('name').value;
        var email = document.getElementById('email').value;
        var password = document.getElementById('password').value;
        console.log(45345455443)
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/user",
            data: {
                name: name,
                email: email,
                password: password
            },

            success: function (success) {
                alert(success);
            },
            error: function (e) {
                console.log("ERROR : ", e,'7887788878788');
            }
        });
    });

    //for login
    $('#loginUser').click(function (e) {
        var lemail = document.getElementById('lemail').value;
        var lpassword = document.getElementById('lpassword').value;
        console.log(lpassword,lemail)
        $.ajax({
            type: "POST",
            // dataType: "json",
            url: "http://localhost:8080/user/{{login}}",
            data: {
                email: lemail,
                password: lpassword
            },

            success: function (success) {
                alert(success);
            },
            error: function (e) {
                console.log("ERROR : ", e,'7887788878788');
            }
        });
    });
});