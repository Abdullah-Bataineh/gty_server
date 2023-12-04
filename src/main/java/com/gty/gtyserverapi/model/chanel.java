package com.gty.gtyserverapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "chanel")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Service
public class chanel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nameChanel")
    private String nameChanel;
    @Column(name = "categoryId")
    private String categoryId;
    @Column(name = "streamId")

    private int streamId;
    @Column(name = "iconUrl")
    private String iconUrl;

}
