"use strict"
class DiceController {

	constructor(root = null) {
		this.root = root;
		this.run = this.run.bind(this);
		this.rollDice = this.rollDice.bind(this);
	}

	run() {
		const btRef = document
			.getElementById(this.root)
			.querySelector("[data-dicebutton]");
		btRef.addEventListener("click", this.rollDice, true);
	}

	rollDice() {
		const dice = new Dice();
		dice.roll();
		const diceoutput = document
			.getElementById(this.root)
			.querySelector("[data-diceoutput]");
		diceoutput.innerText = dice.value;
	}

}

const controller = new DiceController("root");
document.addEventListener("DOMContentLoaded", controller.run, true);

