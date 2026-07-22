function removeFromCart(idProdotto) {
    if (!confirm("Vuoi davvero rimuovere questo prodotto dal carrello?")) return;

    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    const xhr = new XMLHttpRequest();

    xhr.open("POST", contextPath + "/cart?action=remove&id=" + idProdotto, true);

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            const rigaProdotto = document.getElementById("prodotto-row-" + idProdotto);
            if (rigaProdotto) {
                rigaProdotto.remove();
            }

            const nuovoTotale = xhr.responseText;
            if (nuovoTotale) {
                const elementoTotale = document.getElementById("totale-carrello");
                if (elementoTotale) {
                    elementoTotale.innerText = nuovoTotale + " €";
                }
                
                const tabella = document.getElementById("tabella-carrello");
                if (tabella && tabella.getElementsByTagName("tr").length <= 1) {
                    const contenitore = document.getElementById("contenitore-carrello");
                    if (contenitore) {
                        contenitore.innerHTML = "<p>Il tuo carrello è vuoto.</p>";
                    }
                }
            }
        } else {
            alert("Impossibile rimuovere il prodotto.");
        }
    };

    xhr.onerror = function() {
        alert("Errore di rete durante la rimozione del prodotto.");
    };

    xhr.send();
}