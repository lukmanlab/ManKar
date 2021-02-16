package ManKar;

import com.mysql.cj.log.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    Database db = new Database();

    public Boolean loginKaryawan(Login login) {
        ResultSet rs;

        try {
            db.connect();
            rs = db.select("SELECT * FROM tb_login WHERE username='"+login.getUsername()+"' AND password='"+login.getPassword()+"' ");
            rs.next();
                if (login.getUsername().equals(rs.getString("username")) && login.getPassword().equals(rs.getString("password"))){
                    return true;
                }
            db.disconnect();
        }catch (Exception err){
            System.out.println("Input Data Salah!");
        }

        return false;
    }

    public Login getloginbyId(String id){
        ResultSet rs;

        Login login = new Login();
        try {
            db.connect();
            rs = db.select("SELECT * FROM tb_login WHERE id='"+id+"'");
            rs.next();

            login.setId(rs.getString("id"));
            login.setUsername(rs.getString("username"));
            login.setPassword(rs.getString("password"));

            db.disconnect();
        }catch (Exception err){
            System.out.println("ID Tidak Ada");
            //err.printStackTrace();
        }

        return login;
    }

    public List<Login> listLogin() throws SQLException {
        ResultSet rs;
        List<Login> listOfLogin = new ArrayList<Login>();

        db.connect();
        rs = db.select("SELECT * FROM tb_login");
        while (rs.next()){
            Login login = new Login();
            login.setId(rs.getString("id"));
            login.setUsername(rs.getString("username"));
            login.setPassword(rs.getString("password"));
            listOfLogin.add(login);
        }
        db.disconnect();

        return listOfLogin;

    }

    public void tambahKaryawan(Karyawan karyawan){
        try {
            db.connect();
            db.createOrUpdateOrDelete("INSERT INTO tb_karyawan (id) VALUES ('"+karyawan.getId()+"')  ");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    public boolean editKaryawan(Karyawan karyawan){
        int check = 0;
        try {
            db.connect();
            check = db.createOrUpdateOrDelete("UPDATE tb_karyawan SET nik='"+karyawan.getNik()+"', fname='"+karyawan.getFname()+"', lname='"+karyawan.getLname()+"', gender='"+karyawan.getGender()+"', alamat='"+karyawan.getAlamat()+"', foto='"+karyawan.getFoto()+"', ktp='"+karyawan.getKtp()+"', kk='"+karyawan.getKk()+"', ijazah='"+karyawan.getIjazah()+"', bpjs_kesehatan='"+karyawan.getBpjs_kesehatan()+"', bpjs_ketenagakerjaan='"+karyawan.getBpjs_ketenagakerjaan()+"', asuransi='"+karyawan.getAsuransi()+"', sertifikat='"+karyawan.getSertifikat()+"' WHERE id='"+karyawan.getId()+"'  ");
            db.disconnect();
        }catch (Exception err){
            //err.printStackTrace();
            System.out.println("Data salah!");
        }

        if (check == 1) {
            return true;
        }else{
            return false;
        }
    }

    public boolean editLogin(Login login){
        int check = 0;
        try {
            db.connect();
            check = db.createOrUpdateOrDelete("UPDATE tb_login SET username='"+login.getUsername()+"', password='"+login.getPassword()+"' WHERE id='"+login.getId()+"'  ");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }

        if (check == 1) {
            return true;
        }else{
            return false;
        }
    }

    public Karyawan getKaryawan(String id){
        ResultSet rs;

        // OOP
        Karyawan karyawan = new Karyawan();

        try {
            db.connect();
            rs = db.select("SELECT * FROM tb_karyawan WHERE id='"+id+"'");
            rs.next();

            karyawan.setId(rs.getString("id"));
            karyawan.setNik(rs.getString("nik"));
            karyawan.setFname(rs.getString("fname"));
            karyawan.setLname(rs.getString("lname"));
            karyawan.setGender(rs.getString("gender"));
            karyawan.setAlamat(rs.getString("alamat"));
            karyawan.setFoto(rs.getString("foto"));
            karyawan.setKtp(rs.getString("ktp"));
            karyawan.setKk(rs.getString("kk"));
            karyawan.setIjazah(rs.getString("ijazah"));
            karyawan.setBpjs_kesehatan(rs.getString("bpjs_kesehatan"));
            karyawan.setBpjs_ketenagakerjaan(rs.getString("bpjs_ketenagakerjaan"));
            karyawan.setAsuransi(rs.getString("asuransi"));
            karyawan.setSertifikat(rs.getString("sertifikat"));

            db.disconnect();
        }catch (Exception err){
            System.out.println("Something wrong!");
        }

        return karyawan;
    }

    public boolean daftar(Login login){
        int check = 0;
        try {
            db.connect();
            check =  db.createOrUpdateOrDelete("INSERT INTO tb_login (id,username,password) VALUES ('"+login.getId()+"', '"+login.getUsername()+"', '"+login.getPassword()+"' ) ");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }

        if (check == 1) {
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteLogin(String id){
        int check = 0;
        try {
            db.connect();
            check = db.createOrUpdateOrDelete("DELETE FROM tb_login WHERE id='"+id+"' ");
            db.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }

        if (check == 1) {
            return true;
        }else{
            return false;
        }
    }

    public String getId(Login login){
        ResultSet rs;
        String id = null;
        try {
            db.connect();
            rs = db.select("SELECT id FROM tb_login WHERE username='"+login.getUsername()+"'");
            rs.next();
            id = rs.getString("id");
            db.disconnect();
        }catch (Exception err){
            System.out.println("Something wrong!");
        }

        return id;
    }

    public boolean loginAdmin(String password){
        ResultSet rs;

        try {
            db.connect();
            rs = db.select("SELECT id FROM tb_admin WHERE password='"+password+"'");
            rs.next();

            if (rs.getInt("id") != 0) {
                System.out.println("OK password terdeteksi!");
                return true;
            }
            db.disconnect();

        }catch (Exception err){
            System.out.println("Something wrong!");
        }

        return false;
    }

}
