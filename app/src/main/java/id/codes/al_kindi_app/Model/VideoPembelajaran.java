package id.codes.al_kindi_app.Model;

public class VideoPembelajaran {
    String content,created_at,embed,gambar,jenjang,judul,kelas,mapel,updated_at;
    int like;

    public  VideoPembelajaran(){}

    public VideoPembelajaran(String content, String created_at, String embed, String gambar, String jenjang, String judul, String kelas, String mapel, String updated_at, int like) {
        this.content = content;
        this.created_at = created_at;
        this.embed = embed;
        this.gambar = gambar;
        this.jenjang = jenjang;
        this.judul = judul;
        this.kelas = kelas;
        this.mapel = mapel;
        this.updated_at = updated_at;
        this.like = like;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getEmbed() {
        return embed;
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

    public String getKelas() {
        return kelas;
    }

    public String getMapel() {
        return mapel;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getLike() {
        return like;
    }
}
