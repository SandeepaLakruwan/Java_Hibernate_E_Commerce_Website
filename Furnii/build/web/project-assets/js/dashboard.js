var modelList;

async function loadFeatures() {

    const response = await fetch(
            "LoadFeatures"
            );

    const popup = Notification();

    if (response.ok) {

        const json = await response.json();

        const categoryList = json.categoryList;
        const colorList = json.colorList;
        const conditionList = json.conditionList;
        const productList = json.productList;
        const user = json.user;
console.log(productList);
        loadSelect("categorySelect", categoryList, ["id", "name"]);
        loadSelect("colorSelect", colorList, ["id", "name"]);
        loadSelect("conditionSelect", conditionList, ["id", "name"]);
        
        document.getElementById("up-first-name").value = user.fname;
        document.getElementById("up-last-name").value = user.lname;
        document.getElementById("up-email").value = user.email;
        document.getElementById("up-password").value = user.password;

        //load user product list
        let cartItemContainer = document.getElementById("history-item-container");
            let cartItemRow = document.getElementById("history-item-row");

            cartItemContainer.innerHTML = "";

            productList.forEach(item => {
                

                let cartItemClone = cartItemRow.cloneNode(true);
                cartItemClone.querySelector("#history-item-a").href = "single-product.html?id=" + item.id;
                cartItemClone.querySelector("#history-item-a2").href = "single-product.html?id=" + item.id;
                cartItemClone.querySelector("#history-item-image").src = "product-images/" + item.id + "/image1.png";
                cartItemClone.querySelector("#history-item-title").innerHTML = item.title;
                cartItemClone.querySelector("#history-item-price").innerHTML = item.price;
                cartItemClone.querySelector("#history-item-qty").value = item.qty;

                cartItemContainer.appendChild(cartItemClone);


            });


    } else {
        window.location = "sign-in.html";
        popup.error({
            message: "Try again later."
        });
    }
}

function loadSelect(selectTagId, list, propertyArray) {
    const selectTag = document.getElementById(selectTagId);
    list.forEach(item => {
        let optionTag = document.createElement("option");
        optionTag.value = item[propertyArray[0]];
        optionTag.innerHTML = item[propertyArray[1]];
        selectTag.appendChild(optionTag);
    });
}

async function productListing() {
    const categoryTag = document.getElementById("categorySelect");
    const titleTag = document.getElementById("title");
    const descriptionTag = document.getElementById("description");
    const colorTag = document.getElementById("colorSelect");
    const conditionTag = document.getElementById("conditionSelect");
    const priceTag = document.getElementById("price");
    const qtyTag = document.getElementById("qty");
    const deliveryFeeTag = document.getElementById("deliveryFee");
    const image1Tag = document.getElementById("image1");
    const image2Tag = document.getElementById("image2");
    const image3Tag = document.getElementById("image3");

    const data = new FormData();
    data.append("categoryId", categoryTag.value);
    data.append("title", titleTag.value);
    data.append("description", descriptionTag.value);
    data.append("colorId", colorTag.value);
    data.append("conditionId", conditionTag.value);
    data.append("price", priceTag.value);
    data.append("quentity", qtyTag.value);
    data.append("deliveryFee", deliveryFeeTag.value);
    data.append("image1", image1Tag.files[0]);
    data.append("image2", image2Tag.files[0]);
    data.append("image3", image3Tag.files[0]);



    const response = await fetch(
            "ProductListing",
            {
                method: "POST",
                body: data
            });

        const popup = Notification();
        
    if (response.ok) {

        const json = await response.json();

        if (json.success) {
            categoryTag.value = 0;
            titleTag.value = "";
            descriptionTag.value = "";
            colorTag.value = 0;
            conditionTag.value = 0;
            priceTag.value = "";
            qtyTag.value = 1;
            deliveryFeeTag.value = "";
            image1Tag.value = null;
            image2Tag.value = null;
            image3Tag.value = null;


            popup.success({
                message: json.content
            });
        } else {

            popup.error({
                message: json.content
            });
        }

    } else {
        popup.error({
                message: "Try again later."
            });
    }
}

async function updateProfile(){
    
    const fname = document.getElementById("up-first-name");
    const lname = document.getElementById("up-last-name");
    const email = document.getElementById("up-email");
    const password = document.getElementById("up-password");
    const newPassword = document.getElementById("new-password");
    
    const data = new FormData();
    data.append("fname", fname.value);
    data.append("lname", lname.value);
    data.append("email", email.value);
    data.append("password", password.value);
    data.append("newPassword", newPassword.value);
    
    console.log(data);
    const response = await fetch(
            "UpdateProfile",
            {
                method: "POST",
                body: data
                
            });

        const popup = Notification();
        
    if (response.ok) {

        const json = await response.json();

        if (json.success) {
//            categoryTag.value = 0;
//            titleTag.value = "";
//            descriptionTag.value = "";
//            colorTag.value = 0;
            
            popup.success({
                message: json.message
            });
        } else {

            popup.error({
                message: json.message
            });
        }

    } else {
        popup.error({
                message: "Try again later."
            });
    }
    
}