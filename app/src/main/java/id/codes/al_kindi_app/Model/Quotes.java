package id.codes.al_kindi_app.Model;

public class Quotes {
    String quotes,nama;
    int like;

    public Quotes(String quotes, String nama, int like) {
        this.quotes = quotes;
        this.nama = nama;
        this.like = like;
    }

    public Quotes(){

    }
    public String getQuotes() {
        return quotes;
    }

    public String getNama() {
        return nama;
    }

    public int getLike() {
        return like;
    }
}
