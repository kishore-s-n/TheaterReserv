package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class movieDAO {
    public static String getScreeningMovie(int movieId) throws SQLException {
        String query = "SELECT * FROM movies WHERE movieid = "+movieId;
        Connection con = DbConnection.getdbConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        return rs.getString("moviename");
    }

    public String getMovieRating(int movieId) throws SQLException {
        String query = "SELECT * FROM movies WHERE movieid = "+movieId;
        Connection con = DbConnection.getdbConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        return rs.getString("rating");
    }
    public static void displaytMovieDetails() throws SQLException {
        String query = "SELECT * FROM movies";
        Connection con = DbConnection.getdbConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            System.out.println();
            System.out.println("//-------------------------//");
            System.out.println(rs.getString("moviename"));
            System.out.println(rs.getString("moviedescription"));
            System.out.println(rs.getString("movierating"));
            System.out.println("//-------------------------//");
            System.out.println();

        }
    }
    public movieDAO() {}

}
