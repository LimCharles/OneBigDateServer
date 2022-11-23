package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@Entity
public class Professor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // auto increment
	@Column
	private Long id;

	@NotNull(message="Employee ID cannot be null")
	@Column
	private int employeeId;

	@NotNull(message="Department cannot be null")
	@Pattern(regexp="SOM|SOH|SOSE|SOSS")
	@Column
	private String department;
	
	@NotNull(message="Name cannot be null")
	@Column
	private String name;
	
	@NotNull(message="Email cannot be null")
	@Pattern(regexp="^[\\w\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	@Column
	private String email;

	@Column
	private Boolean isSingle;
	
	@Column
	private long partnerId;
	
	@NotNull(message="Gender cannot be null")
	@Column
	private String gender;
	
	@NotNull(message="Sexual Orientation cannot be null")
	@Column
	private String sexualOrientation;
	
	@NotNull(message="Latitude cannot be null")
	@Range(min=-90, max=90)
	@Column
	private double latitude;
	
	@NotNull(message="Longitude cannot be null")
	@Range(min=-180, max=180)
	@Column
	private double longitude;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public Boolean getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(Boolean isSingle) {
		this.isSingle = isSingle;
	}
	
	public long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSexualOrientation() {
		return sexualOrientation;
	}

	public void setSexualOrientation(String sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
