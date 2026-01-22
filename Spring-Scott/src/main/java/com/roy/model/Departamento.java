package com.roy.model;

import java.util.Date;

public class Departamento {
	private int deptNo;    // DEPTNO (Primary Key, Not Null)
    private String dName;  // DNAME (Not Null)
    private String loc;
    private String imagen = "no-image.png";
    
	public Departamento() {
		super();
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Departamento [deptNo=" + deptNo + ", dName=" + dName + ", loc=" + loc + ", imagen=" + imagen + "]";
	}
	
}
