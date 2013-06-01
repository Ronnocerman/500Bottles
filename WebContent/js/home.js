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
        // No-display class.
        var NO_DISPLAY = "no_display";

        // Login form animation classes.
        var LOGIN_FORM_ANIM_IN = "flipInY";
        var LOGIN_FORM_ANIM_OUT = "fadeOutDown";
        var LOGIN_DELAY_ANIM = "";
        var LOGIN_FORM_DELAY_IN = "delay_07s";
        var LOGIN_FORM_DELAY_OUT = "";
        var LOGIN_FORM_OUT_TIMER = 1000;

        // Signup form animation classes.
        var SIGNUP_FORM_ANIM_IN = "flipInY";
        var SIGNUP_FORM_ANIM_OUT = "fadeOutDown";
        var SIGNUP_FORM_DELAY_IN = "delay_07s";
        var SIGNUP_FORM_DELAY_OUT = "";
        var SIGNUP_FORM_OUT_TIMER = 1000;


        var HOME_ANIM_OUT = "";
        var HOME_ANIM_IN = "";
        var HOME_DELAY_OUT = "";
        var HOME_DELAY_IN = "";
        var HOME_DELAY_TIMER = 1000;

        var ACCOUNT_ANIM_IN = "fadeIn";
        var ACCOUNT_ANIM_OUT = "fadeOut";
        var ACCOUNT_DELAY_IN = "";
        var ACCOUNT_DELAY_OUT = "";
        var ACCOUNT_DELAY_TIMER = 1000;

        var POPULAR_ANIM_OUT = "fadeOutDownBig";
        var POPULAR_ANIM_IN = "fadeInUpBig";
        var POPULAR_DELAY_OUT = "";
        var POPULAR_DELAY_IN = "delay_02s";

        var LOGINLINKS_ANIM_OUT = "rotateOutUpRight";
        var LOGINLINKS_ANIM_IN = "rotateInDownRight";
        var LOGINLINKS_DELAY_OUT = "";
        var LOGINLINKS_DELAY_IN = "delay_02s";

        var HEADINGS_ANIM_OUT = "fadeOut";
        var HEADINGS_ANIM_IN = "fadeIn";
        var HEADINGS_DELAY_OUT = "";
        var HEADINGS_DELAY_IN = "delay_02s";

        var login = document.getElementById("log_in_link");
        var signup = document.getElementById("sign_up_link");
        var view_login = document.getElementById("view_login");
        var home = document.getElementById("home");

        var back_to_home = document.getElementById("back_to_home");

        var popular_wines = document.getElementById("popular_wines");
        var login_links = document.getElementById("login_links");
        var home_headings = document.getElementById("home_headings");

        var login_form = document.getElementById("login_form");
        var signup_form = document.getElementById("create_account_form");

        /**
         * Shows the Account View and displays the login form.
         */
        function show_login_form()
        {
            show_account_view();

            $(login_form).removeClass(NO_DISPLAY);
            $(login_form).removeClass(LOGIN_FORM_ANIM_OUT);
            $(login_form).addClass(LOGIN_FORM_DELAY_IN);
            $(login_form).addClass(LOGIN_FORM_ANIM_IN);
        }

        /**
         * Shows the Account View and displays the sign-up form.
         */
        function show_sign_up_form()
        {
            show_account_view();

            $(signup_form).removeClass(NO_DISPLAY);
            $(signup_form).removeClass(SIGNUP_FORM_ANIM_OUT);
            $(signup_form).addClass(SIGNUP_FORM_DELAY_IN);
            $(signup_form).addClass(SIGNUP_FORM_ANIM_IN);
        }

        /**
         * Hides the login form.
         */
        function hide_login_form()
        {
            $(login_form).removeClass(LOGIN_FORM_DELAY_IN);
            $(login_form).removeClass(LOGIN_FORM_ANIM_IN);
            $(login_form).addClass(LOGIN_FORM_DELAY_OUT);
            $(login_form).addClass(LOGIN_FORM_ANIM_OUT);

            setTimeout(function() {
                console.log("adding no display to login form.");
                $(login_form).addClass(NO_DISPLAY);
            }, LOGIN_FORM_OUT_TIMER);
        }

        /**
         * Hides the sign-up form.
         */
        function hide_sign_up_form()
        {
            $(signup_form).removeClass(SIGNUP_FORM_ANIM_IN);
            $(signup_form).removeClass(SIGNUP_FORM_DELAY_IN);
            $(signup_form).addClass(SIGNUP_FORM_DELAY_OUT);
            $(signup_form).addClass(SIGNUP_FORM_ANIM_OUT);

            setTimeout(function() {
                console.log("adding no display to signup form.");
                $(signup_form).addClass(NO_DISPLAY);
            }, SIGNUP_FORM_OUT_TIMER);
        }

        /**
         * Shows the account view.
         */
        function show_account_view()
        {
            $(view_login).removeClass(ACCOUNT_DELAY_OUT);
            $(view_login).removeClass(ACCOUNT_ANIM_OUT);;
            $(view_login).removeClass(NO_DISPLAY);
            $(view_login).addClass(ACCOUNT_DELAY_IN);
            $(view_login).addClass(ACCOUNT_ANIM_IN);

            hide_home();
        }

        /**
         * Hides the account view.
         */
        function hide_account_view()
        {
            $(view_login).removeClass(ACCOUNT_DELAY_IN);
            $(view_login).removeClass(ACCOUNT_ANIM_IN);;
            $(view_login).addClass(ACCOUNT_DELAY_OUT);
            $(view_login).addClass(ACCOUNT_ANIM_OUT);

            setTimeout(function() {
                $(view_login).addClass(NO_DISPLAY);
            }, ACCOUNT_DELAY_TIMER);

            hide_sign_up_form();
            hide_login_form();

            show_home();
        }

        /**
         * Hides the home view.
         */
        function hide_home()
        {
            $(home).removeClass(HOME_DELAY_IN);
            $(home).removeClass(HOME_ANIM_IN);
            $(home).addClass(HOME_DELAY_OUT);
            $(home).addClass(HOME_ANIM_OUT);

            setTimeout(function() {
                $(home).addClass(NO_DISPLAY);
            }, HOME_DELAY_TIMER);

            $(popular_wines).removeClass(POPULAR_ANIM_IN);
            $(popular_wines).removeClass(POPULAR_DELAY_IN);
            $(popular_wines).addClass(POPULAR_DELAY_OUT);
            $(popular_wines).addClass(POPULAR_ANIM_OUT);

            $(login_links).removeClass(LOGINLINKS_ANIM_IN);
            $(login_links).removeClass(LOGINLINKS_DELAY_IN);
            $(login_links).addClass(LOGINLINKS_DELAY_OUT);
            $(login_links).addClass(LOGINLINKS_ANIM_OUT);

            $(home_headings).removeClass(HEADINGS_ANIM_IN);
            $(home_headings).removeClass(HEADINGS_DELAY_IN);
            $(home_headings).addClass(HEADINGS_DELAY_OUT);
            $(home_headings).addClass(HEADINGS_ANIM_OUT);
        }

        /**
         * Shows the home view.
         */
        function show_home()
        {
            $(home).removeClass(NO_DISPLAY);
            $(home).removeClass(HOME_DELAY_OUT);
            $(home).removeClass(HOME_ANIM_OUT);
            $(home).addClass(HOME_DELAY_IN);
            $(home).addClass(HOME_ANIM_IN);

            $(popular_wines).removeClass(POPULAR_DELAY_OUT);
            $(popular_wines).removeClass(POPULAR_ANIM_OUT);
            $(popular_wines).addClass(POPULAR_DELAY_IN);
            $(popular_wines).addClass(POPULAR_ANIM_IN);

            $(login_links).removeClass(LOGINLINKS_ANIM_OUT);
            $(login_links).removeClass(LOGINLINKS_DELAY_OUT);
            $(login_links).addClass(LOGINLINKS_DELAY_IN);
            $(login_links).addClass(LOGINLINKS_ANIM_IN);

            $(home_headings).removeClass(HEADINGS_ANIM_OUT);
            $(home_headings).removeClass(HEADINGS_DELAY_OUT);
            $(home_headings).addClass(HEADINGS_DELAY_IN);
            $(home_headings).addClass(HEADINGS_ANIM_IN);
        }

        signup.addEventListener("click", show_sign_up_form);
        login.addEventListener("click", show_login_form);
        back_to_home.addEventListener("click", hide_account_view);
    }

    function on_load()
    {
        login_form_interaction();
        login_view_interaction();
        sign_up_interaction();
    }

    window.addEventListener("load", on_load);
})();
