/**
 * 
 */

// Så bør du ha en Validator-klasse med metoder for å sjekke passord, fornavn osv. 
// Denne vet ingenting om websiden, kun hvordan validere.

class DeltagerValidering {

	constructor() {
		this.validProps = {
			name: true,
			lastName: true,
			phone: true,
			password: true,
			passwordRepeated: true,
			checkboxes: true,
		}
	}

	isEverythingValid() {
		let isValid = true;
		for (const a in this.validProps) {
			if (this.validProps[a] === false) {
				isValid = false;
				break;
			}
		}

		return isValid;
	}

	validate(obj) {
		if (obj.customConditional !== undefined) {
			if (obj.customConditional) {
				return true;
			} else {
				return false;
			}
		} else {
			if (obj.pattern.test(obj.input.value)) {
				return true;
			} else {
				return false;
			}
		}
	}

	testName(input) {
		const pattern = /^[A-ZÆØÅ][A-ZÆØÅa-zæøå \-]{1,19}$/;

		if (this.validate({ pattern, input, })) {
			this.validProps.name = true;
			return true;
		} else {
			this.validProps.name = false;
			return false;
		}
	}

	testLastName(input) {
		// no spaces allowed
		const pattern = /^[A-ZÆØÅ][A-ZÆØÅa-zæøå\-]{1,19}$/;

		if (this.validate({ pattern, input, })) {
			this.validProps.lastName = true;
			return true;
		} else {
			this.validProps.lastName = false;
			return false;
		}
	}

	testPhone(input) {
		const pattern = /^(\d){8}$/;

		if (this.validate({ pattern, input, })) {
			this.validProps.phone = true;
			return true;
		} else {
			this.validProps.phone = false;
			return false;
		}
	}



	testPassword(input1, input2) {
		const pattern = /^.{5,30}$/;
		const password = input1.value;
		const passwordRepeated = input2.value;
		const cond = (password === passwordRepeated);

		if (this.validate({ pattern, input: input1 })) {
			this.validProps.password = true;
		} else {
			this.validProps.password = false;
			return false;
		}

		if (passwordRepeated) {
			if (this.validate({ input: input2, customConditional: cond, })) {
				this.validProps.passwordRepeated = true;
			} else {
				this.validProps.passwordRepeated = false;
				return false;
			}
		}

		return true;
	}

	testPasswordRepeated(input1, input2) {
		const pattern = /^.{5,30}$/;
		const password = input1.value;
		const passwordRepeated = input2.value;
		const cond = passwordRepeated && (password === passwordRepeated);

		if (this.validate({ pattern, input: input2, customConditional: cond, })) {
			this.validProps.passwordRepeated = true;
			return true;
		} else {
			this.validProps.passwordRepeated = false;
			return false;
		}
	}

	testCheckboxes(kjonn) {
		const radioBtns = kjonn.querySelectorAll("input[type=radio]");
		let checked = false;
		this.validProps.checkboxes = false;

		radioBtns.forEach(btn => {
			if (btn.checked) {
				checked = true;
				this.validProps.checkboxes = true;
				return;
			}
		});

		return checked;
	}
}

