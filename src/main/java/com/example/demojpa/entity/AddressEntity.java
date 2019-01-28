package com.example.demojpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_address")
@Data
@NoArgsConstructor
public class AddressEntity {
    @Id
    private int id;
    private String city;
}
