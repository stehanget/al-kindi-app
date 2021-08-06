package id.codes.al_kindi_app.Model;

public class Tafsir {
    String created_at,gambar,nama,pdf,update_at;
    public Tafsir(){

    }
    public Tafsir(String nama, String gambar, String pdf) {
        this.nama = nama;
        this.gambar = gambar;
        this.pdf = pdf;
    }

    public String getNama() {
        return nama;
    }

    public String getGambar() {
        return gambar;
    }

    public String getPdf() {
        return pdf;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }
}

