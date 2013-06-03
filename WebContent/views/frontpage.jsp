<%@ include file="/views/frontpage/account.jsp" %>

<section id="front" class="view animated no_header no_bg fadeInUp">

    <section class="content_container clearfix">

        <div class="column_66 animated" id="front_headings">
            <h1 class="animated rotateInDownRight delay_1s" style="opacity: 1; text-shadow: 0 0 20px rgba(0,0,0,1);  font-family: 'Raleway'; font-size: 7em; font-weight: 100 ">The best wine app<br/> period</h1>
        </div>

        <div class="column_33">
            <div class="content">

                <nav class="dark_bg animated" id="login_links">
                    <ul>
                        <li id="sign_up_link">sign up</li>
                        <li id="">about</li>
                        <li id="log_in_link">log in</li>
                    </ul>
                </nav>
            </div>
        </div>
    </section>

    <!--Popular Wines Grid-->
    <section id="popular_wines" class="animated">
        <div class="wine_grid_container dark_bg" data-rows="1">
            <h2>Popular Today</h2>
            <ul class="wine_grid">
                <li class="wine">
                    <img src="img/mockup/label_1.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_2.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_3.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_4.png" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_5.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_6.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_4.png" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_5.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_6.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_1.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_2.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_3.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_4.png" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_5.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_6.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_4.png" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_5.jpeg" />
                </li>
                <li class="wine">
                    <img src="img/mockup/label_6.jpeg" />
                </li>
            </ul>
            <div class="next"></div>
            <div class="prev"></div>
        </div>
    </section>

    <div class="clearfix"></div>

    <script type="text/javascript">
        _500bottles.gallery.buildGallery($("#popular_wines .wine_grid_container"));
    </script>

</section>

<script class="frontpage" src="js/frontpage.js" type="text/javascript"></script>
<div class="frontpage animated" id="frontpage_background"></div>

