package com.example.bibishop.controller.Magiamgia.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateValueDiscountDTO {

  @NotNull(message = "Tên mã giảm giá không được để trống")
  @Size(min = 3, max = 255, message = "Tên mã giảm giá phải từ 3 đến 255 ký tự")
  private String ten;

  @Size(max = 20, message = "Mã giảm giá phải dưới 20 ký tự")
  private String ma; // Optional, will be randomized if null

  @NotNull(message = "Giá trị giảm không được để trống")
  @Min(value = 100, message = "Giá trị giảm phải lớn hơn 100")
  private BigDecimal giaTriGiam;

  @NotNull(message = "Giá trị đơn tối thiểu không được để trống")
  @Min(value = 1000, message = "Giá trị đơn tối thiểu phải lớn hơn 1000")
  private BigDecimal donToiThieu;

  @NotNull(message = "Số lượng không được để trống")
  @Min(value = 1, message = "Số lượng phải lớn hơn 0")
  private Integer soLuong;

  @NotNull(message = "Ngày bắt đầu không được để trống")
  private Date ngayBatDau;

  @NotNull(message = "Ngày kết thúc không được để trống")
  private Date ngayKetThuc;
}