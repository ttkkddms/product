package com.kh.product.domain.dao;

import com.kh.product.domain.Product;

import java.util.List;

public interface ProductDAO {
  /**
   * 저장
   * @param product 상품
   * @return
   */
  int save(Product product);


  /**
   * 조회 by 상품아이디
   * @param pid 상품아이디
   * @return
   */
  Product findById(Long pid);

  /**
   * 수정
   * @param pid 상품아이디
   * @param product 수정할정보
   */
  int update(Long pid, Product product);

  /**
   * 삭제
   * @param pid 상품아이디
   */
  int delete(Long pid);

  /**
   * 상품목록
   * @return
   */
  List<Product> findAll();


  /**
   * 상품아이디 생성
   */
  Long generatePid();

}
