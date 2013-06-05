(function() {
    window._500bottles = window._500bottles || {};
    window._500bottles.views = window._500bottles.views || {};

    var NO_DISPLAY = "no_display";
    var BLACKOUT_ANIM_IN = "fadeIn";
    var BLACKOUT_ANIM_OUT = "fadeOut";
    var BLACKOUT_TIMER = 1000;

    window._500bottles.setBlackout = function()
    {
        var blackout = document.getElementById("blackout");

        if (blackout == null)
            $("body").prepend($("<div/>").attr("id", "blackout").addClass("animated"));

        blackout = document.getElementById("blackout");

        $(blackout).removeClass(NO_DISPLAY);
        $(blackout).removeClass(BLACKOUT_ANIM_OUT);
        $(blackout).addClass(BLACKOUT_ANIM_IN);
    }

    window._500bottles.clearBlackout = function()
    {
        var blackout = document.getElementById("blackout");

        if (blackout == null)
            return;

        $(blackout).removeClass(BLACKOUT_ANIM_IN);
        $(blackout).addClass(BLACKOUT_ANIM_OUT);

        setTimeout(function() {
            $(blackout).addClass(NO_DISPLAY);
        }, BLACKOUT_TIMER);
    }

    /***************************************************************************
     *
     * Object animation scripts.
     *
     **************************************************************************/

    /**
     * Tracks all animation timers.
     * @type {Array}
     */

    var animation_timers = [];

    window._500bottles.animate_in = function(data)
    {
        data.animation_class_in  = data.animation_class_in  || $(data.element).data("anim-in");
        data.animation_class_out = data.animation_class_out || $(data.element).data("anim-out");
        data.time = data.time || 1000;

        clearTimeout(animation_timers[data.element]);

        $(data.element).removeClass(NO_DISPLAY);
        $(data.element).removeClass(data.animation_class_out);
        $(data.element).addClass(data.animation_class_in);

        animation_timers[data.element] = animation_timers[data.element] = setTimeout(function() {
            if (data.callback)
                data.callback.call(this);
        }, data.time);
    }

    window._500bottles.animate_out = function(data)
    {
        data.animation_class_in  = data.animation_class_in  || $(data.element).data("anim-in");
        data.animation_class_out = data.animation_class_out || $(data.element).data("anim-out");
        data.time = data.time || 1000;

        clearTimeout(animation_timers[data.element]);

        $(data.element).removeClass(data.animation_class_in);
        $(data.element).addClass(data.animation_class_out);

        animation_timers[data.element] = setTimeout(function() {
            $(data.element).addClass(NO_DISPLAY);

            if (data.callback)
                data.callback.call(this);

        }, data.time);
    }


    var VIEW_CONTAINER = "#view_container";
    window._500bottles.add_view = function(view_contents)
    {
        $(VIEW_CONTAINER).append(view_contents);
    }

    window._500bottles.fix_body_height = function(element)
    {
        var header_height = $("header").outerHeight(true);
        var content_height = $(element).outerHeight(true);

        $("body, #view_container").height(content_height + header_height);
    }

})();

