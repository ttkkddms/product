package com.kh.product.domain.svc;

import com.kh.product.domain.Product;
import com.kh.product.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{
  private final ProductDAO productDAO;

  /**
   * 등록
   *
   * @param product 상품정보
   * @return 등록된 상품
   */
  @Override
  public Product save(Product product) {
    Long generatePid =productDAO.generatePid();
    product.setPid(generatePid);
    productDAO.save(product);
    return productDAO.findById(generatePid);
  }


  /**
   * 조회
   *
   * @param pid 상품아이디
   * @return 상품
   */
  @Override
  public Product findById(Long pid) {
    return productDAO.findById(pid);
  }

  /**
   * 수정
   *
   * @param pid
   * @param product 수정할 상품정보
   */
  @Override
  public int update(Long pid, Product product) {
    int cnt = productDAO.update(pid,product);
    return cnt;
  }

  /**
   * 삭제
   *
   * @param pid 상품아이디
   */
  @Override
  public int delete(Long pid) {
    int cnt = productDAO.delete(pid);
    return cnt;
  }

  /**
   * 목록
   *
   * @return 상품전체
   */
  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }
}
