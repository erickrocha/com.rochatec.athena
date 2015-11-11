package com.rochatec.athena.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="USERS")
@XmlRootElement
public class User implements Serializable {

	private static final long serialVersionUID = -2531256429170657120L;

	@Id
	@SequenceGenerator(sequenceName="SEQ_USER",name="SEQ_USER",allocationSize=1,initialValue=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_USER")
	@Column(name="ID",updatable=false,unique=true)
	protected Long id;

	@Column(name="USERNAME",insertable=true,updatable=true,nullable=false,unique=true,length=30)
	protected String username;

	@Column(name="PASSWORD",insertable=true,updatable=true,nullable=false,unique=false,length=50)
	protected String password;

    @Column(name = "EMAIL",insertable = true,updatable = true,nullable = false,unique = true)
    protected String email;

	@OneToOne
	@JoinColumn(name="EMPLOYEE",referencedColumnName="ID")
    @XmlElement
	protected Employee employee;

	@OneToOne
	@JoinColumn(name="PROFILE",referencedColumnName="ID")
    @XmlElement
	protected Profile profile;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS",insertable=true,updatable=true,nullable=false,unique=false)
    @XmlElement
	protected Status status;

	@Column(name="BLOCKED",insertable=true,updatable=true,nullable=false,unique=false)
	protected int blocked;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
		name="USER_ROLE"
		, joinColumns={
			@JoinColumn(name="USERS", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="ROLE", nullable=false)
			}
		)
    @XmlElement
	protected List<Role> roles;
	
	public User() {
	}

	public User(Long id, String username, String password, Employee employee,
			Profile profile, Status status, int blocked) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.employee = employee;
		this.profile = profile;
		this.status = status;
		this.blocked = blocked;
	}
	
	public User(Long id, String username, String password, Employee employee,
			Profile profile,boolean active, int blocked) {
		super();
		this.id = id;
		this.username = username.trim();
		this.password = password;
		this.employee = employee;
		this.profile = profile;
		setActive(active);
		this.blocked = blocked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.toUpperCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public boolean isActive() {
		return status.equals(Status.ACTIVE) ? true : false;
	}

	public void setActive(Status status) {
		this.status = status;
	}

    public void setActive(boolean active) {
        this.status = active ? Status.ACTIVE : Status.INACTIVE;
    }

    public boolean isBlocked() {
        return this.blocked == 1 ? true : false;
    }

	public void setBlocked(boolean blocked){
		this.blocked = blocked ? 1 : 0;
	}

    public Status getStatus() {
        return this.status;
    }

    public List<Role> getRoles() {
        Set<Role> allRoles = new HashSet<Role>();
        allRoles.addAll(roles);
        allRoles.addAll(profile.roles);
        return new ArrayList<Role>(allRoles);
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

    public void setRoles(Set<Role> roles) {
        this.roles = new ArrayList<Role>(roles);
    }

    public Set<Role> getRoleSet() {
        return new HashSet<Role>(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (employee != null){
			return employee.getName();
		}
		return username;
	}

}
