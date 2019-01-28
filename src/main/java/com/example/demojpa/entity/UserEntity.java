package com.example.demojpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    private int id;
    @Column(name = "pwd")
    private String password;
    @Column(name = "username")
    private String username;
}
