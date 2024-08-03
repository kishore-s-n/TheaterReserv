package org.example;

import java.sql.SQLException;

public class Pricing {
    public Pricing(){}

    public static int pricing(int theaterId,Integer[] seats) throws SQLException {
        int capacity=theaterDAO.getTheaterCapacity(theaterId);
        int numOfRows=theaterDAO.getNumberOfRows(theaterId);

        int price=0;
        int seatsPerRow=capacity/numOfRows;
        for(Integer seat:seats){
            if(seat<=2*seatsPerRow){
                price+=60;
            }else if(seat>2*seatsPerRow && seat<=capacity-3*seatsPerRow){
                price+=120;
            }else if(seat>capacity-3*seatsPerRow){
                price+=240;
            }
        }
        return price;
    }

}
