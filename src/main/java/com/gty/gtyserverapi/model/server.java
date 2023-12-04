package com.gty.gtyserverapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "server")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Service
public class server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "host")
    private String host;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

}
