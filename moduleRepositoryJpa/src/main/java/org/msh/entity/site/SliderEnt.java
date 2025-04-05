package org.msh.entity.site;

import jakarta.persistence.*;
import lombok.*;
import org.msh.entity.file.FileEnt;

@Entity(name = "slider")
@Table(name = "tbl_slider")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SliderEnt {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_slider")
    private Long id;

    @Column(length = 1000, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String link;

    private Integer orderNumber;

    private Boolean enabled = true;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FileEnt img;

}
