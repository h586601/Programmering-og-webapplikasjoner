/**
 * Her har vi ingen DOMContentLoaded, så vi må bruker defer eller type="module" i html script taggen.
 */

function buttonWasClicked(event) {
    console.log("Du klikket på et HTML-element. Bakgrunnsfargen til elementet blir nå satt til 'yellow'.")
    event.target.style.backgroundColor = "yellow"

	console.log("Dette er parameteren i funksjonen: ")
	console.dir(event)
	//This code console.dir lets you look at all the properties and methods that come with the MouseEvent. 
}

let buttonElement = document.getElementById("buttonDemo")
buttonElement.addEventListener("click",buttonWasClicked)