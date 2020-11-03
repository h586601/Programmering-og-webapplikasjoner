/**
 * 
 */
"use strict"

// En FormController som henter ut elementene og endrer utseende via classList, ikke style attributtet.
// FormController-klassen vet om hvordan formskjema sin struktur er, men ingenting om validering.

class FormController {
	
	constructor(root) {
		this.root = root;
		this.run = this.run.bind(this);
		this.mouseOver = this.mouseOver.bind(this);
	}
	
	mouseOver() {
		alert("Passordstyrke: \n\nKortere enn 5 tegn er svakt (rød) \nMellom 5 og 8 er middels (gul) \nLengre enn 8 er sterkt (grønn) \n\nTrykk enter for å få vekk alert");
	}
	
	run() {
		this.rootElement = document.getElementById(this.root);
		this.rootElement.querySelector("???").addEventListener("mouseOver", this.mouseOver);
	}
	
	
	
}

const controller = new FormController("root");
document.addEventListener("DOMContentLoaded", controller.run)
