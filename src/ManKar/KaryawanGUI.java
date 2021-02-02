package ManKar;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class KaryawanGUI {
    private JTextField id_field;
    private JTextField fn_field;
    private JComboBox gender_cb;
    private JTextField ln_field;
    private JButton saveButton;
    private JButton resetButton;
    private JPanel jp_karyawan;
    private JLabel jLbl_photo;
    private JButton updateButton1;
    private JButton updateButton2;
    private JButton updateButton3;
    private JLabel jLbl_ktp;
    private JLabel jLbl_kk;
    private JLabel jLbl_dKtp;
    private JLabel jLbl_dKk;
    private JLabel jLbl_dPhoto;

    File file;
    JFileChooser jfc;

    Dao dao = new Dao();
    Karyawan karyawan = new Karyawan();


    public KaryawanGUI(){}

    public KaryawanGUI(Karyawan karyawan) {
        Init();
        updateButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = chooseFile(jLbl_photo, jLbl_dPhoto);
                dao.tambahFoto(filename, karyawan.getId());
            }
        });
        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = chooseFile(jLbl_ktp, jLbl_dKtp);
                dao.tambahKtp(filename, karyawan.getId());
            }
        });
        updateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = chooseFile(jLbl_kk, jLbl_dKk);
                dao.tambahKk(filename, karyawan.getId());
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dao.tambahKaryawan(karyawan);
            }
        });

//        resetButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dao.verifikasiPassword();
//            }
//        });

        id_field.setText(karyawan.getId());
        fn_field.setText(karyawan.getFname());
        ln_field.setText(karyawan.getLname());
        if (karyawan.getGender() == "M"){
            gender_cb.setSelectedItem("Male");
        }else{
            gender_cb.setSelectedItem("Female");
        }

        tampil(karyawan.getId());
    }

    public void Init(){
        JFrame frame = new JFrame("Karyawan");
        frame.setContentPane(jp_karyawan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public String chooseFile(JLabel label, JLabel labeldesc) {
        jfc = new JFileChooser();
        String filename = null;
        if (jfc.showOpenDialog(label) == JFileChooser.APPROVE_OPTION) {
            filename = jfc.getSelectedFile().getName();
            tampilGambar(label, labeldesc, jfc.getSelectedFile().getAbsolutePath(), filename);
            file = new File(jfc.getSelectedFile().getPath());
            try {
                String path = new File(".").getCanonicalPath();
                FileUtils.copyFileToDirectory(file, new File(path + "/image"));
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        return filename;
    }

    public void tampil(String id) {
        try {
            String filefoto = dao.viewFoto(id);
            String path = new File(".").getCanonicalPath();
            tampilGambar(jLbl_photo, jLbl_dPhoto,path+"/image/"+filefoto, filefoto);
            String filektp = dao.viewKtp(id);
            tampilGambar(jLbl_ktp, jLbl_dKtp, path+"/image/"+filektp, filektp);
            String filekk = dao.viewKk(id);
            tampilGambar(jLbl_kk, jLbl_dKk, path+"/image/"+filekk, filekk);
        } catch (Exception err) {
            err.printStackTrace();
        }

    }


    public void tampilGambar(JLabel label, JLabel labeldesc, String path, String filename) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(path);
        Image imageResize = image.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(imageResize);
        label.setIcon(imageIcon);
        labeldesc.setText(filename);
    }

}
