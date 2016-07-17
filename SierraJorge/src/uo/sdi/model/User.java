package uo.sdi.model;

/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 * 
 */
public class User {

    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;

    private UserStatus status;

    public String getEmail() {
	return email;
    }

    public User setEmail(String email) {
	this.email = email;
	return this;
    }

    public UserStatus getStatus() {
	return status;
    }

    public User setStatus(UserStatus status) {
	this.status = status;
	return this;
    }

    public Long getId() {
	return id;
    }

    public User setId(Long id) {
	this.id = id;
	return this;
    }

    public String getLogin() {
	return login;
    }

    public User setLogin(String login) {
	this.login = login;
	return this;
    }

    public String getPassword() {
	return password;
    }

    public User setPassword(String password) {
	this.password = password;
	return this;
    }

    public String getName() {
	return name;
    }

    public User setName(String name) {
	this.name = name;
	return this;
    }

    public String getSurname() {
	return surname;
    }

    public User setSurname(String surname) {
	this.surname = surname;
	return this;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", login=" + login + ", password=" + password
		+ ", name=" + name + ", surname=" + surname + ", status="
		+ status + ", email=" + email + "]";
    }

}
