
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SeatAssignment {

	private static final String OK = null;
	// Connection to the mySQL database
	static DatabaseConnection connect = new DatabaseConnection(
			"jdbc:mysql://localhost/cs157a", "com.mysql.jdbc.Driver", "root",
			"root");
       
        static boolean userVerified = false;
   
        static int numOfSeats; 
        static boolean taken = true;
        static Seats seat;
        static ViewBooking viewB; 
        static int attempts = 0; 
    	
    	static JTextField  bookingRefTxt = new JTextField();
    	static JTextField surnameTxt = new JTextField();
    	static JTextField emailTxt = new JTextField();
    	static Font font = new Font("Times New Roman", Font.BOLD, 16);
    	static ArrayList<String> resultList = new ArrayList<String>();
    	static ImageIcon back = new ImageIcon("images/backk.png"); 
    	
	// static ArrayList<Integer> ids = new ArrayList<Integer>();

	public static void main(String[] args) throws SQLException {
		
		try {
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	
	//opens main menu, determines whether user is regular user or admin
	     mainMenu();
	
	
	}
	
	


	/**			mainMenu
	 * main menu for the application
	 * 
	 * - user selects between user/admin/exit button
	 * 
	 */
	public static void mainMenu()
	{
		
		JMenuBar menuBar;
		JMenu  signIn;
		JMenuItem  admin;
		
		
		//Create the menu bar.
		
		menuBar = new JMenuBar();
	    menuBar.setBackground(Color.PINK);
	   
		//----------------------------------------------------------------------
		
        
        menuBar.add(Box.createHorizontalGlue());
        
        //-----------------------------------------------------------------------
		signIn = new JMenu("Sign In");
		signIn.setMnemonic(KeyEvent.VK_O);
		signIn.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
	
		 
		admin = new JMenuItem("Admin/Staff", new ImageIcon("images/signIn.png")); 
		admin.setMnemonic(KeyEvent.VK_L);
		signIn.add(admin);
		
		menuBar.add(signIn);
		
		admin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				adminMenu();
			
			}
     		
     	});
        
        //----------------------------------------------------------------------
     
		
		JPanel homePage = new JPanel(); 
		homePage.setBounds(0,700,400,700);
		

	  
		
		
		JButton[][] buttons = new JButton[2][2]; //Declared much earlier in the program, right after the class declaration.
        ImageIcon[] buttonimages = { new ImageIcon("images/rebooknew.png"), new ImageIcon("images/changeSeat.png") ,
        		new ImageIcon("images/viewB.png"), new ImageIcon("images/FAQ.png") };
       
        String[] label = {"Book a Seat", "Change My Seat", "View My Booking", "FAQs"};
        
        int i = 0; 
        int j = 0;
		int r; 
		int c;
		
		    for( r = 0 ; r < 2; r++)
		    {
		    	
		        for(c = 0 ; c < 2; c++)
		        {
		        	
		        	buttons[r][c] = new JButton(buttonimages[i]);
		        	buttons[r][c].setText(label[j]);
		        	buttons[r][c].setContentAreaFilled(false);
		        	buttons[r][c].setBorderPainted(false);
		        	buttons[r][c].setOpaque(false);
		            homePage.add(buttons[r][c]);
		            i++;
		            j++;
   
		        }
		    }
		    
		    
		    buttons[0][0].setContentAreaFilled(false);
        	buttons[0][0].setBorderPainted(false);
        	buttons[0][0].setOpaque(false);
		    
		        	
 		        	buttons[0][0].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							
							userMenu();
					      }
 		        	});
 		        	
 		        	
 		        	buttons[0][1].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							userMenu1();
						}
					
 		        		
 		        	});
 		        	
		
 		        	buttons[1][0].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							showBooking();

						} 
						
 		        		
 		        	});
 		        	
 		        	buttons[1][1].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							
							Help.getHelp(); 
						
						}
 		        		
 		        	});
        	 
        	 
     
        
        
		//frame declaration, initialization
		final JFrame frame = new JFrame();
        frame.setTitle("Seat Assignment System");
        frame.setBounds(400, 150,700,400);
     
        Image img=back.getImage();
	    Image temp = img.getScaledInstance(600,600,Image.SCALE_SMOOTH);
	    back=new ImageIcon(temp);
	    JLabel backk=new JLabel(back);
	   
	    backk.setBounds(0,100,600,500);  
       
       
	    frame.setContentPane(backk);
	      
		//create container               
		Container con = frame.getContentPane();
		  
		con.setLayout(new BorderLayout());
	
		con.add(homePage);
		 //Ask for window decorations provided by the look and feel.
	     JFrame.setDefaultLookAndFeelDecorated(true);
	    //Set the frame icon to an image loaded from a file.
	    frame.setIconImage(new ImageIcon("images/Logo-image.png.").getImage());
	    frame.setJMenuBar(menuBar); 
	        
	     
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	
	
public static void adminMenu(){
		
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5,5,5,5));
		panel1.setLayout(null); 
		JLabel label1 = new JLabel(" Please verify your account to access to the system's data");
		label1.setBounds(0,20,500,20);
		panel1.add(label1);
		
		
		JTextField usernameTxt = new JTextField(14);
		usernameTxt.setBounds(188,51,300,30);
		panel1.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		
		JPasswordField passwordTxt = new JPasswordField(14);
		
		
		passwordTxt.setBounds(188,106,300,30);
		panel1.add(passwordTxt);
		passwordTxt.setColumns(10);
		
		JLabel usernameLB = new JLabel("Email Address:");
		usernameLB.setBounds(70,60,200,19);
		panel1.add(usernameLB); 
		
		
		JLabel passwordLB = new JLabel("Password:");
		passwordLB.setBounds(70,109,200,14);
		panel1.add(passwordLB); 
		

		//Verify button declaration
		JButton verify = new JButton("Verify");
		verify.setBounds(131,165,89,23);
		  //add components to panel
			panel1.add(verify);
			
	    //Back button declaration
		 JButton back = new JButton("Back");
		 back.setBounds(131,195,89,23);
		//add components to panel
		 panel1.add(back);
		 
			ImageIcon show = new ImageIcon("images/showPAss (2).png"); 
		  //Back button declaration
		 JButton showpassButton = new JButton(show);
		
		 showpassButton.setContentAreaFilled(false);
		 showpassButton.setBounds(500,110,15,15);
		 showpassButton.setBorderPainted(false);
		 showpassButton.setOpaque(false);
		 
		 JLabel showpss = new JLabel("show my passowrd.");
		 showpss .setBounds(520,100,150,30);
		//add components to panel
		 panel1.add(showpassButton);
		 panel1.add(showpss);
			

			//frame declaration, initialization
			final JFrame frame1 = new JFrame();
	        frame1.setTitle("Admin Verification");
	        frame1.setBounds(200, 300, 700, 300);
	   
			  
			//create container               
			Container con = frame1.getContentPane();
			
			con.add(panel1);
			
			frame1.setVisible(true);
		 frame1.setIconImage(new ImageIcon("images/Logo-image.png.").getImage());
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			 //back clicked
			back.addActionListener(new ActionListener() {
				//close frame, open user menu
				public void actionPerformed(ActionEvent e) {
					frame1.dispose();
					mainMenu();
				}
			});
			
			
			
			showpassButton.getModel().addChangeListener(new ChangeListener() {
	              @Override
	                public void stateChanged(ChangeEvent e) {
	                    ButtonModel model = showpassButton.getModel();
	                    if (model.isArmed()) {
	                    	passwordTxt.setEchoChar((char)0);
	                    } else {
	                    	passwordTxt.setEchoChar('•');
	                    }
	                }

					
	            });
	        
			
	
	
			class verify implements ActionListener {

				
				public void actionPerformed(ActionEvent e) {
					
		
				ResultSet rs;
				PreparedStatement st;
				Connection connection;
				
					
					
					//get value of text fields
					String username = usernameTxt.getText().trim(); 
					String password1 =String.valueOf(passwordTxt.getPassword());
				
					
					try { 
						
						connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");    
						st = connection.prepareStatement("SELECT `adminEmail` , `adminPass` FROM `admins` WHERE `adminEmail` = ? AND `adminPass` = ?");
					    st.setString(1, username);
					    st.setString(2, password1);
					    rs = st.executeQuery(); 
					    
					    if(rs.next()) { 
					    	
					    	JOptionPane.showMessageDialog(null,"Login Success, Welcome");
					    	frame1.dispose();
					    	Admin.menu();
					    	
					    } else { 
					    	
					    	JOptionPane.showMessageDialog(null, "Login Failed");
					    	
					    }
							
						
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			
		}
		
		verify.addActionListener(new verify());
		
	}
	
	


 static public void showBooking(){
		
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5,5,5,5));
		panel1.setLayout(null); 
		Font font = new Font("Times New Roman", Font.BOLD,14);
		JLabel label1 = new JLabel(" Please verify your booking to access to book a seat !!");
		label1 .setFont(new Font("Times New Roman", Font.BOLD,16));
		label1 .setForeground(Color.black);
		label1.setBounds(0,20,500,20);
		panel1.add(label1);
		
		
		    /**ImageIcon background=new ImageIcon("images/back5.jpg");
		    Image img=background.getImage();
		    Image temp = img.getScaledInstance(600,600,Image.SCALE_SMOOTH);
		    background=new ImageIcon(temp);
		    JLabel back=new JLabel(background);
		    back.setLayout(null);
		    back.setBounds(0,-150,550,600);  
              */
		
		
		bookingRefTxt.setBounds(188,51,300,20);
		panel1.add(bookingRefTxt);
		bookingRefTxt.setColumns(10);
		
		
		
		surnameTxt .setBounds(188,106,300,20);
		panel1.add(surnameTxt );
		surnameTxt .setColumns(10);
		
		
		
		JLabel bookinRedLB = new JLabel("Booking Reference:");
		bookinRedLB.setBounds(50,60,200,19);
		panel1.add(bookinRedLB); 
		bookinRedLB.setFont(font);
		bookinRedLB.setForeground(Color.black);
		
		JLabel RefExp = new JLabel("Hint: The booking reference e.g 123ABC");
		RefExp.setBounds(188,70,700,20);
		panel1.add(RefExp); 
		RefExp.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		RefExp.setForeground(Color.black);
		
		
		JLabel surnameLB = new JLabel("Surname:");
		surnameLB .setBounds(60,109,200,14);
		panel1.add(surnameLB); 
		surnameLB.setFont(font);
		surnameLB.setForeground(Color.black);

		
		//button declaration
		JButton verify = new JButton("Submit");
		verify.setBounds(131,165,89,23);
		  //add components to panel
			panel1.add(verify);
			
			//frame declaration, initialization
			final JFrame frame1 = new JFrame();
	        frame1.setTitle("User Booking Verification");
	        frame1.setBounds(380, 250, 700, 300);
	         
			
			class userVerify implements ActionListener {

				 
				public void actionPerformed(ActionEvent e) {
				

					ResultSet rs;
					PreparedStatement st;
					Connection connection;
					
						
						//declare variables 
						String bookingRef; 
						String surname; 
						
						//get value of text fields
						bookingRef = bookingRefTxt.getText().trim(); 
						surname= surnameTxt.getText().trim();
					
						
						try { 
							
							connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");    
							st = connection.prepareStatement("SELECT `surname` , `bookingRef`  FROM `passengerbooking` WHERE `surname` = ? AND `bookingRef` = ?");
						    st.setString(1, surname.toUpperCase());
						    st.setString(2, bookingRef.toUpperCase());
						    rs = st.executeQuery(); 
						   
						    if(rs.next()) { 
						    	
						    	JOptionPane.showMessageDialog(null,"Login Success, Welcome");
						    	frame1.dispose();
						    	userVerified = true; 
                                ViewBooking.view();
						   
						    } else { 
						    	
						    	JOptionPane.showMessageDialog(null, "Login Failed");
						    	attempts++;
						    }
							
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
						 if (attempts ==3) {
							 
						    	JLabel error = new JLabel("You have exceeded the maximum login attempts. Please verify your email address :"); 	
						    	
						    	JButton verifyEmail = new JButton("Verify Email"); 
						    	verifyEmail.setBounds(560,220,100,25);
						    	emailTxt.setBounds(40,220,500,20);
						    	error.setBounds(40,150,700,100);
						    	error.setFont( new Font("Serif", Font.BOLD, 14));
						    	error.setForeground(Color.RED);
						    	panel1.add(error); 
						    	panel1.add(emailTxt);
						    	panel1.add(verifyEmail); 
						    	verify.setEnabled(false);
						    	
						    	
						    	verifyEmail.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e)
									{
										
										SendEmail.sendEmail();
										JLabel sent = new JLabel("The booking reference has been sent to your email, please check your mailbox and try again."); 
										
										sent.setBounds(40,200,700,100);
										sent.setFont( new Font("Serif", Font.BOLD, 12));
										panel1.add(sent); 
									
										verify.setEnabled(true);
										verifyEmail.setEnabled(false);
									}
								});	
						    }	
				} 
				
			}
			
			
			
			verify.addActionListener(new userVerify());
			
			//create container               
			Container con = frame1.getContentPane();
			
			con.add(panel1);
			JFrame.setDefaultLookAndFeelDecorated(true);
	        frame1.setIconImage(new ImageIcon("images/Logo-image.png").getImage());
	        frame1.setBackground(Color.BLACK);
		      
			
			frame1.setVisible(true);
			
		
		}	
		

static public void userMenu() { 
    	 

 		JPanel panel1 = new JPanel();
 		panel1.setBorder(new EmptyBorder(5,5,5,5));
 		panel1.setLayout(null); 
 		Font font = new Font("Times New Roman", Font.BOLD,14);
 		JLabel label1 = new JLabel(" Please verify your booking to access to book a seat !!");
 		label1 .setFont(new Font("Times New Roman", Font.BOLD,16));
 		label1 .setForeground(Color.black);
 		label1.setBounds(0,20,500,20);
 		panel1.add(label1);
 		
 		
 		    /**ImageIcon background=new ImageIcon("images/back5.jpg");
 		    Image img=background.getImage();
 		    Image temp = img.getScaledInstance(600,600,Image.SCALE_SMOOTH);
 		    background=new ImageIcon(temp);
 		    JLabel back=new JLabel(background);
 		    back.setLayout(null);
 		    back.setBounds(0,-150,550,600);  
               */
 		
 		
 		bookingRefTxt.setBounds(188,51,300,20);
 		panel1.add(bookingRefTxt);
 		bookingRefTxt.setColumns(10);
 		
 		
 		
 		surnameTxt .setBounds(188,106,300,20);
 		panel1.add(surnameTxt );
 		surnameTxt .setColumns(10);
 		
 		
 		
 		JLabel bookinRedLB = new JLabel("Booking Reference:");
 		bookinRedLB.setBounds(50,60,200,19);
 		panel1.add(bookinRedLB); 
 		bookinRedLB.setFont(font);
 		bookinRedLB.setForeground(Color.black);
 		
 		JLabel RefExp = new JLabel("Hint: The booking reference e.g 123ABC");
 		RefExp.setBounds(188,70,700,20);
 		panel1.add(RefExp); 
 		RefExp.setFont(new Font("Times New Roman", Font.PLAIN, 11));
 		RefExp.setForeground(Color.black);
 		
 		
 		JLabel surnameLB = new JLabel("Surname:");
 		surnameLB .setBounds(60,109,200,14);
 		panel1.add(surnameLB); 
 		surnameLB.setFont(font);
 		surnameLB.setForeground(Color.black);

 		
 		//button declaration
 		JButton verify = new JButton("Submit");
 		verify.setBounds(131,165,89,23);
 		  //add components to panel
 			panel1.add(verify);
 			
 			//frame declaration, initialization
 			final JFrame frame1 = new JFrame();
 	        frame1.setTitle("User Booking Verification");
 	        frame1.setBounds(380, 250, 700, 300);
 	         
		class userVerified implements ActionListener {

			 
			public void actionPerformed(ActionEvent e) {
			

				ResultSet rs;
				PreparedStatement st;
				Connection connection;
				
					
					//declare variables 
					String bookingRef; 
					String surname; 
					
					//get value of text fields
					bookingRef = bookingRefTxt.getText().trim(); 
					surname= surnameTxt.getText().trim();
				
					
					try { 
						
						connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");    
						st = connection.prepareStatement("SELECT `bookingID` ,`surname` , `bookingRef` , `buisnessClass` , `firstClass` FROM `passengerbooking` WHERE `surname` = ? AND `bookingRef` = ?");
					    st.setString(1, surname.toUpperCase());
					    st.setString(2, bookingRef.toUpperCase());
					    rs = st.executeQuery(); 
					   
					    if(rs.next()) { 
					    	
					    	int bId = rs.getInt("bookingID");
					    	
					    	if (idExsist(bId)) {
					    		JOptionPane.showMessageDialog(null,"You have already Booked seats choose `Change Seat` to change your seat");
					    		frame1.dispose();
					    		
					    	}
					    	
					    	else {
					    	JOptionPane.showMessageDialog(null,"Login Success, Welcome");
					    	frame1.dispose();
					    	userVerified = true; 

					    	int business  = rs.getInt("buisnessClass");
					    	int first  = rs.getInt("firstClass");
					    	
					    	if(business == 1) {
					    	
					    		Seats.businessSeats();
					    	}
					    	
					    	 if(first== 1) { 
					    	
					    		Seats.firstSeats(); 
					    	}
					    	
					    	else {
					    
					    	Seats.economySeats();
					    	}
					   
					    	}
					    	
					    } else { 
					    	
					    	JOptionPane.showMessageDialog(null, "Login Failed");
					    	attempts++;
					    }
						
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
					 if (attempts ==3) {
						 
					    	JLabel error = new JLabel("You have exceeded the maximum login attempts. Please verify your email address :"); 	
					    	
					    	JButton verifyEmail = new JButton("Verify Email"); 
					    	verifyEmail.setBounds(560,220,100,25);
					    	emailTxt.setBounds(40,220,500,20);
					    	error.setBounds(40,150,700,100);
					    	error.setFont( new Font("Serif", Font.BOLD, 14));
					    	error.setForeground(Color.RED);
					    	panel1.add(error); 
					    	panel1.add(emailTxt);
					    	panel1.add(verifyEmail); 
					    	verify.setEnabled(false);
					    	
					    	
					    	verifyEmail.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e)
								{
									
									SendEmail.sendEmail();
									JLabel sent = new JLabel("The booking reference has been sent to your email, please check your mailbox and try again."); 
									
									sent.setBounds(40,200,700,100);
									sent.setFont( new Font("Serif", Font.BOLD, 12));
									panel1.add(sent); 
								
									verify.setEnabled(true);
									verifyEmail.setEnabled(false);
								}
							});	
					    }	
			} 
			
		}
		
		
		
		verify.addActionListener(new userVerified());
		
		//create container               
		Container con = frame1.getContentPane();
		
		con.add(panel1);
		JFrame.setDefaultLookAndFeelDecorated(true);
        frame1.setIconImage(new ImageIcon("images/Logo-image.png").getImage());
        frame1.setBackground(Color.BLACK);
	      
		
		frame1.setVisible(true);
		
	
	}	

static public void userMenu1() { 
	 

		JPanel panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5,5,5,5));
		panel1.setLayout(null); 
		Font font = new Font("Times New Roman", Font.BOLD,14);
		JLabel label1 = new JLabel(" Please verify your booking to access to book a seat !!");
		label1 .setFont(new Font("Times New Roman", Font.BOLD,16));
		label1 .setForeground(Color.black);
		label1.setBounds(0,20,500,20);
		panel1.add(label1);
		
		
		    /**ImageIcon background=new ImageIcon("images/back5.jpg");
		    Image img=background.getImage();
		    Image temp = img.getScaledInstance(600,600,Image.SCALE_SMOOTH);
		    background=new ImageIcon(temp);
		    JLabel back=new JLabel(background);
		    back.setLayout(null);
		    back.setBounds(0,-150,550,600);  
           */
		
		
		bookingRefTxt.setBounds(188,51,300,20);
		panel1.add(bookingRefTxt);
		bookingRefTxt.setColumns(10);
		
		
		
		surnameTxt .setBounds(188,106,300,20);
		panel1.add(surnameTxt );
		surnameTxt .setColumns(10);
		
		
		
		JLabel bookinRedLB = new JLabel("Booking Reference:");
		bookinRedLB.setBounds(50,60,200,19);
		panel1.add(bookinRedLB); 
		bookinRedLB.setFont(font);
		bookinRedLB.setForeground(Color.black);
		
		JLabel RefExp = new JLabel("Hint: The booking reference e.g 123ABC");
		RefExp.setBounds(188,70,700,20);
		panel1.add(RefExp); 
		RefExp.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		RefExp.setForeground(Color.black);
		
		
		JLabel surnameLB = new JLabel("Surname:");
		surnameLB .setBounds(60,109,200,14);
		panel1.add(surnameLB); 
		surnameLB.setFont(font);
		surnameLB.setForeground(Color.black);

		
		//button declaration
		JButton verify = new JButton("Submit");
		verify.setBounds(131,165,89,23);
		  //add components to panel
			panel1.add(verify);
			
			//frame declaration, initialization
			final JFrame frame1 = new JFrame();
	        frame1.setTitle("User Booking Verification");
	        frame1.setBounds(380, 250, 700, 300);
	         
	class userVerified implements ActionListener {

		 
		public void actionPerformed(ActionEvent e) {
		

			ResultSet rs;
			PreparedStatement st;
			Connection connection;
			
				
				//declare variables 
				String bookingRef; 
				String surname; 
				
				//get value of text fields
				bookingRef = bookingRefTxt.getText().trim(); 
				surname= surnameTxt.getText().trim();
			
				
				try { 
					
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");    
					st = connection.prepareStatement("SELECT `bookingID` ,`surname` , `bookingRef` , `buisnessClass` , `firstClass` FROM `passengerbooking` WHERE `surname` = ? AND `bookingRef` = ?");
				    st.setString(1, surname.toUpperCase());
				    st.setString(2, bookingRef.toUpperCase());
				    rs = st.executeQuery(); 
				   
				    if(rs.next()) { 
				    	
				    	int bId = rs.getInt("bookingID");
				    	
				    	if (!idExsist(bId)) {
				    		JOptionPane.showMessageDialog(null,"You have not Booked seats choose `Book a Seat` to book your seat");
				    		frame1.dispose();
				    		
				    	}
				    	
				    	else {
				    	JOptionPane.showMessageDialog(null,"Login Success, Welcome");
				    	frame1.dispose();
				    	userVerified = true; 

				    	int business  = rs.getInt("buisnessClass");
				    	int first  = rs.getInt("firstClass");
				    	
				    	if(business == 1) {
				    		ChangeSeat.businessSeats();
				    	}
				    	
				    	 if(first== 1) { 
				    		ChangeSeat.firstSeats(); 
				    	}
				    	
				    	else {
				    	ChangeSeat.economySeats();
				    	}
				   
				    	}
				    	
				    } else { 
				    	
				    	JOptionPane.showMessageDialog(null, "Login Failed");
				    	attempts++;
				    }
					
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
				 if (attempts ==3) {
					 
				    	JLabel error = new JLabel("You have exceeded the maximum login attempts. Please verify your email address :"); 	
				    	
				    	JButton verifyEmail = new JButton("Verify Email"); 
				    	verifyEmail.setBounds(560,220,100,25);
				    	emailTxt.setBounds(40,220,500,20);
				    	error.setBounds(40,150,700,100);
				    	error.setFont( new Font("Serif", Font.BOLD, 14));
				    	error.setForeground(Color.RED);
				    	panel1.add(error); 
				    	panel1.add(emailTxt);
				    	panel1.add(verifyEmail); 
				    	verify.setEnabled(false);
				    	
				    	
				    	verifyEmail.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e)
							{
								
								SendEmail.sendEmail();
								JLabel sent = new JLabel("The booking reference has been sent to your email, please check your mailbox and try again."); 
								
								sent.setBounds(40,200,700,100);
								sent.setFont( new Font("Serif", Font.BOLD, 12));
								panel1.add(sent); 
							
								verify.setEnabled(true);
								verifyEmail.setEnabled(false);
							}
						});	
				    }	
		} 
		
	}
	
	
	
	verify.addActionListener(new userVerified());
	
	//create container               
	Container con = frame1.getContentPane();
	
	con.add(panel1);
	JFrame.setDefaultLookAndFeelDecorated(true);
    frame1.setIconImage(new ImageIcon("images/Logo-image.png").getImage());
    frame1.setBackground(Color.BLACK);
      
	
	frame1.setVisible(true);
	

}	



  public static String getEmail() {
	
		String email1 = null;
		ResultSet rs;
		PreparedStatement st;
		Connection connection;
		String email = emailTxt.getText().trim(); 
		
		try { 
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");    
			st = connection.prepareStatement("SELECT `email` FROM `passenger` WHERE `email` = ? ");
		    st.setString(1, email);
		   
		    rs = st.executeQuery(); 
		   
		    if(rs.next()) { 
		    	email1 = rs.getString("email");
		    	
		    }st.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	return email1;
}
  
  public static String getPEmail() {
		
		String email1 = null;
		ResultSet rs;
		PreparedStatement st;
		Connection connection;
		String bookingRef = bookingRefTxt.getText().trim(); 
		  
		try { 
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");    
			st = connection.prepareStatement("SELECT `email` FROM `passenger` INNER JOIN  `passengerbooking` ON "
					+ "`pbookingID` = `bookingID` WHERE `bookingRef` = ? ");
		    st.setString(1, bookingRef);
		   
		    rs = st.executeQuery(); 
		   
		    if(rs.next()) { 
		    	email1 = rs.getString("email");
		    	
		    }st.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	return email1;
}


  public static String forgotRef() {
		
		String email1 = null;
		ResultSet rs;
		PreparedStatement st;
		Connection connection;
		String email = emailTxt.getText().trim(); 
		
		try { 
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");    
			st = connection.prepareStatement("SELECT `bookingRef` FROM `passengerbooking` INNER JOIN `passenger`  WHERE `bookingID` = `pbookingID` AND `email` =? ");
		    st.setString(1, email);
		   
		    rs = st.executeQuery(); 
		   
		    if(rs.next()) { 
		    	email1 = rs.getString("bookingRef");
		    	
		    } st.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	return email1;
    }

  
    public static String getReference() {
    	return bookingRefTxt.getText().trim(); 
    }
    
    public static String getSurname() {
    	return surnameTxt.getText().trim();
    }
    
    public static String seatType() {
    	
    	String type = null ; 
    	 if (SeatAssignment.firstClass() ==1) {
    		 type = "First Class"; 
   	  }else if (SeatAssignment.businessClass() == 1 ) {
   		     type ="Business Class " ;
   	  }else { 
   		     type ="Economy Seat" ; 
   	  }
		
		return type; 
		
    	
    }
    
    public static int getNumberOfSeats() {
	try {
		
		   PreparedStatement st1;
		   Connection connection;
		   ResultSet rs;
		   String bookingRef = bookingRefTxt.getText().trim(); 
		  
          
      		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
      		st1 = connection.prepareStatement("SELECT  `passengersNum` FROM `passengerbooking` WHERE  `bookingRef` = ?");
      		st1.setString(1, bookingRef); 
      		rs = st1.executeQuery(); 
      		
      		while (rs.next())
            {
      			numOfSeats = rs.getInt("passengersNum");
            } st1.close();
            
		}catch (SQLException e1) {
			
			System.out.println(e1.getMessage());
		} 
	
	return numOfSeats; 
    }
    
    public static int firstClass() {
    	int first = 0;
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   ResultSet rs;
    		   String bookingRef = bookingRefTxt.getText().trim(); 
    			  
              
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("SELECT  `firstClass` FROM `passengerbooking` WHERE  `bookingRef` = ?");
          		st1.setString(1, bookingRef); 
          		rs = st1.executeQuery(); 
          		
          		while (rs.next())
                {
          			first = rs.getInt("firstClass");
                } st1.close();
                
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
    	return first ; 
        }
    
    
    public static int businessClass() {
    	int business = 0;
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   ResultSet rs;
    		   String bookingRef = bookingRefTxt.getText().trim(); 
    			  
              
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("SELECT  `buisnessClass` FROM `passengerbooking` WHERE  `bookingRef` = ?");
          		st1.setString(1, bookingRef); 
          		rs = st1.executeQuery(); 
          		
          		while (rs.next())
                {
          			 business = rs.getInt("buisnessClass");
                } st1.close();
                
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
    	return business ; 
        }
    
   
    public static void seatTaken(String seatID) {
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   int rs;
    		   
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("update seats set taken = ? where seatNum = ? ");
          		st1.setInt(1, 1);
          		
          		st1.setString(2, seatID);
          		
          		rs = st1.executeUpdate(); 
          		st1.close();
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
        }
    
    public static void insertBookingID(int bID, String seatID) {
    	
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   int rs;
    		   
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("update seats set IDbooking= ? where seatNum = ? ");
          		st1.setInt(1, bID);
          		st1.setString(2, seatID);
          		
          		
          		rs = st1.executeUpdate(); 
          		st1.close();
          		
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
        }
    
    
    public static int isTaken(String seatID ) { 
    	int t = 0; 
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   ResultSet rs;
    		   
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("SELECT  `taken` FROM `seats` WHERE `seatNum` = ? ");
          		st1.setString(1, seatID);
          		rs = st1.executeQuery(); 
          		
          		while (rs.next())
                {
          			t= rs.getInt("taken");
                } st1.close();
                
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
    	return t; 
	 
    }
    
    public static void seatNotTaken(String seatID) {
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   int rs;
    		   
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("update seats set taken = ? where seatNum = ? ");
          		st1.setInt(1, 0);
          		
          		st1.setString(2, seatID);
          		
          		rs = st1.executeUpdate(); 
          		st1.close();
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
        }
  
  public static void deleteBookingID( String seatID) {
    	
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   int rs;
    		   
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("update seats set IDbooking= ? where seatNum = ? ");
          		st1.setInt(1, 0);
          		st1.setString(2, seatID);
          		
          		rs = st1.executeUpdate(); 
          		st1.close();
          		
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
        }
  
 
 public static int seatID() {
    	
    	int seatNum = 0; 
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   ResultSet rs;
    		   
              
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("SELECT  `seatID` FROM `seats` ");
          		
          		rs = st1.executeQuery(); 
          		
          		while (rs.next())
                {
          			seatNum = rs.getInt("seatID");
                } st1.close();
                
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
    	return seatNum ; 
        }
 public static int getBookingId() {
 	
 	int bID = 0; 
 	try {
 		
 		   PreparedStatement st1;
 		   Connection connection;
 		   ResultSet rs;
 		   String bookingRef = bookingRefTxt.getText().trim(); 
		   
       		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
       		st1 = connection.prepareStatement("SELECT  `bookingID` FROM `passengerbooking` WHERE `bookingRef` = ?" );
       		st1.setString(1, bookingRef); 
       		
       		rs = st1.executeQuery(); 
       		
       		while (rs.next())
             {
       			bID = rs.getInt("bookingID");
             } st1.close();
             
 		}catch (SQLException e1) {
 			
 			System.out.println(e1.getMessage());
 		} 
 	
 	return bID ; 
     }
 
    public static String BseatNum() {
    	
    	String seatNum = null; 
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   ResultSet rs;
    		   
              
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("SELECT  `seatNum` FROM `seats` WHERE `seatID` BETWEEN 30 AND 85");
          		
          		rs = st1.executeQuery(); 
          		
          		while (rs.next())
                {
          			seatNum = rs.getString("seatNum");
                } st1.close();
                
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
    	return seatNum ; 
        }
    
 public static String FseatNum() {
    	
    	String seatNum = null; 
    	try {
    		
    		   PreparedStatement st1;
    		   Connection connection;
    		   ResultSet rs;
    		   
              
          		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
          		st1 = connection.prepareStatement("SELECT  `seatNum` FROM `seats` WHERE `seatID` BETWEEN 0 AND 31");
          		
          		rs = st1.executeQuery(); 
          		
          		while (rs.next())
                {
          			seatNum = rs.getString("seatNum");
                } st1.close();
                
    		}catch (SQLException e1) {
    			
    			System.out.println(e1.getMessage());
    		} 
    	
    	return seatNum ; 
        }
    
 public static String EseatNum() {
 	
 	String seatNum = null; 
 	try {
 		
 		   PreparedStatement st1;
 		   Connection connection;
 		   ResultSet rs;
 		   
           
       		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
       		st1 = connection.prepareStatement("SELECT  `seatNum` FROM `seats` WHERE `seatID` > 84 ");
       		
       		rs = st1.executeQuery(); 
       		
       		while (rs.next())
             {
       			seatNum = rs.getString("seatNum");
             } st1.close();
             
 		}catch (SQLException e1) {
 			
 			System.out.println(e1.getMessage());
 		} 
 	
 	return seatNum ; 
     }
   
   static  public String getArivAirport() { 

    	
    	String arivAirport = null;
		
	  try {
			
		   PreparedStatement st1;
		   Connection connection;
		   ResultSet rs;
		  String bookingRef = bookingRefTxt.getText().trim(); 
		   
         
     		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
     		st1 = connection.prepareStatement("SELECT `arivAirport` FROM `flight` INNER JOIN `passengerbooking` ON  "
     				+ "`flightID` = `flightCode` WHERE `bookingRef` = ? ");
     		st1.setString(1, bookingRef); 
     		rs = st1.executeQuery(); 
     		
     		while (rs.next())
           {
     			arivAirport= rs.getString("arivAirport");	
           } st1.close();
           
           
		}catch (SQLException e1) { 
			
			System.out.println(e1.getMessage());
		}
	  

	return arivAirport;
	  
  }
   
   static  public String getArivDate() { 

   	
   	String depAirport = null;
		
	  try {
			
		   PreparedStatement st1;
		   Connection connection;
		   ResultSet rs;
		   String bookingRef = bookingRefTxt.getText().trim(); 
		   
        
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
    		st1 = connection.prepareStatement("SELECT `arivDate` FROM `flight` INNER JOIN `passengerbooking` ON  "
    				+ "`flightID` = `flightCode` WHERE `bookingRef` = ? ");
    		st1.setString(1, bookingRef); 
    		rs = st1.executeQuery(); 
    		
    		while (rs.next())
          {
    			depAirport= rs.getString("arivDate");	
          } st1.close();
          
          
		}catch (SQLException e1) { 
			
			System.out.println(e1.getMessage());
		}
	  

	return depAirport;
	  
 }
   static  public String getArivTime() { 

   	
   	String depAirport = null;
		
	  try {
			
		   PreparedStatement st1;
		   Connection connection;
		   ResultSet rs;
		   String bookingRef = bookingRefTxt.getText().trim(); 
		   
        
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
    		st1 = connection.prepareStatement("SELECT `arivTime` FROM `flight` INNER JOIN `passengerbooking` ON  "
    				+ "`flightID` = `flightCode` WHERE `bookingRef` = ? ");
    		st1.setString(1, bookingRef); 
    		rs = st1.executeQuery(); 
    		
    		while (rs.next())
          {
    			depAirport= rs.getString("arivTime");	
          } st1.close();
          
          
		}catch (SQLException e1) { 
			
			System.out.println(e1.getMessage());
		}
	  

	return depAirport;
	  
 }
   static  public String getDepDate() { 

   	
   	String depAirport = null;
		
	  try {
			
		   PreparedStatement st1;
		   Connection connection;
		   ResultSet rs;
		   String bookingRef = bookingRefTxt.getText().trim(); 
		   
        
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
    		st1 = connection.prepareStatement("SELECT `depDate` FROM `flight` INNER JOIN `passengerbooking` ON  "
    				+ "`flightID` = `flightCode` WHERE `bookingRef` = ? ");
    		st1.setString(1, bookingRef); 
    		rs = st1.executeQuery(); 
    		
    		while (rs.next())
          {
    			depAirport= rs.getString("depDate");	
          } st1.close();
          
          
		}catch (SQLException e1) { 
			
			System.out.println(e1.getMessage());
		}
	  

	return depAirport;
	  
 }
   static  public String getDepTime() { 

    	
    	String depAirport = null;
		
	  try {
			
		   PreparedStatement st1;
		   Connection connection;
		   ResultSet rs;
		   String bookingRef = bookingRefTxt.getText().trim(); 
		   
         
     		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
     		st1 = connection.prepareStatement("SELECT `depTime` FROM `flight` INNER JOIN `passengerbooking` ON  "
     				+ "`flightID` = `flightCode` WHERE `bookingRef` = ? ");
     		st1.setString(1, bookingRef); 
     		rs = st1.executeQuery(); 
     		
     		while (rs.next())
           {
     			depAirport= rs.getString("depTime");	
           } st1.close();
           
           
		}catch (SQLException e1) { 
			
			System.out.println(e1.getMessage());
		}
	  

	return depAirport;
	  
  }
   
   static  public boolean idExsist(int bID) { 
	   	
	   	boolean exsist = false;
			
		  try {
				
			   PreparedStatement st1;
			   Connection connection;
			   ResultSet rs;
			  
	        
	    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
	    		st1 = connection.prepareStatement("SELECT `IDbooking` FROM `seats` WHERE `IDbooking` = ? ");
	    		st1.setInt(1, bID); 
	    		rs = st1.executeQuery(); 
	    		
	    		while (rs.next())
	          {
	    			int idBooking= rs.getInt("IDbooking");	
	    			if (bID == idBooking) {
	    				exsist = true; 
	    			}else
	    			{
	    				exsist = false; 	
	    			}
	          } st1.close();
	          
	          
			}catch (SQLException e1) { 
				
				System.out.println(e1.getMessage());
			}
		  

		return exsist;
		  
	 }
   
   static  public String getDepAirport() { 
   	
   	String depAirport = null;
		
	  try {
			
		   PreparedStatement st1;
		   Connection connection;
		   ResultSet rs;
		   String bookingRef = bookingRefTxt.getText().trim(); 
		   
        
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
    		st1 = connection.prepareStatement("SELECT `depAirport` FROM `flight` INNER JOIN `passengerbooking` ON  "
    				+ "`flightID` = `flightCode` WHERE `bookingRef` = ? ");
    		st1.setString(1, bookingRef); 
    		rs = st1.executeQuery(); 
    		
    		while (rs.next())
          {
    			depAirport= rs.getString("depAirport");	
          } st1.close();
          
          
		}catch (SQLException e1) { 
			
			System.out.println(e1.getMessage());
		}
	  

	return depAirport;
	  
 }

    static  public String getFlightID() { 
    	
    	String flightID = null;
		
	  try {
			
		   PreparedStatement st1;
		   Connection connection;
		   ResultSet rs;
		   String bookingRef = bookingRefTxt.getText().trim(); 
		   
         
     		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
     		st1 = connection.prepareStatement("SELECT `flightID` FROM `flight` INNER JOIN `passengerbooking` ON  "
     				+ "`flightID` = `flightCode` WHERE `bookingRef` = ? ");
     		st1.setString(1, bookingRef); 
     		rs = st1.executeQuery(); 
     		
     		while (rs.next())
           {
     			 flightID = rs.getString("flightID");	
           } st1.close();
           
           
		}catch (SQLException e1) { 
			
			System.out.println(e1.getMessage());
		}
	  

	return flightID;
	  
  }

  static  public ArrayList<String> getPassengerNames() { 
    	
	 
	  try {
			
		   PreparedStatement st1;
		   Connection connection;
		   ResultSet rs;
		   String bookingRef = bookingRefTxt.getText().trim(); 
		  
     		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");
     		st1 = connection.prepareStatement(" SELECT seatNum  FROM seats" + 
     				                     " INNER JOIN passengerbooking "
     				                     + "ON IDbooking = bookingID" + 
     				                       " WHERE passengerbooking.bookingRef = ? ");
     		st1.setString(1, bookingRef); 
     		rs = st1.executeQuery(); 
     		
     		while (rs.next())
           {
     			String passengerName = rs.getString("seatNum");
     			 resultList.add(passengerName);
     			 
           } st1.close();
           
           
		}catch (SQLException e1) { 
			
			System.out.println(e1.getMessage());
		}
	  
	  System.out.print(resultList.toString());
		
	return resultList;
	  
  }

  
	
}