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
        console.error("Errore AJAX:", err);
        alert("Errore di rete nell'aggiunta al carrello.");
    });
}