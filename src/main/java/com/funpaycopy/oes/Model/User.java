package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @NotBlank(message = "Вы не указали логин!")
    @Size(min = 4, max = 100, message = "Логин должен содержать от 4 до 10 символов!")
    private String login;

    @NotBlank(message = "Вы не указали пароль!")
    @Size(min = 8)
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=!?]).*", message = "1. Должна быть хотя бы одна цифра\n2. Должна быть хотя бы одна строчная буква\n3. Должна быть хотя бы одна заглавная буква\n4. Должен быть хотя бы один специальный символ\n5. Нельзя использовать пробелы\n6. Как минимум 8 символов")
    private String password;

    @Email(message = "Невалидная почта")
    private String email;


    @Digits(integer = 6, fraction = 2)
    private BigDecimal balance;

    private Boolean active;

    @Size(max = 1200)
    private String profilePhoto;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "userRoles",
            joinColumns = @JoinColumn(name = "userId"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @JsonIgnoreProperties(value = {"seller"})
    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<GoodsList> goods;

    @JsonIgnoreProperties(value = {"buyer"})
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<BuyList> buys;

    @JsonIgnoreProperties(value = {"employee"})
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<RequestTS> requestTSCollection;

    @JsonIgnoreProperties(value = {"user"})
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<RequestMRG> requestMRGCollection;

    public User() {
    }

    public User(String login, String password, String email, BigDecimal balance, Boolean active, String profilePhoto, Set<Role> roles, Collection<GoodsList> goods, Collection<BuyList> buys, Collection<RequestTS> requestTSCollection, Collection<RequestMRG> requestMRGCollection) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.active = active;
        this.profilePhoto = profilePhoto;
        this.roles = roles;
        this.goods = goods;
        this.buys = buys;
        this.requestTSCollection = requestTSCollection;
        this.requestMRGCollection = requestMRGCollection;
    }

    public User(long idUser, String login, String password, String email, BigDecimal balance, Boolean active, String profilePhoto) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.active = active;
        this.profilePhoto = profilePhoto;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Collection<GoodsList> getGoods() {
        return goods;
    }

    public void setGoods(Collection<GoodsList> goods) {
        this.goods = goods;
    }

    public Collection<BuyList> getBuys() {
        return buys;
    }

    public void setBuys(Collection<BuyList> buys) {
        this.buys = buys;
    }

    public Collection<RequestTS> getRequestTSCollection() {
        return requestTSCollection;
    }

    public void setRequestTSCollection(Collection<RequestTS> requestTSCollection) {
        this.requestTSCollection = requestTSCollection;
    }

    public Collection<RequestMRG> getRequestMRGCollection() {
        return requestMRGCollection;
    }

    public void setRequestMRGCollection(Collection<RequestMRG> requestMRGCollection) {
        this.requestMRGCollection = requestMRGCollection;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
