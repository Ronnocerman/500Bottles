<section id="winebook_entry_form"
         class="floating_view animated curved shadow no_display"
         data-anim-in="fadeIn"
         data-anim-out="fadeOut">
    <div class="close_icon"></div>
    <h2 id="winebook_entry_form_title">New Winebook Entry</h2>

    <form action="/upload" method="post" enctype="multipart/form-data">
        <%--<input type="text" id="winebook_title" value="title" />--%>
        <%--<textarea>today I made some liver and fava beans, so I decided to crack open a nice chianti to go with it...</textarea>--%>
        <%--<input id="entry_save" type="button" value="save"/>--%>
        <input type="text" name="filename" />
        <input type="file" name="file" />
        <input type="submit" />
    </form>

</section>
