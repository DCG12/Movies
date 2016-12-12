package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by 46406163y on 12/12/16.
 */
public class createSQLite {

    public static void main(String[] args) {
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:test.db");
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                String sql = "CREATE TABLE MOVIES " +
                        "(ID            INT     PRIMARY KEY     NOT NULL," +
                        " title         TEXT                    NOT NULL, " +
                        " release_date  DATE                    NOT NULL)";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Table created successfully");
        }

    }

}
