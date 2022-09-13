package com.kh.product.web;

import com.kh.product.domain.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.form.EditForm;
import com.kh.product.web.form.ItemForm;
import com.kh.product.web.form.SaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
private final ProductSVC productSVC;

//등록
  @GetMapping("/add")
public String saveForm(Model model){
    model.addAttribute("form",new SaveForm());
  return "product/addForm";
}
//등록처리
  @PostMapping("/add")
public String save(@Valid @ModelAttribute("form") SaveForm saveForm,
                   RedirectAttributes redirectAttributes){

    Product product = new Product();
    product.setPname(saveForm.getPname());
    product.setCount(saveForm.getCount());
    product.setPrice(saveForm.getPrice());
    Product savedProduct = productSVC.save(product);
    Long pid = savedProduct.getPid();
    redirectAttributes.addAttribute("pid",pid);
    return "redirect:/products/{pid}";
}

//상품조회
  @GetMapping("/{pid}")
public String findByPid(
    @PathVariable("pid") Long pid, Model model
){
    Product findedProduct = productSVC.findById(pid);

  ItemForm itemForm = new ItemForm();
  itemForm.setPid(findedProduct.getPid());
  itemForm.setPname(findedProduct.getPname());
  itemForm.setCount(findedProduct.getCount());
  itemForm.setPrice(findedProduct.getPrice());

  model.addAttribute("form",itemForm);
  return "product/itemForm";
}

//수정양식
@GetMapping("/{pid}/edit")
  public String updateForm(@PathVariable("pid") Long pid, Model model){
    Product findedProduct = productSVC.findById(pid);
    EditForm editForm = new EditForm();
    editForm.setPid(findedProduct.getPid());
    editForm.setPname(findedProduct.getPname());
    editForm.setCount(findedProduct.getCount());
    editForm.setPrice(findedProduct.getPrice());
    model.addAttribute("form",editForm);
    return "product/editForm";
  }

  //수정처리
  @PostMapping("/{pid}/edit")
  public String update(@PathVariable("pid") Long pid, EditForm editForm,
                       BindingResult bindingResult){

    if(bindingResult.hasErrors()){
      return "product/editForm";
    }
    Product product = new Product();
    product.setPname(editForm.getPname());
    product.setCount(editForm.getCount());
    product.setPrice(editForm.getPrice());
    int updatedRow = productSVC.update(pid,product);
    if(updatedRow ==0){
      return "product/editForm";
    }

    return "redirect:/products/{pid}";
  }

  //삭제처리
  @GetMapping("/{pid}/del")
  public String delete(@PathVariable("pid") Long pid){
    int deletedRow = productSVC.delete(pid);
    if(deletedRow ==0){
      return "redirect:/product/{pid}";
    }
    return "redirect:/products"; // 전체 목록 view
  }

  //목록화면
  @GetMapping()
  public String list(Model model){
    List<Product> products = productSVC.findAll();
    List<ItemForm> list = new ArrayList<>();
    products.stream().forEach(product -> {
      ItemForm itemForm = new ItemForm();
      BeanUtils.copyProperties(product,itemForm);
      list.add(itemForm);
    });
    model.addAttribute("list",list);
    return "product/all";
  }

}
