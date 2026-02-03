package com.roy.model;

import java.io.Serializable;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * The persistent class for the emp database table.
 * 
 */
@Entity
@NamedQuery(name="Emp.findAll", query="SELECT e FROM Emp e")
public class Emp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer empno;

	private Float comm;

	private String ename;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hiredate;

	private String job;

	private Integer mgr;

	private Float sal;

	//bi-directional many-to-one association to Dept
	@ManyToOne
@JoinColumn(name="DEPTNO")
	private Dept dept;

	public Emp() {
		super();
	}

	public Integer getEmpno() {
		return this.empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public Float getComm() {
		return this.comm;
	}

	public void setComm(Float comm) {
		this.comm = comm;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return this.mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Float getSal() {
		return this.sal;
	}

	public void setSal(Float sal) {
		this.sal = sal;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	public void reset() {
		this.ename=null;
	}

}