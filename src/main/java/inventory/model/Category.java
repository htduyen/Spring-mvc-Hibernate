package inventory.model;
// Generated Aug 2, 2020 5:41:10 PM by Hibernate Tools 5.4.18.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category implements java.io.Serializable {

	private Integer id;
	private String name;
	private String code;
	private String description;
	private int activeFlag;
	private Date createddate;
	private Date updateddate;
	private Set productInfos = new HashSet(0);

	public Category() {
	}

	public Category(String name, String code, int activeFlag, Date createddate, Date updateddate) {
		this.name = name;
		this.code = code;
		this.activeFlag = activeFlag;
		this.createddate = createddate;
		this.updateddate = updateddate;
	}

	public Category(String name, String code, String description, int activeFlag, Date createddate, Date updateddate,
			Set productInfos) {
		this.name = name;
		this.code = code;
		this.description = description;
		this.activeFlag = activeFlag;
		this.createddate = createddate;
		this.updateddate = updateddate;
		this.productInfos = productInfos;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set getProductInfos() {
		return this.productInfos;
	}

	public void setProductInfos(Set productInfos) {
		this.productInfos = productInfos;
	}

	@Override
	public String toString() {
		return "Name = "+name+" Code = "+code+" description= "+description;
	}

}
