package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class AdminSignup extends JFrame implements ActionListener{
    
    JButton create, back;
    JLabel accountType;
    JTextField meter, username, name, password;
    AdminSignup(){
        super("SIGN UP");
        setBounds(450, 200, 700, 400);
        getContentPane().setBackground( Color.WHITE);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(51, 153, 255), 2), "Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 153, 255)));
        panel.setBackground( Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);
        
        JLabel heading = new JLabel("Create Account AS");
        heading.setBounds(80, 70, 140, 20);
        heading.setForeground(Color.GRAY);
        heading.setFont( new Font("Tahoma", Font.BOLD, 14));
        panel.add(heading);
        
        
       // accountType = new Choice();
       // accountType.add("Admin");
       // accountType.add("Customer");
        accountType = new JLabel("Admin");
        accountType.setBounds(260, 70, 150, 20);
        accountType.setForeground(Color.GRAY);
        accountType.setFont( new Font("Tahoma", Font.BOLD, 14));
        panel.add(accountType);
       // accountType.setBounds(260, 50, 150, 20);
       // panel.add(accountType);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(80, 90, 140, 20);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont( new Font("Tahoma", Font.BOLD, 14));
       // panel.add(lblmeter);
        
        meter = new JTextField();
        meter.setBounds(230, 90, 180, 28);
       // panel.add(meter);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(80, 130, 140, 20);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont( new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblusername);
        
        username = new JTextField();
        username.setBounds(230, 130, 180, 28);
        panel.add(username);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(80, 170, 140, 20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont( new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblname);
        
        name = new JTextField();
        name.setBounds(230, 170, 180, 28);
        panel.add(name);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(80, 210, 140, 20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont( new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblpassword);
        
        password = new JTextField();
        password.setBounds(230, 210, 180, 28);
        panel.add(password);
        
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icon/c2.png"));
        Image img = i.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        create = new JButton("Create", new ImageIcon(img));
        create.setBackground(new Color(0,128,255));
        create.setForeground(Color.WHITE);
        create.setBounds(140, 260, 110, 28);
        create.addActionListener(this);
        panel.add(create);
        
        ImageIcon im = new ImageIcon(ClassLoader.getSystemResource("icon/back1.png"));
        Image imgg = im.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        back = new JButton("Back", new ImageIcon(imgg));
        back.setBackground(new Color(0,128,255));
        back.setForeground(Color.WHITE);
        //back.setFont( new Font("Tahoma", Font.PLAIN, 14));
        back.setBounds(310, 260, 110, 28);
        back.addActionListener(this);
        panel.add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/s3.png"));
        Image i2 = i1.getImage().getScaledInstance(370, 320, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(390, 30, 250, 250);
        panel.add(image);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
          if(ae.getSource() == create){
              String atype = accountType.getText();
              String susername = username.getText();
              String sname = name.getText();
              String spassword = password.getText();
              String smeter = meter.getText();
              
              try {
                  Conn c = new Conn();
                  String query = "insert into login values('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+atype+"')";
                  c.s.executeUpdate(query);
                  
                  JOptionPane.showMessageDialog(null, "Account Created Successfully");
                  
                  setVisible(false);
                   new AdminLogin();
              }catch(Exception e){
                  e.printStackTrace();
              }
              
          } else if(ae.getSource() == back){
              setVisible(false);
              
              new AdminLogin();
          }
    }
    
    public static void main(String[] args){
        new AdminSignup();
    }
    
}
