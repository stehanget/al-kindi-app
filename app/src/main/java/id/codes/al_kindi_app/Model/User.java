package id.codes.al_kindi_app.Model;

public class User {
    String jenjang,email;

    public User(){}
    public User(String jenjang, String email) {
        this.jenjang = jenjang;
        this.email = email;
    }

    public String getJenjang() {
        return jenjang;
    }

    public String getEmail() {
        return email;
    }
}
