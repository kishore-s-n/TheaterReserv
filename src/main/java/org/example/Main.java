package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Are you a user or a admin");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        if(user.equals("admin")) {
//            String adminname=scanner.nextLine();
//            String adminpassword=scanner.nextLine();
//            boolean a=checkAdmin(adminname,adminpassword);
//            if(a==true){
//                while(a==true){
//                    System.out.println("Select the theater to see profit\n"
//                    +"type 1864 to exit the admin and use as a user");
//                    int theaterid=scanner.nextInt();
//                    if(theaterid==1864){
//                        a=false;
//                    }else{
//                        Pricing.pricing(theaterid,theaterDAO.getSeatsBooked(theaterid));
//                    }
//                }
//            }
        }else{
            System.out.println("You are not a admin");

            System.out.println("Welcome to Kishore Cinemas");
            System.out.println();
            System.out.println("Here is the list of movies that are beign screened");
            movieDAO.displaytMovieDetails();

            System.out.println("Select the theater that you would like to watch");


            System.out.println("Enter the theater id to know about the seating \n"
                    + "\t1.Theater 1 screening " + movieDAO.getScreeningMovie(1) + "\n"
                    + "\t2.Theater 2 screening " + movieDAO.getScreeningMovie(2) + "\n"
                    + "\t3.Theater 3 screening " + movieDAO.getScreeningMovie(3) + "\n"
                    + "\t4.Theater 4 screening " + movieDAO.getScreeningMovie(4) + "\n"
                    + "\t5.Theater 5 screening " + movieDAO.getScreeningMovie(5) + "\n"
                    + "\t6.Press 6 or above to exit"

            );
            int flag = chooseOption();
            while (flag == 1) {
                System.out.println("Enter the theater id to know about the seating \n"
                        + "\t1.Theater 1 screening " + movieDAO.getScreeningMovie(1) + "\n"
                        + "\t2.Theater 2 screening " + movieDAO.getScreeningMovie(2) + "\n"
                        + "\t3.Theater 3 screening " + movieDAO.getScreeningMovie(3) + "\n"
                        + "\t4.Theater 4 screening " + movieDAO.getScreeningMovie(4) + "\n"
                        + "\t5.Theater 5 screening " + movieDAO.getScreeningMovie(5) + "\n"
                        + "\t6.Press 6 or above to exit"

                );
                flag = chooseOption();
            }


        }


        }
    private static int chooseOption() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        int opt;
        switch(option) {
            case 1:
                theaterDAO.displayTheaterById(1);
                System.out.println("what would you like to do?"
                        +"\n1. Book seats"
                        +"\n2. cancel seats"
                        +"\n3 or above. continue watching other theaters");
                opt=sc.nextInt();
                if(opt==1){
                    Booking.bookseats(option);
                    break;
                }else if(opt==2){
                    Booking.cancelseats(option);
                    break;
                }else{
                    break;
                }
            case 2:
                theaterDAO.displayTheaterById(2);
                System.out.println("what would you like to do?"
                        +"\n1. Book seats"
                        +"\n2. cancel seats"
                        +"\n3 or above. continue watching other theaters");
                opt=sc.nextInt();
                if(opt==1) {
                    Booking.bookseats(option);
                    break;
                }else if(opt==2){
                    Booking.cancelseats(option);
                    break;
                }else{
                    break;
                }

            case 3:
                theaterDAO.displayTheaterById(3);
                System.out.println("what would you like to do?"
                        +"\n1. Book seats"
                        +"\n2. cancel seats"
                        +"\n3 or above. continue watching other theaters");
                opt=sc.nextInt();
                if(opt==1){
                    Booking.bookseats(option);
                    break;
                }else if(opt==2){
                    Booking.cancelseats(option);
                    break;
                }else{
                    break;
                }

            case 4:
                theaterDAO.displayTheaterById(4);
                System.out.println("what would you like to do?"
                        +"\n1. Book seats"
                        +"\n2. cancel seats"
                        +"\n3 or above. continue watching other theaters");
                opt=sc.nextInt();
                if(opt==1){
                    Booking.bookseats(option);
                    break;
                }else if(opt==2){
                    Booking.cancelseats(option);
                    break;
                }else{
                    break;
                }

            case 5:
                theaterDAO.displayTheaterById(5);
                System.out.println("what would you like to do?"
                        +"\n1. Book seats"
                        +"\n2. cancel seats"
                        +"\n3 or above. continue watching other theaters");
                opt=sc.nextInt();
                if(opt==1){
                    Booking.bookseats(option);
                    break;
                }else if(opt==2){
                    Booking.cancelseats(option);
                    break;
                }else{
                    break;
                }

            case 6:
                break;
            default:
                System.out.println("\n Hope to see you coming back soon");
                break;
        }
        if(option >= 6) {
            return 0;
        }else{
            return 1;
        }
    }

    private static boolean checkAdmin(String adminname, String adminpassword) {
        return adminname.equals("admin") && adminpassword.equals("root");
    }
}
