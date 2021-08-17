package id.codes.al_kindi_app.Model;

public class Feed {
    String content,created_at,gambar,jenjang,judul,mapel,update_at,key;
    int like;

    public Feed(){

    }
    public Feed(String content, String created_at, String gambar, String jenjang, String judul, String mapel, String update_at, int like) {
        this.content = content;
        this.created_at = created_at;
        this.gambar = gambar;
        this.jenjang = jenjang;
        this.judul = judul;
        this.mapel = mapel;
        this.update_at = update_at;
        this.like = like;
    }

    public String getKey() {
        return key;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getGambar() {
        return gambar;
    }

    public String getJenjang() {
        return jenjang;
    }

    public String getJudul() {
        return judul;
    }

    public String getMapel() {
        return mapel;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public int getLike() {
        return like;
    }
}
