async function checkSignIn() {
    const response = await fetch(
            "CheckSignIn",
            );

//const popup = Notification();

    if (response.ok) {

        const json = await response.json();
        const response_dto = json.response_DTO;


        if (response_dto.success) {
            //signed in
            const user = response_dto.content;

            document.getElementById("sign-in-check").innerHTML = "Hi, " + user.fname;
            document.getElementById("sign-in-check").onclick = "viewDashboard();";
            document.getElementById("sign-up-check").innerHTML = "Sign Out";
            document.getElementById("sign-up-check").href = "SignOut";

            let sign_in_check = document.getElementById("sign-in-check");
            let sign_in_check_li = document.getElementById("sign-in-check-li");
            let sign_up_check = document.getElementById("sign-up-check");
            let sign_up_check_li = document.getElementById("sign-up-check-li");

            sign_in_check.remove();
            sign_up_check.remove();

            let new_a_tag1 = document.createElement("a");
            new_a_tag1.innerHTML = "Hi, " + user.fname;
            new_a_tag1.href = "#";
            sign_in_check_li.appendChild(new_a_tag1);

            let new_a_tag2 = document.createElement("a");
            new_a_tag2.innerHTML = "Sign Out";
            new_a_tag2.href = "SignOut";
            sign_up_check_li.appendChild(new_a_tag2);

        } else {
            //not signed in
            const msg = json.content;


        }

        //display last 3 products
        let product_index_main = document.getElementById("product-index-main");
        let product_index_card = document.getElementById("product-index-card");
        product_index_main.innerHTML = "";

        const productList = json.products;

        productList.forEach(product => {
            let product_index_card_clone = product_index_card.cloneNode(true);
            product_index_card_clone.querySelector("#card-title").innerHTML = product.title;
            product_index_card_clone.querySelector("#card-title").href = 'single-product.html?id=' + product.id;
            product_index_card_clone.querySelector("#card-a1").href = 'single-product.html?id=' + product.id;
            product_index_card_clone.querySelector("#card-img1").src = "product-images/" + product.id + "/image1.png";
            product_index_card_clone.querySelector("#card-img2").src = "product-images/" + product.id + "/image2.png";
            product_index_card_clone.querySelector("#card-price").innerHTML = product.price;

            product_index_card_clone.querySelector("#card-addToCart").addEventListener(
                    "click",
                    (e) => {
                addToCart(product.id, 1);
                e.preventDefault();
            }
            );


            product_index_main.appendChild(product_index_card_clone);
        });



    }

}

async function viewer(url, loader, returnUrl) {
    try {
        const response = await fetch(url);

        if (response.ok) {
            const htmlText = await response.text();

            
            if (htmlText && htmlText.trim() !== "") {
                const parser = new DOMParser();
                const cartHtml = parser.parseFromString(htmlText, "text/html");

                
                const content_main = cartHtml.querySelector(".page-wrapper");

                if (content_main) {
                    
                    document.querySelector(".main").innerHTML = content_main.innerHTML;
                    loader(); 
                } else {
                    
                    window.location = returnUrl;
                }
            } else {
               
                window.location = returnUrl;
            }
        } else {
            
            window.location = returnUrl;
        }
    } catch (error) {
        
        console.error("Error fetching dashboard:", error);
        window.location = returnUrl; 
    }
}

async function loadCategory(){
    const response = await fetch(
            "LoadCategory",
            );

//const popup = Notification();

    if (response.ok) {

        const json = await response.json();
        const categoryList = json.category;

        let category_main = document.getElementById("category-main");
        let category_list = document.getElementById("category-list");
        category_main.innerHTML = "";

        categoryList.forEach(category => {
            let category_list_clone = category_list.cloneNode(true);
            category_list_clone.querySelector("#category-name-li").innerHTML = category.name;


            category_list_clone.querySelector("#category-name-li").addEventListener(
                    "click",
                    (e) => {
                window.location = "search.html";

            }
            );


            category_main.appendChild(category_list_clone);


        });



    }
}

