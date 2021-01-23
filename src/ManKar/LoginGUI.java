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
                        Karyawan objKar = new Karyawan("P001","Ahmad Lukman", "Hakim","F");
                        new KaryawanGUI(objKar);
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
    }
}
