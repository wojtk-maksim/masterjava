
package ru.javaops.masterjava.xml.schema;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fullName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="groups" type="{http://javaops.ru}userGroups"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="flag" use="required" type="{http://javaops.ru}flagType" /&gt;
 *       &lt;attribute name="email" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="city" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" /&gt;
 *       &lt;attribute name="topjava" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="masterjava" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "fullName",
        "groups"
})
@XmlRootElement(name = "User", namespace = "http://javaops.ru")
public class User {

    @XmlElement(namespace = "http://javaops.ru", required = true)
    protected String fullName;
    @XmlList
    @XmlElement(namespace = "http://javaops.ru", required = true)
    protected List<String> groups;
    @XmlAttribute(name = "flag", required = true)
    protected FlagType flag;
    @XmlAttribute(name = "email", required = true)
    protected String email;
    @XmlAttribute(name = "city", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object city;
    @XmlAttribute(name = "topjava")
    protected boolean topjava;
    @XmlAttribute(name = "masterjava")
    protected boolean masterjava;

    /**
     * Gets the value of the fullName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the groups property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the groups property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroups().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getGroups() {
        if (groups == null) {
            groups = new ArrayList<String>();
        }
        return this.groups;
    }

    /**
     * Gets the value of the flag property.
     *
     * @return possible object is
     * {@link FlagType }
     */
    public FlagType getFlag() {
        return flag;
    }

    /**
     * Sets the value of the flag property.
     *
     * @param value allowed object is
     *              {@link FlagType }
     */
    public void setFlag(FlagType value) {
        this.flag = value;
    }

    /**
     * Gets the value of the email property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the city property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setCity(Object value) {
        this.city = value;
    }

    /**
     * Gets the value of the topjava property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isTopjava() {
        return topjava;
    }

    /**
     * Sets the value of the topjava property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setTopjava(boolean value) {
        this.topjava = value;
    }

    /**
     * Gets the value of the masterjava property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isMasterjava() {
        return masterjava;
    }

    /**
     * Sets the value of the masterjava property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setMasterjava(boolean value) {
        this.masterjava = value;
    }

    @Override
    public String toString() {
        return "name = " + fullName + ", email = " + email;
    }

}
