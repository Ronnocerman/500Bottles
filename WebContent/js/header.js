(function() {
    var VIEW_CONTAINER = "#view_container";

    /**
     * Main navigation link elements.
     * @type {HTMLElement}
     */
    var logo = document.getElementById("logo");
    var nav_wine = document.getElementById("nav_wines");
    var nav_wizard = document.getElementById("nav_wizard");
    var nav_cellar = document.getElementById("nav_cellar");
    var nav_winebook = document.getElementById("nav_winebook");

    /**
     * Animation classes to be used in view animation.
     * @type {string}
     */
    var VIEW_ANIM_IN_CLASS = "fadeIn";
    var VIEW_ANIM_OUT_CLASS = "fadeOut";
    var VIEW_NO_DISPLAY = "no_display";

    /**
     * The duration of view animation. This is the amount of time to wait
     * before applying the NO_DISPLAY class.
     * @type {number}
     */
    var VIEW_ANIM_TIMER = 1000;


    /**
     * View elements.
     * @type {HTMLElement}
     */
    var home = document.getElementById("home");
    var view_wine = null;
    var view_wizard = null;
    var view_cellar = null;
    var view_winebook = null;

    /**
     * Holds the currently active view. Initialized to the homepage.
     * @type {HTMLElement}
     */
    var active_view = home;

    /**
     * Update flags indicating if the corresponding view should be updated
     * on the next switch-view request.
     * TODO: These should probably be available outside this class.
     * @type {boolean}
     */
    var update_wine_flag = false;
    var update_wizard_flag = false;
    var update_cellar_flag = false;
    var update_winebook_flag = false;

    function on_logo_click(){
        animate_in_view(home);
    }

    /**
     * Called on main navigation "Wine" button click.
     */
    function on_nav_wine_click()
    {
        // Retrieves a new wine view if the view isn't present on the page,
        // or if an update request has been flagged.
        if (!view_wine || update_wine_flag) {
            get_view("wine", function(data) {

                if (view_wine)
                    $(view_wine).remove();

                add_view(data);

                view_wine = document.getElementById("wine");
                update_wine_flag = false;

                on_nav_wine_click();
            });
        } else {
            animate_in_view(view_wine);
        }
    }

    /**
     * Called on main navigation "Wizard" button click.
     */
    function on_nav_wizard_click()
    {
        // Retrieves a new wizard view if the view isn't present on the page,
        // or if an update request has been flagged.
        if (!view_wizard || update_wizard_flag) {
            get_view("wizard", function(data) {

                if (view_wizard)
                    $(view_wizard).remove();

                add_view(data);

                view_wizard = document.getElementById("wizard");
                update_wizard_flag = false;

                on_nav_wizard_click();
            });
        } else {
            animate_in_view(view_wizard);
        }
    }

    /**
     * Called on main navigation "Cellar" button click.
     */
    function on_nav_cellar_click()
    {
        // Retrieves a new cellar view if the view isn't present on the page,
        // or if an update request has been flagged.
        if (!view_cellar || update_cellar_flag) {
            get_view("cellar", function(data) {

                if (view_cellar)
                    $(view_cellar).remove();

                add_view(data);

                view_cellar = document.getElementById("cellar");
                update_cellar_flag = false;

                on_nav_cellar_click();
            });
        } else {
            animate_in_view(view_cellar);
        }
    }

    /**
     * Called on main navigation "Winebook" button click.
     */
    function on_nav_winebook_click()
    {
        // Retrieves a new winebook view if the view isn't present on the page,
        // or if an update request has been flagged.
        if (!view_winebook || update_winebook_flag) {
            get_view("winebook", function(data) {

                if (view_winebook)
                    $(view_winebook).remove();

                add_view(data);

                view_winebook = document.getElementById("winebook");
                update_winebook_flag = false;

                on_nav_winebook_click();
            });
        } else {
            animate_in_view(view_winebook);
        }
    }

    /**
     * Appends the received HTML to the page.
     */
    function add_view(view_contents)
    {
        $(VIEW_CONTAINER).append(view_contents);
    }

    /**
     * Executes an AJAX request to retrieve a view.
     * @param view_name     The name of the view.
     * @param cb            Callback function.
     */
    function get_view(view_name, cb)
    {
        var url = "/";

        var data = {
            "action": "getView",
            "view": view_name
        };

        $.ajax({
            url: url,
            data: data
        }).success(function (data, textStatus, jqXHR) {
            cb.call(this, data);
        });
    }

    /**
     * Animates in the given view.
     * @param view
     */
    function animate_in_view(view)
    {
        if (view == active_view)
            return;

        $(active_view).removeClass(VIEW_ANIM_IN_CLASS);
        $(active_view).addClass(VIEW_ANIM_OUT_CLASS);

        $(view).removeClass(VIEW_NO_DISPLAY);
        $(view).removeClass(VIEW_ANIM_OUT_CLASS);
        $(view).addClass(VIEW_ANIM_IN_CLASS);

        setTimeout(function() {
            $(active_view).addClass(VIEW_NO_DISPLAY);
            active_view = view;
            fix_body_height(view);
        }, VIEW_ANIM_TIMER);

        $("body").animate({
            scrollTop: 0
        }, 500);
    }

    function fix_body_height(view)
    {
        var header_height = $("header").height();
        var content_height = $(view).height();

        console.log("new height: ", content_height + header_height);

        $("body").height(content_height + header_height);
    }

    // Attach the header event listeners.
    $(logo).on("click", on_logo_click);
    $(nav_wine).on("click", on_nav_wine_click);
    $(nav_wizard).on("click", on_nav_wizard_click);
    $(nav_cellar).on("click", on_nav_cellar_click);
    $(nav_winebook).on("click", on_nav_winebook_click);
})();