package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class ViewInformation extends JFrame implements ActionListener{
    
 
    JButton cancel;
    JLabel meternumber, name, address, city, state, email, phone;
    ViewInformation( String meter){
        setSize(1000, 500);
        setLocation(300, 160);
        
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        add(p);
        
        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(120, 10, 360, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        
        
        JLabel lblname = new JLabel("Name");
        lblname.setForeground(Color.BLACK);
        lblname.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblname.setBounds(40, 80, 100, 20);
        p.add(lblname);

        name = new JLabel("");
        name.setBounds(220, 80, 200, 30);
        p.add(name);
        
        
        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setForeground(Color.BLACK);
        lblmeterno.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblmeterno.setBounds(40, 120, 100, 20);
        p.add(lblmeterno);
        
        meternumber = new JLabel("");
        meternumber.setBounds(220, 120, 220, 30);
        p.add(meternumber);
        
        
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setForeground(Color.BLACK);
        lbladdress.setFont( new Font("Tahoma", Font.BOLD, 12));
        lbladdress.setBounds(40, 160, 100, 20);
        p.add(lbladdress);

        address = new JLabel("");
        address.setBounds(220, 160, 200, 30);
        p.add(address);
        
        
        JLabel lblcity = new JLabel("City");
        lblcity.setForeground(Color.BLACK);
        lblcity.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblcity.setBounds(40, 200, 100, 20);
        p.add(lblcity);

        city = new JLabel("");
        city.setBounds(220, 200, 200, 30);
        p.add(city);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setForeground(Color.BLACK);
        lblstate.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblstate.setBounds(40, 240, 100, 20);
        p.add(lblstate);

        state = new JLabel("");
        state.setBounds(220, 240, 200, 30);
        p.add(state);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setForeground(Color.BLACK);
        lblemail.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblemail.setBounds(40, 280, 100, 20);
        p.add(lblemail);

        email = new JLabel("");
        email.setBounds(220, 280, 200, 30);
        p.add(email);
        
        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setForeground(Color.BLACK);
        lblphone.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblphone.setBounds(40, 320, 100, 20);
        p.add(lblphone);

        phone = new JLabel("");
        phone.setBounds(220, 320, 200, 30);
        p.add(phone);
        
        try{
           Conn c = new Conn();
           ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
           while(rs.next()){
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        ImageIcon im = new ImageIcon(ClassLoader.getSystemResource("icon/back1.png"));
        Image imgg = im.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel", new ImageIcon(imgg));
        cancel.setBackground(new Color(0,128,255));
        cancel.setForeground(Color.WHITE);
       
        cancel.setBounds(380, 390, 110, 28);
        cancel.addActionListener(this);
        p.add(cancel);
        
        setLayout(new BorderLayout());
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/next.jpg"));
        Image i2 = i1.getImage().getScaledInstance(240, 280, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }  
    
    public static void main(String[] args){
        new ViewInformation("");
    }
}

