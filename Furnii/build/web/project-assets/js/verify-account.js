async function verify() {

    const dto = {
        verification: document.getElementById("verification").value
    };

    const response = await fetch(
            "Verification",
            {
                method: "POST",
                body: JSON.stringify(dto),
                headers: {
                    "Content-Type": "application/json"
                }
            });

    if (response.ok) {

        const popup = Notification();
        const json = await response.json();

        if (json.success) {
            window.location = "index.html";
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