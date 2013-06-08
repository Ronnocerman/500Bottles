/**
 *
 */
(function () {
    /* Parent of image gallery jQuery selector. */
    var GALLERY_PARENT_SEL = ".wine_grid_container";

    /* jQuery selector for gallery container element. */
    var GALLERY_CONTAINER_SEL = ".wine_grid";

    /* Prefix to add to gallery element class names. */
    var GALLERY_CLASS_PREFIX = "wine_";

    /* jQuery selector for elements within GALLERY_CONTAINER to treat as image. */
    var IMAGE_ELEMENT_SEL = ".wine";

    /* jQuery selector for next page button. */
    var NEXT_PAGE_BTN = ".next";

    /* jQuery selector for previous page button. */
    var PREV_PAGE_BTN = ".prev";

    /* Number of image gallery rows to create. Can be overwritten by setting
     * data-row attribute of the GALLERY_CONTAINER_SEL.
     */
    var NUM_OF_ROWS = 2;

    var SLIDE_MARGIN = 300;

    /************************** End Gallery Config ****************************/

    /* Holds references to image objects. */
    var images = [];        // populated by guild_gallery

    /* Holds references to each gallery row. */
    var image_rows = [];    // populated by build_gallery()

    /* Max number of images per row. */
    var images_per_row;     // calculated by build_gallery()

    /*
     * Tracks the currently open wine details view.
     */
    var open_wine_details_view = null;

    /* Binds button event handlers. */
    function bind_events(gallery_parent_selector)
    {
        /*
         * Attaches event handlers to this specific gallery's next and previous
         * buttons.
         */
        $(gallery_parent_selector).each(function (index, el) {

            $(el).children(NEXT_PAGE_BTN).on("click", function () {
                return (function (e) { next_page(e); })(el);
            });

            $(el).children(PREV_PAGE_BTN).on("click", function () {
                return (function (e) { previous_page(e); })(el);
            });
        });

        /*
         * This trickery adds the flipping effects to the wine items. A class
         * "disable_flip is added to prevent successive hovers from repeatedly
         * flipping the items when hovering over a nearby element, specifically
         * the next and previous arrows.
         */
        $(gallery_parent_selector + " " + IMAGE_ELEMENT_SEL).hover(function (e) {
            // Test if the flip is disabled. If so, return.
            if ($(this).hasClass("disable_flip"))
                return;
            $(this).addClass("flipped");

        }, function () {
            $(this).removeClass("flipped");
            $(this).addClass("disable_flip");

            // Grab a reference to `this` wine item for the timeout and, and
            // remove the "disable_flip" class after a specified period of time.
            var el = this;
            setTimeout(function () {
                $(el).removeClass("disable_flip");
            }, 400);
        });

        // Bind the open details event.
        $(gallery_parent_selector + " " + IMAGE_ELEMENT_SEL).on("click", open_wine_details);

        // Binds the close details event.
        $(gallery_parent_selector + " " + IMAGE_ELEMENT_SEL + " .close_icon").on("click", close_wine_details);

        $(gallery_parent_selector + " " + IMAGE_ELEMENT_SEL + " .favorite_banner").on("click", favorites_banner_click);
    }

    /**
     * Opens a wine details view. Closes any other open wine details view.
     * @param e
     */
    function open_wine_details(e)
    {
        var details_view = $(this).children(".details")[0];
        _500bottles.anim.animate_in({
            element: $(this).children(".details")[0],
            callback: function() {
                $(details_view).addClass("open");
            }
        });

        $(details_view).draggable();

        if ($(details_view).offset().top < 0)
            $(details_view).css("margin-top", "-2%");

        if ($(details_view).offset().left < $(details_view).outerWidth(true))
            $(details_view).css("margin-left", "0%");
    }

    /**
     * Closes the wine details views.
     * @param e
     */
    function close_wine_details(e)
    {
        var detais_view = $(this).parent()[0];

        _500bottles.anim.animate_out({
            element: detais_view,
            time: 700,
            callback: function() {
                $(detais_view).removeClass("open");
                $(detais_view).draggable("destroy");
                $(detais_view).removeAttr("style");
            }
        });
        e.stopPropagation();
    }

    function favorites_banner_click(e)
    {
        var parent_wine = $(this).parents(".wine");
        var is_favorite;

        if ($(parent_wine).hasClass("favorite"))
            is_favorite = true;
        else
            is_favorite = false;

        if (!is_favorite)
            set_favorite(parent_wine);
        else
            clear_favorite(parent_wine);

        e.stopPropagation();
    }

    function set_favorite(wine_el)
    {
        var wine_id = $(wine_el).data("wine-id");
        var url = "/favorites"

        $(wine_el).addClass("favorite");

        var data = {
            "action": "setFavorite",
            "wineId": wine_id
        };

        $.ajax({
            url: url,
            data: data
        }).success(function (data, textStatus, jqXHR) {
            console.log("favorites success!");
        });
    }

    function clear_favorite(wine_el)
    {
        var wine_id = $(wine_el).data("wine-id");
        var url = "/favorites"

        $(wine_el).removeClass("favorite");

        var data = {
            "action": "clearFavorite",
            "wineId": wine_id
        };

        $.ajax({
            url: url,
            data: data
        }).success(function (data, textStatus, jqXHR) {
                console.log("favorites success!");
            });
    }

    function position_your_rating(e)
    {
        var your_rating = $(e.target).siblings(".your_rating");
        var width = $(your_rating).outerWidth(true);
        $(your_rating).css("left", -(width - e.offsetX));
    }

    function position_their_rating(wine)
    {
        var their_rating_div = $(wine).find(".their_rating");
        var rating = $(their_rating_div).data("wine-rating");
        var width = 100;//$(their_rating_div).outerWidth(true);

        console.log("their rating: ", rating);
        console.log("width: ", width);

        var position = (Number(rating) / 5) * width;

        console.log("position: ", position - width);

        $(their_rating_div).css("left", position - width);

        //console.log("setting rating for ", wine, position);
    }

    function on_rating_hover(e)
    {
        $(e.target).on("mousemove", position_your_rating);
    }

    function off_rating_hover(e)
    {
        $(e.target).off("mousemove", position_your_rating);
    }

    function bind_rating_events(wine)
    {
        $(wine).find(".rating").hover(on_rating_hover, off_rating_hover);
    }

    /* Builds internal representation of gallery. */
    function build_gallery(gallery_parent_selector, randomize) {
        var i, j,               // loop index
            start_index,        // start index for adding images to rows
            end_index;     // count of images per row

        // Remove transitions.
        $(gallery_parent_selector + " " + GALLERY_CONTAINER_SEL).addClass("no_transition");

        //NUM_OF_ROWS = $(GALLERY_PARENT_SEL).data("rows") || NUM_OF_ROWS;
        NUM_OF_ROWS = $(gallery_parent_selector).data("rows") || NUM_OF_ROWS;

        // get all the images
        images = $(gallery_parent_selector + " " + GALLERY_CONTAINER_SEL).children(IMAGE_ELEMENT_SEL);

        // calculate the number of images for each row
        images_per_row = Math.ceil(images.length / NUM_OF_ROWS);

        // build row containers
        for (i = 0; i < NUM_OF_ROWS; i++) {
            image_rows[i] = $("<div/>").addClass(GALLERY_CLASS_PREFIX + "row");

            // calculate the start and end indexes for this row
            start_index = i * images_per_row;
            end_index = (i + 1) * images_per_row;

            // add the images to this row
            for (j = start_index; j < end_index; j++) {

                // stop the loop if we've run out of images
                if (j >= images.length) break;

                // append the image to the row container
                $(image_rows[i]).append(images[j]);

                // Give the images some random-ass locations.
                var neg_left, neg_top;
                Math.random() > .5 ? neg_left = 1 : neg_left = -1;
                Math.random() > .5 ? neg_top = 1 : neg_top = -1;

                // If the randomize position flag is set, randomize the initial
                // position of the wines and set a timeout for them to animate
                // back to the correct positions.
                if (randomize) {
                    $(images[j]).css("left", Math.random() * 1000 * neg_left);
                    $(images[j]).css("top", Math.random() * 1000 * neg_top);
                    $(images[j]).css("opacity", "0");

                    setTimeout((function(el) {

                        return function() {
                            $(el).css("left", 0);
                            $(el).css("top", 0);
                            $(el).css("opacity", 1);
                        }

                    })(images[j]), 1000);
                }

                position_their_rating(images[j]);
                bind_rating_events(images[j]);
            }

            // add this row container to the gallery container
            $(gallery_parent_selector + " " + GALLERY_CONTAINER_SEL).append(image_rows[i]);
        }

        // position and size the gallery
        position_gallery(gallery_parent_selector + " " + GALLERY_CONTAINER_SEL);

        // Replace transitions.
        setTimeout(function () {
            $(gallery_parent_selector + " " + GALLERY_CONTAINER_SEL).removeClass("no_transition");
        }, 500);


        bind_events(gallery_parent_selector);
    }

    /* Positions images within the gallery container. */
    function position_gallery(selector) {
        var image_width,
            image_height,
            image_selector;

        image_selector = "." + GALLERY_CLASS_PREFIX + "row " + IMAGE_ELEMENT_SEL;

        image_width = $(image_selector).outerWidth(true);
        image_height = $(image_selector).outerHeight(true);

        $(selector).css({
            "width": image_width * images_per_row,
            "height": image_height * NUM_OF_ROWS
        });
    }

    /* Scrolls gallery to next page. */
    function next_page(gallery_parent) {
        var gallery_container = $(gallery_parent).children(GALLERY_CONTAINER_SEL);

        var parent_width = $(gallery_parent).width();
        var gallery_width = $(gallery_container).width();
        var gallery_position = parseInt($(gallery_container).css("margin-left"));
        var new_position = gallery_position - parent_width + SLIDE_MARGIN;

        if (-new_position > gallery_width - parent_width)
            new_position = -(gallery_width - parent_width);

        $(gallery_container).css("margin-left", new_position + "px");
    }

    /* Scrolls gallery to previous page. */
    function previous_page(gallery_parent) {
        var gallery_container = $(gallery_parent).children(GALLERY_CONTAINER_SEL);

        var parent_width = $(gallery_parent).width();
        var gallery_width = $(gallery_container).width();
        var gallery_position = parseInt($(gallery_container).css("margin-left"));
        var new_position = gallery_position + parent_width - SLIDE_MARGIN;

        if (new_position > 0)
            new_position = 0;

        $(gallery_container).css("margin-left", new_position + "px");
    }

    /* Handles window resize events. */
    function on_window_resize() {
        var parent_width = $(GALLERY_PARENT_SEL).width();
        var gallery_position = parseInt($(GALLERY_CONTAINER_SEL).css("margin-left"));
        var gallery_width = $(GALLERY_CONTAINER_SEL).width();

        if (-gallery_position > gallery_width - parent_width)
            gallery_position = -(gallery_width - parent_width);

        $(GALLERY_CONTAINER_SEL).css("margin-left", gallery_position + "px");
    }

    /* Fire Away! */
    $(window).on("resize", on_window_resize);

    window._500bottles = window._500bottles || {};
    window._500bottles.gallery = window._500bottles.gallery || {};
    window._500bottles.gallery.buildGallery = window._500bottles.gallery.buildGallery || build_gallery;

})();
