package vn.ptit.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String fullName;
    private String email;
    private String mobile;
    private boolean sex;
    private Date dateOfBirth;
    private String username;
    private String password;
    private String position;
    private String image;
    private boolean status;
}
