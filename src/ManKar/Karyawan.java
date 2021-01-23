package ManKar;

public class Karyawan {
    private String id;
    private String fname;
    private String lname;
    private String gender;
    private String alamat;
    private String foto;
    private String ktp;
    private String kk;

    public Karyawan(){}

    public Karyawan(String id){
        this.id = id;
    }

    public Karyawan(String id, String fname, String lname, String gender){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKk() {
        return kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }
}
