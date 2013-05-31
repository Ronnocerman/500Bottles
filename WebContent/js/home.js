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

    function sign_up_interaction()
    {
        var fn = document.getElementById("create_account_firstname");
        var ln = document.getElementById("create_account_lastname");
        var e = document.getElementById("create_account_email");
        var p = document.getElementById("create_account_pwd");
        var fp = document.getElementById("create_account_faux_pwd");
        var cp = document.getElementById("create_account_conf_pwd");
        var fcp = document.getElementById("create_account_faux_conf_pwd");
        var fn_placeholder = fn.value;
        var ln_placeholder = ln.value;
        var e_placeholder = e.value;
        var fp_placeholder = fp.value;
        var fcp_placeholder = fcp.value;

        fn.addEventListener("focus", function() {
            if (fn.value == fn_placeholder)
                fn.value = "";
        });

        fn.addEventListener("blur", function() {
            if (fn.value == "")
                fn.value = fn_placeholder;
        });

        ln.addEventListener("focus", function() {
            if (ln.value == ln_placeholder)
                ln.value = "";
        });

        ln.addEventListener("blur", function() {
            if (ln.value == "")
                ln.value = ln_placeholder;
        });

        e.addEventListener("focus", function() {
            if (e.value == e_placeholder)
                e.value = "";
        });

        e.addEventListener("blur", function() {
            if (e.value == "")
                e.value = e_placeholder;
        });




        fp.addEventListener("focus", function() {
            fp.style.display = "none";
            p.style.display = "block";
            p.focus();
        });

        fcp.addEventListener("focus", function() {
            fcp.style.display = "none";
            cp.style.display = "block";
            cp.focus();
        });

        p.addEventListener("blur", function() {
            if (p.value != "")
                return;

            p.style.display = "none";
            fp.style.display = "block";
        });

        cp.addEventListener("blur", function() {
            if (cp.value != "")
                return;

            cp.style.display = "none";
            fcp.style.display = "block";
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
        //view_login.addEventListener("click", hide_login_view);
    }

    function on_load()
    {
        login_form_interaction();
        login_view_interaction();
        sign_up_interaction();
    }

    window.addEventListener("load", on_load);

})();