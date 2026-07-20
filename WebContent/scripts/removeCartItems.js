function addToCart(idProdotto) {
    const quantityInput = document.getElementById('quantity');
    const quantity = quantityInput ? quantityInput.value : 1;

    fetch("/cart?action=add&id=" + idProdotto + "&qty=" + quantity, { 
        method: "POST" 
    })
    .then(res => {
        if (res.ok) {
            alert("Prodotto aggiunto al carrello con successo!");
        } else {
            res.text().then(text => alert("Errore: " + text));
        }
    })
    .catch(err => console.error("Errore nell'invio:", err));
}

function removeFromCart(idProdotto) {
    if (!confirm("Vuoi davvero rimuovere questo prodotto dal carrello?")) return;

    fetch("/cart?action=remove&id=" + idProdotto, { 
        method: "POST" 
    })
    .then(res => {
        if (res.ok) {
            const rigaProdotto = document.getElementById("prodotto-row-" + idProdotto);
            if (rigaProdotto) {
                rigaProdotto.remove();
            }
            
            return res.text();
        } else {
            throw new Error("Impossibile rimuovere il prodotto.");
        }
    })
    .then(nuovoTotale => {
        if (nuovoTotale) {
            document.getElementById("totale-carrello").innerText = nuovoTotale + " €";
            
            const tabella = document.getElementById("tabella-carrello");
            if (tabella && tabella.getElementsByTagName("tr").length <= 1) {
                document.getElementById("contenitore-carrello").innerHTML = "<p>Il tuo carrello è vuoto.</p>";
            }
        }
    })
    .catch(err => alert(err.message));
}