package com.example.bibishop.entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class SuperEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "createDate")
  private LocalDate createDate;

  @Column(name = "updateDate")
  private LocalDateTime updateDate;


  @PreUpdate
  protected void onUpdate() {
    this.updateDate = LocalDate.now().atStartOfDay();
  }

  protected void createdate() {
    this.createDate = LocalDate.from(LocalDate.now());
  }

}



