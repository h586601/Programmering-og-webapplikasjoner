/**
 * 
 */
"use strict"

// En FormController som henter ut elementene og endrer utseende via classList, ikke style attributtet.
// FormController-klassen vet om hvordan form
// skjema sin struktur er, men ingenting om validering.

class FormController {
	
	constructor(root) {
		this.root = root;
		
		this.mediumPasswordLen = 6;
		this.strongPasswordLen = 8;
		
		this.showPasswordInfo = this.showPasswordInfo.bind(this);
		this.run = this.run.bind(this);
	}
	
	showPasswordInfo() {
		alert("Passordstyrke: \n\nMellom 4 og 5 tegn er svakt (rød) \nMellom 6 og 8 er middels (gul) \nLengre enn 8 er sterkt (grønn) \n\nTrykk enter for å få vekk alert");
	}
	
	addPasswordStrengthStyling(obj) {
		if (obj.input.value.length < this.mediumPasswordLen) {
			obj.elem.classList.add("formcontroller_weakPassword");
			obj.elem.classList.remove("formcontroller_mediumPassword");
			obj.elem.classList.remove("formcontroller_strongPassword");
		} else if (obj.input.value.length < this.strongPasswordLen) {
			obj.elem.classList.add("formcontroller_mediumPassword");
			obj.elem.classList.remove("formcontroller_weakPassword");
			obj.elem.classList.remove("formcontroller_strongPassword");
		} else {
			obj.elem.classList.add("formcontroller_strongPassword");
			obj.elem.classList.remove("formcontroller_weakPassword");
			obj.elem.classList.remove("formcontroller_mediumPassword");
		}
	}
	
	addValidStyling(elem) {
		elem.classList.add("formcontroller_validInput");
		elem.classList.remove("formcontroller_invalidInput");
	}
	
	addInvalidStyling(elem) {
		elem.classList.add("formcontroller_invalidInput");
		elem.classList.remove("formcontroller_validInput");
	}

	addValidationStyling(obj) {		
		if (obj.test) {
			this.addValidStyling(obj.elem);
			if (obj.elem2 !== undefined) this.addValidStyling(obj.elem2);
		} else {
			this.addInvalidStyling(obj.elem);
			if (obj.elem2 !== undefined) this.addInvalidStyling(obj.elem2);
		}
	}
	
	run() {
		const rootElement = document.getElementById(this.root);
		const pwInfo = rootElement.querySelector(".pw-info");
		const fornavnInput = rootElement.querySelector("#fornavn");
		const etternavnInput = rootElement.querySelector("#etternavn");
		const mobilInput = rootElement.querySelector("#mobil");
		const passordInput = rootElement.querySelector("#passord");
		const passordRepetertInput = rootElement.querySelector("#passordRepetert");
		const kjonn = rootElement.querySelector(".kjonn");
		const submitInput = rootElement.querySelector("button[type='submit']");
		
		const validator = new DeltagerValidering();
		
		const addValidationStylingForName = () => {
			this.addValidationStyling({
				elem: fornavnInput, test: 
				validator.testName(fornavnInput),
			});
		}
		
		const addValidationStylingForLastName = () => {
			this.addValidationStyling({
				elem: etternavnInput, 
				test: validator.testLastName(etternavnInput),
			});
		}
		
		const addValidationStylingForPhone = () => {
			this.addValidationStyling({
				elem: mobilInput, 
				test: validator.testPhone(mobilInput),
			});
		}
		
		const addValidationStylingForPassword = () => {
			this.addValidationStyling({
				elem: passordInput,
				test: validator.testPassword(passordInput, passordRepetertInput),
			});
		}
		
		const addValidationStylingForPasswordRepeated = () => {
			this.addValidationStyling({
				elem: passordRepetertInput,
				test: validator.testPassword(passordInput, passordRepetertInput),
			});
		}
		
		const addValidationStylingForCheckboxes = () => {
			this.addValidationStyling({
				elem: kjonn, 
				test: validator.testCheckboxes(kjonn),
			});
		}
		
		// We first validate on pageLoad
		addValidationStylingForName();
		addValidationStylingForLastName();
		addValidationStylingForPhone();
		addValidationStylingForPassword();
		addValidationStylingForPasswordRepeated();
		addValidationStylingForCheckboxes()
		//validator.testSubmit(submitInput);
		
		const readyToSubmit = () => {
			if (validator.isEverythingValid()) {
				submitInput.disabled = false;
			} else {
				submitInput.disabled = true;
			}
		}
		
		readyToSubmit();
		
		
		
		// Then we validate on input change
		fornavnInput.addEventListener("input", () => {
			addValidationStylingForName();
			readyToSubmit();
		});
		
		etternavnInput.addEventListener("input", () => {
			addValidationStylingForLastName();
			readyToSubmit();
		});
		
		mobilInput.addEventListener("input", () => {
			addValidationStylingForPhone();
			readyToSubmit();
		});
		
		passordInput.addEventListener("input", () => {
			addValidationStylingForPassword();
			this.addPasswordStrengthStyling({ elem: pwInfo, input: passordInput });
			readyToSubmit();
		});
		
		passordRepetertInput.addEventListener("input", () => {
			addValidationStylingForPasswordRepeated();
			readyToSubmit();
		});
		
		kjonn.addEventListener("input", () => {
			addValidationStylingForCheckboxes();
			readyToSubmit();
		});
		
		submitInput.addEventListener("click", (e) => {
			if (confirm("Jeg ønsker å sende inn skjemaet!")) {
				return true;
			} else {
				e.preventDefault();
				return false;
			}
		});
		
		
		
		rootElement
			.querySelector(".pw-info")
			.addEventListener("mouseover", this.showPasswordInfo);
	}
}

const controller = new FormController("root");
document.addEventListener("DOMContentLoaded", controller.run)
