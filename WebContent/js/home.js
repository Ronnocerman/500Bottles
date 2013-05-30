(function(){
    function login_form_interaction() {
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

    window.addEventListener("load", login_form_interaction);
})();