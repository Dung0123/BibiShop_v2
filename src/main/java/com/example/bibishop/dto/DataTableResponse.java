package com.example.bibishop.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataTableResponse  implements Serializable {
    private Integer draw,recordsTotal,recordsFiltered;
    private List<?> data;
}
