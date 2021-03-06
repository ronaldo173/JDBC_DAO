package ua.mycompany.ronaldo173.domain;

import ua.mycompany.ronaldo173.dao.Identified;

public class Group implements Identified<Integer> {
	private Integer id;
	private int number;
	private String department;

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Group [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", department=");
		builder.append(department);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public Integer getId() {
		return id;
	}

}
