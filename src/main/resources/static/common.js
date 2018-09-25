function validateForm(){
var pwd = document.getElementById("password").value;
var cnfmPwd = document.getElementById("confirm_password").value;
	if(pwd !== cnfmPwd){
		alert("Your password and confirm password does not match");
		document.registaration_form.password.focus();
		
		return false;
	}

}