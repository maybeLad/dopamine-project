/**
 * 
 */


document.getElementById("metodoPagamento").addEventListener("change", function() {

    var cardFields = document.getElementById("cardFields");
    var numeroCarta = document.getElementById("numeroCarta");
    var scadenzaCarta = document.getElementById("scadenzaCarta");

    if (this.value === "Carta") {

        cardFields.style.display = "block";
        numeroCarta.required = true;
        scadenzaCarta.required = true;

    } else {

        cardFields.style.display = "none";
        numeroCarta.required = false;
        scadenzaCarta.required = false;

        numeroCarta.value = "";
        scadenzaCarta.value = "";

        document.getElementById("error_numeroCarta").textContent = "";
        document.getElementById("error_scadenzaCarta").textContent = "";

        numeroCarta.classList.remove("error");
        scadenzaCarta.classList.remove("error");
    }
});


document.getElementById("numeroCarta").addEventListener("blur", function(){
    check("numeroCarta");
});


document.getElementById("scadenzaCarta").addEventListener("blur", function(){
    check("scadenzaCarta");
});



function validateForm(){

    let metodo = document.getElementById("metodoPagamento");
    let numeroCarta = document.getElementById("numeroCarta");
    let scadenzaCarta = document.getElementById("scadenzaCarta");


    if(!metodo.checkValidity()){
        return false;
    }


    if(metodo.value === "Carta"){

        if(!numeroCarta.checkValidity() || !scadenzaCarta.checkValidity()){
            return false;
        }


        // 16 cifre (con o senza spazi)
        if(!/^[0-9 ]{16,19}$/.test(numeroCarta.value)){

            document.getElementById("error_numeroCarta").textContent =
                "Inserisci un numero di carta valido";

            numeroCarta.classList.add("error");

            return false;
        }


        // MM/AA
        if(!/^(0[1-9]|1[0-2])\/([0-9]{2})$/.test(scadenzaCarta.value)){

            document.getElementById("error_scadenzaCarta").textContent =
                "Inserisci una scadenza valida (MM/AA)";

            scadenzaCarta.classList.add("error");

            return false;
        }
    }


    return true;
}



/*
 * Invio AJAX del form
 */

document.getElementById("changePaymentMethodForm")
.addEventListener("submit", function(e) {

    e.preventDefault();


    document.getElementById("messaggio").innerHTML = "";

    if(!validateForm()){
        return;
    }


    let metodoPagamento = document.getElementById("metodoPagamento").value;
    let numeroCarta = document.getElementById("numeroCarta").value;
    let scadenzaCarta = document.getElementById("scadenzaCarta").value;


    let xhr = new XMLHttpRequest();


    xhr.onreadystatechange = function() {

        if(this.readyState == 4) {

            if(this.status == 200) {

                let risposta = JSON.parse(this.responseText);


                if(risposta.success) {

                    document.getElementById("messaggio").innerHTML =
                        "Metodo di pagamento aggiornato con successo!";

                    document.getElementById("messaggio").style.color = "green";


                } else {

                    document.getElementById("messaggio").innerHTML =
                        risposta.errore;

                    document.getElementById("messaggio").style.color = "red";
                }


            } else {

                document.getElementById("messaggio").innerHTML =
                    "Errore di comunicazione con il server.";

                document.getElementById("messaggio").style.color = "red";
            }
        }
    };


    let contextPath = document.getElementById("contextPath").value;


    xhr.open(
        "POST",
        contextPath + "/changePaymentMethod",
        true
    );


    xhr.setRequestHeader(
        "Content-type",
        "application/x-www-form-urlencoded"
    );


    xhr.send(
        "metodoPagamento=" + encodeURIComponent(metodoPagamento)
        + "&numeroCarta=" + encodeURIComponent(numeroCarta)
        + "&scadenzaCarta=" + encodeURIComponent(scadenzaCarta)
    );

});