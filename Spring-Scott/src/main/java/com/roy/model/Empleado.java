package com.roy.model;

import java.util.Date;

public class Empleado {
	private int empNo;          // EMPNO (Primary Key)
    private String eName;       // ENAME
    private String job;         // JOB
    private Integer mgr;        // MGR (Usamos Integer por si el jefe es NULL)
    private double sal;         // SAL
    private Double comm;        // COMM (Usamos Double por si no tiene comisión/NULL)
    private int deptNo;         // DEPTNO (Foreign Key)
    private Date hireDate; // HIREDATE
    private String imagen = "no-image.png";
    
	public Empleado() {
		super();
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public Double getComm() {
		return comm;
	}

	public void setComm(Double comm) {
		this.comm = comm;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Departamento [empNo=" + empNo + ", eName=" + eName + ", job=" + job + ", mgr=" + mgr + ", sal=" + sal
				+ ", comm=" + comm + ", deptNo=" + deptNo + ", hireDate=" + hireDate + ", imagen=" + imagen + "]";
	}
    
	
}
