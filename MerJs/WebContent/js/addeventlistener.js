/**
 * click()
 * Knytte metode til hendelse på element
 */


"use strict";

const root = document.getElementById("root")
const buttonRef = root.querySelector("button[type='button']")

function helloWorld () {
    console.log("Hello world")
}

/**
 * Legger til lytter for hendelse 'click' på HTML BUTTON elementet.
 * Ved 'click' på HTML BUTTON kjøres metoden 'helloWorld'.
 */
buttonRef.addEventListener("click",helloWorld)