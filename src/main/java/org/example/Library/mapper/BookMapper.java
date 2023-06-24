package org.example.Library.mapper;

import lombok.*;
import org.example.Library.Entity.CategoryEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookMapper {
    private Integer id;
    private String  title;
    private String author;
    private CategoryEntity category;

}
