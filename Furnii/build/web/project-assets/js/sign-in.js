async function signIn(){
    
    const user_dto = { 
      email:document.getElementById("email").value,  
      password:document.getElementById("password").value 
    };

    const response = await fetch(
            "SignIn",
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
            window.location = "index.html";
        }else{
            
            if(json.content==="Unverified"){
                window.location = "verify-account.html";
            }else{
                popup.error({
                message: json.content
            });
            }
            
        }
        
    }else{
        popup.error({
                message: "Try again later."
            });
    }
}