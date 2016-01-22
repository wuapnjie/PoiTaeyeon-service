package com.flying.xiaopo.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Flying SnowBean on 2016/1/17.
 */
@XmlRootElement(name = "taeyeons")
public class Taeyeons implements Serializable{
    private List<Taeyeon> taeyeons;

    public Taeyeons() {
    }

    public Taeyeons(List<Taeyeon> taeyeons) {
        this.taeyeons = taeyeons;
    }

    @XmlElement(name = "taeyeon")
    @XmlElementWrapper
    public List<Taeyeon> getTaeyeons() {
        return taeyeons;
    }

    public void setTaeyeons(List<Taeyeon> taeyeons) {
        this.taeyeons = taeyeons;
    }
}
