<%@ page import="com._500bottles.object.wine.WineQueryResult" %>
<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com._500bottles.manager.SessionManager" %>
<%@ page import="com._500bottles.manager.FavoritesManager" %>
<%@ page import="com._500bottles.action.RatingsAction" %>
<%@ page import="com._500bottles.action.CellarAction" %>

<ul class="wine_grid">

    <%
        WineQueryResult wines = (WineQueryResult) request.getAttribute("grid_wines");

        Iterator<Wine> it = wines.getIterator();

        long user_id = SessionManager.getSessionManager().getLoggedInUser().getUserId();

        request.setAttribute("user_id", user_id);

        Wine wine;

        while(it.hasNext()) {
            wine = it.next();

        String image = wine.getImage();
        long wine_id = wine.getId();
        boolean is_favorite = FavoritesManager.isFavorite(wine_id, user_id);
        double rating = wine.getRating();
        long year = wine.getYear();
        String displayYear = "";
        String description = wine.getDescription();
        long cellar_quantity = CellarAction.getCellarQuantity(wine_id);

        //double user_rating = Rating
        double user_rating = RatingsAction.getRating(wine_id);

        if (year > 0)
            displayYear = Long.toString(year);

        if (description.length() == 0)
            description = "Sorry, no description available.";

        if (image.length() == 0)
            image = "/img/blank_wine_bottle.png";

        if (is_favorite) {
    %> <li class="wine favorite" data-wine-id="<%= wine_id %>"> <%
        } else {
        %> <li class="wine" data-wine-id="<%= wine_id %>"> <%
    }
%>

    <div class="back table">
        <div class="table_row">
            <div class="wine_info table_cell">
                <div class="rating">
                    <div class="stars"></div>
                    <div class="your_rating" data-your-rating="<%= Double.toString(user_rating) %>"></div>
                    <div class="their_rating" data-wine-rating="<%= Double.toString(rating) %>"></div>
                    <div class="stars_bg"></div>
                </div>
                <div class="favorite_banner"></div>
                <span class="wine_name"><%= wine.getName() %></span>
                <span class="vineyard_name"><%= wine.getVineyard().getName() %></span>
                <span class="wine_vintage"><%= displayYear %></span>
            </div>
        </div>
    </div>

    <div class="front table">
        <div class="table_row">
            <div class="wine_image_container table_cell">
                <div class="favorite_banner"></div>
                <div class="wine_image" style="background-image: url('<%= image %>');"></div>
            </div>
            <div class="wine_info table_cell">
                <span class="wine_name"><%= wine.getName() %></span>
                <span class="wine_vintage"><%= displayYear %></span>
            </div>
        </div>
    </div>

    <div class="details animated no_display" data-anim-in="flipInY" data-anim-out="fadeOutLeftBig">
        <div class="close_icon"></div>
        <span class="wine_vintage"><%= displayYear %></span>
        <div class="table">
            <div class="table_row">
                <div class="wine_info table_cell">
                    <div class="favorite_banner"></div>
                    <span class="wine_name"><%= wine.getName() %></span>
                    <span class="vineyard_name"><%= wine.getVineyard().getName() %></span>
                </div>
            </div>
            <div class="table_row">
                <div class="table_cell">
                    <p class="description"><%= description %></p>
                </div>
            </div>
            <div class="table_row">
                <div class="table_cell">
                    <div class="align_left cellar_indicator_container">
                        <span class="tooltip" data-tip="Quantity in your cellar.">
                            <a class="cellar_indicator"><%= cellar_quantity %></a>
                        </span>
                        <div class="cellar_quantity_buttons">
                            <span class="tooltip" data-tip="Increment cellar quantity.">
                                <a class="cellar_inc"></a>
                            </span>
                            <span class="tooltip" data-tip="Decrement cellar quantity.">
                                <a class="cellar_dec"></a>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</li>
            <%

        } // End wine_item while loop.

    %>

</ul>
<div class="next"></div>
<div class="prev"></div>
