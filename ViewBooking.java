
import java.awt.Color;

import java.awt.Font;
import java.awt.Image;

import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;

public class ViewBooking extends SeatAssignment{

	// Connection to the mySQL database
	static DatabaseConnection connect = new DatabaseConnection(
			"jdbc:mysql://localhost/cs157a", "com.mysql.jdbc.Driver", "root",
			"root");
       
        static boolean userVerified = false;
   
        static int numOfSeats; 
        static boolean taken = true;
        static Seats seat;
        static int attempts = 0; 
        static Font font = new Font("Serif", Font.BOLD, 14);
        static Font fontSmall = new Font("Times New Roma" , Font.PLAIN, 12);
    	static JTextField  bookingRefTxt = new JTextField();
    	static JTextField surnameTxt = new JTextField();
    	static JTextField emailTxt = new JTextField();
    	
    	
    	static JLabel  bookingRef = new JLabel( "Booking Reference: " + SeatAssignment.getReference());	
    	static ImageIcon ticket = new ImageIcon ("images/ticket1.png");
    	static ImageIcon fromTo = new ImageIcon ("images/fromTo.png");
    	static ImageIcon barcode = new ImageIcon ("images/barcode.png");
    	static JLabel numPassengers =  new JLabel("Number of Passengers: " +  SeatAssignment.getNumberOfSeats() );
    	static JLabel departFrom = new JLabel("Departure");
    	static JLabel arriveTo = new JLabel("Arrival " );
    	static JLabel flightID  =  new JLabel("Flight ID: " + SeatAssignment.getFlightID());
    	static JLabel departTime = new JLabel("Time: " + SeatAssignment.getDepTime());
    	static JLabel departDate = new JLabel("Date: " + SeatAssignment.getDepDate());
    	static JLabel arivDate = new JLabel("Date: " + SeatAssignment.getArivDate());
    	static JLabel arivTime = new JLabel("Time: " + SeatAssignment.getArivTime());
    	static JLabel surname = new JLabel("Ms/Mr, " + SeatAssignment.getSurname() );
    	static JLabel dep1 = new JLabel( SeatAssignment.getDepAirport() );
    	static JLabel arr1 = new JLabel( SeatAssignment.getArivAirport() );
    	static JLabel dep2 = new JLabel( SeatAssignment.getDepAirport() );
    	static JLabel arr2 = new JLabel( SeatAssignment.getArivAirport() );
    	static JLabel seatl = new JLabel("Seat " );
    	static JLabel from = new JLabel("From " );
    	static JLabel to = new JLabel("To " );
    	static JLabel flightIDl = new JLabel("Flight" );
    	static JLabel getID = new JLabel(SeatAssignment.getFlightID());
        static JLabel seatType = new JLabel(SeatAssignment.seatType());
    	
    	static ArrayList<JLabel> namesLabel = new ArrayList <JLabel>();
 	
    	
	   public static void view()  throws SQLException{
		
		
			//frame declaration, initialization
			final JFrame frame1 = new JFrame();
	        frame1.setTitle("Your Booking");
	        frame1.setBounds(380, 250, 700, 350);

		    Image img=ticket.getImage();
		    Image temp = img.getScaledInstance(600,600,Image.SCALE_SMOOTH);
		    ticket=new ImageIcon(temp);
		    JLabel back=new JLabel(ticket);
		   
		    back.setBounds(0,100,500,500);  
	       
	        frame1.setContentPane(back);
	        
	        JLabel fromToL = new JLabel(fromTo);
	        JLabel barcodeL = new JLabel(barcode);
	        JLabel barcodeL2 = new JLabel(fromTo);
			
	        
	        bookingRef.setFont(font);
	        bookingRef.setBounds(75,5, 200,50);
		    numPassengers.setFont(font); 
		    numPassengers.setBounds(290,5, 200,50);
		    
		    departFrom.setFont(fontSmall); 
		    departFrom.setForeground(Color.GRAY);
		    
		    departFrom.setBounds(75,65,100,50);
		    dep2.setFont(font);
		    dep2.setBounds(75,90, 200,50);
		    
		    barcodeL2.setBounds(200,90,50,50);
		    
		    arriveTo.setFont(fontSmall); 
		    arr2.setBounds(300,90, 200,50);
		    arriveTo.setBounds(300,65,100,50);
		    arriveTo.setForeground(Color.GRAY);
		    
		    arr2.setFont(font);
		    
		    
		    flightID.setFont(font); 
		    flightID.setBounds(75,30, 200,50);
		    arivDate.setFont(font);
		    departDate.setBounds(75,120, 200,50);
		    arivTime.setBounds(300,150, 200,50);
		    arivTime.setFont(font);
		    arivDate.setBounds(300,120, 200,50);
		    departTime.setFont(font);
		    departTime.setBounds(75,150, 200,50);
		    departDate.setFont(font);
		    surname.setFont(font);
		    surname.setBounds(480,5,200,50);
		    
		    dep1.setFont(font); 
		    dep1.setBounds(470,100,200,50);
		   
		    fromToL.setBounds(520 , 100, 50, 50);
		    barcodeL.setBounds(100,75,300,300);
		    arr1.setFont(font);
		    arr1.setBounds(580,100,200,50);
		    
		    seatl.setBounds(480,116,400,200);
		    seatl.setFont(fontSmall);
		    seatl.setForeground(Color.GRAY);
		    
		    from.setFont(fontSmall);
		    from.setForeground(Color.GRAY);
		    
		    from.setBounds(470,80,50,50);
		    to.setFont(fontSmall);
		    to.setBounds(580,80,50,50);
		    to.setForeground(Color.GRAY);
		    
		    flightIDl.setFont(fontSmall);
		    flightIDl.setBounds(470,35,50,50);
		    flightIDl.setForeground(Color.GRAY);
		    
		    getID.setFont(font);
		    getID.setBounds(470,50,50,50);
		    
		    seatType.setFont(new Font("Andalus" , Font.BOLD, 18));
		    seatType.setForeground(Color.DARK_GRAY);
		    seatType.setBounds(470,150,200,50);
		    
		    frame1.add(arr2);
		    frame1.add(dep2);
		    frame1.add(bookingRef); 
		    frame1.add(dep1); 
		    frame1.add(fromToL); 
		    frame1.add(arr1); 
		    frame1.add(surname);
		    frame1.add(seatl);
		    frame1.add(barcodeL);
		    frame1.add(from); 
		    frame1.add(to); 
		    frame1.add(flightIDl); 
		    frame1.add(seatType);
		    frame1.add(barcodeL2);
		
		for (String s : SeatAssignment.getPassengerNames()) {
			 
			JLabel names = new JLabel(s);
			 names.setFont(font);
			 names.setBounds(480,130,400,200);   
			 frame1.add(names);
		}
		
		
	    frame1.add(numPassengers);
	    frame1.add(getID);
	    frame1.add(departFrom);
	    frame1.add(arriveTo); 
	    frame1.add(flightID); 
	    frame1.add(departDate); 
	    frame1.add(departTime); 
	    frame1.add(arivDate); 
	    frame1.add(arivTime); 
		
		//create container               
		
		
		JFrame.setDefaultLookAndFeelDecorated(true);
        frame1.setIconImage(new ImageIcon("images/Logo-image.png").getImage());
        frame1.setBackground(Color.BLACK);
	      
		
		frame1.setVisible(true);
		
	
	}
}


