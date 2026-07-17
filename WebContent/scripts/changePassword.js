/**
 * 
 */
document.getElementById("oldPassword").addEventListener("blur", function(){
    check("oldPassword");
});

document.getElementById("newPassword").addEventListener("blur", function(){
    check("newPassword");
});

function validateForm(){
    let oldPassword = document.getElementById("oldPassword");
    let newPassword = document.getElementById("newPassword");
	
    if(!oldPassword.checkValidity() || !newPassword.checkValidity()){
        return false;
    }
    return true;
}

document.getElementById("changePasswordForm").addEventListener("submit", function(e) {
    e.preventDefault();

    document.getElementById("error_oldPassword").innerHTML = "";
    document.getElementById("error_newPassword").innerHTML = "";
    document.getElementById("error_confirm").innerHTML = "";
    document.getElementById("messaggio").innerHTML = "";

    if(!validateForm()){
        return;
    }
	
    let oldPassword = document.getElementById("oldPassword").value;
    let newPassword = document.getElementById("newPassword").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if(newPassword !== confirmPassword){
        document.getElementById("error_confirm").innerHTML = "Le password non coincidono";
        return;
    }
	
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(this.readyState == 4) {
            if(this.status == 200) {
                let risposta = JSON.parse(this.responseText);
				
                if(risposta.success) {
                    document.getElementById("messaggio").innerHTML = "Password aggiornata con successo!";
                    document.getElementById("messaggio").style.color = "green";
                    document.getElementById("changePasswordForm").reset();
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

    let contextPath = document.getElementById("contextPath").value;
    xhr.open("POST", contextPath + "/changePassword",true);

    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
    xhr.send("oldPassword=" + encodeURIComponent(oldPassword) + "&newPassword=" + encodeURIComponent(newPassword));
});