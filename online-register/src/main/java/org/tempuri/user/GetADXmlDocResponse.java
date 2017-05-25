
package org.tempuri.user;

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
 *         &lt;element name="GetADXmlDocResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getADXmlDocResult"
})
@XmlRootElement(name = "GetADXmlDocResponse")
public class GetADXmlDocResponse {

    @XmlElement(name = "GetADXmlDocResult")
    protected String getADXmlDocResult;

    /**
     * Gets the value of the getADXmlDocResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetADXmlDocResult() {
        return getADXmlDocResult;
    }

    /**
     * Sets the value of the getADXmlDocResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetADXmlDocResult(String value) {
        this.getADXmlDocResult = value;
    }

}
