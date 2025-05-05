package com.example.Millionaire.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Данное поле не может быть пустым!")
    @Size(min = 4, max = 15, message = "Имя пользователя должно состоять минимум из 4 символов и максимум из 15")
    private String name;

    @NotBlank(message = "Данное поле не может быть пустым!")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "Пароль должен содержать минимум 8 символов, 1 заглавную букву и 1 спецсимвол")
    @Column(name = "password")
    private String password;

    @Column(name = "wins")
    private int wins;

    @Column(name = "capital")
    private Integer capital = 0;

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(@NotBlank(message = "Данное поле не может быть пустым!") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "Пароль должен содержать минимум 8 символов, 1 заглавную букву и 1 спецсимвол") String password) {
        this.password = password;
    }

    public void setName(@NotBlank(message = "Данное поле не может быть пустым!") @Size(min = 4, max = 15, message = "Имя пользователя должно состоять минимум из 4 символов и максимум из 15") String name) {
        this.name = name;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
