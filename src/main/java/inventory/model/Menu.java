package inventory.model;
// Generated Aug 2, 2020 5:41:10 PM by Hibernate Tools 5.4.18.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Menu generated by hbm2java
 */
public class Menu implements java.io.Serializable {

	private Integer id;
	private int parentId;
	private String url;
	private String name;
	private int orderIndex;
	private int activeFlag;
	private Date createddate;
	private Date updateddate;
	private Set auths = new HashSet(0);

	public Menu() {
	}

	public Menu(int parentId, String url, String name, int orderIndex, int activeFlag, Date createddate,
			Date updateddate) {
		this.parentId = parentId;
		this.url = url;
		this.name = name;
		this.orderIndex = orderIndex;
		this.activeFlag = activeFlag;
		this.createddate = createddate;
		this.updateddate = updateddate;
	}

	public Menu(int parentId, String url, String name, int orderIndex, int activeFlag, Date createddate,
			Date updateddate, Set auths) {
		this.parentId = parentId;
		this.url = url;
		this.name = name;
		this.orderIndex = orderIndex;
		this.activeFlag = activeFlag;
		this.createddate = createddate;
		this.updateddate = updateddate;
		this.auths = auths;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
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

	public Set getAuths() {
		return this.auths;
	}

	public void setAuths(Set auths) {
		this.auths = auths;
	}

}
