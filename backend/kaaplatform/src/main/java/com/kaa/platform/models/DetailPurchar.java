package com.kaa.platform.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "detailpurchar")
public class DetailPurchar {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_purchar",nullable = false)
    private int idPurchar;
	@Column(name = "name",nullable = false)
    private String name;
	@ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
	public int getIdPurchar() {
		return idPurchar;
	}
	public void setIdPurchar(int idPurchar) {
		this.idPurchar = idPurchar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
}