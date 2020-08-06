package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.models.Product;
import com.geekbrains.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService service;

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("frontProducts", service.getAll());
        return "products-all";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String name, @RequestParam int price) {
        service.save(new Product(name, price));
        return "redirect:/products/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        service.delete(id);
        return "redirect:/products/all";
    }

    @GetMapping("/find")
    @ResponseBody
    public Product findProduct(@RequestParam long id) {
        return service.find(id);
    }
}
