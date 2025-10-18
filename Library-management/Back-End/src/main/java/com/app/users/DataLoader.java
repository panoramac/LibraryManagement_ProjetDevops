package com.app.users;

import com.app.users.bo.Client;
import com.app.users.bo.Livre;
import com.app.users.dao.LivreDAO;
import com.app.users.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LivreDAO livreDAO;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== CHARGEMENT DES DONNÉES DE TEST ===");

        // Vérifier si des clients existent déjà
        if (userDAO.count() == 0) {
            System.out.println("Ajout des clients...");

            userDAO.save(Client.builder()
                    .cin("AB123456")
                    .nom("Dupont")
                    .prenom("Jean")
                    .build());

            userDAO.save(Client.builder()
                    .cin("CD789012")
                    .nom("Martin")
                    .prenom("Marie")
                    .build());

            userDAO.save(Client.builder()
                    .cin("EF345678")
                    .nom("Bernard")
                    .prenom("Pierre")
                    .build());

            userDAO.save(Client.builder()
                    .cin("GH901234")
                    .nom("Dubois")
                    .prenom("Sophie")
                    .build());

            System.out.println("✅ 4 clients ajoutés !");
        } else {
            System.out.println("ℹ️  Clients déjà existants dans la base");
        }

        // Vérifier si des livres existent déjà
        if (livreDAO.count() == 0) {
            System.out.println("Ajout des livres...");

            livreDAO.save(Livre.builder()
                    .isbn(978207036)
                    .nom("Le Petit Prince")
                    .auteur("Antoine de Saint-Exupéry")
                    .prix(12.50f)
                    .disponible(true)
                    .build());

            livreDAO.save(Livre.builder()
                    .isbn(978208071)
                    .nom("L'Étranger")
                    .auteur("Albert Camus")
                    .prix(15.00f)
                    .disponible(true)
                    .build());

            livreDAO.save(Livre.builder()
                    .isbn(978225301)
                    .nom("1984")
                    .auteur("George Orwell")
                    .prix(18.75f)
                    .disponible(true)
                    .build());

            livreDAO.save(Livre.builder()
                    .isbn(978226600)
                    .nom("Harry Potter à l'école des sorciers")
                    .auteur("J.K. Rowling")
                    .prix(22.90f)
                    .disponible(true)
                    .build());

            System.out.println("✅ 4 livres ajoutés !");
        } else {
            System.out.println("ℹ️  Livres déjà existants dans la base");
        }

        System.out.println("=== CHARGEMENT TERMINÉ ===");
    }
}