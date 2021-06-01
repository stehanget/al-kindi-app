package id.codes.al_kindi_app.Model;

public class AsmaulHusna {
    int urutan;
    String latin,arab,arti;

    public AsmaulHusna(int urutan, String latin, String arab, String arti) {
        this.urutan = urutan;
        this.latin = latin;
        this.arab = arab;
        this.arti = arti;
    }

    public int getUrutan() {
        return urutan;
    }

    public String getLatin() {
        return latin;
    }

    public String getArab() {
        return arab;
    }

    public String getArti() {
        return arti;
    }
}
