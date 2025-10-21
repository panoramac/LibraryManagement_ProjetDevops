window.onload = fetchUsers;

function fetchUsers() {
    fetch('http://backend:9090/livres')
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur réseau');
            }
            return response.json();
        })
        .then(livres => {
            console.log('Livres reçus:', livres);
            const tableBody = document.getElementById('userTableBody');
            tableBody.innerHTML = '';

            if (livres.length === 0) {
                tableBody.innerHTML = `
                    <tr>
                        <td colspan="6" class="text-center">Aucun livre trouvé</td>
                    </tr>
                `;
                return;
            }

            livres.forEach(livre => {
                const row = `
                    <tr>
                        <td>${livre.isbn}</td>
                        <td>${livre.nom}</td>
                        <td>${livre.auteur}</td>
                        <td>${livre.prix} €</td>
                        <td>${livre.disponible ?
                    '<span class="badge badge-success">Oui</span>' :
                    '<span class="badge badge-danger">Non</span>'}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="editUser(${livre.isbn}, '${livre.nom.replace(/'/g, "\\'")}', '${livre.auteur.replace(/'/g, "\\'")}', ${livre.prix})">Modifier</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteUser(${livre.isbn})">Supprimer</button>
                            <button class="btn btn-success btn-sm" onclick="emprunter(${livre.isbn})">Emprunter</button>
                        </td>
                    </tr>
                `;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error('Erreur lors du chargement des livres:', error);
            document.getElementById('userTableBody').innerHTML = `
                <tr>
                    <td colspan="6" class="text-center text-danger">Erreur de chargement des données</td>
                </tr>
            `;
        });
}

function deleteUser(id) {
    if (confirm('Confirmer la suppression de ce livre ?')) {
        fetch(`http://backend:9090/livre/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erreur lors de la suppression');
                }
                return response.json();
            })
            .then(deletedLivre => {
                alert('Livre supprimé avec succès');
                fetchUsers();
            })
            .catch(error => {
                console.error('Erreur suppression:', error);
                alert('Erreur lors de la suppression: ' + error.message);
            });
    }
}

function editUser(id, nom, auteur, prix) {
    document.getElementById('editUserId').value = id;
    document.getElementById('editUserName').value = nom;
    document.getElementById('editUserAuteur').value = auteur;
    document.getElementById('editUserPrix').value = prix;

    $('#editUserModal').modal('show');
}

function updateUser() {
    const id = document.getElementById('editUserId').value;
    const livreData = {
        nom: document.getElementById('editUserName').value,
        auteur: document.getElementById('editUserAuteur').value,
        prix: parseFloat(document.getElementById('editUserPrix').value)
    };

    fetch(`http://backend:9090/livre/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(livreData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur lors de la modification');
            }
            return response.json();
        })
        .then(updatedLivre => {
            alert('Livre modifié avec succès');
            $('#editUserModal').modal('hide');
            fetchUsers();
        })
        .catch(error => {
            console.error('Erreur modification:', error);
            alert('Erreur lors de la modification: ' + error.message);
        });
}

function emprunter(isbn) {
    alert(`Fonction d'emprunt pour le livre ISBN: ${isbn} - À implémenter`);
    // Ici vous pourrez ajouter la logique d'emprunt plus tard
}