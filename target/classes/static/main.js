/*
$(document).ready(function(e) {
    console.log(45345455443)
    $('#register').click(function (e) {
        console.log(45345455443)

        var name = document.getElementById('name').value;
        var email = document.getElementById('email').value;
        var password = document.getElementById('password').value;

        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/home",
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
    $('#login').click(function (e) {
        var lemail = document.getElementById('lemail').value;
        var lpassword = document.getElementById('lpassword').value;
        console.log(lpassword,lemail)
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "http://localhost:8080/home/login",
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
});*/
