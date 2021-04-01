package co.simplon.backtothefuture;

import co.simplon.backtothefuture.model.Personnage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        // Attention, ce code est un exemple pour aller vite, mais il convient de
        // noter qu'il n'est pas sécurisé de laisser un mot de passe en clair dans un fichier Java.
        // Nous verrons ensemble comment gérer ça plus proprement dans la suite de la formation.
        String url = "jdbc:postgresql://localhost:5432/back_to_the_future";
        String user = "postgres";
        String password = "postgres";

        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            Personnage.getPersonnages(connection);

            System.out.println("Coucou, entre un nouveau personnage");
            System.out.println("Donne moi le prenom du nouveau perso stp : ");
            String prenom = scanner.nextLine();
            System.out.println("Donne moi le nom du nouveau perso stp : ");
            String nom = scanner.nextLine();

            Personnage nouveauPerso = new Personnage(prenom, nom);
            nouveauPerso.savePersonnage(connection);

            Personnage.getPersonnages(connection);

            connection.close();

        } catch (SQLException exception) {
            // Ma gestion du problème
            System.out.println("Erreur de connexion à la base de données");
            // exception.printStackTrace();
        }

    }
}
