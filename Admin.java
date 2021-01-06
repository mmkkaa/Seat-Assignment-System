import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Admin extends SeatAssignment {
	
	static DatabaseConnection connect = new DatabaseConnection(
			"jdbc:mysql://localhost/cs157a", "com.mysql.jdbc.Driver", "root",
			"root");
	
	
	static public void menu() {
		
		JTable table;
		String[] columnNames = {"Seat ID" , "Seat", "Taken", "Booking ID"};

	    final JFrame frame = new JFrame("Admin"); 
	    frame.setSize(new Dimension(500, 550));
	    
	    JPanel panel1 = new JPanel();
	    panel1.setBorder(BorderFactory.createTitledBorder("Update seats"));
	    panel1.setBounds(200, 100, 100, 100);
	    
	    frame.setLayout(new BorderLayout()); 
	    frame.setBackground(Color.gray);
	  //TableModel tm = new TableModel();
	  DefaultTableModel model = new DefaultTableModel();
	  model.setColumnIdentifiers(columnNames);
	  //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames()); 
	  //table = new JTable(model);
	  table = new JTable();
	  table.setModel(model); 
	  table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	  table.setFillsViewportHeight(true);
	  JScrollPane scroll = new JScrollPane(table);
	  scroll.setHorizontalScrollBarPolicy(
	  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  scroll.setVerticalScrollBarPolicy(
	  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
	  
	  JButton submit = new JButton("Submit Changes");
	  submit.setBounds(250,450 , 100, 30);
	  int seatId= 0;
	  String seatNum= "";
	  int taken = 0;
	  int bookingID = 0; 
	  
	  
	  try {
	   
		  
		    ResultSet rs;
			
			Connection connection;
			
		
	  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");  
	  String sql = "select * from seats ";
       PreparedStatement ps = connection.prepareStatement(sql);
	   rs = ps.executeQuery();
	  
	  while(rs.next())
	  {
	  seatId = rs.getInt("seatID");
	  seatNum = rs.getString("seatNum");
	  taken = rs.getInt("taken");
	  bookingID  = rs.getInt("IDbooking"); 
	  
	  model.addRow(new Object[]{seatId, seatNum, taken, bookingID});
	   
	  }
	  
	  }
	catch(Exception ex)
	{
	JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
	JOptionPane.ERROR_MESSAGE);
	}
	  
	  //back clicked
		submit.addActionListener(new ActionListener() {
			//close frame, open user menu
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
	  
	  //tablemodellisten for editing database
	    table.getModel().addTableModelListener(new TableModelListener(){
	            public void tableChanged(TableModelEvent e)
	            {
	                    int row = e.getFirstRow();
	                    int column = e.getColumn();
	                    
	                   
	                    if(column == 0 || column ==1)
	                    	
	                            JOptionPane.showMessageDialog(null, "Warning: Cannot change seat ID or seat Num");
	                    else
	                    {
	                            TableModel model = (TableModel)e.getSource();                                
	                            String columnName = model.getColumnName(column);
	                               
	                            Object data1 = model.getValueAt(row, column);
	                                    
	                            String  seatId =  table.getValueAt(row, 1).toString();
	                             
	                                    try {
											editSeat(columnName, data1 , seatId);
											
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
	                            
	                    }
	                            
	                    
	            }
	    });
	  

	panel1.add(scroll);
	
	panel1.add(submit);
	  
	    Container con  = frame.getContentPane();
	    con.add(panel1);
		 //Ask for window decorations provided by the look and feel.
	     JFrame.setDefaultLookAndFeelDecorated(true);
	    //Set the frame icon to an image loaded from a file.
	    frame.setIconImage(new ImageIcon("images/Logo-image.png.").getImage());
	     
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	
	
	public static void editSeat(String columnName,  Object data ,  String seatID) throws SQLException{
		
		PreparedStatement st;
		Connection connection;
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs157a","root","root");    
		st = connection.prepareStatement("UPDATE seats SET " + columnName + " = \"" + data + "\" WHERE seatNum = \"" + seatID + "\"");
	   
	    int rs = st.executeUpdate(); 
	    
        
}

	

	  
	
}
