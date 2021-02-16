package ManKar;

import com.mysql.cj.log.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class AdminGUI {
    private JTextField id_textField;
    private JTextField username_textField;
    private JPasswordField password_passwordField;
    private JButton daftarButton;
    private JPanel jp_admin;
    private JTextField id_textFieldRmv;
    private JButton del_button;
    private JTable table1;
    private JButton update_button;
    private JButton goButton;
    private List<Login> listuserLogin;

    Dao dao = new Dao();

    public AdminGUI() {
        daftarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setId(id_textField.getText());
                login.setUsername(username_textField.getText());
                login.setPassword(new String(password_passwordField.getPassword()));

                System.out.println(id_textField.getText());

                if(id_textField.getText().equals("") || username_textField.getText().equals("") || password_passwordField.getPassword().equals("")){
                    JOptionPane.showMessageDialog(null ,"Mohon melengkapi Data!");
                }else{
                    if (dao.daftar(login) == true){
                        dao.tambahKaryawan(login);
                        JOptionPane.showMessageDialog(null ,"Berhasil mendaftarkan: " + username_textField.getText());
                        id_textField.setText("");
                        username_textField.setText("");
                        password_passwordField.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null ,"GAGAL...!!! Silahkan Hubungi Administrator System!");
                    }
                }

                try {
                    refreshList();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        del_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dao.deleteLogin(id_textFieldRmv.getText()) == true ){
                    JOptionPane.showMessageDialog(null ,"Berhasil mengapus: " + id_textFieldRmv.getText() );
                    try {
                        refreshList();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(null ,"Gagal Hapus!" );
                }
            }
        });


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                TableModel model = model1;

                int viewRow = table1.getSelectedRow();
                int modelRow = table1.convertRowIndexToModel(viewRow);
                int viewColumn = table1.getSelectedColumn();
                int modelColumn = table1.convertColumnIndexToModel(viewColumn);
                Object cell = model.getValueAt(modelRow,modelColumn);

                Login login = new Login();
                login.setId(cell.toString());
                idKar = login.getId();

                Login updateLogin = dao.getloginbyId(idKar);

                id_textField.setText(updateLogin.getId()); id_textField.setEditable(false); id_textField.setEnabled(false);
                username_textField.setText(updateLogin.getUsername());
                password_passwordField.setText(updateLogin.getPassword());

                daftarButton.setEnabled(false);

                //System.out.println(cell);

            }
        });

        try {
            refreshList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (idKar.equals(id_textField.getText())){
                        //System.out.println(idKar + id_textField.getText());
                        goEdit(idKar);
                    }else{
                        JOptionPane.showMessageDialog(null ,"Klik Bagian ID :D" );
                    }
                }catch (Exception err){
                    err.printStackTrace();
                }
            }
        });

        update_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login updateLogin = new Login();
                updateLogin.setId(id_textField.getText());
                updateLogin.setUsername(username_textField.getText());
                updateLogin.setPassword(new String(password_passwordField.getPassword()) );

                if (dao.editLogin(updateLogin) == true){
                    JOptionPane.showMessageDialog(null ,"SUKSES Update!" );
                }else{
                    JOptionPane.showMessageDialog(null ,"GAGAL Update!" );
                }
            }
        });

        init();
    }

    // init table
    int row = 0;
    Object column[] = {"ID", "Username"};
    Object rowData[] = new Object[2];
    DefaultTableModel model1 = new DefaultTableModel(column,row);

    // handle id sementara
    String idKar;

    public void refreshList() throws SQLException {

        listuserLogin = dao.listLogin();

        model1.setRowCount(0);
        for (int i = 0; i < listuserLogin.size(); i++){
            rowData[0] = listuserLogin.get(i).getId();
            rowData[1] = listuserLogin.get(i).getUsername();
            model1.addRow(rowData);
            model1.fireTableDataChanged();
        }
        table1.setModel(model1);
    }

    public void init(){
        JFrame frame = new JFrame("Admin GUI");
        frame.setContentPane(jp_admin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void goEdit(String id){
        new KaryawanGUI(dao.getKaryawan(id));
    }
}
