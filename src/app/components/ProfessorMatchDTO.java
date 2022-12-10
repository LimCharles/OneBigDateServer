package app.components;

public class ProfessorMatchDTO implements Comparable<ProfessorMatchDTO> {
	private Long id;
	private int employeeId;
	private String department;
	private String name;
	private String email;
	private Boolean isSingle;
	private long partnerId;
	private String gender;
	private String sexualOrientation;
	private double latitude;
	private double longitude;
	private double compatibility;
	
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


	public double getCompatibility() {
		return compatibility;
	}


	public void setCompatibility(double compatibility) {
		this.compatibility = compatibility;
	}


	@Override
	public String toString() {
		return "ProfessorMatchDTO [id=" + id + ", employeeId=" + employeeId + ", department=" + department + ", name="
				+ name + ", email=" + email + ", isSingle=" + isSingle + ", partnerId=" + partnerId + ", gender="
				+ gender + ", sexualOrientation=" + sexualOrientation + ", latitude=" + latitude + ", longitude="
				+ longitude + ", compatibility=" + compatibility + "]";
	}


	public int compareTo(ProfessorMatchDTO o) {
		if (this.getCompatibility() > o.getCompatibility()) {
			return 1;
		} else if (this.getCompatibility() < o.getCompatibility()) {
			return -1;
		} else {
			return 0;
		}
	}
}