(function()
{
    function login_form_interaction()
    {
        var fp = document.getElementById("faux_password");
        var p = document.getElementById("password");
        var e = document.getElementById("email");
        var email_placeholder = e.value;

        e.addEventListener("blur", function() {
            if (e.value == "")
                e.value = email_placeholder;
        });

        e.addEventListener("focus", function() {
            if (e.value == email_placeholder)
                e.value = "";
        });

        fp.addEventListener("focus", function() {
            fp.style.display = "none";
            p.style.display = "block";
            p.focus();
        });

        p.addEventListener("blur", function() {
            if (p.value != "")
                return;

            p.style.display = "none";
            fp.style.display = "block";
        });
    }

    function login_view_interaction()
    {
        var login = document.getElementById("log_in");
        var view_login = document.getElementById("view_login");
        var bottle = $(".wine_bottle")[0];

        function show_login_view()
        {
            $(view_login).removeClass("fadeOutUpBig");
            $(view_login).removeClass("no_display");
            $(view_login).removeClass("delay_1s");
            $(view_login).addClass("fadeInDownBig");

            $(bottle).removeClass("bounceOutDown");
            $(bottle).addClass("delay_1s");
            $(bottle).addClass("bounceInUp");
        }

        function hide_login_view()
        {
            $(bottle).removeClass("bounceInUp");
            $(bottle).removeClass("delay_1s");
            $(bottle).addClass("bounceOutDown");

            $(view_login).addClass("delay_1s");
            $(view_login).removeClass("fadeInDownBig");
            $(view_login).addClass("fadeOutUpBig");
        }

        login.addEventListener("click", show_login_view);
        view_login.addEventListener("click", hide_login_view);
    }

    function on_load()
    {
        login_form_interaction();
        login_view_interaction();
    }

    window.addEventListener("load", on_load);

})();