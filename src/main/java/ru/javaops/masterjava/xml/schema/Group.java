
package ru.javaops.masterjava.xml.schema;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="project" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" /&gt;
 *       &lt;attribute name="enrollment" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="status" use="required" type="{http://javaops.ru}groupType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name"
})
@XmlRootElement(name = "Group", namespace = "http://javaops.ru")
public class Group {

    @XmlElement(namespace = "http://javaops.ru", required = true)
    protected String name;
    @XmlAttribute(name = "project", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object project;
    @XmlAttribute(name = "enrollment", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String enrollment;
    @XmlAttribute(name = "status", required = true)
    protected GroupType status;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the project property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getProject() {
        return project;
    }

    /**
     * Sets the value of the project property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setProject(Object value) {
        this.project = value;
    }

    /**
     * Gets the value of the enrollment property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEnrollment() {
        return enrollment;
    }

    /**
     * Sets the value of the enrollment property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEnrollment(String value) {
        this.enrollment = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link GroupType }
     */
    public GroupType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link GroupType }
     */
    public void setStatus(GroupType value) {
        this.status = value;
    }

}
