package ManKar;

public class Karyawan {
    private String id;
    private String nik;
    private String fname;
    private String lname;
    private String gender;
    private String alamat;
    private String foto;
    private String ktp;
    private String kk;
    private String ijazah;
    private String bpjs_kesehatan;
    private String bpjs_ketenagakerjaan;
    private String asuransi;
    private String sertifikat;

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

    public String getNik(){
        return this.nik;
    }

    public void setNik(String nik){
        this.nik = nik;
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

    public void setLname(String lname) {
        this.lname = lname;
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

    public String getIjazah(){
        return this.ijazah;
    }

    public void setIjazah(String ijazah){
        this.ijazah = ijazah;
    }

    public String getBpjs_kesehatan(){
        return this.bpjs_kesehatan;
    }

    public void setBpjs_kesehatan(String bpjs_kesehatan){
        this.bpjs_kesehatan = bpjs_kesehatan;
    }

    public String getBpjs_ketenagakerjaan() {
        return this.bpjs_ketenagakerjaan;
    }

    public void setBpjs_ketenagakerjaan(String bpjs_ketenagakerjaan){
        this.bpjs_ketenagakerjaan = bpjs_ketenagakerjaan;
    }

    public String getAsuransi() {
        return this.asuransi;
    }

    public void setAsuransi(String asuransi){
        this.asuransi = asuransi;
    }

    public String getSertifikat() {
        return this.sertifikat;
    }

    public void setSertifikat(String sertifikat){
        this.sertifikat = sertifikat;
    }
}
