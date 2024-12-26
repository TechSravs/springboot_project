package com.springboot.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderWrapper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int owid;
	private int totalprice;
	private int qty;
	private String status;
	private String pname;
	
	
	public OrderWrapper() {
	}


	public OrderWrapper(int owid, int totalprice, int qty, String status, String pname) {
		super();
		this.owid = owid;
		this.totalprice = totalprice;
		this.qty = qty;
		this.status = status;
		this.pname = pname;
	}


	public int getOwid() {
		return owid;
	}


	public void setOwid(int owid) {
		this.owid = owid;
	}


	public int getTotalprice() {
		return totalprice;
	}


	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}
	
	

}
