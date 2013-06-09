<section id="winebook_entry_form"
         class="floating_view animated curved shadow no_display"
         data-anim-in="fadeIn"
         data-anim-out="fadeOut">
    <div class="close_icon"></div>
    <h2 id="winebook_entry_form_title">New Winebook Entry</h2>

    <form action="/upload" method="post" enctype="multipart/form-data">
        <input type="text" id="winebook_title" placeholder="title" />
        <textarea id="winebook_content" placeholder="entry"></textarea>
        <input id="entry_save" type="button" value="save"/>
    </form>

    <input id="fileupload" class="no_display" type="file" name="files[]" multiple>

    <div id="progress" class="progress progress-success progress-striped no_display">
        <div class="bar"></div>
    </div>

</section>
