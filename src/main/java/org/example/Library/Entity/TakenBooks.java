package org.example.Library.Entity;

import lombok.Getter;
import lombok.Setter;
import org.example.Library.enums.TakenBookStatus;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Entity
@Table(name = "taken_book")
@Setter
@Getter
public class TakenBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private ProfileEntity studentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity bookId;
    private LocalDateTime taken_date;
    private LocalDateTime returned_date;
    @Enumerated(EnumType.STRING)
    private TakenBookStatus status;
    private String note;
}
