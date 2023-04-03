package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.dto.binding.EditProductBindingDto;
import bg.softuni.fooddelivery.domain.dto.binding.AddProductBindingDto;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.exception.WrongCategoryException;
import bg.softuni.fooddelivery.service.ProductService;
import bg.softuni.fooddelivery.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService menuService,
                             UserService userService) {
        this.productService = menuService;
        this.userService = userService;
    }

    @ModelAttribute("productDto")
    public AddProductBindingDto initBindingDto() {
        return new AddProductBindingDto();
    }

    @ModelAttribute("editedProductDto")
    public EditProductBindingDto initEditProductBindingDto() {
        return new EditProductBindingDto();
    }

    @ExceptionHandler(WrongCategoryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView onProductNotFound(WrongCategoryException productNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("category-does-not-exist");

        modelAndView.addObject("category", productNotFoundException.getCategory());

        return modelAndView;
    }

    @GetMapping("/menu")
    public String getMenu(Principal principal,Model model) {


        model.addAttribute("countProducts",this.userService.getUserByUsername(principal.getName()).getCart().getCountProducts());

        return "menu-categories";
    }

    @GetMapping("/menu/{category}")
    public String getCategoryPage(@PathVariable("category")
                                  String category,
                                  Model model,Principal principal) {

        model.addAttribute("category", this.productService.findCategory(category));
        model.addAttribute("products", this.productService.allProducts(ProductCategoryEnum.valueOf(category)));
        model.addAttribute("countProducts",this.userService.getUserByUsername(principal.getName()).getCart().getCountProducts());

        return "categories-page";
    }

    @GetMapping("/products/add")
    public String addProduct() {
        return "add-product";
    }

    @PostMapping("/products/add")
    public String postAddProduct(@Valid AddProductBindingDto productDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("productDto", productDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productDto"
                    , bindingResult);

            return "redirect:/products/add";

        }

        this.productService.addProduct(productDto);

        return "redirect:/";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long productId,
                              Model model) {

        model.addAttribute("product", this.productService.getProductById(productId));

        return "edit-product";
    }


    @PatchMapping("/products/edited/{id}")
    public String editedProduct(@Valid EditProductBindingDto editedProductDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                @PathVariable("id") Long productId) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("editedProductDto", editedProductDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editedProductDto"
                    , bindingResult);

            return "redirect:/products/edit/{id}";

        }

        this.productService.editProduct(productId, editedProductDto);

        final String category = this.productService.getCategoryName(productId);

        return "redirect:/menu/" + category;
    }

    @DeleteMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {

        final String category = this.productService.getCategoryName(productId);

        this.productService.deleteProduct(productId);

        return "redirect:/menu/" + category;
    }

}
