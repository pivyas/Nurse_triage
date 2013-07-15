package javaclasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patient_remarks")
public class PatientRemarksPOJO {
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	
	@Column(name="patient_id")
	private String patientid;
	@Column(name="nurse_id")
	private String nurseid;
	@Column(name="time_stamp")
	private String timestamp;
	@Column(name="remarks")
	private String remarks;
	//@Column(name="visit_id")
	//private String visitid;
	@Column(name="systolicBP")
	private String systolicBP;
	@Column(name="diastolicBP")
	private String diastolicBP;
	@Column(name="pulse")
	private String pulse;
	@Column(name="pulserhythm")
	private String pulserhythm;
	@Column(name="height")
	private String height;
	@Column(name="weight")
	private String weight;
	@Column(name="bmi")
	private String bmi;
	@Column(name="waistsize")
	private String waistsize;
	
	public int getId() {
		return id;
	}
	
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getNurseid() {
		return nurseid;
	}
	public void setNurseid(String nurseid) {
		this.nurseid = nurseid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/*public String getVisitid() {
		return visitid;
	}
	public void setVisitid(String visitid) {
		this.visitid = visitid;
	}*/
	public String getSystolicBP() {
		return systolicBP;
	}
	public void setSystolicBP(String systolicBP) {
		this.systolicBP = systolicBP;
	}
	public String getDiastolicBP() {
		return diastolicBP;
	}
	public void setDiastolicBP(String diastolicBP) {
		this.diastolicBP = diastolicBP;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getPulserhythm() {
		return pulserhythm;
	}
	public void setPulserhythm(String pulserhythm) {
		this.pulserhythm = pulserhythm;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getWaistsize() {
		return waistsize;
	}
	public void setWaistsize(String waistsize) {
		this.waistsize = waistsize;
	}
	
}
