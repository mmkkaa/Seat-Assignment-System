import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Help extends SeatAssignment  {
	static ImageIcon face = new ImageIcon("images/face.png");
	static ImageIcon twitter = new ImageIcon("images/tweet.png");
	static Font font = new Font("Serif", Font.BOLD, 14);
    static Font fontSmall = new Font("Times New Roma" , Font.PLAIN, 12);
	
	static public void getHelp() { 
	 final JFrame frame = new JFrame("Admin"); 
	    frame.setSize(new Dimension(400, 200));
	    frame.setBounds(400, 200, 400, 200);
	    frame.setBackground(Color.WHITE);
	    
	    
	    JLabel l1 = new JLabel("Contact us:");
	    JLabel contactNum = new JLabel ("Contact Number: +4474559873");
	    JLabel email = new JLabel ("Email Address: 123@liverpool.ac.uk");
	    JLabel FAX = new JLabel ("Fax: +447123456");
	    JLabel faceBook = new JLabel("seatSystem11    ||");
	    JLabel Twitter = new JLabel("@seatSystem11");
	    JLabel faceIcon = new JLabel(face);
	    JLabel TweetIcon = new JLabel(twitter);
	     
	    l1.setFont(new Font("Andalus" , Font.BOLD, 18));
	    contactNum.setFont(font);
	    email.setFont(font);
	    FAX.setFont(font);
	    faceBook.setFont(fontSmall);
	    faceBook.setForeground(Color.GRAY);
	    Twitter.setFont(fontSmall);
	    Twitter.setForeground(Color.GRAY);
	    
	    l1.setBounds(140,5,200,30);
	    contactNum.setBounds(0,40,400,30);
	    email.setBounds(0,60,400,30);
	    FAX.setBounds(0,80,200,30);
	    faceBook.setBounds(100,120,100,30);
	    Twitter.setBounds(220,120,100,30);
	    faceIcon.setBounds(60,120,40,40);
	    TweetIcon.setBounds(180,120,40,40);
	    
	    
	    
	    frame.add(l1);
	    frame.add(contactNum); 
	    frame.add(email); 
	    frame.add(FAX); 
	    frame.add(faceBook); 
	    frame.add(Twitter); 
	    frame.add(faceIcon); 
	    frame.add(TweetIcon); 
	    
	   
	    
	    frame.setLayout(new BorderLayout()); 
       
	 //Ask for window decorations provided by the look and feel.
     JFrame.setDefaultLookAndFeelDecorated(true);
    //Set the frame icon to an image loaded from a file.
    frame.setIconImage(new ImageIcon("images/Logo-image.png.").getImage());
     
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
}
