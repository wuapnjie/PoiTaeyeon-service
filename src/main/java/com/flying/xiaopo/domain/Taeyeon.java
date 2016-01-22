package com.flying.xiaopo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

/**
 * JPA bean and JAX-RS bean
 * Created by Flying SnowBean on 2016/1/16.
 */
@Entity
@Table(name = "taeyeon_feed")
@XmlRootElement
public class Taeyeon implements Serializable{
    private int id;
    private String pic;
    private String description;
    private String date;

    public Taeyeon() {
    }

    public Taeyeon(String pic, String description, String date) {
        this.pic = pic;
        this.description = description;
        this.date = date;
    }

    @Id
    @Column(name = "id")
    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute(name = "pic")
    @Column(name = "pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @XmlAttribute(name = "description")
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute(name = "date")
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String mId;

    public void setId(String id) {
        mId = id;
    }
}
