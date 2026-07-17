document.getElementById("via").addEventListener("blur", function(){
	check("via");
});


document.getElementById("civico").addEventListener("blur", function(){
	check("civico");
});


document.getElementById("citta").addEventListener("blur", function(){
	check("citta");
});


document.getElementById("cap").addEventListener("blur", function(){
	check("cap");
});


function validateForm(){

	let via = document.getElementById("via");
	let civico = document.getElementById("civico");
	let citta = document.getElementById("citta");
	let cap = document.getElementById("cap");


	if(!via.checkValidity() ||
	   !civico.checkValidity() ||
	   !citta.checkValidity() ||
	   !cap.checkValidity()){

		return false;
	}


	if(!/^[0-9]{5}$/.test(cap.value)){
		document.getElementById("error_cap").textContent = "Inserisci un CAP valido";
		cap.classList.add("error");
		return false;
	}


	if(!/[0-9]/.test(civico.value)){
		document.getElementById("error_civico").textContent = "Inserisci un numero civico valido";
		civico.classList.add("error");
		return false;
	}


	return true;
}


document.getElementById("addressForm").addEventListener("submit", function(e) {
    e.preventDefault();

    document.getElementById("error_via").innerHTML = "";
    document.getElementById("error_civico").innerHTML = "";
    document.getElementById("error_citta").innerHTML = "";
    document.getElementById("error_cap").innerHTML = "";
    document.getElementById("messaggio").innerHTML = "";

    if(!validateForm()){
        return;
    }

    var via = document.getElementById("via").value;
    var civico = document.getElementById("civico").value;
    var citta = document.getElementById("citta").value;
    var cap = document.getElementById("cap").value;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState == 4) {
            if (this.status == 200) {
                var risposta = JSON.parse(this.responseText);

                if (risposta.success) {
                    document.getElementById("messaggio").innerHTML = "Indirizzo salvato con successo!";
                    document.getElementById("messaggio").style.color = "green";
                } else {
                    document.getElementById("messaggio").innerHTML = risposta.errore;
                    document.getElementById("messaggio").style.color = "red";
                }
            } else {
                document.getElementById("messaggio").innerHTML = "Errore di comunicazione con il server.";
                document.getElementById("messaggio").style.color = "red";
            }
        }
    };

    var contextPath = document.getElementById("contextPath").value;
    xhr.open("POST", contextPath + "/setAddress", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("via=" + encodeURIComponent(via) + "&civico=" + encodeURIComponent(civico) + "&citta=" + encodeURIComponent(citta) + "&cap=" + encodeURIComponent(cap));
});