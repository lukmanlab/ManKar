package ManKar;

import java.sql.ResultSet;

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
            db.createOrUpdateOrDelete("INSERT INTO tb_karyawan (id,fname,lname,gender,alamat) VALUES ('"+karyawan.getId()+"', '"+karyawan.getFname()+"', '"+karyawan.getLname()+"', '"+karyawan.getGender()+"', '"+karyawan.getAlamat()+"') ");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    public void tambahFoto(String filename, String id){
        try {
            db.connect();
            db.createOrUpdateOrDelete("UPDATE tb_karyawan set foto='"+filename+"' WHERE id='"+id+"' ");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    public String viewFoto(String id){
        String foto = null;
        ResultSet rs;
        try {
            db.connect();
            rs = db.select("SELECT `foto` from tb_karyawan WHERE id='"+id+"' ");
            rs.next();
            foto = rs.getString("foto");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }
        return foto;
    }

    public void tambahKtp(String filename, String id){
        try {
            db.connect();
            db.createOrUpdateOrDelete("UPDATE tb_karyawan set ktp='"+filename+"' WHERE id='"+id+"' ");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    public String viewKtp(String id){
        String ktp = null;
        ResultSet rs;
        try {
            db.connect();
            rs = db.select("SELECT `ktp` from tb_karyawan WHERE id='"+id+"' ");
            rs.next();
            ktp = rs.getString("ktp");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }
        return ktp;
    }

    public void tambahKk(String filename, String id){
        try {
            db.connect();
            db.createOrUpdateOrDelete("UPDATE tb_karyawan set kk='"+filename+"' WHERE id='"+id+"' ");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    public String viewKk(String id){
        String kk = null;
        ResultSet rs;
        try {
            db.connect();
            rs = db.select("SELECT `kk` from tb_karyawan WHERE id='"+id+"' ");
            rs.next();
            kk = rs.getString("kk");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }
        return kk;
    }
}
