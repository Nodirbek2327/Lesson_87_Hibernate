package org.example.Library.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.Library.Entity.CategoryEntity;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BestsellerMapper {
    private Integer id;
    private String  title;
    private String author;
    private CategoryEntity category;
    private Long count;
}
