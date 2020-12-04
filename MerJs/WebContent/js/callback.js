/**
 * 
 */

"use strict";


document.addEventListener("DOMContentLoaded", () => {
	const rootElm = document.getElementById("root")
	rootElm.querySelector("button[type='button']").addEventListener("click",
		(event) => {
			window.setInterval(() => { event.target.classList.toggle("gyldig") }, 2000)
			console.dir(event)
		}
	)
}
)


