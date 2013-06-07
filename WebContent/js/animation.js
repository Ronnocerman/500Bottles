(function() {
    window._500bottles.anim = window._500bottles.anim || {};
    var $ns = window._500bottles;

    var NO_DISPLAY = "no_display";

    /**
     * Tracks all animation timers.
     * @type {Array}
     */
    var animation_timers = [];

    /**
     * Animates an object in.
     * @param data
     *      data.element                element to animate in
     *      data.animation_class_in     animate in class
     *      data.animation_class_out    animate out class
     *      data.time                   animation time for adding no_display
     *      data.parent                 parent of element for size fixing
     */
    $ns.anim.animate_in = function(data)
    {
        data.animation_class_in  = data.animation_class_in  || $(data.element).data("anim-in");
        data.animation_class_out = data.animation_class_out || $(data.element).data("anim-out");
        data.time = data.time || 1000;
        data.parent = data.parent || false;

        clearTimeout(animation_timers[data.element]);

        $(data.element).removeClass(NO_DISPLAY);
        $(data.element).removeClass(data.animation_class_out);
        $(data.element).addClass(data.animation_class_in);

        animation_timers[data.element] = setTimeout(function() {
            if (data.parent)
                $ns.views.fix_height(data.parent, data.element);

            if (data.callback)
                data.callback.call(this);
        }, data.time);
    }

    /**
     * Animates an object out.
     * @param data
     */
    $ns.anim.animate_out = function(data)
    {
        data.animation_class_in  = data.animation_class_in  || $(data.element).data("anim-in");
        data.animation_class_out = data.animation_class_out || $(data.element).data("anim-out");
        data.time = data.time || 1000;
        data.parent = data.parent || false;

        clearTimeout(animation_timers[data.element]);

        $(data.element).removeClass(data.animation_class_in);
        $(data.element).addClass(data.animation_class_out);

        animation_timers[data.element] = setTimeout(function() {
            $(data.element).addClass(NO_DISPLAY);

            if (data.parent)
                $ns.views.fix_height(parent, data.element);

            if (data.callback)
                data.callback.call(this);

        }, data.time);
    }

})();