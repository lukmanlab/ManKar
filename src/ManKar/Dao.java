package ManKar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
    Database db = new Database();
    Connection con = db.connect();

    public Boolean loginKaryawan(Login login) {
        ResultSet rs;
        //String id = "";

        try {
            db.connect();
            rs = db.select("SELECT * FROM tb_login WHERE username='" + login.getUsername() + "' AND password='" + login.getPassword() + "' ");
            rs.next();
            if (login.getUsername().equals(rs.getString("username")) && login.getPassword().equals(rs.getString("password"))) {
                //id = rs.getString("id");
                return true;
            }
            db.disconnect();
        } catch (Exception err) {
            System.out.println("Input Data Salah!");
        }

        return false;
        // System.out.println(login.getPassword() + "\n" + rs.getString("password"));
    }

    public void tambahKaryawan(Karyawan karyawan) {
        try {
            db.connect();
            db.createOrUpdateOrDelete("");
            db.disconnect();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void imageWrite(File file) {
        try {

            FileInputStream io = new FileInputStream(file);
            String query = "insert into tb_image(IMG) values(?)";
            java.sql.PreparedStatement stmt = con.prepareStatement(query);
            stmt.setBinaryStream(1, (InputStream) io, (int) file.length());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public BufferedImage getImageById(int id) {
        String query = "select IMG from tb_image where IMG_ID = ?";
        BufferedImage buffimg = null;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            result.next();
            InputStream img = result.getBinaryStream(1); // reading image as InputStream
            buffimg = ImageIO.read(img); // decoding the inputstream as BufferedImage

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffimg;
    }
}