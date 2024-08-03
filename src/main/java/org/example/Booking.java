package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Booking {
    public Booking(){}

    public int getBookedCount(int theaterid) throws SQLException {
        String query="select theatercapacity,seatsbooked from theater where theaterid="+theaterid;
        Connection con= DbConnection.getdbConnection();
        PreparedStatement ps=con.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        rs.next();
        int capacity=rs.getInt("theatercapacity");
        Array seats=rs.getArray("seatsbooked");
        Integer[] seatsbooked = (Integer[]) seats.getArray();
        return capacity-seatsbooked.length;
    }

    public static void bookseats(int theaterId) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to continue to book seats at theater "+theaterId);
        System.out.print("Enter the number of tickets to be booked: ");
        int noofseats=sc.nextInt();
        System.out.println("Enter seat numbers to confirm your seats: ");
        Integer[] seats=new Integer[noofseats];
        for(int i=0;i<noofseats;i++){
            System.out.print("Enter the seat number: "+(i+1)+":");
            int seatno=sc.nextInt();
            seats[i]=seatno;
        }
        System.out.println("\nThe price of the tickets purchased are: $"+Pricing.pricing(theaterId,seats)+"\n"
        +"Hope you enjoy the Moviee...");
        String selectQuery = "SELECT seatsbooked FROM theater WHERE theaterid = ?";
        String updateQuery = "UPDATE theater SET seatsbooked = ? WHERE theaterid = ?";

        try (Connection con = DbConnection.getdbConnection();
             PreparedStatement selectPst = con.prepareStatement(selectQuery);
             PreparedStatement updatePst = con.prepareStatement(updateQuery)) {

                // Retrieve the current seatsbooked array
             selectPst.setInt(1, theaterId);
             ResultSet rs = selectPst.executeQuery();

             if (rs.next()) {
                 Array seatsArray = rs.getArray("seatsbooked");
                 Integer[] currentSeats = (Integer[]) seatsArray.getArray();

                    // Combine the current seats with the new seats
                 List<Integer> combinedSeatsList = new ArrayList<>(Arrays.asList(currentSeats));
                 combinedSeatsList.addAll(Arrays.asList(seats));
                 Integer[] combinedSeats = combinedSeatsList.toArray(new Integer[0]);

                    // Update the seatsbooked column with the combined array
                 Array combinedSeatsArray = con.createArrayOf("INTEGER", combinedSeats);
                 updatePst.setArray(1, combinedSeatsArray);
                 updatePst.setInt(2, theaterId);
                 updatePst.executeUpdate();
            }
            theaterDAO theaterDAO = new theaterDAO();
             System.out.println("Booking successful enjoy your show at "+theaterDAO.getTheaterName(theaterId)+" Screening "+movieDAO.getScreeningMovie(theaterId)+"\n");
        }
    }
    public static void cancelseats(int theaterId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        theaterDAO.displayTheaterByIdForCancel(theaterId);

        // Get the number of seats to be removed
        System.out.print("Enter the number of seats to be removed: ");
        int numSeats = scanner.nextInt();
        Integer[] seatsToRemove = new Integer[numSeats];

        // Get the seat numbers to be removed
        for (int i = 0; i < numSeats; i++) {
            System.out.print("Enter seat number " + (i + 1) + ": ");
            seatsToRemove[i] = scanner.nextInt();
        }
        System.out.println();
        System.out.println("Your refund of $"+Pricing.pricing(theaterId,seatsToRemove)+" will be sent to you within 1-2 working days\n"
        +"Thanks for visiting Kishore Cinemas\n"
        +"Hope to see you soon");

        String selectQuery = "SELECT seatsbooked FROM theater WHERE theaterid = ?";
        String updateQuery = "UPDATE theater SET seatsbooked = ? WHERE theaterid = ?";

        try (Connection con = DbConnection.getdbConnection();
             PreparedStatement selectPst = con.prepareStatement(selectQuery);
             PreparedStatement updatePst = con.prepareStatement(updateQuery)) {

            // Retrieve the current seatsbooked array
            selectPst.setInt(1, theaterId);
            ResultSet rs = selectPst.executeQuery();

            if (rs.next()) {
                Array seatsArray = rs.getArray("seatsbooked");
                Integer[] currentSeats = (Integer[]) seatsArray.getArray();

                // Remove the specified seats from the current seats array
                currentSeats = Arrays.stream(currentSeats)
                        .filter(seat -> !Arrays.asList(seatsToRemove).contains(seat))
                        .toArray(Integer[]::new);

                // Update the seatsbooked column with the updated array
                Array updatedSeatsArray = con.createArrayOf("INTEGER", currentSeats);
                updatePst.setArray(1, updatedSeatsArray);
                updatePst.setInt(2, theaterId);
                updatePst.executeUpdate();
            }
        }
        theaterDAO.displayTheaterById(theaterId);
    }
}
