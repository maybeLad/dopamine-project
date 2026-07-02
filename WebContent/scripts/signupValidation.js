/**
 * Script per la validazione dei campi del sign up. Usano una funzione che si trova in 
 * validationScript che controlla se i campi sono correttamente inseriti
 * appena l'utente toglie il focus dal campo.
 */

document.getElementById("email").addEventListener("blur", function(){
	check("email");
});

document.getElementById("telefono").addEventListener("blur", function(){
	check("telefono");
});

document.getElementById("password").addEventListener("blur", function(){
	check("password");
});

function validateForm(){
    let email = document.getElementById("email");
    let password = document.getElementById("password");
	let telefono = document.getElementById("telefono");


    if(!email.checkValidity() || !password.checkValidity() || !telefono.checkValidity()){
        return false;
    }
    return true;
}
