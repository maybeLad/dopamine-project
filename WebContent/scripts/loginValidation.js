	/**
	 * Script per la validazione dei campi del login. Usano una funzione che si trova in 
	 * validationScript che controlla se i campi sono correttamente inseriti
	 * appena l'utente toglie il focus dal campo.
	 */
	
	document.getElementById("email").addEventListener("blur", function(){
		check("email")
	});
	
	document.getElementById("password").addEventListener("blur", function(){
	    check("password")
	});
	
	function validateForm(){
	    let email = document.getElementById("email");
	    let password = document.getElementById("password");
	
	    if(!email.checkValidity() || !password.checkValidity()){
	        return false;
	    }
	    return true;
	}
	
