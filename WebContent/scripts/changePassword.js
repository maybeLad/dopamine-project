/**
 * 
 */
document.getElementById("changePasswordForm").addEventListener("submit", function(e) {
    e.preventDefault();

    document.getElementById("error_oldPassword").innerHTML = "";
    document.getElementById("error_newPassword").innerHTML = "";
    document.getElementById("error_confirm").innerHTML = "";
    document.getElementById("messaggio").innerHTML = "";

    var oldPassword = document.getElementById("oldPassword").value;
    var newPassword = document.getElementById("newPassword").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    if (newPassword !== confirmPassword) {
        document.getElementById("error_confirm").innerHTML = "Le password non coincidono";
        return;
    }

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState == 4) {
            if (this.status == 200) {
                var risposta = JSON.parse(this.responseText);

                if (risposta.success) {
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

    var contextPath = document.getElementById("contextPath").value;
    xhr.open("POST", contextPath + "/changePassword", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("oldPassword=" + encodeURIComponent(oldPassword) + "&newPassword=" + encodeURIComponent(newPassword));
});