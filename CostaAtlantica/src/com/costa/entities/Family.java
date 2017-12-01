package com.costa.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Family {

	private int id;
	private String lastname;
	private String address;
	private String telephone;
	private String cbu;
	private int quantity;
	private LocalDate fromDate;
	private LocalDate toDate;
	private double reservation;
	
	private SimpleIntegerProperty sipId;
	private SimpleStringProperty sspLastname;
	private SimpleStringProperty sspAddress;
	private SimpleStringProperty sspTelephone;
	private SimpleStringProperty sspCbu;
	private SimpleIntegerProperty sspQuantity;
	private SimpleStringProperty sspFromDate;
	private SimpleStringProperty sspToDate;
	private SimpleDoubleProperty sspReservation;
	private SimpleStringProperty sspHouse;
		
	public Family(int sipId, String sspLastname, String sspAddress, String sspTelephone, String sspCbu, int sspQuantity, String sspFromDate, String sspToDate, double sspReservation, String sspHouse ) {
		this.sipId = new SimpleIntegerProperty(sipId);
		this.sspLastname = new SimpleStringProperty(sspLastname);
		this.sspAddress = new SimpleStringProperty(sspAddress);
		this.sspTelephone = new SimpleStringProperty(sspTelephone);
		this.sspCbu = new SimpleStringProperty(sspCbu);
		this.sspQuantity = new SimpleIntegerProperty(sspQuantity);
		this.sspFromDate = new SimpleStringProperty(sspFromDate);
		this.sspToDate = new SimpleStringProperty(sspToDate);
		this.sspReservation = new SimpleDoubleProperty(sspReservation);
		this.sspHouse = new SimpleStringProperty(sspHouse);
	}
	

	public Family(String lastname, String address, String telephone, String cbu, int quantity, LocalDate fromDate,
			LocalDate toDate, double reservation) {
		super();
		this.lastname = lastname;
		this.address = address;
		this.telephone = telephone;
		this.cbu = cbu;
		this.quantity = quantity;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reservation = reservation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAdress() {
		return address;
	}

	public void setAdress(String adress) {
		this.address = adress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate from) {
		this.fromDate = from;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate to) {
		this.toDate = to;
	}

	public double getReservation() {
		return reservation;
	}

	public void setReservation(double reservation) {
		this.reservation = reservation;
	}

	
	/* Simple properties */
	
	public int getSipId() {
		return sipId.get();
	}

	public void setSipId(int sipId) {
		this.sipId.set(sipId);
	}
	
	public String getSspLastname() {
		return sspLastname.get();
	}

	public void setSspLastname(String sspLastname) {
		this.sspLastname.set(sspLastname);
	}
	
	public String getSspAddress() {
		return sspAddress.get();
	}

	public void setSspAddress(String sspAddress) {
		this.sspLastname.set(sspAddress);
	}

	public String getSspTelephone() {
		return sspTelephone.get();
	}

	public void setSspTelephone(String sspTelephone) {
		this.sspTelephone.set(sspTelephone);
	}
	
	public String getSspCbu() {
		return sspCbu.get();
	}

	public void setSspCbu(String sspCbu) {
		this.sspCbu.set(sspCbu);
	}
	
	public int getSspQuantity() {
		return sspQuantity.get();
	}

	public void setSspQuantity(int sspQuantity) {
		this.sspQuantity.set(sspQuantity);
	}
	
	public String getSspFromDate() {
		return sspFromDate.get();
	}

	public void setSspFromDate(String sspFromDate) {
		this.sspFromDate.set(sspFromDate);
	}
	
	public String getSspToDate() {
		return sspToDate.get();
	}

	public void setSspToDate(String sspToDate) {
		this.sspToDate.set(sspToDate);
	}
	
	public double getSspReservation() {
		return sspReservation.get();
	}

	public void setSspReservation(double sspReservation) {
		this.sspReservation.set(sspReservation);
	}
	
	public String getSspHouse() {
		return sspHouse.get();
	}

	public void setSspHouse(String sspHouse) {
		this.sspHouse.set(sspHouse);
	}
	
	@Override
	public String toString() {
		return "Backup Fecha y hora= "+ LocalDateTime.now() +", Family [id=" + id + ", lastname=" + lastname + ", address=" + address + ", telephone=" + telephone
				+ ", cbu=" + cbu + ", quantity=" + quantity + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", reservation=" + reservation + "]";
	}


}
