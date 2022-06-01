package com.gymworkouts.gymworkouts.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "url_small")
    public String urlSmall;

    @Column(name = "url_medium")
    public String urlMedium;

    @Column(name = "url_high")
    public String urlHigh;
}
