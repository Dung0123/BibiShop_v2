package com.example.bibishop.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateKhachHangRequest {
    private Integer id;
    private String email;
    private String phone;
    private String fullName;
    //    private String loginName;
    private String firstName;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonProperty("dob")
    private Timestamp dob;




    //    private String gender;
    private String password;
    //    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
//    @JsonProperty("dob")
//    private Timestamp dob;
////    private String sdt;
//    private String account;
//    private String name;
    private String midName;
    private Integer status;
    private String address;
    //    private String email;
    private String role;
//    private Integer id;
}
