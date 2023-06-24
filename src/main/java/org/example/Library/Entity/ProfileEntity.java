package org.example.Library.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Library.enums.ProfileRoles;
import org.example.Library.enums.ProfileStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profileLib")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String login;
    private String password;
    @Column(name = "phone", unique = true)
    private String phone;
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;
    @Enumerated(EnumType.STRING)
    private ProfileRoles role;
    private LocalDateTime created_date;
    @OneToOne(mappedBy = "profileLib")
    private TakenBooks takenBooks;

    @Override
    public String toString() {
        return "ProfileEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", created_date=" + created_date +
                '}';
    }
}
