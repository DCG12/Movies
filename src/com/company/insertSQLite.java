package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by 46406163y on 12/12/16.
 */
public class insertSQLite {

    public static void listaMovies(String ORIGINAL_TITLE, int movie_id, String release_date) {

        Connection c = null;
        Statement stmt = null;
        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:peliculas.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "INSERT INTO MOVIES (titulo, id, fecha) " +
                    "VALUES" + "(?, ?, ?);";

            PreparedStatement preparedstament = c.prepareStatement(sql);
            preparedstament.setString(1, ORIGINAL_TITLE);
            preparedstament.setInt(2, movie_id);
            preparedstament.setString(3, release_date);

            preparedstament.executeUpdate();

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

}


