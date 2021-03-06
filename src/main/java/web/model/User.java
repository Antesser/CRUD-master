package web.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users_crud")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Field shouldn't be empty")
    @Size(min = 2, max = 25, message = "from 2 to 25 symbols")
    private String name;

    @NotEmpty(message = "Field shouldn't be empty")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Field shouldn't be empty")
    @Email(message = "Incorrect e-mail")
    private String email;

    public User() {
    }

    // Конструктор для UserDaoArrayListImpl - id поле в реальной БД будет присваиваться автоматически
    public User(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
