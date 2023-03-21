package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener {
    
    JTextField tfname, tfaddress, tfunits, tfstate, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblname, labeladdress;
    Choice meternumber, cmonth;
    CalculateBill(){
        setSize(1000, 500);
        setLocation(300, 160);
        
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);
        
        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(90, 10, 280, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setForeground(Color.BLACK);
        lblmeternumber.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblmeternumber.setBounds(40, 80, 100, 20);
        p.add(lblmeternumber);
        
        meternumber = new Choice();
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
              meternumber.add(rs.getString("meter_no"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        meternumber.setBounds(220, 80, 200, 30);
        p.add(meternumber);
       // tfname = new JTextField();
       // tfname.setBounds(220, 80, 200, 30);
       // p.add(tfname);
        
        
        JLabel lblmeterno = new JLabel("Name");
        lblmeterno.setForeground(Color.BLACK);
        lblmeterno.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblmeterno.setBounds(40, 120, 100, 20);
        p.add(lblmeterno);
        
        lblname = new JLabel("");
        lblname.setBounds(220, 120, 220, 30);
        p.add(lblname);
        
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setForeground(Color.BLACK);
        lbladdress.setFont( new Font("Tahoma", Font.BOLD, 12));
        lbladdress.setBounds(40, 160, 100, 20);
        p.add(lbladdress);

        labeladdress = new JLabel();
        labeladdress.setBounds(220, 160, 200, 30);
        p.add(labeladdress);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meternumber.getSelectedItem()+"'");
            while(rs.next()){
                lblname.setText(rs.getString("name"));
                labeladdress.setText(rs.getString("address"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        meternumber.addItemListener(new ItemListener(){
              public void itemStateChanged(ItemEvent ie){
                  try{
                      Conn c = new Conn();
                      ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meternumber.getSelectedItem()+"'");
                  while(rs.next()){
                       lblname.setText(rs.getString("name"));
                       labeladdress.setText(rs.getString("address"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
              } 
        });
        
        
        JLabel lblcity = new JLabel("Units Consumed");
        lblcity.setForeground(Color.BLACK);
        lblcity.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblcity.setBounds(40, 200, 100, 20);
        p.add(lblcity);

        tfunits = new JTextField();
        tfunits.setBounds(220, 200, 200, 30);
        p.add(tfunits);
        
        JLabel lblstate = new JLabel("Month");
        lblstate.setForeground(Color.BLACK);
        lblstate.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblstate.setBounds(40, 240, 100, 20);
        p.add(lblstate);

        cmonth = new Choice();
        cmonth.setBounds(220, 240, 200, 30);
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
        
        p.add(cmonth);
        
       
        
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icon/c2.png"));
        Image img = i.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        next = new JButton("Submit", new ImageIcon(img));
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
            String meter = meternumber.getSelectedItem();
            String units = tfunits.getText();
            String month = cmonth.getSelectedItem();
            
            int totalbill = 0;
            int unit_consumed = Integer.parseInt(units);

            String query = "select * from tax";
        
            
            try {
                Conn c = new Conn();
                ResultSet rs =  c.s.executeQuery(query);
                
                while(rs.next()){
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("meter_rent"));
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("service_charge"));
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("service_tax"));
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("fixed_tax"));
                
                }
               
            } catch(Exception e){
                e.printStackTrace();
            }
            String query2 = "insert into bill values('"+meter+"', '"+month+"', '"+units+"', '"+totalbill+"', 'Not Paid')";
            try {
                 Conn c = new Conn();
                 c.s.executeUpdate(query2);
                 
                 JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                 setVisible(false);
            }catch(Exception e){
            e.printStackTrace();
            }
        } else {
            setVisible(false);
            
            new Project();
        }
    }
    
    
    public static void main(String[] args){
        new CalculateBill();
    }
}
