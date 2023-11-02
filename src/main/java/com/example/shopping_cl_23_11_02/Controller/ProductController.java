package com.example.shopping_cl_23_11_02.Controller;


import com.example.shopping_cl_23_11_02.DTO.ProductDTO;
import com.example.shopping_cl_23_11_02.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
//6.디자인 작업 후
//7. 맵핑 연결

@Controller
@RequiredArgsConstructor

public class ProductController {
    //연결할 서비스
    private final ProductService productService;
    //파일개수만큼 복사 ->맵핑명 ->메소드선언
    @GetMapping("/product/list")   //목록, 뒤에 파일명 + page(form), +proc
    public String listPage(Model model)throws Exception{
        List<ProductDTO> productDTOS = productService.list();

        model.addAttribute("productDTOS",productDTOS);

        return "product/list";
    }

    @GetMapping("/product/detail")   //상세보기
    public String detailPage(Integer pid,Model model)throws Exception{
        ProductDTO productDTO = productService.detail(pid);
        model.addAttribute("productDTO",productDTO);
        return "product/detail";
    }

    @GetMapping("/product/register")   //삽입
    public String registerPage(Model model ,RedirectAttributes redirectAttributes)throws Exception{
        ProductDTO productDTO = new ProductDTO();   //검증을 위한 빈 DTO

        model.addAttribute("productDTO", productDTO);
        return "product/register";
    }

    @PostMapping("/product/register")
    public String registerProc(@Valid ProductDTO productDTO, BindingResult bindingResult,
                               MultipartFile imgFile, Model model)throws Exception{
        if(bindingResult.hasErrors()){  //검증오류시 등록페이지로 이동
            return "product/register";
        }
        productService.register(productDTO,imgFile);
        return "redirect:/product/list";
    }

    @GetMapping("/product/modify")   //수정
    public String modifyPage(Integer pid ,Model model)throws Exception{
        ProductDTO productDTO = productService.detail(pid);

        model.addAttribute("productDTO",productDTO);
        return "product/modify";
    }

    @PostMapping("/product/modify")
    public String modifyProc(@Valid ProductDTO productDTO, BindingResult bindingResult,
                             MultipartFile imgFile)throws Exception{
        if(bindingResult.hasErrors()){
            return "product/modify";
        }
        productService.modify(productDTO, imgFile);
        return "redirect:/product/list";
    }

    @GetMapping("/product/remove")   //삭제
    public String removeProc(Integer pid, Model model ,RedirectAttributes redirectAttributes)throws Exception{
        productService.remove(pid);
        return "redirect:/product/list";
    }

    @GetMapping("/product/productlist")   //사용자목록
    public String productlistPage(Model model ,RedirectAttributes redirectAttributes)throws Exception{
        List<ProductDTO> productDTOS = productService.list();

        model.addAttribute("productDTOS",productDTOS);

        return "product/productlist";
    }

    @GetMapping("/product/productdetail")   //사용자 상세보기
    public String productdetailPage(Integer pid, Model model ,RedirectAttributes redirectAttributes)throws Exception{
        ProductDTO productDTO = productService.detail(pid);
        model.addAttribute("productDTO",productDTO);
        return "product/productdetail";
    }
}
