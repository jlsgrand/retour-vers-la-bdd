package co.simplon.backtothefuture.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Personnage {
    private int id;
    private String prenom;
    private String nom;

    public Personnage(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public Personnage(int newId, String newPrenom, String newNom) {
        id = newId;
        prenom = newPrenom;
        nom = newNom;
    }

    @Override
    public String toString() {
        return id + ". " + prenom + " " + nom;
    }

    public void savePersonnage(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO personnage (prenom, nom) VALUES ('" + prenom + "','" + nom + "')");
        ordreSQL.close();
    }

    public static List<Personnage> getPersonnages(Connection connection) throws SQLException {
        // Pouvoir lister des personnages
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from personnage");

        List<Personnage> personnageList = new ArrayList<>();

        while (resultats.next()) {
            Personnage dbPersonnage = new Personnage(resultats.getInt("id"),
                    resultats.getString("prenom"),
                    resultats.getString("nom"));
            personnageList.add(dbPersonnage);

            System.out.println(dbPersonnage);
        }

        resultats.close();
        ordreSQL.close();

        return personnageList;
    }
}
