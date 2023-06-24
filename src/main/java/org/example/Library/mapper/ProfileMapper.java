package org.example.Library.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.Library.enums.ProfileRoles;
import org.example.Library.enums.ProfileStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileMapper {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String phone;
    private ProfileStatus status;
    private ProfileRoles role;
    private LocalDateTime created_date;
}
