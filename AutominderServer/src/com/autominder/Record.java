package com.autominder;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable{

	private static final long serialVersionUID = -8228566643725427154L;
	
	private double cost;
	private String nombreTaller;
	private double kmPassedSince;
	private String maintenanceName;
	private Date fecha;
	
	public Record(double cost, String nombreTaller, double kmPassedSince,
			String maintenanceName, Date fecha) {
		this.cost = cost;
		this.nombreTaller = nombreTaller;
		this.kmPassedSince = kmPassedSince;
		this.maintenanceName = maintenanceName;
		this.fecha = fecha;
	}

	public double getCost() {
		return cost;
	}

	public String getNombreTaller() {
		return nombreTaller;
	}

	public double getKmPassedSince() {
		return kmPassedSince;
	}

	public void setKmPassedSince(double kmPassedSince) {
		this.kmPassedSince = kmPassedSince;
	}

	public String getMaintenanceName() {
		return maintenanceName;
	}

	public Date getFecha() {
		return fecha;
	}
		
}
