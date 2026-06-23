document.getElementById("email").addEventListener("blur", function(){
    let email = document.getElementById("email");
    let errorEmail = document.getElementById("errorEmail");

    if(!email.checkValidity()){
        errorEmail.textContent = "Inserire una email valida.";
        email.classList.add("error");
    } else {
        errorEmail.textContent = "";
        email.classList.remove("error");
    }
});

document.getElementById("password").addEventListener("blur", function(){
    let password = document.getElementById("password");
    let errorPassword = document.getElementById("errorPassword");

    if(!password.checkValidity()){
        errorPassword.textContent = "La password deve essere tra 8 e 20 caratteri.";
        password.classList.add("error");
    } else {
        errorPassword.textContent = "";
        password.classList.remove("error");
    }
});

function validateForm(){
    let email = document.getElementById("email");
    let password = document.getElementById("password");

    if(!email.checkValidity() || !password.checkValidity()){
        return false;
    }
    return true;
}