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
public class CreateNhanVienRequest {
    private Integer  id;
    private String email;
    private String sdt;
    private String firstName;
    private String name;
    private String loginName;
    private String position;
    private String department;
    private String cccd;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonProperty("dob")
    private Timestamp dob;
    private String password;
    private String account;
    private String midName;
    private Integer status;
    private Integer address;
    private int role;
}
