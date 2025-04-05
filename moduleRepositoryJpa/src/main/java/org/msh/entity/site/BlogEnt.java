package org.msh.entity.site;

import jakarta.persistence.*;
import lombok.*;
import org.msh.entity.file.FileEnt;
import org.msh.enums.BlogStatus;

import java.time.LocalDateTime;

@Entity(name = "blog")
@Table(name = "tbl_blog")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogEnt {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_blog")
    private Long id;

    @Column(length = 1000, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String subTitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "blog_status")
    private BlogStatus blogStatus;

    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @Column(name = "visit_count")
    private Integer visitCount;


    //private Boolean enabled;


    @ManyToOne
    @JoinColumn(nullable = false)
    private FileEnt img;

}
