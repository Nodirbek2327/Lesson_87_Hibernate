package org.example.Library.Entity;

import lombok.*;
import org.example.Library.enums.BookStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Id
    @Column(name = "name", unique = true)
    private String name;
    private LocalDateTime created_date;
    @Enumerated(EnumType.STRING)
    private BookStatus visible;
    @OneToOne(mappedBy = "category")
    private BookEntity bookEntity;


}
