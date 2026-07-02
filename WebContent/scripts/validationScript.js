/**
 * In questo file troviamo lo script per aiutarci a non
 * ripetere il codice per ogni campo. 
 * L'idea e' quella di creare una funzione che prende l'attributo passato come
 * parametro e verificarlo e mandare un feedback all'utente.
 */

function check(attributeName){
	let field = document.getElementById(attributeName);
	let errorField = document.getElementById("error_" + attributeName);

	if(!field.checkValidity()){
		if(attributeName == "password"){
			errorField.textContent = "Inserisci una password tra 8 caratteri e 20 caratteri"
		}else{
			errorField.textContent = "Inserisci un " + attributeName + " valido";			
		}
	    field.classList.add("error");
	} else {
	    errorField.textContent = "";
	    field.classList.remove("error");
	}
	
}