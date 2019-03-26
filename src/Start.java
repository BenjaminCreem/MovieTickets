import javax.swing.*;

public class Start {
    public static void main(String[] args){
        JFrame frame = new JFrame("Movie Tickets");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        JButton employeeLogin = new JButton("Employee");
        JButton customerLogin = new JButton("Customer");
        frame.getContentPane().add(employeeLogin);
        frame.getContentPane().add(customerLogin);
        frame.setVisible(true);
    }
}
