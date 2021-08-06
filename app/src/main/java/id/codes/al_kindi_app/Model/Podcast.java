package id.codes.al_kindi_app.Model;

public class Podcast {
    String created_at,description,gambar,nama,pembuat,podcast,update_at;

    public Podcast(){

    }
    public Podcast(String created_at, String description, String gambar, String nama, String pembuat, String podcast, String update_at) {
        this.created_at = created_at;
        this.description = description;
        this.gambar = gambar;
        this.nama = nama;
        this.pembuat = pembuat;
        this.podcast = podcast;
        this.update_at = update_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getDescription() {
        return description;
    }

    public String getGambar() {
        return gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getPembuat() {
        return pembuat;
    }

    public String getPodcast() {
        return podcast;
    }

    public String getUpdate_at() {
        return update_at;
    }
}
