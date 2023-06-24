package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "lesson")
public class LessonEntity {
//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//     @Id
//     @GeneratedValue(generator = "UUID")
//     @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       private UUID id;


}
