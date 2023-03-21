package electricity.billing.system;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class BillDetails extends JFrame implements ActionListener{
    
  
    JTable table;
    JButton print;
      
    BillDetails( String meter){
        super("Bill Details");
        
        setSize(1000, 500);
        setLocation(300, 160);
        
        table = new JTable();
        try{
           Conn c = new Conn();
           
           String query = "select * from bill where meter_no = '"+meter+"'";
           
           ResultSet rs = c.s.executeQuery(query);
           
          table.setModel(DbUtils.resultSetToTableModel(rs));
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);
        add(sp);
        
        
       
        
        print = new JButton("Print");  
        print.setBackground(new Color(0,128,255));
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print, "South");
        
        
        
        
        
        
        
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
       
            try{
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        
    }
    
    public static void main(String[] args){
        new BillDetails("");
    }
}