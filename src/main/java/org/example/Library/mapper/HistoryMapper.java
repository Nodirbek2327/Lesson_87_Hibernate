package org.example.Library.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HistoryMapper {
    private LocalDateTime taken_date;
    private LocalDateTime returned_date;
    private String name;
    private String  surname;
    private String phone;
    private String note;
}
