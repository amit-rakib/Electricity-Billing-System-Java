package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener {
    
    JTextField tfname, tfaddress, tfcity, tfstate, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblmeter;
    NewCustomer(){
        setSize(1000, 500);
        setLocation(300, 160);
        
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);
        
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        
        
        JLabel lblname = new JLabel("Customer Name");
        lblname.setForeground(Color.BLACK);
        lblname.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblname.setBounds(40, 80, 100, 20);
        p.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(220, 80, 200, 30);
        p.add(tfname);
        
        
        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setForeground(Color.BLACK);
        lblmeterno.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblmeterno.setBounds(40, 120, 100, 20);
        p.add(lblmeterno);
        
        lblmeter = new JLabel("");
        lblmeter.setBounds(220, 120, 220, 30);
        p.add(lblmeter);
        
        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        lblmeter.setText("" + Math.abs(number));
        
        
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
        
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icon/c2.png"));
        Image img = i.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        next = new JButton("Next", new ImageIcon(img));
        next.setBackground(new Color(0,128,255));
        next.setForeground(Color.WHITE);
        next.setBounds(80, 390, 110, 28);
        next.addActionListener(this);
        p.add(next);
        
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
        if (ae.getSource() == next){
            String name = tfname.getText();
            String meter = lblmeter.getText();
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();
            
            String query1 = "insert into customer values('"+name+"', '"+meter+"', '"+address+"', '"+city+"', '"+state+"', '"+email+"', '"+phone+"')";
            String query2 = "insert into login values('"+meter+"', '', '"+name+"', '', '')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);
                
                // new frame
                new MeterInfo(meter);
            } catch(Exception e){
                e.printStackTrace();
            }
            
        } else {
            setVisible(false);
            
            new Project();
        }
    }
    
    
    public static void main(String[] args){
        new NewCustomer();
    }
}
