package com.limbo.app.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "deleted_repairs")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class DeletedRepairs {
	
	@Id
	@Column(name = "DELETED_REPAIR_ID")
	@GeneratedValue
	private Integer id;	
	
	@Column(name = "REPAIR_ID")
	private Integer repairId;	

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
	private boolean charger;

	@Column(name = "CLIENT_FULLNAME")
	@NotNull
    @Size(max=5) 
	private String clientFullName;

	@Column(name = "CLIENT_MOBILE_NUMBER")
	private String clientMobileNumber;
	
	
	@Column(name = "RECEIPT_DATE")
	private Date receiptDate;

	@Column(name = "REPAIR_DATE")
	private Date repairDate;

	@Column(name = "RETURNED")
	private boolean returned;

	@Column(name = "RETURN_DATE")
	private Date returnDate;
	
	
	@Column(name = "COMPLAINS")
	private String complains;

	@Column(name = "PAYMENT_AMOUNT")
	private Integer paymentAmount;

	@Column(name = "WARRANTY")
	private boolean warranty;

	@Column(name = "WARRANTY_PERIOD")
	private Integer warrantyPeriod;

	@Column(name = "USER_ID")
	private Integer userId;

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

	public boolean getCharger() {
		return charger;
	}

	public void setCharger(boolean charger) {
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

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getComplains() {
		return complains;
	}

	public void setComplains(String complains) {
		this.complains = complains;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public boolean isWarranty() {
		return warranty;
	}

	public void setWarranty(boolean warranty) {
		this.warranty = warranty;
	}

	public Integer getRepairId() {
		return repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public Integer getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(Integer warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
