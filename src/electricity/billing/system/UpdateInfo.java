package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class UpdateInfo extends JFrame implements ActionListener{
    
 
    JButton update, cancel;
    JTextField tfaddress, tfcity, tfstate, tfemail, tfphone;
    String meter;
    UpdateInfo( String meter){
        this.meter = meter;
        setSize(1000, 500);
        setLocation(300, 160);
        
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        add(p);
        
        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(120, 10, 390, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        
        
        JLabel lblname = new JLabel("Name");
        lblname.setForeground(Color.BLACK);
        lblname.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblname.setBounds(40, 80, 100, 20);
        p.add(lblname);

        JLabel name = new JLabel("");
        name.setBounds(220, 80, 200, 30);
        p.add(name);
        
        
        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setForeground(Color.BLACK);
        lblmeterno.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblmeterno.setBounds(40, 120, 100, 20);
        p.add(lblmeterno);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(220, 120, 220, 30);
        p.add(meternumber);
        
        
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setForeground(Color.BLACK);
        lbladdress.setFont( new Font("Tahoma", Font.BOLD, 12));
        lbladdress.setBounds(40, 160, 100, 20);
        p.add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 160, 200, 30);
        p.add(tfaddress);
        
        
        JLabel lblcity = new JLabel("City");
        lblcity.setForeground(Color.BLACK);
        lblcity.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblcity.setBounds(40, 200, 100, 20);
        p.add(lblcity);

        tfcity = new JTextField();
        tfcity.setBounds(220, 200, 200, 30);
        p.add(tfcity);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setForeground(Color.BLACK);
        lblstate.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblstate.setBounds(40, 240, 100, 20);
        p.add(lblstate);

        tfstate = new JTextField();
        tfstate.setBounds(220, 240, 200, 30);
        p.add(tfstate);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setForeground(Color.BLACK);
        lblemail.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblemail.setBounds(40, 280, 100, 20);
        p.add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(220, 280, 200, 30);
        p.add(tfemail);
        
        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setForeground(Color.BLACK);
        lblphone.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblphone.setBounds(40, 320, 100, 20);
        p.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 320, 200, 30);
        p.add(tfphone);
        
        try{
           Conn c = new Conn();
           ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
           while(rs.next()){
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfemail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icon/c2.png"));
        Image img = i.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        update = new JButton("Update", new ImageIcon(img));
        update.setBackground(new Color(0,128,255));
        update.setForeground(Color.WHITE);
        update.setBounds(80, 390, 110, 28);
        update.addActionListener(this);
        p.add(update);
        
        ImageIcon im = new ImageIcon(ClassLoader.getSystemResource("icon/back1.png"));
        Image imgg = im.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel", new ImageIcon(imgg));
        cancel.setBackground(new Color(0,128,255));
        cancel.setForeground(Color.WHITE);
       
        cancel.setBounds(280, 390, 110, 28);
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
        if(ae.getSource() == update){
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', email = '"+email+"', phone = '"+phone+"' where meter_no = '"+meter+"'");
                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            } catch(Exception e){
                e.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }  
    
    public static void main(String[] args){
        new UpdateInfo("");
    }
}

