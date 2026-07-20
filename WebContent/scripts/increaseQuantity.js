function increaseQty() {
    const input = document.getElementById('quantity');
    const max = parseInt(input.max);
    let value = parseInt(input.value);
    if (value < max) {
        input.value = value + 1;
    }
}

function decreaseQty() {
    const input = document.getElementById('quantity');
    let value = parseInt(input.value);
    if (value > 1) {
        input.value = value - 1;
    }
}

function addToCart(idProdotto) {
    const quantityInput = document.getElementById('quantity');
    const quantity = quantityInput ? quantityInput.value : 1;

    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

    fetch(contextPath + "/cart?action=add&id=" + idProdotto + "&qty=" + quantity, { 
        method: "POST" 
    })
    .then(res => {
        if (res.ok) {
            alert("Prodotto aggiunto al carrello con successo!");
        } else {
            res.text().then(text => alert("Errore: " + text));
        }
    })
    .catch(err => {
        console.error("Errore nell'invio della richiesta:", err);
        alert("Si è verificato un errore di rete. Riprova più tardi.");
    });
}