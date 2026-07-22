<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pannello Amministrazione - Dopamine</title>
    <link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/header.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/footer.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/admin.css">
</head>
<body>

    <header>
        <jsp:include page="../navigation/header.jsp"></jsp:include>
    </header>

    <div class="admin-container">
        <h1><i class="fa-solid fa-database"></i> Pannello di Controllo Database</h1>

        <div class="tab-buttons">
            <button class="tab-btn active" onclick="openTab(event, 'tab-prodotti')"><i class="fa-solid fa-box"></i> Prodotti</button>
            <button class="tab-btn" onclick="openTab(event, 'tab-utenti')"><i class="fa-solid fa-users"></i> Utenti</button>
            <button class="tab-btn" onclick="openTab(event, 'tab-ordini')"><i class="fa-solid fa-receipt"></i> Ordini</button>
        </div>

        <div id="tab-prodotti" class="tab-content active">
            <div class="tab-header">
                <h2>Gestione Prodotti</h2>
                <button class="btn btn-add" onclick="openProdottoModal()"><i class="fa-solid fa-plus"></i> Nuovo Prodotto</button>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Immagine</th>
                        <th>Nome</th>
                        <th>Prezzo</th>
                        <th>Stock</th>
                        <th>Azioni</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Map<String, Object>> prodotti = (List<Map<String, Object>>) request.getAttribute("prodotti");
                        if(prodotti != null) {
                            for(Map<String, Object> p : prodotti) {
                    %>
                    <tr>
                        <td><%= p.get("id") %></td>
                        <td><img src="<%= request.getContextPath() %>/<%= p.get("url_immagine") %>" class="thumb"></td>
                        <td><strong><%= p.get("nome") %></strong></td>
                        <td>€ <%= String.format("%.2f", p.get("prezzo")) %></td>
                        <td><span class="badge <%= (int)p.get("stock") < 5 ? "danger" : "success" %>"><%= p.get("stock") %></span></td>
                        <td>
                            <form action="<%= request.getContextPath() %>/adminDashboard" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="deleteProdotto">
                                <input type="hidden" name="id" value="<%= p.get("id") %>">
                                <button type="submit" class="btn btn-delete" onclick="return confirm('Sicuro di voler eliminare questo prodotto?')"><i class="fa-solid fa-trash"></i></button>
                            </form>
                        </td>
                    </tr>
                    <% } } %>
                </tbody>
            </table>
        </div>

        <div id="tab-utenti" class="tab-content">
            <h2>Gestione Utenti</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome Completo</th>
                        <th>Email</th>
                        <th>Ruolo</th>
                        <th>Azioni</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Map<String, Object>> utenti = (List<Map<String, Object>>) request.getAttribute("utenti");
                        if(utenti != null) {
                            for(Map<String, Object> u : utenti) {
                                boolean isAdm = (boolean) u.get("admin");
                    %>
                    <tr>
                        <td><%= u.get("id") %></td>
                        <td><%= u.get("nome") %> <%= u.get("cognome") %></td>
                        <td><%= u.get("email") %></td>
                        <td><span class="badge <%= isAdm ? "admin" : "user" %>"><%= isAdm ? "ADMIN" : "UTENTE" %></span></td>
                        <td>
                            <form action="<%= request.getContextPath() %>/adminDashboard" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="toggleAdmin">
                                <input type="hidden" name="id" value="<%= u.get("id") %>">
                                <input type="hidden" name="currentAdmin" value="<%= isAdm %>">
                                <button type="submit" class="btn btn-edit"><i class="fa-solid fa-shield-halved"></i> <%= isAdm ? "Rimuovi Admin" : "Rendi Admin" %></button>
                            </form>
                            <form action="<%= request.getContextPath() %>/adminDashboard" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="deleteUtente">
                                <input type="hidden" name="id" value="<%= u.get("id") %>">
                                <button type="submit" class="btn btn-delete" onclick="return confirm('Eliminare l\'utente?')"><i class="fa-solid fa-trash"></i></button>
                            </form>
                        </td>
                    </tr>
                    <% } } %>
                </tbody>
            </table>
        </div>

        <div id="tab-ordini" class="tab-content">
            <h2>Gestione Ordini</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID Ordine</th>
                        <th>Cliente</th>
                        <th>Data</th>
                        <th>Totale</th>
                        <th>Stato</th>
                        <th>Azioni</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Map<String, Object>> ordini = (List<Map<String, Object>>) request.getAttribute("ordini");
                        if(ordini != null) {
                            for(Map<String, Object> o : ordini) {
                    %>
                    <tr>
                        <td>#<%= o.get("id") %></td>
                        <td><%= o.get("email_utente") != null ? o.get("email_utente") : "ID Utente: " + o.get("id_utente") %></td>
                        <td><%= o.get("data_ordine") %></td>
                        <td>€ <%= String.format("%.2f", o.get("totale")) %></td>
                        <td>
                            <form action="<%= request.getContextPath() %>/adminDashboard" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="updateStatoOrdine">
                                <input type="hidden" name="id" value="<%= o.get("id") %>">
                                <select name="stato" onchange="this.form.submit()" class="select-stato">
                                    <option value="in_attesa" <%= "in_attesa".equals(o.get("stato")) ? "selected" : "" %>>in_attesa</option>
                                    <option value="in_lavorazione" <%= "in_lavorazione".equals(o.get("stato")) ? "selected" : "" %>>in_lavorazione</option>
                                    <option value="spedito" <%= "spedito".equals(o.get("stato")) ? "selected" : "" %>>spedito</option>
                                    <option value="consegnato" <%= "consegnato".equals(o.get("stato")) ? "selected" : "" %>>consegnato</option>
                                    <option value="annullato" <%= "annullato".equals(o.get("stato")) ? "selected" : "" %>>annullato</option>
                                </select>
                            </form>
                        </td>
                        <td>
                            <form action="<%= request.getContextPath() %>/adminDashboard" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="deleteOrdine">
                                <input type="hidden" name="id" value="<%= o.get("id") %>">
                                <button type="submit" class="btn btn-delete" onclick="return confirm('Sicuro di voler cancellare questo ordine?')"><i class="fa-solid fa-trash"></i></button>
                            </form>
                        </td>
                    </tr>
                    <% } } %>
                </tbody>
            </table>
        </div>
    </div>

    <div id="prodottoModal" class="modal">
        <div class="modal-content">
            <span class="close-btn" onclick="closeProdottoModal()">&times;</span>
            <h3 id="modalTitle">Aggiungi Prodotto</h3>
            <form action="<%= request.getContextPath() %>/adminDashboard" method="post">
                <input type="hidden" name="action" value="saveProdotto">
                <input type="hidden" id="prodId" name="id" value="">
                
                <div class="form-group">
                    <label>Nome Prodotto:</label>
                    <input type="text" id="prodNome" name="nome" required>
                </div>
                <div class="form-group">
                    <label>Descrizione:</label>
                    <textarea id="prodDesc" name="descrizione" required></textarea>
                </div>
                <div class="form-group">
                    <label>Prezzo (€):</label>
                    <input type="number" step="0.01" id="prodPrezzo" name="prezzo" required>
                </div>
                <div class="form-group">
                    <label>Stock (Quantità):</label>
                    <input type="number" id="prodStock" name="stock" required>
                </div>
                <div class="form-group">
                    <label>URL Immagine (es. images/classic.png):</label>
                    <input type="text" id="prodUrl" name="url_immagine" required>
                </div>
                
                <button type="submit" class="btn btn-add" style="width: 100%; margin-top: 15px;">Salva Prodotto</button>
            </form>
        </div>
    </div>

    <script>
        function openTab(evt, tabName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tab-content");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].classList.remove("active");
            }
            tablinks = document.getElementsByClassName("tab-btn");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].classList.remove("active");
            }
            document.getElementById(tabName).classList.add("active");
            evt.currentTarget.classList.add("active");
        }

        function openProdottoModal() {
            document.getElementById("modalTitle").innerText = "Aggiungi Prodotto";
            document.getElementById("prodId").value = "";
            document.getElementById("prodNome").value = "";
            document.getElementById("prodDesc").value = "";
            document.getElementById("prodPrezzo").value = "";
            document.getElementById("prodStock").value = "";
            document.getElementById("prodUrl").value = "";
            document.getElementById("prodottoModal").style.display = "block";
        }

        function editProdotto(id, nome, desc, prezzo, stock, url) {
            document.getElementById("modalTitle").innerText = "Modifica Prodotto #" + id;
            document.getElementById("prodId").value = id;
            document.getElementById("prodNome").value = nome;
            document.getElementById("prodDesc").value = desc;
            document.getElementById("prodPrezzo").value = prezzo;
            document.getElementById("prodStock").value = stock;
            document.getElementById("prodUrl").value = url;
            document.getElementById("prodottoModal").style.display = "block";
        }

        function closeProdottoModal() {
            document.getElementById("prodottoModal").style.display = "none";
        }
    </script>

    <footer>
        <jsp:include page="../navigation/footer.jsp"></jsp:include>
    </footer>
</body>
</html>