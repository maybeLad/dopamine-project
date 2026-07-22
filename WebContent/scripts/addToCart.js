function addToCart(idProdotto) {
    const quantityInput = document.getElementById('quantity');
    const quantity = quantityInput ? quantityInput.value : 1;

    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    const url = contextPath + "/cart?action=add&id=" + idProdotto + "&qty=" + quantity;

	    const xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            alert("Prodotto aggiunto al carrello con successo!");
        } else {
            alert("Errore: " + xhr.responseText);
        }
    };

    xhr.onerror = function() {
        console.error("Errore AJAX:", xhr.statusText);
        alert("Errore di rete nell'aggiunta al carrello.");
    };
    xhr.send();
}