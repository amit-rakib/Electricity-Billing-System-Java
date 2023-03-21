package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton login, cancel, signup;
    JTextField username, password;
    JLabel logginin;
    Login(){
        super("LOGIN");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
          JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(51, 153, 255), 2), "Customer Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 153, 255)));
        panel.setBackground( Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300, 80, 100, 20);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont( new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblusername);
        
        username = new JTextField();
        username.setBounds(400, 80, 180, 28);
        panel.add(username);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 120, 100, 20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont( new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblpassword);
        
        password = new JTextField();
        password.setBounds(400, 120, 180, 28);
        panel.add(password);
        
        JLabel loggininas = new JLabel("Login As");
        loggininas.setBounds(300, 40, 100, 20);
        loggininas.setForeground(Color.GRAY);
        loggininas.setFont( new Font("Tahoma", Font.BOLD, 14));
        panel.add(loggininas);
        
        logginin = new JLabel("Customer");
        logginin.setBounds(400, 40, 150, 20);
        logginin.setForeground(Color.GRAY);
        logginin.setFont( new Font("Tahoma", Font.BOLD, 14));
        panel.add(logginin);
        
        
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login3.png"));
        Image i2 = i1.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        login = new JButton("Login", new ImageIcon(i2));
        login.setBackground(new Color(0,128,255));
        login.setForeground(Color.WHITE);
        login.setFont( new Font("Tahoma", Font.TYPE1_FONT, 14));
        login.setBounds(310, 180, 120, 30);
        login.addActionListener(this);
        panel.add(login);
        
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/signup7.png"));
        Image i4 = i3.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        cancel = new JButton("Admin Login", new ImageIcon(i4));
        cancel.setBackground(new Color(0,204,102));
        cancel.setForeground(Color.WHITE);
        cancel.setFont( new Font("Tahoma", Font.TYPE1_FONT, 14));
        cancel.setBounds(370, 240, 150, 30);
        cancel.addActionListener(this);
        panel.add(cancel);
        
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup8.png"));
        Image i6 = i5.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        signup = new JButton("Sign Up", new ImageIcon(i6));
        signup.setBackground(new Color(0,128,255));
        signup.setForeground(Color.WHITE);
        signup.setFont( new Font("Tahoma", Font.TYPE1_FONT, 14));
        signup.setBounds(460, 180, 120, 30);
        signup.addActionListener(this);
        panel.add(signup);
        
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/l1.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(50, 50, 200, 200);
        panel.add(image);
        
        
       // setSize(640, 300);
       // setLocation(380, 200);
        setBounds(350, 150, 700, 400);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == login){
            String susername = username.getText();
            String spassword = password.getText();
            String user = logginin.getText();
            
            try{
                Conn c = new Conn();
                String query = "select * from login where username = '"+susername+"' and password = '"+spassword+"' and user = '"+user+"' ";
                
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    
                    new UserHome(meter).setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        } else if(ae.getSource() == cancel){
            setVisible(false);
            
            new AdminLogin();
        } else if(ae.getSource() == signup){
            setVisible(false);
            
            new Signup();
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
}
