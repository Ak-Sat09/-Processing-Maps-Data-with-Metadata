package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "metadata")
public class Metadata {
    @Id
    private String id;
    
    private String type;
    private double rating;
    private int reviews;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private location location;
}
