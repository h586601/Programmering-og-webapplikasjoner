<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="no">
  <head>
    <link rel="shortcut icon" href="#">
    <link href="main.css" rel="stylesheet" type="text/css" />
    <link href="formcontroller.css" rel="stylesheet" type="text/css" />
    <script src="js/DeltagerValidering.js" defer></script>
    <script src="js/FormController.js" defer></script>

    <title>Påmelding</title>
  </head>
  <body>
    <h2>Påmelding</h2>

    <div id="root">
      <form method="post" class="paamelding">
        <fieldset>
          <div class="fornavn paamelding-input">
	          <label for="fornavn">Fornavn:</label> 
	          <input type="text" name="fornavn" id="fornavn" value="${skjema.fornavn}" 
	          required />
	          <span class="melding">${skjema.fornavnFeil}</span>
		  </div>

		  <div class="etternavn paamelding-input">
	          <label for="etternavn">Etternavn:</label> 
	          <input type="text" name="etternavn" id="etternavn" value="${skjema.etternavn}"
	          required />
	          <span class="melding">${skjema.etternavnFeil}</span>
		  </div>

		  <div class="mobil paamelding-input">
	          <label for="mobil">Mobil (8 siffer):</label> 
	          <input type="text" name="mobil" id="mobil" value="${skjema.mobil}"
	          required />
	          <span class="melding">${skjema.mobilFeil}</span>
	      </div>

		  <div class="passord paamelding-input">
	          <label for="passord">Passord:</label> 
	          <input type="password" name="passord" id="passord" value="${skjema.passord}"
	          required />
	          <span class="pw-info">?</span>
	          <span class="melding">${skjema.passordFeil}</span>
          </div>

		  <div class="passord-repetert paamelding-input">
	          <label for="passordRepetert">Passord repetert:</label> 
	          <input type="password" name="passordRepetert" id="passordRepetert" value="${skjema.passordRep}"
	          required />
	          <span class="melding">${skjema.passordRepFeil}</span>
          </div>

		  <div class="kjonn paamelding-input">
	          <span class="columnfirst">Kjønn:</span>
	          <span data-kjonn>
	            <label><input type="radio" name="kjonn" value="mann" ${skjema.kjonn eq "mann" ? "checked=\"checked\"" : ""} /> mann</label>
	            <label><input type="radio" name="kjonn" value="kvinne" ${skjema.kjonn eq "kvinne" ? "checked=\"checked\"" : ""} />kvinne</label>
	          </span>
	          <span class="melding">${skjema.kjonnFeil}</span>
          </div>

          <button type="submit">Meld meg på</button>
        </fieldset>
      </form>
      
      <div data-info="passord"></div>
      <div data-info="submit"></div>
    </div>
  </body>
</html>
