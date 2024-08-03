package org.example;

import java.sql.*;
import java.util.Arrays;


public class theaterDAO {

    public theaterDAO() {}

    public static String getTheaterName(int theaterID) throws SQLException {
        Connection con = DbConnection.getdbConnection();
        PreparedStatement ps = con.prepareStatement("select theaterName from theater where theaterID = ?");
        ps.setInt(1, theaterID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }
    public static Integer[] getSeatsBooked(int theaterid) throws SQLException {
        String query="select seatsBooked from theater where theaterId = ?";
        Connection con = DbConnection.getdbConnection();
        PreparedStatement ps=con.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        rs.next();
        Array seatsArray = rs.getArray("seatsbooked");
        Integer[] currentSeats = (Integer[]) seatsArray.getArray();
        return currentSeats;
    }
    public void displayTheaterInfo() throws SQLException {
        String query = "SELECT * FROM theater";
        Connection con = DbConnection.getdbConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println();
            System.out.println();

            System.out.println("//**************************************************//");

            int id = rs.getInt("theaterId");
            System.out.println("Theater Id: " + id);

            String name = rs.getString("theaterName");
            System.out.println("Theater Name: " + name);

            String address = rs.getString("theaterAddress");
            System.out.println("Theater Address: " + address);

            int number = rs.getInt("theaterNumber");
            System.out.println("Theater Number: " + number);

            int capacity = rs.getInt("theaterCapacity");
            System.out.println("Theater Capacity: " + capacity);

            int movieId = rs.getInt("movieid");
//            movieDAO movieDAO = new movieDAO();
            System.out.println("Screened Movie: " + movieDAO.getScreeningMovie(movieId));

            Array seatsBooked = rs.getArray("seatsBooked");
            Integer[] seatsbooked = (Integer[]) seatsBooked.getArray();
            int availableseats = capacity - seatsbooked.length;
            System.out.println("No of seats available: " + availableseats + "\n");


        }

        rs.close();
        st.close();
        con.close();
    }
    public static int getNumberOfTheaters() throws SQLException {
        Connection con = DbConnection.getdbConnection();
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM theater");
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    public static int getTheaterCapacity(int theaterId) throws SQLException {
        Connection con = DbConnection.getdbConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM theater where theaterid="+ theaterId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int theaterCapacity = rs.getInt("theaterCapacity");
        return theaterCapacity;
    }

    public static int getNumberOfRows(int theaterId) throws SQLException {
        Connection con = DbConnection.getdbConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM theater where theaterid="+ theaterId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int numberOfRows = rs.getInt("noofrows");
        return numberOfRows;
    }
    public static void displayTheaterById(int id) throws SQLException {
        String query = "SELECT * FROM theater WHERE theaterid = "+id;

        Connection con = DbConnection.getdbConnection();
        Statement pst = con.createStatement();
        ResultSet rs = pst.executeQuery(query);

        if (rs.next()) {
            // Retrieve and display the data for the specific theater
            System.out.println("Retrieved data for theater ID: " + id);
            int capacity = rs.getInt("theaterCapacity");
            Array seatsBooked = rs.getArray("seatsBooked");
            Integer[] seatsbooked = (Integer[]) seatsBooked.getArray();
            int noofrows = rs.getInt("noofrows");
            System.out.println("Theater Id: " + id + "\n");


            printTheater(noofrows, capacity, seatsbooked,id);
            System.out.println();
            System.out.println();
            Booking booked = new Booking();
            System.out.println(booked.getBookedCount(id));
        } else {
            System.out.println("Theater with Id " + id + " not found.");
        }


        // Close resources
        rs.close();
        pst.close();
        con.close();
    }

    public static void displayTheaterByIdForCancel(int id) throws SQLException {
        String query = "SELECT * FROM theater WHERE theaterid = "+id;

        Connection con = DbConnection.getdbConnection();
        Statement pst = con.createStatement();
        ResultSet rs = pst.executeQuery(query);

        if (rs.next()) {
            // Retrieve and display the data for the specific theater
//            System.out.println("Retrieved data for theater ID: " + id);
            int capacity = rs.getInt("theaterCapacity");
            Array seatsBooked = rs.getArray("seatsBooked");
            Integer[] seatsbooked = (Integer[]) seatsBooked.getArray();
            int noofrows = rs.getInt("noofrows");
            System.out.println("Theater Id: " + id + "\n");
            printTheaterForCancel(noofrows, capacity, seatsbooked);


            System.out.println();
            System.out.println();
            Booking booked = new Booking();
            System.out.println(booked.getBookedCount(id));
        } else {
            System.out.println("Theater with Id " + id + " not found.");
        }


        // Close resources
        rs.close();
        pst.close();
        con.close();
    }

    private static void printTheater(int noOfRows, int seats, Integer[] seatsBooked,int theaterId) throws SQLException{
        Arrays.sort(seatsBooked);

        for (int i = 0; i < seats / noOfRows; i++) {
            System.out.print("_______");
        }
        System.out.print("____");
        System.out.println();
        for (int i = 0; i < seats / noOfRows; i++) {

            if(i==(seats/noOfRows)/2){
                System.out.print(movieDAO.getScreeningMovie(theaterId));
            }else{
                System.out.print("      ");
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        int seatNo = 0;
        int k = 0;
        for (int i = 0; i < noOfRows; i++) {
            if (i == 2) System.out.println();
            if (i == noOfRows - 3) System.out.println();
            for (int j = 0; j < seats / noOfRows; j++) {
                if (j == seats / noOfRows - 3) System.out.print("   ");
                if (j == 3) System.out.print("   ");

                if (k < seatsBooked.length && seatNo == seatsBooked[k]) {
                    System.out.print(" [___] ");
                    seatNo++;
                    k++;
                } else {
                    System.out.printf(" [" + "%3d" + "] ", seatNo++);
                }
            }
            System.out.println("");
        }
    }
    private static void printTheaterForCancel(int noOfRows, int seats, Integer[] seatsBooked) {
        Arrays.sort(seatsBooked);

        for (int i = 0; i < seats / noOfRows; i++) {
            System.out.print("_______");
        }
        System.out.print("____");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        int seatNo = 0;
        int k = 0;
        for (int i = 0; i < noOfRows; i++) {
            if (i == 2) System.out.println();
            if (i == noOfRows - 3) System.out.println();
            for (int j = 0; j < seats / noOfRows; j++) {
                if (j == seats / noOfRows - 3) System.out.print("   ");
                if (j == 3) System.out.print("   ");

                if (k < seatsBooked.length && seatNo == seatsBooked[k]) {
                    System.out.printf(" [" + "%3d" + "] ", seatNo++);
                    k++;
                } else {
                    System.out.print(" [___] ");
                    seatNo++;
                }
            }
            System.out.println("");
        }
    }
}
