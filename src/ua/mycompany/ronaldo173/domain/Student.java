package ua.mycompany.ronaldo173.domain;

import java.util.Date;

import ua.mycompany.ronaldo173.dao.Identified;

public class Student implements Identified<Integer> {
	private Integer id;
	private String name;
	private String surName;
	private Date enrolmentDate;
	private Integer groupId;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * @param surName
	 *            the surName to set
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * @return the enrolmentDate
	 */
	public Date getEnrolmentDate() {
		return enrolmentDate;
	}

	/**
	 * @param enrolmentDate
	 *            the enrolmentDate to set
	 */
	public void setEnrolmentDate(Date enrolmentDate) {
		this.enrolmentDate = enrolmentDate;
	}

	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
