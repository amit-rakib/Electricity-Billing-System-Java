
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bkash extends JFrame implements ActionListener{
    String meter;
    JButton back;
    Bkash(String meter){
        this.meter = meter;
        
        JEditorPane j = new JEditorPane();
        j.setEditable(false);
        
        try {
            j.setPage("https://www.bkash.com/en/products-services?service=pay-bill");
        }catch(Exception e){
            j.setContentType("text/html");
            j.setText("<html>Could not load<html>");
        }
        
        JScrollPane pane = new JScrollPane(j);
        add(pane);
        
        ImageIcon im = new ImageIcon(ClassLoader.getSystemResource("icon/back1.png"));
        Image imgg = im.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        back = new JButton("Back", new ImageIcon(imgg));
        back.setBackground(new Color(0,128,255));
        back.setForeground(Color.WHITE);
       
        back.setBounds(780, 30, 110, 28);
        back.addActionListener(this);
        j.add(back);
        
        setSize(1000, 500);
        setLocation(300, 160);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new PayBill(meter);
    }
    
    public static void main(String[] args){
        new Bkash("");
    }
}
