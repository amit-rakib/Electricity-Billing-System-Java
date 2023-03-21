package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener {
    
    JTextField tfname, tfaddress, tfunits, tfstate, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblname, labeladdress;
    Choice meternumber, cmonth;
    String meter;
    PayBill( String meter){
        this.meter = meter;
        setSize(1000, 500);
        setLocation(300, 160);
        
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);
        
        JLabel heading = new JLabel("Electricity Bill");
        heading.setBounds(90, 10, 280, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setForeground(Color.BLACK);
        lblmeternumber.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblmeternumber.setBounds(40, 80, 100, 20);
        p.add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(220, 80, 220, 30);
        p.add(meternumber);
       

        JLabel lblname = new JLabel("Name");
        lblname.setForeground(Color.BLACK);
        lblname.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblname.setBounds(40, 120, 100, 20);
        p.add(lblname);
        
        JLabel labelname = new JLabel("");
        labelname.setBounds(220, 120, 220, 30);
        p.add(labelname);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setForeground(Color.BLACK);
        lblmonth.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblmonth.setBounds(40, 160, 100, 20);
        p.add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(220, 160, 200, 30);
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
        
        JLabel lblunits = new JLabel("Units");
        lblunits.setForeground(Color.BLACK);
        lblunits.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblunits.setBounds(40, 200, 100, 20);
        p.add(lblunits);

        JLabel labelunits = new JLabel();
        labelunits.setBounds(220, 200, 200, 30);
        p.add(labelunits);
        
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setForeground(Color.BLACK);
        lbltotalbill.setFont( new Font("Tahoma", Font.BOLD, 12));
        lbltotalbill.setBounds(40, 240, 100, 20);
        p.add(lbltotalbill);

        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(220, 240, 200, 30);
        p.add(labeltotalbill);
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setForeground(Color.BLACK);
        lblstatus.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblstatus.setBounds(40, 280, 100, 20);
        p.add(lblstatus);

        JLabel labelstatus = new JLabel("");
        labelstatus.setForeground(Color.RED);
        labelstatus.setBounds(220, 280, 200, 30);
        p.add(labelstatus);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()){
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }
              rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
            while(rs.next()){
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        cmonth.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){
                 try{
                      Conn c = new Conn();
            
                      ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
                      while(rs.next()){
                               labelunits.setText(rs.getString("units"));
                               labeltotalbill.setText(rs.getString("totalbill"));
                               labelstatus.setText(rs.getString("status"));
                      }
                   }catch(Exception e){
                           e.printStackTrace();
                      }
            }
        });
       
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icon/c2.png"));
        Image img = i.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        next = new JButton("Pay", new ImageIcon(img));
        next.setBackground(new Color(0,128,255));
        next.setForeground(Color.WHITE);
        next.setBounds(80, 390, 110, 28);
        next.addActionListener(this);
        p.add(next);
        
        ImageIcon im = new ImageIcon(ClassLoader.getSystemResource("icon/back1.png"));
        Image imgg = im.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        cancel = new JButton("Back", new ImageIcon(imgg));
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
            try{
                      Conn c = new Conn();
            
                        c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
                   
                   }catch(Exception e){
                           e.printStackTrace();
                      }
            setVisible(false);
            new Bkash(meter);
          
        } else {
            setVisible(false); 
        }
    }
    
    
    public static void main(String[] args){
        new PayBill("");
    }
}
