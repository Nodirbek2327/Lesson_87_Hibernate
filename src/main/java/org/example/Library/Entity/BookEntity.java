package org.example.Library.Entity;

import lombok.*;
import org.example.Library.enums.BookStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  title;
    private String author;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private CategoryEntity category;
    private LocalDate publishDate;
    private LocalDate availableDay;
    @Enumerated(EnumType.STRING)
    private BookStatus visible;
    private LocalDateTime created_date;
}
