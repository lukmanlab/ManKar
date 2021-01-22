package ManKar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JTextField usernamefield;
    private JPasswordField passwordfield;
    private JButton logInButton;
    private JButton cancelButton;
    private JPanel jp_login;

    public Login(){
        JFrame frame = new JFrame("Login");
        frame.setContentPane(jp_login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Karyawan();
                }catch (Exception err){
                    err.printStackTrace();
                }
            }
        });


    }
}
