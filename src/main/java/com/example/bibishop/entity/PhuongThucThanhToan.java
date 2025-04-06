package com.example.bibishop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Entity
@Table(name = "phuong_thuc_thanh_toan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhuongThucThanhToan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String ma;

  @Temporal(TemporalType.TIMESTAMP)
  private Date ngaySua;

  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayTao;

  private String ten;
  private Integer trangThai;
}
