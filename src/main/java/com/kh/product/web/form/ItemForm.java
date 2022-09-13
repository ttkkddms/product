package com.kh.product.web.form;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemForm {
  private Long pid;
  private String pname;
  private Integer count;
  private Integer price;
}
