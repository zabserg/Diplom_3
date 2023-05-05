package praktikum.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserCredentialsModel {

    private String email;
    private String password;

    public UserCredentialsModel(UserModel user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public static UserCredentialsModel from(UserModel user) {
        return new UserCredentialsModel(user);
    }

}
