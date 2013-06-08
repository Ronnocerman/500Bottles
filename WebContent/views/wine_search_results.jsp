<%@ page import="com._500bottles.object.wine.WineQueryResult" %>
<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com._500bottles.manager.SessionManager" %>
<%@ page import="com._500bottles.manager.FavoritesManager" %>

<section class="wrapper clearfix subview animated" id="quick_search_results" data-anim-out="fadeOutRightBig" data-anim-in="fadeInRightBig">
    <div id="quick_search_results_wine_container" class="wine_grid_container" data-rows="3">
        <ul class="wine_grid">
<%
    WineQueryResult result = (WineQueryResult) request.getAttribute("search_result");

    Iterator<Wine> it = result.getIterator();

    SessionManager sm = SessionManager.getSessionManager();
    long user_id = sm.getLoggedInUser().getUserId();

    Wine w;

    while(it.hasNext()) {
        w = it.next();
        String image = w.getImage();

        long wine_id = w.getId();

        boolean is_favorite = FavoritesManager.isFavorite(wine_id, user_id);

        double rating = w.getRating();

        long year = w.getYear();
        String displayYear = "";
        if (year > 0)
            displayYear = Long.toString(year);

        String description = w.getDescription();
        if (description.length() == 0)
            description = "Sorry, no description available.";

        if (image.length() == 0)
            image = "/img/blank_wine_bottle.png";


        if (is_favorite) { %>
            <li class="wine favorite" data-wine-id="<%= wine_id %>">

        <% } else { %>
            <li class="wine" data-wine-id="<%= wine_id %>">
        <%
        }
        %>

            <div class="back table">
                <div class="table_row">
                    <div class="wine_info table_cell">
                        <div class="rating">
                            <div class="stars"></div>
                            <div class="your_rating" data-your-rating="0"></div>
                            <div class="their_rating" data-wine-rating="<%= Double.toString(rating) %>"></div>
                            <div class="stars_bg"></div>
                        </div>
                        <div class="favorite_banner"></div>
                        <span class="wine_name"><%= w.getName() %></span>
                        <span class="vineyard_name"><%= w.getVineyard().getName() %></span>
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
                        <span class="wine_name"><%= w.getName() %></span>
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
                            <span class="wine_name"><%= w.getName() %></span>
                            <span class="vineyard_name"><%= w.getVineyard().getName() %></span>
                        </div>
                    </div>
                    <div class="table_row">
                        <div class="table_cell">
                            <p><%= description %></p>
                        </div>
                    </div>
                </div>
            </div>
        </li>
    <%
    }
    %>
        </ul>
        <div class="next"></div>
        <div class="prev"></div>
    </div>

    <script type="text/javascript">
        _500bottles.gallery.buildGallery("#quick_search_results_wine_container", true);
    </script>

</section>