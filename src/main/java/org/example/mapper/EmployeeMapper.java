package org.example.mapper;

public class EmployeeMapper {
    private Integer id;
    private String firstname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public EmployeeMapper() {
    }

    public EmployeeMapper(Integer id, String firstname) {
        this.id = id;
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "EmployeeMapper{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}
