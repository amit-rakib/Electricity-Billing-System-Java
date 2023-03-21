package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener {
    
    JTextField tfname, tfaddress, tfcity, tfstate, tfemail, tfphone;
    JButton next;
    JLabel lblmeter;
    Choice meterlocation, metertype, phasecode, billtype;
    String meternumber;
    MeterInfo( String meternumber){
        this.meternumber = meternumber;
        
        setSize(1000, 500);
        setLocation(300, 160);
        
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);
        
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        
        
        JLabel lblname = new JLabel("Meter Number");
        lblname.setForeground(Color.BLACK);
        lblname.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblname.setBounds(40, 80, 100, 20);
        p.add(lblname);
        
        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setForeground(Color.BLACK);
        lblmeternumber.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblmeternumber.setBounds(220, 80, 100, 20);
        p.add(lblmeternumber);

      
        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setForeground(Color.BLACK);
        lblmeterno.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblmeterno.setBounds(40, 120, 100, 20);
        p.add(lblmeterno);
        
        meterlocation =  new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(220, 120, 120, 28);
        p.add(meterlocation);
        
        
        
        JLabel lbladdress = new JLabel("Meter Type");
        lbladdress.setForeground(Color.BLACK);
        lbladdress.setFont( new Font("Tahoma", Font.BOLD, 12));
        lbladdress.setBounds(40, 160, 100, 20);
        p.add(lbladdress);

        
        metertype =  new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(220, 160, 120, 28);
        p.add(metertype);
      
        
        JLabel lblcity = new JLabel("Phase Code");
        lblcity.setForeground(Color.BLACK);
        lblcity.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblcity.setBounds(40, 200, 100, 20);
        p.add(lblcity);

        
        phasecode =  new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(220, 200, 120, 28);
        p.add(phasecode);
     
        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setForeground(Color.BLACK);
        lblstate.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblstate.setBounds(40, 240, 100, 20);
        p.add(lblstate);

        billtype =  new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(220, 240, 120, 28);
        p.add(billtype);
        
       
        
        JLabel lblemail = new JLabel("Days");
        lblemail.setForeground(Color.BLACK);
        lblemail.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblemail.setBounds(40, 280, 100, 20);
        p.add(lblemail);
        
        JLabel lblemails = new JLabel("30 Days");
        lblemails.setForeground(Color.BLACK);
        lblemails.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblemails.setBounds(220, 280, 100, 20);
        p.add(lblemails);


        JLabel lblphone = new JLabel("Note :");
        lblphone.setForeground(Color.BLACK);
        lblphone.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblphone.setBounds(40, 320, 100, 20);
        p.add(lblphone);
        
        JLabel lblphones = new JLabel("By Default Bill is calculated for 30 days only");
        lblphones.setForeground(Color.BLACK);
        lblphones.setFont( new Font("Tahoma", Font.BOLD, 12));
        lblphones.setBounds(141, 320, 500, 20);
        p.add(lblphones);

        
        
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icon/c2.png"));
        Image img = i.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        next = new JButton("Submit", new ImageIcon(img));
        next.setBackground(new Color(0,128,255));
        next.setForeground(Color.WHITE);
        next.setBounds(180, 390, 110, 28);
        next.addActionListener(this);
        p.add(next);
        
        
        
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
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";
            
            String query = "insert into meter_info values('"+meter+"', '"+location+"', '"+type+"', '"+code+"', '"+typebill+"', '"+days+"')";
           
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                
                
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);
                
                // new frame
            } catch(Exception e){
                e.printStackTrace();
            }
            
        } else {
            setVisible(false);
            
            new AdminHome().setVisible(true);
        }
    }
    
    
    public static void main(String[] args){
        new MeterInfo("");
    }
}
