package com.kh.product.domain.svc;

import com.kh.product.domain.Product;

import java.util.List;

public interface ProductSVC {

  /**
   * 등록
   * @param product 상품정보
   * @return  등록된 상품
   */
  Product save(Product product);

  /**
   * 조회
   * @param pid 상품아이디
   * @return 상품
   */
  Product findById(Long pid);

  /**
   * 수정
   * @param product 수정할 상품정보
   */
  int update(Long pid, Product product);

  /**
   * 삭제
   * @param pid 상품아이디
   */
  int delete(Long pid);

  /**
   * 목록
   * @return 상품전체
   */
  List<Product> findAll();
}
