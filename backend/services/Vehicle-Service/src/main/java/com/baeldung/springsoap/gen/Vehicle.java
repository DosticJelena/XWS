//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.09 at 03:14:19 PM CEST 
//


package com.baeldung.springsoap.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="owner_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fuel_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transmission" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vehicle_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="distance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="distancePerRent" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="distancePerRentStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="additionalPricePerKm" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="CDWStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="childrenSeats" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ownerId",
    "location",
    "fuelType",
    "brand",
    "transmission",
    "vehicleType",
    "price",
    "distance",
    "distancePerRent",
    "distancePerRentStatus",
    "additionalPricePerKm",
    "cdwStatus",
    "childrenSeats"
})
@XmlRootElement(name = "vehicle")
public class Vehicle {

    @XmlElement(name = "owner_id")
    protected long ownerId;
    @XmlElement(required = true)
    protected String location;
    @XmlElement(name = "fuel_type", required = true)
    protected String fuelType;
    @XmlElement(required = true)
    protected String brand;
    @XmlElement(required = true)
    protected String transmission;
    @XmlElement(name = "vehicle_type", required = true)
    protected String vehicleType;
    protected double price;
    protected double distance;
    protected double distancePerRent;
    @XmlElement(required = true)
    protected String distancePerRentStatus;
    protected double additionalPricePerKm;
    @XmlElement(name = "CDWStatus", required = true)
    protected String cdwStatus;
    protected int childrenSeats;

    /**
     * Gets the value of the ownerId property.
     * 
     */
    public long getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the value of the ownerId property.
     * 
     */
    public void setOwnerId(long value) {
        this.ownerId = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the fuelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Sets the value of the fuelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuelType(String value) {
        this.fuelType = value;
    }

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrand(String value) {
        this.brand = value;
    }

    /**
     * Gets the value of the transmission property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * Sets the value of the transmission property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransmission(String value) {
        this.transmission = value;
    }

    /**
     * Gets the value of the vehicleType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the value of the vehicleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleType(String value) {
        this.vehicleType = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the distance property.
     * 
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the value of the distance property.
     * 
     */
    public void setDistance(double value) {
        this.distance = value;
    }

    /**
     * Gets the value of the distancePerRent property.
     * 
     */
    public double getDistancePerRent() {
        return distancePerRent;
    }

    /**
     * Sets the value of the distancePerRent property.
     * 
     */
    public void setDistancePerRent(double value) {
        this.distancePerRent = value;
    }

    /**
     * Gets the value of the distancePerRentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistancePerRentStatus() {
        return distancePerRentStatus;
    }

    /**
     * Sets the value of the distancePerRentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistancePerRentStatus(String value) {
        this.distancePerRentStatus = value;
    }

    /**
     * Gets the value of the additionalPricePerKm property.
     * 
     */
    public double getAdditionalPricePerKm() {
        return additionalPricePerKm;
    }

    /**
     * Sets the value of the additionalPricePerKm property.
     * 
     */
    public void setAdditionalPricePerKm(double value) {
        this.additionalPricePerKm = value;
    }

    /**
     * Gets the value of the cdwStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCDWStatus() {
        return cdwStatus;
    }

    /**
     * Sets the value of the cdwStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCDWStatus(String value) {
        this.cdwStatus = value;
    }

    /**
     * Gets the value of the childrenSeats property.
     * 
     */
    public int getChildrenSeats() {
        return childrenSeats;
    }

    /**
     * Sets the value of the childrenSeats property.
     * 
     */
    public void setChildrenSeats(int value) {
        this.childrenSeats = value;
    }

}
