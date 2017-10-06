package com.kaa.platform.models;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "customer")
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_customer",nullable = false)
	private int idCustomer;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "address", nullable = false)
	private String address;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<DetailPurchar> detaipurchar;

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
	
	public Set<DetailPurchar> getDetaipurchar() {
		return detaipurchar;
	}
	
	
	public void setDetaipurchar(Set<DetailPurchar> detaipurchar) {
		this.detaipurchar = detaipurchar;
	}

	@Override
	public String toString() {
		return "Customer [idCustomer=" + idCustomer + ", name=" + name + ", address=" + address + ", detaipurchar="
				+ detaipurchar + "]";
	}
	
}