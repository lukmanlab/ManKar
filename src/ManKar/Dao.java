package ManKar;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
    Database db = new Database();

    public Boolean loginKaryawan(Login login) {
        ResultSet rs;
        //String id = "";

        try {
            db.connect();
            rs = db.select("SELECT * FROM tb_login WHERE username='"+login.getUsername()+"' AND password='"+login.getPassword()+"' ");
            rs.next();
                if (login.getUsername().equals(rs.getString("username")) && login.getPassword().equals(rs.getString("password"))){
                    //id = rs.getString("id");
                    return true;
                }
            db.disconnect();
        }catch (Exception err){
            System.out.println("Input Data Salah!");
        }

        return false;
        // System.out.println(login.getPassword() + "\n" + rs.getString("password"));
    }

    public void tambahKaryawan(Karyawan karyawan){
        try {
            db.connect();
            db.createOrUpdateOrDelete("");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }
    }
}
