<section class="wrapper clearfix view animated no_display fadeInUp" id="wizard">
    <section class="column_50">
    
		<div class="light_bg_suggestion with_border shadow">
			<h1>Recent Suggestions</h1>
			<hr style="width: 75%; left: -5px">
			
			<ul class="wine_grid">
				<li class="wine">sangiovese. palumbo family vineyards. 1992-2012. $35-$40. red.
				
				<a>use this search &rarr;</a></li>
			
			
				<li class="wine">white zinfandel. elk cove vineyards. 2011-2012. $5-$20. white.
				
				<a>use this search &rarr;</a></li>
			
			
				<li class="wine">chianti. lecter winery. 1933-1991. $200-$400. red.
				
				<a>use this search &rarr;</a></li>
			</ul>
            <div class="clearfix"></div>
		</div>
    </section>
    <section class="column_50">
		<div class="light_bg_wizard with_border shadow">
			<form id="wizard_form">
				<select id="type" name="type">
					<option>type</option>
					<option value="Red Wine">red</option>
					<option value="White Wine">white</option>
					<option value="Rosé Wine">ros&eacute;</option>
					<option value="Champagne &amp; Sparkling">champagne & sparkling</option>
					<option value="">sak&eacute;</option>
					<option value="Dessert/Fortified">dessert, sherry, & port</option>
				</select>
			
				<select id="varietalSelect" name="varietal">
					<option>grape varietal</option>
					<option>Albarino</option>
					<option>Barbera</option>
					<option>Bordeaux</option>
					<option>Cabernet Franc</option>
					<option>Cabernet Sauvignon</option>
					<option>Carmenere</option>
					<option>Chardonnay</option>
					<option>Chenin Blanc</option>
					<option>Dolcetto</option>
					<option>Gamay</option>
					<option>Gewurztraminer</option>
					<option>Grenache</option>
					<option>Gruner Veltliner</option>
					<option>Madeira</option>
					<option>Malbec</option>
					<option>Merlot</option>
					<option>Mourvedre</option>
					<option>Muscat</option>
					<option value="Nebbiolo">Nebbiolo</option>
					<option>Nero d'Avola</option>
					<option>Non-vintage</option>
					<option value="Riesling">Riesling</option>
					<option>Rh&ocirc;ne</option>
					<option>Ros&eacute;</option>
					<option>Sangiovese</option>
					<option>Sauvignon Blanc</option>
					<option>Semillon</option>
					<option>Sherry</option>
					<option>Syrah/Shiraz</option>
					<option>Tempranillo</option>
					<option>Torrontes</option>
					<option>Petite Sirah</option>
					<option>Pinot Blanc</option>
					<option>Pinot Gris/Grigio</option>
					<option>Primivito</option>
					<option>Port</option>
					<option>Voignier</option>
					<option>Zinfandel</option>
					<option>Other</option>
				</select>
				
				<input type="text" name="vineyard" placeholder="vineyard" value="" />
				<input type="text" name="min_year" placeholder="min year" value="" class="number"/>
				<input type="text" name="max_year" placeholder="max year" value="" class="number"/><br>
				<input type="text" name="min_price" placeholder="min price" value="" class="number"/>
				<input type="text" name="max_price" placeholder="max price" value="" class="number"/>
				<hr style="width:50%; text-align:center; margin-left:auto; margin-right:auto">
				<input type="button" value="submit" />
			</form>
		</div>
    </section>

    <script type="text/javascript" src="/js/wizard.js"></script>
</section>
