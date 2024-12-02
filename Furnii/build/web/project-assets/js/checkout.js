// Payment completed. It can be a successful failure.
payhere.onCompleted = function onCompleted(orderId) {
    console.log("Payment completed. OrderID:" + orderId);

    window.location = "index.html";
    // Note: validate the payment and show success or failure page to the customer
};

// Payment window closed
payhere.onDismissed = function onDismissed() {
    // Note: Prompt user to pay again or show an error page
    console.log("Payment dismissed");
};

// Error occurred
payhere.onError = function onError(error) {
    // Note: show an error page
    console.log("Error:" + error);
};

var address;

async function loadData() {
    const response = await fetch(
            "LoadCheckout",
            );

    if (response.ok) {

        const json = await response.json();

        const popup = Notification();

        if (json.success) {

            //store response data

            const cityList = json.cityList;
            const cartList = json.cartList;

            //load cities
            let citySelect = document.getElementById("city");
            citySelect.length = 1;

            cityList.forEach(city => {
                let cityOption = document.createElement("option");
                cityOption.value = city.id;
                cityOption.innerHTML = city.name;
                citySelect.appendChild(cityOption);

            });

            //load current address
            let city = document.getElementById("city");
            let currentAddressCheckBox = document.getElementById("checkbox1");
            currentAddressCheckBox.addEventListener("change", e => {

                let first_name = document.getElementById("first-name");
                let last_name = document.getElementById("last-name");

                let address1 = document.getElementById("address1");
                let address2 = document.getElementById("address2");
                let postal_code = document.getElementById("postal-code");
                let mobile = document.getElementById("mobile");

                if (currentAddressCheckBox.checked) {

                    if (json.address && json.address !== null) {
                        let address = json.address;

                        // Safely access and assign properties
                        if (address.first_name)
                            first_name.value = address.first_name;
                        if (address.last_name)
                            last_name.value = address.last_name;

                        if (address.city && address.city.id) {
                            city.value = address.city.id;
                            city.disabled = true;
                            city.dispatchEvent(new Event("change"));
                        } else {
                            city.disabled = false;  // Enable city if no valid city.id is present
                        }

                        if (address.line1)
                            address1.value = address.line1;
                        if (address.line2)
                            address2.value = address.line2;
                        if (address.postal_code)
                            postal_code.value = address.postal_code;
                        if (address.mobile)
                            mobile.value = address.mobile;

                    } else {
                        currentAddressCheckBox.checked = false;
                        popup.error({
                            message: json.message || "No address found!"
                        });
                    }
                } else {
                    first_name.value = "";
                    last_name.value = "";

                    city.value = "0";
                    city.disabled = false;
                    city.dispatchEvent(new Event("change"));

                    address1.value = "";
                    address2.value = "";
                    postal_code.value = "";
                    mobile.value = "";
                }
            });

            //load cart items
            let st_tbody = document.getElementById("st-tbody");
            let st_item = document.getElementById("st-item-tr");
            st_tbody.innerHTML = "";

            let sub_total = 0;
            let delivery_fee = 0;
            cartList.forEach(item => {
                let st_item_clone = st_item.cloneNode(true);
                st_item_clone.querySelector("#st-item-title").innerHTML = item.product.title;
                st_item_clone.querySelector("#st-item-qty").innerHTML = item.qty;

                let item_sub_total = item.product.price * item.qty;
                sub_total += item_sub_total;

                st_item_clone.querySelector("#st-item-subtotal").innerHTML = "Rs. " + item_sub_total + ".00";

                delivery_fee += item.product.delivery_fee;

                st_tbody.appendChild(st_item_clone);
            });

            document.getElementById("st-order-subtotal").innerHTML = "Rs. " + sub_total + ".00";

            //update total on city change
            citySelect.addEventListener("change", e => {
                //update shipping charges

                //get cart item count
                let item_count = cartList.length;

                let shipping_amount = 0;

                //check Colombo or not
                if (citySelect.value == 9) {
                    //colombo
                    shipping_amount = delivery_fee;

                } else {
                    //out of colombo
                    shipping_amount = delivery_fee + (2000 * item_count);
                }

                document.getElementById("st-order-shipping").innerHTML = "Rs. " + shipping_amount + ".00";

                //update total
                let total = sub_total + shipping_amount;
                document.getElementById("st-order-total").innerHTML = "Rs. " + total + ".00";

            });

            city.dispatchEvent(new Event("change"));

        } else {
            window.location = "sign-in.html";

        }

    }
}

async function checkout() {
    //check address status
    let isCurrentAddress = document.getElementById("checkbox1").checked;

    //get address data
    let first_name = document.getElementById("first-name");
    let last_name = document.getElementById("last-name");
    let city = document.getElementById("city");
    let address1 = document.getElementById("address1");
    let address2 = document.getElementById("address2");
    let postal_code = document.getElementById("postal-code");
    let mobile = document.getElementById("mobile");

    const data = {
        isCurrentAddress: isCurrentAddress,
        first_name: first_name.value,
        last_name: last_name.value,
        city_id: city.value,
        address1: address1.value,
        address2: address2.value,
        postal_code: postal_code.value,
        mobile: mobile.value
    };

    const response = await fetch(
            "Checkout",
            {
                method: "POST",
                body: JSON.stringify(data),
                headers: {
                    "Content-Type": "application/json"
                }
            }
    );

    const popup = Notification();

    if (response.ok) {
        const json = await response.json();

        if (json.success) {
            payhere.startPayment(json.payhereJson);
//            popup.success({
//                message: "Payment Success"
//            });
//            setTimeout(() => {
//                window.location = "index.html";
//            }, 2000);
        } else {
            popup.error({
                message: json.message
            });
        }

    } else {
        popup.error({
            message: "Try Again Later"
        });
    }
}