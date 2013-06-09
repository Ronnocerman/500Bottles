(function() {
    var VIEW_CONTAINER = "#view_container";

    // Grab the views namespace and add the front view object.
    views = window._500bottles.views;
    views.front = {};

    //Booleans set when views entered
    login_enter_enable = false;
    signup_enter_enable = false;
    
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
        var LOGIN_FORM_OUT_TIMER = 700;

        // Signup form animation classes.
        var SIGNUP_FORM_ANIM_IN = "flipInY";
        var SIGNUP_FORM_ANIM_OUT = "fadeOutDown";
        var SIGNUP_FORM_DELAY_IN = "delay_07s";
        var SIGNUP_FORM_DELAY_OUT = "";
        var SIGNUP_FORM_OUT_TIMER = 700;


        var FRONT_ANIM_OUT = "";
        var FRONT_ANIM_IN = "";
        var FRONT_DELAY_OUT = "";
        var FRONT_DELAY_IN = "";
        var FRONT_DELAY_TIMER = 1000;

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
        var front = document.getElementById("front");

        var back_to_front = document.getElementById("back_to_front");

        var popular_wines = document.getElementById("popular_wines");
        var login_links = document.getElementById("login_links");
        var front_headings = document.getElementById("front_headings");

        var login_form = document.getElementById("login_form");
        var signup_form = document.getElementById("create_account_form");

        /**
         * Shows the Account View and displays the login form.
         */
        function show_login_form()
        {
        	login_enter_enable = true;
            show_account_view();
            hide_sign_up_form();

            $(login_form).removeClass(NO_DISPLAY);
            $(login_form).removeClass(LOGIN_FORM_ANIM_OUT);
            $(login_form).addClass(LOGIN_FORM_DELAY_IN);
            $(login_form).addClass(LOGIN_FORM_ANIM_IN);
        }

        function shake_login_form()
        {
            $(login_form).removeClass(LOGIN_FORM_ANIM_IN);
            $(login_form).removeClass(LOGIN_FORM_DELAY_IN);
            $(login_form).addClass("shake");

            setTimeout(function() {
                $(login_form).removeClass("shake");
            }, 1000);
        }

        /**
         * Shows the Account View and displays the sign-up form.
         */
        function show_sign_up_form()
        {
        	signup_enter_enable = true;
            show_account_view();
            hide_login_form();

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
        	login_enter_enable = false;
            $(login_form).removeClass(LOGIN_FORM_DELAY_IN);
            $(login_form).removeClass(LOGIN_FORM_ANIM_IN);
            $(login_form).addClass(LOGIN_FORM_DELAY_OUT);
            $(login_form).addClass(LOGIN_FORM_ANIM_OUT);

            setTimeout(function() {
                $(login_form).addClass(NO_DISPLAY);
            }, LOGIN_FORM_OUT_TIMER);
        }

        /**
         * Hides the sign-up form.
         */
        function hide_sign_up_form()
        {
        	signup_enter_enable = false;
            $(signup_form).removeClass(SIGNUP_FORM_ANIM_IN);
            $(signup_form).removeClass(SIGNUP_FORM_DELAY_IN);
            $(signup_form).addClass(SIGNUP_FORM_DELAY_OUT);
            $(signup_form).addClass(SIGNUP_FORM_ANIM_OUT);

            setTimeout(function() {
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

            hide_front();
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

            show_front();
        }

        /**
         * Hides the front view.
         */
        function hide_front()
        {
            $(front).removeClass(FRONT_DELAY_IN);
            $(front).removeClass(FRONT_ANIM_IN);
            $(front).addClass(FRONT_DELAY_OUT);
            $(front).addClass(FRONT_ANIM_OUT);

            setTimeout(function() {
                $(front).addClass(NO_DISPLAY);
            }, FRONT_DELAY_TIMER);

            $(popular_wines).removeClass(POPULAR_ANIM_IN);
            $(popular_wines).removeClass(POPULAR_DELAY_IN);
            $(popular_wines).addClass(POPULAR_DELAY_OUT);
            $(popular_wines).addClass(POPULAR_ANIM_OUT);

            $(login_links).removeClass(LOGINLINKS_ANIM_IN);
            $(login_links).removeClass(LOGINLINKS_DELAY_IN);
            $(login_links).addClass(LOGINLINKS_DELAY_OUT);
            $(login_links).addClass(LOGINLINKS_ANIM_OUT);

            $(front_headings).removeClass(HEADINGS_ANIM_IN);
            $(front_headings).removeClass(HEADINGS_DELAY_IN);
            $(front_headings).addClass(HEADINGS_DELAY_OUT);
            $(front_headings).addClass(HEADINGS_ANIM_OUT);
        }

        /**
         * Shows the front view.
         */
        function show_front()
        {
            $(front).removeClass(NO_DISPLAY);
            $(front).removeClass(FRONT_DELAY_OUT);
            $(front).removeClass(FRONT_ANIM_OUT);
            $(front).addClass(FRONT_DELAY_IN);
            $(front).addClass(FRONT_ANIM_IN);

            $(popular_wines).removeClass(POPULAR_DELAY_OUT);
            $(popular_wines).removeClass(POPULAR_ANIM_OUT);
            $(popular_wines).addClass(POPULAR_DELAY_IN);
            $(popular_wines).addClass(POPULAR_ANIM_IN);

            $(login_links).removeClass(LOGINLINKS_ANIM_OUT);
            $(login_links).removeClass(LOGINLINKS_DELAY_OUT);
            $(login_links).addClass(LOGINLINKS_DELAY_IN);
            $(login_links).addClass(LOGINLINKS_ANIM_IN);

            $(front_headings).removeClass(HEADINGS_ANIM_OUT);
            $(front_headings).removeClass(HEADINGS_DELAY_OUT);
            $(front_headings).addClass(HEADINGS_DELAY_IN);
            $(front_headings).addClass(HEADINGS_ANIM_IN);
        }

        signup.addEventListener("click", show_sign_up_form);
        login.addEventListener("click", show_login_form);
        back_to_front.addEventListener("click", hide_account_view);

        views.front.showFrontpage = hide_account_view;
        views.front.showLoginForm = show_login_form;
        views.front.showSignupForm = show_sign_up_form;

        views.front.shakeLoginForm = shake_login_form;

        views.front.fadeOutFrontpage = function () {
            $(view_login).removeClass(ACCOUNT_DELAY_IN);
            $(view_login).removeClass(ACCOUNT_ANIM_IN);;
            $(view_login).addClass(ACCOUNT_DELAY_OUT);
            $(view_login).addClass(ACCOUNT_ANIM_OUT);

            $("#frontpage_background").addClass("fadeOut");

            setTimeout(function() {
                $(".frontpage").remove();
                $(front).remove();
                $(view_login).remove();
            }, 1000);
        }
    }

    /**
     * Initiates the Create Account form for user interaction and account
     * creation submission.
     */
    function create_account_init()
    {
        var url = "/user";
        var submit = document.getElementById("create_account_submit");

        function do_signup() {
            var data = {
                "action": "createAccount",
                "firstname": $("#create_account_firstname").val(),
                "lastname": $("#create_account_lastname").val(),
                "email": $("#create_account_email").val(),
                "password": $("#create_account_pwd").val(),
                "confPassword": $("#create_account_conf_pwd").val(),
                "dobDay": $("#bday_day").val(),
                "dobMonth": $("#bday_month").val(),
                "dobYear": $("#bday_year").val()
            };
			if(data.firstname == "")
				alert("Invalid firstname");
			if(data.lastname== "")
				alert("Invalid lastname");
			if(data.email== "" || data.email.indexOf("@" <1))
				alert("Invalid email");
			if(data.password.length < 8)
				alert("Invalid password");
			if(!data.confPassword == password)
				alert("Passwords don't match");
			if(data.dobDay < 1 || data.dobDay > 31)
				alert("Go fuck yourself");
			if(data.dobMonth <1 || data.dobMonth > 12)
				alert("No seriously, go fuck yourself");
			if(data.dobYear > 1992)
				alert("Sorry, jailbait");
            $.ajax({
                url: url,
                data: data,
                type: type
            }).success(function (data, textStatus, jqXHR) {
                    console.log(data);
                    alert("success!");
                });
        }

        $(submit).on("click", do_signup);

        $(document).keypress(function(e) {
        	if(e.which == 13 && signup_enter_enable)
                do_signup();
        });
    }

    /**
     * Initiates the Login form for user interaction and login form submission.
     */
    function login_init()
    {
        var SUCCESSFUL_LOGIN_ACTION = views.front.showFrontpage;
        var FAILED_LOGIN_ACTION = views.front.showSignupForm;

        var url = "/user";
        var submit = document.getElementById("login_submit");

		
        function do_login() {
            var data = {
                "action": "login",
                "email": $("#email").val(),
                "password": $("#password").val(),
                "success": "successful_login",
                "failed": "failed_login"
            };

            var type = "POST";

            $.ajax({
                url: url,
                data: data,
                type: type
            }).success(function (data, textStatus, jqXHR) {
                eval(data);
            });
        }

        $(submit).on("click", do_login);

        $(document).keypress(function(e) {
        	if(e.which == 13 && login_enter_enable)
                do_login();
        });
    }

    /**
     * Successful login callback function. Called after a successful login.
     */
    function successful_login()
    {
        views.front.fadeOutFrontpage();

        var url = "/";

        var data = {
            "action": "getView",
            "view": "homepage"
        };

        $.ajax({
            url: url,
            data: data,
            crossDomain: true
        }).success(function (data, textStatus, jqXHR) {
            $(VIEW_CONTAINER).append(data);

            // set the body height
            setTimeout(function() {
                _500bottles.views.fix_body_height($("#home"));
            }, 1000);
        });
    }

    /**
     * Failed login callback function. Called after a failed login.
     */
    function failed_login()
    {
        views.front.shakeLoginForm();
    }

    function on_load()
    {
        login_form_interaction();
        login_view_interaction();
        sign_up_interaction();

        login_init();
        create_account_init();
    }

    // Call away!
    on_load();
})();
