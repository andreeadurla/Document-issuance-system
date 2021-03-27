package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	private String id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "cnp", nullable = false, unique = true)
	private String cnp;

	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "password", nullable = false)
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<House> houses;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Request> requests;

	public User() {}

	public User(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCnp() {
		return cnp;
	}

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", cnp='" + cnp + '\'' +
				'}';
	}
}
