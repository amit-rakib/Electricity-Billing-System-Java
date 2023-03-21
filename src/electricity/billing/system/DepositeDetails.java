
package electricity.billing.system;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositeDetails extends JFrame implements ActionListener{
    
    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;
      
    DepositeDetails(){
        super("Deposite Details");
        
        setSize(1000, 500);
        setLocation(300, 160);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel lblmeternumber = new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(20, 20, 150, 20);
        add(lblmeternumber);
        
        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(380, 20, 100, 20);
        add(lblmonth);
        
        
        meternumber = new Choice();
        meternumber.setBounds(180, 20, 150, 20);
        add(meternumber);
        
        try {
            
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            
            while(rs.next()){
                meternumber.add(rs.getString("meter_no"));
            }
            
        }catch(Exception e){
        e.printStackTrace();
        
        }
        cmonth = new Choice();
        cmonth.setBounds(500, 20, 150, 20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        table = new JTable();
        try{
           Conn c = new Conn();
           ResultSet rs = c.s.executeQuery("select * from bill");
           
          table.setModel(DbUtils.resultSetToTableModel(rs));
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 600);
        add(sp);
        
        
        search = new JButton("Search");
        search.setBackground(new Color(0,128,255));
        search.setForeground(Color.WHITE);
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBackground(new Color(0,128,255));
        print.setForeground(Color.WHITE);
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(370, 320, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(700, 70, 300, 250);
        add(image);
        
        
        
        
        
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
           String query = "select * from bill where meter_no = '"+meternumber.getSelectedItem()+"' and month = '"+cmonth.getSelectedItem()+"'" ;
           
           try{
               Conn c = new Conn();
               ResultSet rs = c.s.executeQuery(query);
               table.setModel(DbUtils.resultSetToTableModel(rs));
           }catch(Exception e){
               e.printStackTrace();
           }
        } else {
            try{
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args){
        new DepositeDetails();
    }
}
