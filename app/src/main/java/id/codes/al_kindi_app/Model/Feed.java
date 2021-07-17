package id.codes.al_kindi_app.Model;

public class Feed {
    String judul,tanggal_post,url,isi,jenjang,mapel;
    int like;

    public Feed(){
        
    }

    public String getJenjang() {
        return jenjang;
    }

    public String getMapel() {
        return mapel;
    }

    public String getJudul() {
        return judul;
    }

    public String getTanggal_post() {
        return tanggal_post;
    }

    public String getUrl() {
        return url;
    }

    public String getIsi() {
        return isi;
    }

    public int getLike() {
        return like;
    }
}
