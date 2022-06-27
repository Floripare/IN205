<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Liste des emprunts</h1>
      </div>
      <div class="row">
        <div class="container">
	        <div class="col s12">
	          <table class="striped">
                <thead>
                    <tr>
                        <th>Livre</th>
                        <th>Membre emprunteur</th>
                        <th>Date d'emprunt</th>
                        <th>Retour</th>
                    </tr>
                </thead>
                <tbody id="results">
                
                    <%@ page import="com.ensta.librarymanager.service.MembreServiceImpl,com.ensta.librarymanager.service.LivreServiceImpl,com.ensta.librarymanager.service.EmpruntServiceImpl,com.ensta.librarymanager.modele.Livre,com.ensta.librarymanager.modele.Emprunt,com.ensta.librarymanager.modele.Membre" %>
                	<% if (request.getParameter("show") != null && request.getParameter("show").contains("all")) { %>
                	<% for (Emprunt emprunt : EmpruntServiceImpl.getInstance().getList()) {%>
                	<% Livre livre = LivreServiceImpl.getInstance().getById(emprunt.getIdLivre());%>
                	<% Membre membre = MembreServiceImpl.getInstance().getById(emprunt.getIdMembre()); %>
                    <tr>
                        <td><% out.print(livre.getTitre()); %>, <em><% out.print(livre.getAuteur()); %></em></td>
                        <td><% out.print(membre.getPrenom() + " " + membre.getNom()); %></td>
                        <td><% out.print(emprunt.getDateEmprunt()); %></td>
                        <% if (emprunt.getDateRetour() != null) {%>
                        	<td>
                            <% out.print(emprunt.getDateRetour()); %>
							</td>
						<% } else { %>
                        <td>
                            <a href="emprunt_return?id=<%out.print(emprunt.getId());%>"><ion-icon class="table-item" name="log-in"></a>
                        </td>
                        <% } %>
                    </tr>
                    <% }} else { %>
	                    <% for (Emprunt emprunt : EmpruntServiceImpl.getInstance().getListCurrent()) {%>
	                	<% Livre livre = LivreServiceImpl.getInstance().getById(emprunt.getIdLivre());%>
	                	<% Membre membre = MembreServiceImpl.getInstance().getById(emprunt.getIdMembre()); %>
	                    <tr>
	                        <td><% out.print(livre.getTitre()); %>, <em><% out.print(livre.getAuteur()); %></em></td>
	                        <td><% out.print(membre.getPrenom() + " " + membre.getNom()); %></td>
	                        <td><% out.print(emprunt.getDateEmprunt()); %></td>
	                        <td>
	                            <a href="emprunt_return?id=<%out.print(emprunt.getId());%>"><ion-icon class="table-item" name="log-in"></a>
	                        </td>
	                    </tr>
					<% }}%>
                </tbody>
            </table>
          </div>
        </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
