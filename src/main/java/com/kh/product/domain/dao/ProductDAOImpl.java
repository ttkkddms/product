package com.kh.product.domain.dao;

import com.kh.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{
  private final JdbcTemplate jt;

  //저장
  @Override
  public int save(Product product) {
    int result=0;
    StringBuffer sql =new StringBuffer();
    sql.append("insert into product (pid, pname, count, price) ");
    sql.append("values(?,?,?,?) ");
    result = jt.update(sql.toString(),product.getPid(),product.getPname(),
                        product.getCount(),product.getPrice());
    return result;
  }

  //상품아이디 생성
  @Override
  public Long generatePid() {
    String sql = "select product_pid_seq.nextval from dual ";
    Long newPid = jt.queryForObject(sql, Long.class);
    return newPid;
  }


//조회
  @Override
  public Product findById(Long pid) {
    StringBuffer sql = new StringBuffer();
    sql.append("select pid, pname, count, price ");
    sql.append("from product ");
    sql.append("where pid = ? ");

    Product product =null;
    try {
      product = jt.queryForObject(
          sql.toString(),new BeanPropertyRowMapper<>(Product.class),pid);
    } catch (DataAccessException e){
      log.info("삭제대상 상품이 없습니다. 상품아이디={}",pid);
    }
    return product;
  }

  //수정
  @Override
  public int update(Long pid, Product product) {
    int result = 0;
    StringBuffer sql = new StringBuffer();
    sql.append("update product ");
    sql.append("set pname = ?, ");
    sql.append(" count = ?, ");
    sql.append(" price = ? ");
    sql.append("where pid = ? ");

    result = jt.update(sql.toString(),product.getPname(),product.getCount(),product.getPrice(),pid);
return result;
  }


  //삭제
  @Override
  public int delete(Long pid) {
    int result = 0;
    String sql = "delete from product where pid = ? ";
    result = jt.update(sql, pid);
    return result;
  }

  //목록조회
  @Override
  public List<Product> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select pid, pname, count, price ");
    sql.append("from product ");

  return jt.query(sql.toString(),new BeanPropertyRowMapper<>(Product.class));
  }


}
