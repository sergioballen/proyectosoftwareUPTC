package com.Lending.ScenarySports.Entity;


import jakarta.persistence.*;


@Entity
@Table(name = "Scenarys")
public class Scenary {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Name_Scenary", nullable = false, length = 50)
    private String nameScenary;
    @Column(name = "Description", nullable = false, length = 100)
    private String description;

    public Scenary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameScenary() {
        return nameScenary;
    }

    public void setNameScenary(String nameScenary) {
        this.nameScenary = nameScenary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
