package ManKar;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
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

    public KaryawanGUI(){}

    public KaryawanGUI(Karyawan karyawan) {
        Init();
        updateButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(jLbl_photo, jLbl_dPhoto);
            }
        });
        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(jLbl_ktp, jLbl_dKtp);
            }
        });
        updateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(jLbl_kk, jLbl_dKk);
            }
        });

        id_field.setText(karyawan.getId());
        fn_field.setText(karyawan.getFname());
        ln_field.setText(karyawan.getLname());
        if (karyawan.getGender() == "M"){
            gender_cb.setSelectedItem("Male");
        }else{
            gender_cb.setSelectedItem("Female");
        }

    }

    public void Init(){
        JFrame frame = new JFrame("Karyawan");
        frame.setContentPane(jp_karyawan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        try {
            BufferedImage img = dao.getImageById(1);
            jLbl_photo.setIcon(new ImageIcon(img));
        }catch (Exception err){
            err.printStackTrace();
        }

    }

    public void chooseFile(JLabel label, JLabel labeldesc){
        jfc = new JFileChooser();
        if (jfc.showOpenDialog(label) == JFileChooser.APPROVE_OPTION){
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image imageResize = image.getScaledInstance(200,100,Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(imageResize);
            label.setIcon(imageIcon);
            labeldesc.setText(jfc.getSelectedFile().getName());
            file = new File(jfc.getSelectedFile().getPath());

            try {
                String path = new File(".").getCanonicalPath();
                System.out.println(path);
                FileUtils.copyFileToDirectory(file, new File(path + "/image"));
                dao.imageWrite(file);
            }catch (Exception err){
                err.printStackTrace();
            }
        }
    }


}
