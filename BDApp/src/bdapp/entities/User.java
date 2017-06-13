package bdapp.entities;

public class User {

    public String nickname;
    public String forename;
    public String surname;
    public String phone;
    public String email;

    public User(String nickname, String forename, String surname, String phone, String email) {
        this.nickname = nickname;
        this.forename = forename;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }
}
