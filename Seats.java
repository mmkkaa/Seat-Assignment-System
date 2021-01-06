import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;





public class Seats extends SeatAssignment {
	
	// Connection to the mySQL database
	static DatabaseConnection connect = new DatabaseConnection(
			"jdbc:mysql://localhost/cs157a", "com.mysql.jdbc.Driver", "root",
			"root");
	
	static SeatAssignment s; 
   
    static int seatCounter = SeatAssignment.getNumberOfSeats();   
    static boolean taken = false;
    static boolean selected = false; 
	static ImageIcon seat = new ImageIcon("images/Standard.png"); 
	static ImageIcon Fseat = new ImageIcon("images/first.png"); 
	static ImageIcon emeSeat = new ImageIcon("images/eme.png"); 
	static ImageIcon unavailableSeat = new ImageIcon("images/un.png");
	static ImageIcon selectedSeat = new ImageIcon("images/selected.png"); 
	static ImageIcon eatSign = new ImageIcon("images/eatSign.png"); 
	static ImageIcon toiletSign= new ImageIcon("images/ToiletSign.png"); 
	static ImageIcon airplane = new ImageIcon("images/ecoAircraft.png"); 
	static ImageIcon airplane1 = new ImageIcon("images/firstAircraft.png"); 
	static ImageIcon exit = new ImageIcon("images/exit.png"); 
    final static int rows = 29; 
    final static int cols = 11; 
	static JButton[][] seatsButton = new JButton[rows][cols]; 
	static JButton[][] selectedButton = new JButton[rows][cols]; 
	static ArrayList<String> buttonList = new ArrayList <String>();
	static JLabel[] letters1 = {new JLabel("A"), new JLabel("B"), new JLabel("C"), 
			new JLabel("   "), new JLabel("D"), new JLabel("E"), new JLabel("F"), 
			new JLabel("   "), new JLabel("H"), new JLabel("J"), new JLabel("K")};
	
	static JLabel[] letters2 = {new JLabel("A"), new JLabel("B"),  new JLabel("   "),
			new JLabel("C"), new JLabel("D"), new JLabel("E"), 
			new JLabel("   "), new JLabel("F"), new JLabel("K")};
	
	static JLabel[] letters3 = {new JLabel("A"), new JLabel("           "), 
			 new JLabel("B"), new JLabel("C"), new JLabel("          "), 
			 new JLabel("D")};
	static  ImageIcon[] airplaneIcons = { seat , selectedSeat , emeSeat , unavailableSeat , eatSign , toiletSign }; 
	static  JLabel[] airplaneLabels = { new JLabel("Standard Seat") , new JLabel("Selected Seat") , new JLabel("Emeregency Seat"),
	    		                    new JLabel("Unavailable Seat"), new JLabel("Galley"), new JLabel("Lavatory") };

	static int l = 0;
	static Font font = new Font("Serif", Font.BOLD, 14);
    static int size = 300;
	static JButton[] selectedButtons = new JButton[size]; 
	static JLabel  bookingRef = new JLabel( "Booking Reference: " + SeatAssignment.getReference());	
	static JLabel  names = new JLabel( "Passenger Name: " + SeatAssignment.getSurname() + ", " + SeatAssignment.getPassengerNames());
	static JLabel numPassengers =  new JLabel("Number of Passengers: " +  SeatAssignment.getNumberOfSeats() );
	static JLabel departFrom = new JLabel("Departure : " + SeatAssignment.getDepAirport() );
	static JLabel arriveTo = new JLabel("Arrival: " + SeatAssignment.getArivAirport() );
	static JLabel flightID  =  new JLabel("Flight ID: " + SeatAssignment.getFlightID());

	
	  public static void firstSeats() throws SQLException{
		  
		  
		    bookingRef.setFont(font);
		    names.setFont(font); 
		    numPassengers.setFont(font); 
		    departFrom.setFont(font); 
		    arriveTo.setFont(font); 
		    flightID.setFont(font); 
		    
		    for(int i=0; i<airplaneLabels.length; i++ ) {
		    	airplaneLabels[i].setFont(font); 
		    }
		    
		    
		   
		    final JFrame seatsFrame = new JFrame("First Class Seats"); 
		    seatsFrame.setSize(new Dimension(700, 700));
		    
		    
		    JPanel firstSeatsPanel = new JPanel();
		    firstSeatsPanel.setBorder(BorderFactory.createTitledBorder("First Class Seats"));
		    firstSeatsPanel.setBounds(200, 100, 100, 100);
		    firstSeatsPanel.setLayout(new GridLayout(6,6));
		    
		  
		    // set flow layout for the frame  
		    Container con  = seatsFrame.getContentPane();
		    
		    JPanel infoPanel = new JPanel();
		    infoPanel.setBorder(BorderFactory.createTitledBorder("Seats Information"));
		    infoPanel.setSize(100, 200);
		    infoPanel.setLayout(new GridLayout(6,0));
		    
		    
		    JPanel southPanel = new JPanel();
		    southPanel .setLayout(new GridLayout(4,2));
		    southPanel.setBorder(BorderFactory.createTitledBorder("Confirm Changes"));
		    southPanel.setSize(300, 400);
		    
		    JButton confirmSeat = new JButton("Confirm Seat"); 
		    confirmSeat.setBounds(50,150,100,100);
		    JButton returnBack = new JButton(" Return Back");
		    returnBack.setBounds(50,200,100,100);
		 
		    southPanel.add(confirmSeat); 
		    southPanel.add(returnBack);
		    
		    JPanel eastPanel= new JPanel();
		   
		    eastPanel.setBorder(BorderFactory.createTitledBorder("Booking Information"));
		    eastPanel.setSize(300, 400);
		    eastPanel.setLayout(new GridLayout(5, 1));
			eastPanel.add(flightID);
		    eastPanel.add(bookingRef);
		    eastPanel.add(numPassengers);
		    eastPanel.add(departFrom);
		    eastPanel.add(arriveTo);
		    
		    
		    JScrollPane scroll = new JScrollPane(firstSeatsPanel); 
	        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
	        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
	       
	      
	        for (int c=0; c<6; c++) { 
		    	
		    	for (int l=0; l<2; l++) {
		    		
		    		if (l==0 ) {
		  	    	  infoPanel.add(new JLabel (airplaneIcons[c])); 
		  	    	}
		           if ( l == 1)  {
		    	  infoPanel.add(airplaneLabels[c]); 	
		    	}
		      }
		    }

	        
			 
	     	for (int i=0; i<6; i++) {      
	     		
	     		 if(i==0 || i==1 || i==2 || i==5) {
	     			firstSeatsPanel.add(new JLabel(""));
	    		 
	     		 }else {
	     			firstSeatsPanel.add(new JLabel(Integer.toString(i-2))).setFont(font);
	     		 }   
	     		 
	     		for(int j=0; j<6; j++) {
	     		
	     			if (i == 2) {
	     				firstSeatsPanel.add(letters3[l]);
	     				 letters3[l].setFont(font);
	    				 l++;
	     		 
	     			}  else if (i==1 && j==2 || i==1 && j==3) {
	     				firstSeatsPanel.add(new JLabel(eatSign)); 
	       		    } else if (i==5 && j==2 || i==5 && j==3 ) {
	       		    	firstSeatsPanel.add(new JLabel(toiletSign)); 
	       		    } else if (i==1 && j==0 || i==1 && j==5 || i==5 && j==0 || i==5 && j==5) { 
	       		    	firstSeatsPanel.add(new JLabel(exit)); 
	       		    } else if ( j== 1 || j == 4  || i==0 && j==2 || i==0 && j==3 || i==1 && j==2 ||
	       		    		i==5 && j==2 || i==5 && j==3 || i==0 && j==5 || i==0 && j==0) {
	     				firstSeatsPanel.add(new JLabel(""));
	                } 
	       		    
	       		   
	       		    	else {
	                 	
	                    seatsButton[i][j] = new JButton("");
	         			seatsButton[i][j].setIcon(seat);
	         			seatsButton[i][j] .setContentAreaFilled(false);
	         			seatsButton[i][j] .setPreferredSize(new Dimension(20, 20));
	         			seatsButton[i][j] .setBorderPainted(false);
	         			seatsButton[i][j] .setOpaque(false);
	         			

	        			seatsButton[i][j].setName(Integer.toString(i-2)+letters3[j].getText());
	        			
	         		
			setStatus(seatsButton[i][j]);
			firstSeatsPanel.add(seatsButton[i][j]); 
		
		    buttonList.removeAll(buttonList);
            buttonList.clear();
            
            if(isTaken(seatsButton[i][j].getName()) == 1) {
           	 seatsButton[i][j].setIcon(unavailableSeat);
           	seatsButton[i][j] .setContentAreaFilled(false);
			seatsButton[i][j] .setPreferredSize(new Dimension(20, 20));
			seatsButton[i][j] .setBorderPainted(false);
			seatsButton[i][j] .setOpaque(false);
			seatsButton[i][j].setEnabled(false);
            }
          
  	     seatsButton[i][j].addActionListener(new ActionListener(){
  	    	 
				public void actionPerformed(ActionEvent e){
  	        		 
  	        	
  	        			    for(int i=0; i<6; i++) {
  	        						for(int j=0; j<6; j++) {
  	        							
  	        							if(e.getSource() == seatsButton[i][j] && seatCounter !=0 && seatsButton[i][j].getIcon() != selectedSeat) {
  	        								
  	        								if  (!buttonList.contains(seatsButton[i][j].getName())) {
  	        									
  	        									seatsButton[i][j] .setContentAreaFilled(false);
  	        									seatsButton[i][j] .setPreferredSize(new Dimension(20, 20));
  	        									seatsButton[i][j] .setBorderPainted(false);
  	        									seatsButton[i][j] .setOpaque(false);
      	        								seatsButton[i][j].setIcon(selectedSeat); 
      	        							    seatCounter--;
                                    	     }	
  	        								
  	        								 buttonList.add(seatsButton[i][j].getName());
   	        							    
  	        							}
  	        								
  	        								else if (e.getSource() == seatsButton[i][j] && seatsButton[i][j].getIcon() == selectedSeat){
  	        									
  	        									 		
     	        								seatsButton[i][j].setIcon(seat); 
     	        								seatsButton[i][j] .setContentAreaFilled(false);
     	        								seatsButton[i][j] .setPreferredSize(new Dimension(20, 20));
     	        								seatsButton[i][j] .setBorderPainted(false);
     	        								seatsButton[i][j] .setOpaque(false);
  	        									
  	        									
  	        									seatCounter++; 
  	        									buttonList.remove(seatsButton[i][j].getName());
    	        							     
  	        								}	
  	        					}}
  	        			    
  	        			    System.out.println(buttonList.toString()); 
					        System.out.println(buttonList.size()); 
					        
					        }
					    
  	        			});
	     		}
	     	}
	     }	
	     	
	     	confirmSeat.addActionListener(new ActionListener(){
     		   
				public void actionPerformed(ActionEvent e)
				{
					if (seatCounter ==0) {
						 for( String seatID : buttonList) { 
								
							    SeatAssignment.seatTaken(seatID);
							    SeatAssignment.insertBookingID(SeatAssignment.getBookingId(), seatID);
							}
		
							JOptionPane.showMessageDialog(null,"Your Seat have been booked");
							SendSeatEmail.sendEmail(buttonList);
							seatsFrame.dispose();
						
				}
					
				 else  { 
					 JOptionPane.showMessageDialog(null,"Please Select a seat or make sure you have selected seats for all pssengers." );
					 
				
				}
					
				}
				
			});	
  	   
		   
	        JPanel panel3 = new JPanel();
			panel3 .setBorder(BorderFactory.createTitledBorder("Aircraft Layout"));
			panel3.setLayout(new BoxLayout(panel3 , BoxLayout.PAGE_AXIS));
			panel3 .setSize(400,400);
			JLabel airpnaleBack = new JLabel();
			airpnaleBack.setIcon(airplane1);
			panel3.add(airpnaleBack); 
			
			 JScrollPane scroll2 = new JScrollPane(panel3); 
			 scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
			 scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
			 

  	         JTabbedPane tabbedPane = new JTabbedPane();
      		   
      		    
      		tabbedPane.addTab("Booking Information", eastPanel);
      		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

      		tabbedPane.addTab("View Legend", infoPanel);
      		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
      		
      		con.add(scroll2, BorderLayout.EAST);     
  		    con.add(tabbedPane, BorderLayout.NORTH);
  		    con.add(scroll, BorderLayout.CENTER);
  		    con.add(southPanel, BorderLayout.SOUTH); 
	    	
  		   con.setBackground(Color.GRAY);
	        panel3.setBackground(Color.WHITE);
	        firstSeatsPanel.setBackground(Color.WHITE);
		    infoPanel.setBackground(Color.WHITE);
		    southPanel.setBackground(Color.WHITE);
		    eastPanel.setBackground(Color.WHITE);
		    
		    
	        
	        //Ask for window decorations provided by the look and feel.
	        JFrame.setDefaultLookAndFeelDecorated(true);
	       //Set the frame icon to an image loaded from a file.
	        seatsFrame.setIconImage(new ImageIcon("images/Logo-image.png.").getImage());
	        
	    	seatsFrame.setVisible(true);
	    	seatsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	
	 }
	
	  
	  
	  public static void businessSeats() throws SQLException {
		  
		    bookingRef.setFont(font);
		    names.setFont(font); 
		    numPassengers.setFont(font); 
		    departFrom.setFont(font); 
		    arriveTo.setFont(font); 
		    flightID.setFont(font); 
		    
		    for(int i=0; i<airplaneLabels.length; i++ ) {
		    	airplaneLabels[i].setFont(font); 
		    }
		    
		   
		    final JFrame seatsFrame = new JFrame("Business Class Seats"); 
		    seatsFrame.setSize(new Dimension(700, 700));
		    //create container               
		    // set flow layout for the frame  
		    Container con  = seatsFrame.getContentPane();
		    
		    JPanel seatsPanel = new JPanel();
		    seatsPanel.setBorder(BorderFactory.createTitledBorder("Business Seats"));
		    seatsPanel.setBounds(200, 250, 500,500);
		    seatsPanel.setLayout(new GridLayout(10,9));
		    
		    JPanel infoPanel = new JPanel();
		    infoPanel.setBorder(BorderFactory.createTitledBorder("Seats Information"));
		    infoPanel.setSize(100, 200);
		    infoPanel.setLayout(new GridLayout(6,1));
		    
		    JPanel southPanel = new JPanel();
		    southPanel .setLayout(new GridLayout(4,2));
		    southPanel.setBorder(BorderFactory.createTitledBorder("Confirm Changes"));
		    southPanel.setSize(300, 400);
		    
		    JButton confirmSeat = new JButton("Confirm Seat"); 
		    confirmSeat.setBounds(50,150,100,100);
		    JButton returnBack = new JButton("Return Back");
		    returnBack.setBounds(50,200,100,100);
		 
		    southPanel.add(confirmSeat); 
		    southPanel.add(returnBack);
		    
		    JPanel eastPanel= new JPanel();
		   
		    eastPanel.setBorder(BorderFactory.createTitledBorder("Booking Information"));
		    eastPanel.setSize(300, 400);
		    eastPanel.setLayout(new GridLayout(5, 1));
			eastPanel.add(flightID);
		    eastPanel.add(bookingRef);
		    eastPanel.add(numPassengers);
		    eastPanel.add(departFrom);
		    eastPanel.add(arriveTo);
		    
		    
		    JScrollPane scroll = new JScrollPane(seatsPanel); 
	        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
	        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
	       
	      
	    	JPanel panel3 = new JPanel();
			panel3 .setBorder(BorderFactory.createTitledBorder("Aircraft Layout"));
			panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
			panel3 .setSize(400,400);
			JLabel airpnaleBack = new JLabel();
			airpnaleBack.setIcon(airplane);
			panel3.add(airpnaleBack); 
			
			 JScrollPane scroll2 = new JScrollPane(panel3); 
			 scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
			 scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
			 
		       
		    for (int c=0; c<6; c++) { 
		    	
		    	for (int l=0; l<2; l++) {
		    		
		    		if (l==0 ) {
		  	    	  infoPanel.add(new JLabel (airplaneIcons[c])); 
		  	    	}
		           if ( l == 1)  {
		    	    infoPanel.add(airplaneLabels[c]); 	
		    	}
		      }
		    }
		    
		   
	    	for (int i=0; i<10; i++) {      
	    		
	    		 if(i==0|| i==3 || i ==4 || i==5) {
	    			 seatsPanel.add(new JLabel(""));
	    		
	    		 }else {
	                seatsPanel.add( new JLabel(Integer.toString(i+2))).setFont(font);
	    		 }   
	    		
	    		for(int j=0; j<9; j++) {
	    		
	    			if (i == 0) {
	    			     seatsPanel.add(letters2[l]);
	    			     letters2[l].setFont(font);
	    				 l++;
	    		 
	    			} else if (i==3 && j==4 ) {
	        			seatsPanel.add(new JLabel(eatSign)); 
	       		    } else if ( i==5 && j==4) {
	        			seatsPanel.add(new JLabel(toiletSign)); 
	       		    }else if (i==4 && j==0 || i==4 && j==8) {
	       		    	seatsPanel.add(new JLabel(exit)); 
	       		    }
	    			
	    			else {
	                	
	                 if ( j== 2 || j == 6 ) {
	                	seatsPanel.add(new JLabel(""));
	                } else if ( i==3 && j==3 ||i==3 && j==0|| i==3 && j==1 || i==3 && j==5 || i==3 && j==7 || i==3 && j==8 ||
	                		i==4 && j ==1 || i==4 && j==3 ||  i==4 && j==4 || i==4 && j==5 || i==4 && j==6 || i==4 && j==7 || 
	                		i==5 && j==0 ||  i==5 && j==1 || i==5 && j==3 || i==5 && j==7 || i==5 && j==8 || i==5 && j==5) {
	                	seatsPanel.add(new JLabel(""));
	                }
	                	
	                	else {
	                	
	                    seatsButton[i][j] = new JButton("");
	        			seatsButton[i][j].setIcon(seat);	
	        			seatsButton[i][j] .setContentAreaFilled(false);
	        			seatsButton[i][j] .setPreferredSize(new Dimension(20, 20));
	        			seatsButton[i][j] .setBorderPainted(false);
	        			seatsButton[i][j] .setOpaque(false);
	        			
	        			seatsButton[i][j].setName(Integer.toString(i+2)+letters2[j].getText());
	        			
	        		    
	        			setStatus(seatsButton[i][j]);
	        			seatsPanel.add(seatsButton[i][j]); 
	        		
	        		 buttonList.removeAll(buttonList);
	   	             buttonList.clear();
	   	             
	   	             if(isTaken(seatsButton[i][j].getName()) == 1) {
	   	            	 seatsButton[i][j].setIcon(unavailableSeat);
	   	            	seatsButton[i][j] .setContentAreaFilled(false);
	        			seatsButton[i][j] .setPreferredSize(new Dimension(20, 20));
	        			seatsButton[i][j] .setBorderPainted(false);
	        			seatsButton[i][j] .setOpaque(false);
	        			seatsButton[i][j].setEnabled(false);
	   	             }
	   	             
	          	     seatsButton[i][j].addActionListener(new ActionListener(){
	          	    	 
							public void actionPerformed(ActionEvent e){
	          	        		 
	          	        	
	          	        			    for(int i=0; i<10; i++) {
	          	        						for(int j=0; j<9; j++) {
	          	        							
	          	        							if(e.getSource() == seatsButton[i][j] && seatCounter !=0 && seatsButton[i][j].getIcon() != selectedSeat) {
	          	        								
	          	        								if  (!buttonList.contains(seatsButton[i][j].getName())) {
	          	        									
	              	        								seatsButton[i][j] .setOpaque(false);
	              	        								seatsButton[i][j] .setBorderPainted(false);
	              	        								seatsButton[i][j] .setContentAreaFilled(true);
	              	        								seatsButton[i][j].setIcon(selectedSeat); 
	              	        							    seatCounter--;
	                                            	     }	
	          	        								
	          	        						
	          	        								
	          	        								 buttonList.add(seatsButton[i][j].getName());
	           	        							    
	          	        							}
	          	        								
	          	        								else if (e.getSource() == seatsButton[i][j] && seatsButton[i][j].getIcon() == selectedSeat ){
	          	        									 		
	             	        								 seatsButton[i][j].setIcon(seat); 
	             	        								seatsButton[i][j] .setOpaque(false);
	              	        								seatsButton[i][j] .setBorderPainted(false);
	              	        								seatsButton[i][j] .setContentAreaFilled(true);
	              	        							
	          	        									
	          	        									
	          	        									seatCounter++; 
	          	        									buttonList.remove(seatsButton[i][j].getName());
	            	        							     
	          	        								}	
	          	        					}}
	          	        			    
	          	        			    System.out.println(buttonList.toString()); 
	        					        System.out.println(buttonList.size()); 
	        					        System.out.println("Counter: " + seatCounter);
	        					        
	        					        }
								    
	          	        			});
	          	   
	          	     
	          	   confirmSeat.addActionListener(new ActionListener(){
	          		   
						public void actionPerformed(ActionEvent e)
						{
							if (seatCounter ==0) {
								 for( String seatID : buttonList) { 
										
									    SeatAssignment.seatTaken(seatID);
									    SeatAssignment.insertBookingID(SeatAssignment.getBookingId(), seatID);
									}
				
									JOptionPane.showMessageDialog(null,"Your Seat have been booked");
									SendSeatEmail.sendEmail(buttonList);
									seatsFrame.dispose();
								
						}
							
						 else  { 
							 JOptionPane.showMessageDialog(null,"Please Select a seat or make sure you have selected seats for all pssengers." );
							 
						
						}
							
						}
						
					});	
	          	   
	            	
	          	    
	         
	         	      
	          	         JTabbedPane tabbedPane = new JTabbedPane();
		          		   
		          		    
		          		tabbedPane.addTab("Booking Information", eastPanel);
		          		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		          		tabbedPane.addTab("View Legend", infoPanel);
		          		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		          		
		          		con.add(scroll2, BorderLayout.EAST);     
	          		    con.add(tabbedPane, BorderLayout.NORTH);
	          		    con.add(scroll, BorderLayout.CENTER);
	          		    con.add(southPanel, BorderLayout.SOUTH);  
	          		   
	                  }
	        			
	                }
	              }
	    	}	
	    
	    	
	        //Ask for window decorations provided by the look and feel.
	        JFrame.setDefaultLookAndFeelDecorated(true);
	       //Set the frame icon to an image loaded from a file.
	        seatsFrame.setIconImage(new ImageIcon("images/Logo-image.png.").getImage());
	        
	    	seatsFrame.setVisible(true);
	    	seatsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	  }
	  	  

      public static void economySeats() throws SQLException{	
    	  
	    bookingRef.setFont(font);
	    names.setFont(font); 
	    numPassengers.setFont(font); 
	    departFrom.setFont(font); 
	    arriveTo.setFont(font); 
	    flightID.setFont(font); 
	    
	    for(int i=0; i<airplaneLabels.length; i++ ) {
	    	airplaneLabels[i].setFont(font); 
	    }
	    
	    
	    
	    final JFrame seatsFrame = new JFrame("Economy Class Seats"); 
	    seatsFrame.setSize(new Dimension(700, 700));
	    //create container               
	    // set flow layout for the frame  
	    Container con  = seatsFrame.getContentPane();
	    
	    
	    JPanel seatsPanel = new JPanel();
	    seatsPanel.setBorder(BorderFactory.createTitledBorder("Economy Seats"));
	    seatsPanel.setBounds(200, 250, 500,500);
	    seatsPanel.setLayout(new GridLayout(29,11));
	    
	    JPanel infoPanel = new JPanel();
	    infoPanel.setBorder(BorderFactory.createTitledBorder("Seats Information"));
	    infoPanel.setSize(100, 200);
	    infoPanel.setLayout(new GridLayout(6,1));
	    
	    
	    JPanel southPanel = new JPanel();
	    southPanel .setLayout(new GridLayout(4,2));
	    southPanel.setBorder(BorderFactory.createTitledBorder("Confirm Changes"));
	    southPanel.setSize(300, 400);
	    
	    JButton confirmSeat = new JButton("Confirm Seat"); 
	    confirmSeat.setBounds(50,150,100,100);
	    JButton returnBack = new JButton(" Return Back");
	    returnBack.setBounds(50,200,100,100);
	 
	    southPanel.add(confirmSeat); 
	    southPanel.add(returnBack);
	    
	    JPanel eastPanel= new JPanel();
	   
	    eastPanel.setBorder(BorderFactory.createTitledBorder("Booking Information"));
	    eastPanel.setSize(300, 400);
	    eastPanel.setLayout(new GridLayout(5, 1));
		eastPanel.add(flightID);
	    eastPanel.add(bookingRef);
	    eastPanel.add(numPassengers);
	    eastPanel.add(departFrom);
	    eastPanel.add(arriveTo);
	    
	    
	    JScrollPane scroll = new JScrollPane(seatsPanel); 
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
       
      
    	JPanel panel3 = new JPanel();
		panel3 .setBorder(BorderFactory.createTitledBorder("Aircraft Layout"));
		panel3.setLayout(new BoxLayout(panel3 , BoxLayout.PAGE_AXIS));
		panel3 .setSize(400,400);
		JLabel airpnaleBack = new JLabel();
		airpnaleBack.setIcon(airplane);
		panel3.add(airpnaleBack); 
		
		 JScrollPane scroll2 = new JScrollPane(panel3); 
		 scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		 scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		 
	       
	    for (int c=0; c<6; c++) { 
	    	
	    	for (int l=0; l<2; l++) {
	    		
	    		if (l==0 ) {
	  	    	  infoPanel.add(new JLabel (airplaneIcons[c])); 
	  	    	}
	           if ( l == 1)  {
	    	  infoPanel.add(airplaneLabels[c]); 	
	    	}
	      }
	    }
	    
	   
    	for (int i=0; i<29; i++) {      
    		
    		 if(i==0|| i==1 || i ==28) {
    			 seatsPanel.add(new JLabel(""));
    		
    		 }else {
                seatsPanel.add( new JLabel(Integer.toString(i+14))).setFont(font);
    		 }   
    		
    		for(int j=0; j<11; j++) {
    		
    			if (i == 1) {
    			     seatsPanel.add(letters1[l]);
    			     letters1[l].setFont(font);
    				 l++;
    		 
    			} else if (i==1 && j==5 || i==28 && j==5 ) {
        			seatsPanel.add(new JLabel(eatSign)); 
       		    } else if (i==1 && j==9  || i==1 && j==1 || i ==28 && j==1 || i==28 && j==9) {
        			seatsPanel.add(new JLabel(toiletSign)); 
       		    }else if (i==0 && j==0 || i==0 && j==10) {
       		    	seatsPanel.add(new JLabel(exit)); 
       		    }
    			
    			else {
                	
                 if ( j== 3 || j == 7 ) {
                	seatsPanel.add(new JLabel(""));
                } else if (i==1 || i==28|| i==2 && j==2 || i==2 && j ==8 || i==0 && j==1 ||  i==0 && j==2 ||  i==0 && j==4 ||  i==0 && j==5 
                		||  i==0 && j==6 ||  i==0 && j==8 ||  i==0 && j==9) {
                	seatsPanel.add(new JLabel(""));
                }
                	
                	else {
                	
                    seatsButton[i][j] = new JButton("");
        			seatsButton[i][j].setIcon(seat);	
        			seatsButton[i][j] .setContentAreaFilled(false);
        			seatsButton[i][j] .setPreferredSize(new Dimension(20, 20));
        			seatsButton[i][j] .setBorderPainted(false);
        			seatsButton[i][j] .setOpaque(false);
        			
        			seatsButton[i][j].setName(Integer.toString(i+14)+letters1[j].getText());
        			
        		     
        			
        			if (i==2 && j==0 || i==2 && j ==1 || i==2 && j==4 || i==2 && j==5 || i==2 && j==6 || i==2 && j==9 || i==2 && j==10 ||
        				i==3 && j==2  || i==3 && j==8) {
        				seatsButton[i][j].setIcon(emeSeat);
        				
        				seatsButton[i][j] .setPreferredSize(new Dimension(20, 20));
                    }
        			
        			
        			setStatus(seatsButton[i][j]);
        			seatsPanel.add(seatsButton[i][j]); 
        		
        		 buttonList.removeAll(buttonList);
   	             buttonList.clear();
   	             
   	             
   	          if(isTaken(seatsButton[i][j].getName()) == 1) {
   	           	 seatsButton[i][j].setIcon(unavailableSeat);
   	           	seatsButton[i][j] .setContentAreaFilled(false);
   				seatsButton[i][j] .setPreferredSize(new Dimension(20, 20));
   				seatsButton[i][j] .setBorderPainted(false);
   				seatsButton[i][j] .setOpaque(false);
   				seatsButton[i][j].setEnabled(false);
   	            } 
   	          
   	           
          	     seatsButton[i][j].addActionListener(new ActionListener(){
          	    	 
						public void actionPerformed(ActionEvent e){
          	        		 
          	        	
          	        			    for(int i=0; i<29; i++) {
          	        						for(int j=0; j<11; j++) {
          	        							
          	        							if(e.getSource() == seatsButton[i][j] && seatCounter !=0 && seatsButton[i][j].getIcon() != selectedSeat) {
          	        								
          	        								if  (!buttonList.contains(seatsButton[i][j].getName())) {
          	        									
              	        								seatsButton[i][j] .setOpaque(false);
              	        								seatsButton[i][j] .setBorderPainted(false);
              	        								seatsButton[i][j] .setContentAreaFilled(true);
              	        								seatsButton[i][j].setIcon(selectedSeat); 
              	        							    seatCounter--;
                                            	     }	
          	        								
          	        						
          	        								
          	        								 buttonList.add(seatsButton[i][j].getName());
           	        							    
          	        							}
          	        								
          	        								else if (e.getSource() == seatsButton[i][j] && seatsButton[i][j].getIcon() == selectedSeat ){
          	        									
          	        									if (i==2 && j==0 || i==2 && j ==1 || i==2 && j==4 || i==2 && j==5 || i==2 && j==6 || i==2 && j==9 || i==2 && j==10 ||
          	        					        				i==3 && j==2  || i==3 && j==8) {
          	        					        				seatsButton[i][j].setIcon(emeSeat);
          	        					        				seatsButton[i][j] .setOpaque(false);
                      	        								seatsButton[i][j] .setBorderPainted(false);
                      	        								seatsButton[i][j] .setContentAreaFilled(true);
                      	        							
                      	        								 
          	        									} else { 		
             	        								 seatsButton[i][j].setIcon(seat); 
             	        								seatsButton[i][j] .setOpaque(false);
              	        								seatsButton[i][j] .setBorderPainted(false);
              	        								seatsButton[i][j] .setContentAreaFilled(true);
              	        							
          	        									}
          	        									
          	        									seatCounter++; 
          	        									buttonList.remove(seatsButton[i][j].getName());
            	        							     
          	        								}	
          	        					}}
          	        			    
          	        			    System.out.println(buttonList.toString()); 
        					        System.out.println(buttonList.size()); 
        					        System.out.println("Counter: " + seatCounter);
        					        
        					        }
							    
          	        			});
          	   
          	     
          	   confirmSeat.addActionListener(new ActionListener(){
          		   
					public void actionPerformed(ActionEvent e)
					{
						if (seatCounter ==0) {
							 for( String seatID : buttonList) { 
									
								    SeatAssignment.seatTaken(seatID);
								    SeatAssignment.insertBookingID(SeatAssignment.getBookingId(), seatID);
								}
			
								JOptionPane.showMessageDialog(null,"Your Seat have been booked");
								
								SendSeatEmail.sendEmail(buttonList);
								seatsFrame.dispose();
							
					}
						
					 else  { 
						 JOptionPane.showMessageDialog(null,"Please Select a seat or make sure you have selected seats for all pssengers." );
						 
					
					}
						
					}
					
				});	
          	   
          	         JTabbedPane tabbedPane = new JTabbedPane();
	          		   
	          		    
	          		tabbedPane.addTab("Booking Information", eastPanel);
	          		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

	          		tabbedPane.addTab("View Legend", infoPanel);
	          		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	          		
	          		con.add(scroll2, BorderLayout.EAST);     
          		    con.add(tabbedPane, BorderLayout.NORTH);
          		    con.add(scroll, BorderLayout.CENTER);
          		    con.add(southPanel, BorderLayout.SOUTH);  
          	   
                  }
        			
                }
              }
    	}	
    	
    	con.setBackground(Color.GRAY);
        panel3.setBackground(Color.WHITE);
        seatsPanel.setBackground(Color.WHITE);
	    infoPanel.setBackground(Color.WHITE);
	    southPanel.setBackground(Color.WHITE);
	    eastPanel.setBackground(Color.WHITE);
	    
    		
        
     
        //Ask for window decorations provided by the look and feel.
        JFrame.setDefaultLookAndFeelDecorated(true);
       //Set the frame icon to an image loaded from a file.
        seatsFrame.setIconImage(new ImageIcon("images/Logo-image.png.").getImage());
        
       
    	seatsFrame.setVisible(true);
    	seatsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
 
      }
      
    
      public static JLabel setType(JButton seatsButton) { 
    	  JLabel seatType = null; 
    	  
    	  if (SeatAssignment.firstClass() ==1) {
    		  seatType = new JLabel("Seat : " + seatsButton.getName() + " , " +  "First Class Seat"); 
    	  }else if (SeatAssignment.businessClass() == 1 ) {
    		  seatType = new JLabel("Seat : " +  seatsButton.getName()  + " , " + "Business Class Seat"); 
    	  }else { 
    		  seatType = new JLabel("Seat : " +  seatsButton.getName()  + " , " + "Economy Seat") ; 
    	  }
		  
    	seatType.setFont(font);
		return seatType;
      }
      
      
      public static boolean setStatus(JButton seatsButton) { 
    	boolean seatTaken = false; 
    	 
		return seatTaken;
      }
      
    
     
   }
     

      








