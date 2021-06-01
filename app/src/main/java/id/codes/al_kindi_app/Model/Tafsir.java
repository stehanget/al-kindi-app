package id.codes.al_kindi_app.Model;

public class Tafsir {
       String nama,pdf;
       String gambar;

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
}

