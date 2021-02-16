package ManKar;

import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {
    private JTextField usernamefield;
    private JPasswordField passwordfield;
    private JButton logInButton;
    private JButton cancelButton;
    private JPanel jp_login;

    public LoginGUI(){
        JFrame frame = new JFrame("Login");

        JMenuBar menuBar = new JMenuBar();
        JMenu menuAdministrator = new JMenu("Administrator");
        JMenuItem loginAdmin = new JMenuItem("Login");
        menuBar.add(menuAdministrator);
        menuAdministrator.add(loginAdmin);
        frame.setJMenuBar(menuBar);

        frame.setContentPane(jp_login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Dao dao = new Dao();

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Login logininput = new Login(usernamefield.getText(), new String(passwordfield.getPassword()));
                    if (dao.loginKaryawan(logininput)){
                        JOptionPane.showMessageDialog(null ,"Berhasil Login!");

                        new KaryawanGUI(dao.getKaryawan(dao.getId(logininput)));

                        frame.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null ,"Login Gagal! Username atau Password Salah.");
                    }
                }catch (Exception err){
                    err.printStackTrace();
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        loginAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField pass = new JPasswordField();
                int okeClick = JOptionPane.showConfirmDialog(null, pass, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (okeClick == JOptionPane.OK_OPTION){
                    String password = new String(pass.getPassword());
                    if (dao.loginAdmin(password) == true){
                        new AdminGUI();
                        frame.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null ,"Login Gagal! Password Salah.");
                    }
                }
            }
        });
    }
}
