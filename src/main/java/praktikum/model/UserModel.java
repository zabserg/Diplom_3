package praktikum.model;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter

public class UserModel {

    private String email;
    private String name;
    private String password;

    public UserModel(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static UserModel getRandom() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String name = faker.name().fullName();
        String password = RandomStringUtils.random(10, true, true);

        return new UserModel(email, name, password);
    }
}
