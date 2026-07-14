/**
 * 
 */

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
    const quantity = document.getElementById('quantity').value;

    fetch("/cart?action=add&id=" + idProdotto + "&qty=" + quantity, { method: "POST" })
        .then(res => {
            if (res.ok) {
                alert("Prodotto aggiunto al carrello!");
            }
        });
}