async function signUp(){
    
    const user_dto = {
      fname:document.getElementById("first-name").value,  
      lname:document.getElementById("last-name").value,  
      email:document.getElementById("email").value,  
      password:document.getElementById("password").value 
    };

    const response = await fetch(
            "SignUp",
    {
        method:"POST",
        body:JSON.stringify(user_dto),
        headers:{
            "Content-Type":"application/json"
        }
    });
    
    const popup = Notification();
    
    if(response.ok){
        
        const json = await response.json();
        
        if(json.success){
            
            window.location = "verify-account.html";
        }else{
            popup.error({
                message: json.content
            });
        }
        
    }else{
        popup.error({
                message: "Try again later."
            });
    }
}