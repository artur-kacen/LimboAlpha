package com.limbo.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "repair")
public class Repair {

	@Id
	@Column(name = "REPAIR_ID")
	@GeneratedValue
	private Integer id;

	@Column(name = "PHONE")
	private boolean phone;

	@Column(name = "PHONE_MANUFACTURER")
	private String phoneManufacturer;

	@Column(name = "PHONE_MODEL")
	private String phoneModel;

	@Column(name = "PHONE_SECURITY_CODE")
	private String phoneSecurityCode;

	@Column(name = "IMEI")
	private String imei;

	@Column(name = "BATTERY")
	private boolean battery;

	@Column(name = "BATTERY_SERIAL_NUMBER")
	private String baterySerialNumber;

	@Column(name = "CHARGER")
	private String charger;

	@Column(name = "CLIENT_FULLNAME")
	private String clientFullName;

	@Column(name = "CLIENT_MOBILE_NUMBER")
	private String clientMobileNumber;

	@Column(name = "RECEIPT_DATE")
	private String receiptDate;

	@Column(name = "REPAIR_DATE")
	private String repairDate;

	@Column(name = "RETURNED")
	private boolean returned;

	@Column(name = "RETURN_DATE")
	private String returnDate;

	@Column(name = "COMPLAINS")
	private String complains;

	@Column(name = "PAYMENT_AMOUNT")
	private Double paymentAmount;

	@Column(name = "WARRANTY")
	private boolean warranty;

	@Column(name = "WARRANTY_PERIOD")
	private int warrantyPeriod;

	@Column(name = "USER_ID")
	private int userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPhone() {
		return phone;
	}

	public void setPhone(boolean phone) {
		this.phone = phone;
	}

	public String getPhoneManufacturer() {
		return phoneManufacturer;
	}

	public void setPhoneManufacturer(String phoneManufacturer) {
		this.phoneManufacturer = phoneManufacturer;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getPhoneSecurityCode() {
		return phoneSecurityCode;
	}

	public void setPhoneSecurityCode(String phoneSecurityCode) {
		this.phoneSecurityCode = phoneSecurityCode;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public boolean isBattery() {
		return battery;
	}

	public void setBattery(boolean battery) {
		this.battery = battery;
	}

	public String getBaterySerialNumber() {
		return baterySerialNumber;
	}

	public void setBaterySerialNumber(String baterySerialNumber) {
		this.baterySerialNumber = baterySerialNumber;
	}

	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public String getClientMobileNumber() {
		return clientMobileNumber;
	}

	public void setClientMobileNumber(String clientMobileNumber) {
		this.clientMobileNumber = clientMobileNumber;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getComplains() {
		return complains;
	}

	public void setComplains(String complains) {
		this.complains = complains;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public boolean isWarranty() {
		return warranty;
	}

	public void setWarranty(boolean warranty) {
		this.warranty = warranty;
	}

	public int getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(int warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
