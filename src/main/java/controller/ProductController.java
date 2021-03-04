package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;
import service.ProductServiceImp;

import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/products")
public class ProductController {
    ProductService productService = new ProductServiceImp();
    @GetMapping("/index")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<Product> list = productService.findAll();
        modelAndView.addObject("products", list);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Product product){
        productService.create(product);
        return new ModelAndView("create", "product", new Product());
    }

    @GetMapping("/{id}/edit")
    public ModelAndView formEdit(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable int id, @ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("/index");
        product.setId(id);
        productService.edit(id, product);
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @PostMapping("/index")
    public ModelAndView find(@RequestParam String name){
        ModelAndView modelAndView = new ModelAndView("index");
        List<Product> listSearch = productService.findByName(name);
        modelAndView.addObject("products", listSearch);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView formDel(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("{id}/delete")
    public ModelAndView del(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/index");
        productService.delete(id);
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

}
