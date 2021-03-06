package inventory.model;
// Generated Aug 2, 2020 5:41:10 PM by Hibernate Tools 5.4.18.Final

import java.util.Date;

/**
 * UserRole generated by hbm2java
 */
public class UserRole implements java.io.Serializable {

	private Integer id;
	private Role role;
	private Users users;
	private int activeFlag;
	private Date createddate;
	private Date updateddate;

	public UserRole() {
	}

	public UserRole(Role role, Users users, int activeFlag, Date createddate, Date updateddate) {
		this.role = role;
		this.users = users;
		this.activeFlag = activeFlag;
		this.createddate = createddate;
		this.updateddate = updateddate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public int getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getUpdateddate() {
		return this.updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

}
