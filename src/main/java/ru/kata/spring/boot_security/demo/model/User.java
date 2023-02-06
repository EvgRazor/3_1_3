package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 3, max = 20, message = "Минимум 3 символа, максимум 20")
    private String name;

    @Column(name = "email")
    @NotEmpty(message = "Введите email")
    @Email
    private String email;

    @Column(name = "age")
    @Min(value = 16, message = "Минимальны возраст 16 лет")
    private int age;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 1, max = 100, message = "Минимум 1 символа, максимум 100")
    private String password;

    @ManyToMany(cascade = {PERSIST, DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn (name = "role_id")
    )
    @NotEmpty(message = "Роль не может быть пустой")
    private Set<Role> roleSet;

    public User() {
    }

    public User(int id, String name, String email, int age, String password, Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
        this.roleSet = roleSet;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public String toString() {
        return "UserDetailsWrapperImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", roleSet=" + roleSet +
                '}';
    }
}

