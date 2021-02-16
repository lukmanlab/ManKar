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
    private JButton updateButton;
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
    private JTextField nik_field;
    private JTextArea alamat_textArea;
    private JTextField ijazah_textField;
    private JButton choosebt_ijazah;
    private JTextField bpjs_kes_textField;
    private JTextField bpjs_tk_textField;
    private JButton choosebt_bpjs_kes;
    private JButton choosebt_bpjs_tk;
    private JTextField asuransi_textField;
    private JTextField sertifikat_textField;
    private JButton choosebt_asuransi;
    private JButton choosebt_sertifikat;
    private JButton close_button;

    File file;
    JFileChooser jfc;
    JFrame frame = new JFrame("Karyawan");

    Dao dao = new Dao();


    public KaryawanGUI(){

    }

    public KaryawanGUI(Karyawan karyawan) {
        updateButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImage(jLbl_photo, jLbl_dPhoto,100,150);
                updateKarywan();
            }
        });

        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImage(jLbl_ktp, jLbl_dKtp,150,100);
                updateKarywan();
            }
        });

        updateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImage(jLbl_kk, jLbl_dKk,150,100);
                updateKarywan();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(gender_cb.getSelectedItem().toString());
                updateKarywan();
            }
        });

        choosebt_ijazah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(ijazah_textField);
                updateKarywan();
            }
        });
        choosebt_bpjs_kes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(bpjs_kes_textField);
                updateKarywan();
            }
        });

        choosebt_bpjs_tk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(bpjs_tk_textField);
                updateKarywan();
            }
        });

        choosebt_asuransi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(asuransi_textField);
                updateKarywan();
            }
        });

        choosebt_sertifikat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(sertifikat_textField);
                updateKarywan();
            }
        });

        close_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        id_field.setText(karyawan.getId());
        nik_field.setText(karyawan.getNik());
        fn_field.setText(karyawan.getFname());
        ln_field.setText(karyawan.getLname());
        gender_cb.setSelectedItem(karyawan.getGender());
        alamat_textArea.setText(karyawan.getAlamat());
        jLbl_dPhoto.setText(karyawan.getFoto());
        jLbl_dKtp.setText(karyawan.getKtp());
        jLbl_dKk.setText(karyawan.getKk());
        ijazah_textField.setText(karyawan.getIjazah());
        bpjs_kes_textField.setText(karyawan.getBpjs_kesehatan());
        bpjs_tk_textField.setText(karyawan.getBpjs_ketenagakerjaan());
        asuransi_textField.setText(karyawan.getAsuransi());
        sertifikat_textField.setText(karyawan.getSertifikat());


        initImage(jLbl_ktp,karyawan.getKtp(),150,100);
        initImage(jLbl_kk,karyawan.getKk(), 150,100);
        initImage(jLbl_photo,karyawan.getFoto(),100,150);

        Init();
    }

    public void Init(){
        frame.setContentPane(jp_karyawan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void initImage(JLabel label, String filename, int w, int h){
        ImageIcon imageIcon = new ImageIcon("image/"+ filename);
        Image image = imageIcon.getImage();
        Image scaledImg = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(scaledImg);
        label.setIcon(imageIcon1);
    }

    public void chooseFile(JTextField textField){
        jfc = new JFileChooser();
        if (jfc.showOpenDialog(textField) == JFileChooser.APPROVE_OPTION){
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            textField.setText(jfc.getSelectedFile().getName());
            file = new File(jfc.getSelectedFile().getPath());

            try {
                String path = new File(".").getCanonicalPath();
                System.out.println(path);
                FileUtils.copyFileToDirectory(file, new File(path + "/image"));
            }catch (Exception err){
                err.printStackTrace();
            }
        }
    }

    public void chooseImage(JLabel label, JLabel labeldesc, int w, int h){
        jfc = new JFileChooser();
        if (jfc.showOpenDialog(label) == JFileChooser.APPROVE_OPTION){
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image imageResize = image.getScaledInstance(w,h,Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(imageResize);
            label.setIcon(imageIcon);
            labeldesc.setText(jfc.getSelectedFile().getName());
            file = new File(jfc.getSelectedFile().getPath());

            try {
                String path = new File(".").getCanonicalPath();
                System.out.println(path);
                FileUtils.copyFileToDirectory(file, new File(path + "/image"));
            }catch (Exception err){
                err.printStackTrace();
            }
        }
    }

    public void updateKarywan(){
        Karyawan updateKaryawan = new Karyawan();
        updateKaryawan.setId(id_field.getText());
        updateKaryawan.setNik(nik_field.getText());
        updateKaryawan.setFname(fn_field.getText());
        updateKaryawan.setLname(ln_field.getText());
        try {
            updateKaryawan.setGender(gender_cb.getSelectedItem().toString());
        }catch (Exception err){
            System.out.println("Oit");
        }

        updateKaryawan.setAlamat(alamat_textArea.getText());
        updateKaryawan.setFoto(jLbl_dPhoto.getText());
        updateKaryawan.setKtp(jLbl_dKtp.getText());
        updateKaryawan.setKk(jLbl_dKk.getText());
        updateKaryawan.setIjazah(ijazah_textField.getText());
        updateKaryawan.setBpjs_kesehatan(bpjs_kes_textField.getText());
        updateKaryawan.setBpjs_ketenagakerjaan(bpjs_tk_textField.getText());
        updateKaryawan.setAsuransi(asuransi_textField.getText());
        updateKaryawan.setSertifikat(sertifikat_textField.getText());

        if (dao.editKaryawan(updateKaryawan) == true){
            JOptionPane.showMessageDialog(null ,"Sukses Update Data!");
        }else{
            JOptionPane.showMessageDialog(null ,"GAGAL! Mohon periksa kembali data yang diinputkan.");
        }


    }

}
