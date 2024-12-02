async function loadProduct() {
    const parameters = new URLSearchParams(window.location.search);

    if (parameters.has("id")) {
        const productId = parameters.get("id");

        const response = await fetch("LoadSingleProduct?id=" + productId);

        if (response.ok) {
            const json = await response.json();
            console.log(json.product.id);

            const id = json.product.id;
            document.getElementById("product-zoom").src = "product-images/" + id + "/image1.png";
            document.getElementById("product-zoom").setAttribute('data-zoom-image', "product-images/" + id + "/image1.png");

            let imageHTML = document.getElementById("image-a");
            document.getElementById("product-zoom-gallery").innerHTML = "";

            for (var i = 1; i < 4; i++) {
                let imageCloneHTML = imageHTML.cloneNode(true);
                if (imageCloneHTML) {
                    imageCloneHTML.querySelector('#fu-image').src = "product-images/" + id + "/image" + i + ".png";
                    imageCloneHTML.setAttribute('data-image', "product-images/" + id + "/image" + i + ".png");
                    imageCloneHTML.setAttribute('data-zoom-image', "product-images/" + id + "/image" + i + ".png");

                    document.getElementById("product-zoom-gallery").appendChild(imageCloneHTML);
                }

            }

            document.getElementById("fu-product-title").innerHTML = json.product.title;
            document.getElementById("fu-product-date").innerHTML = "Published By - " + json.product.date_time;
            document.getElementById("fu-product-price").innerHTML = new Intl.NumberFormat(
                    "en-US",
                    {
                        minimumFractionDigits: 2
                    }
            ).format(json.product.price);

            document.getElementById("fu-product-category").innerHTML = json.product.category.name;
            document.getElementById("fu-product-condition").innerHTML = json.product.condition.name;
            document.getElementById("fu-product-qty").innerHTML = json.product.qty;
            document.getElementById("fu-product-color").style.backgroundColor = json.product.color.name;
            document.getElementById("fu-product-description").innerHTML = json.product.description;

            document.getElementById("add-to-cart-main").addEventListener(
                    "click",
                    (e) => {
                addToCart(
                        json.product.id,
                        document.getElementById("qty").value
                        );
                e.preventDefault();
            }
            );

            let productHTML = document.getElementById("similer-product");
            document.getElementById("similer-product-main").innerHTML = "";
            json.productList.forEach(item => {
                
                let productCloneHTML = productHTML.cloneNode(true);
                
                if(item.qty==0){
                    productCloneHTML.querySelector("#similer-product-available").innerHTML = "Out Of Stock";
                    
                }else{
                    productCloneHTML.querySelector("#similer-product-available").innerHTML = "In Stock";
                    
                }

                productCloneHTML.querySelector("#similer-product-image").src = "product-images/" + item.id + "/image1.png";
                productCloneHTML.querySelector("#similer-product-a1").href = "single-product.html?id=" + item.id;
                productCloneHTML.querySelector("#similer-product-category").href = "single-product.html?id=" + item.id;
                productCloneHTML.querySelector("#similer-product-title").href = "single-product.html?id=" + item.id;
                
                productCloneHTML.querySelector("#similer-product-title").innerHTML = item.title;
                productCloneHTML.querySelector("#similer-product-category").innerHTML = item.category.name;
                productCloneHTML.querySelector("#similer-product-price").innerHTML = item.price;

                productCloneHTML.querySelector("#similer-product-add-to-cart").addEventListener(
                        "click",
                        (e) => {
                    addToCart(item.id, 1);
                    e.preventDefault();
                }
                );
                

                document.getElementById("similer-product-main").appendChild(productCloneHTML);

            });


        } else {
            window.location = "sign-in.html";
        }
    } else {
        window.location = "sign-up.html";
    }

}

async function addToCart(id, qty) {
    const response = await fetch(
            "AddToCart?id=" + id + "&qty=" + qty
            );

    const popup = Notification();

    if (response.ok) {
        const json = await response.json();
        if (json.success) {

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
            message: "Please try again"
        });
    }
}