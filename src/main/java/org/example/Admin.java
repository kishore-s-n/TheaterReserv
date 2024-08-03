package org.example;

import java.sql.*;

public class Admin {
    public static void getProfit(int theaterid) throws SQLException {
        String query="select seatsbooked from theater where theaterid = ?";
        Connection con=DbConnection.getdbConnection();
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1,theaterid);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){

            Array arr=rs.getArray("seatsbooked");


            Integer[] bookedseats = (Integer[]) arr.getArray();
            int totalProfit = 0;
            for(int i:bookedseats){
                totalProfit+=Pricing.pricing(theaterid,bookedseats);
            }
            System.out.println("\nThe total profit for the theater "+theaterid+" is: "+totalProfit);
        }
    }
}
