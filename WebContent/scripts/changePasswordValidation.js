/**
 * 
 */

document.getElementById("oldPassword").addEventListener("blur", function(){
	check("oldPassword")
});

document.getElementById("newPassword").addEventListener("blur", function(){
    check("newPassword")
});

function validateForm(){
    let oldPassword = document.getElementById("oldPassword");
    let newPassword = document.getElementById("newPassword");

    if(!oldPassword.checkValidity() || !newPass.checkValidity()){
        return false;
    }
    return true;
}