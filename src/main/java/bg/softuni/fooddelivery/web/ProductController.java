package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.dto.binding.EditProductBindingDto;
import bg.softuni.fooddelivery.domain.dto.binding.AddProductBindingDto;
import bg.softuni.fooddelivery.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static bg.softuni.fooddelivery.constants.ControllerAttributesConstants.PRODUCT;


@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService menuService) {
        this.productService = menuService;
    }

    @ModelAttribute("productDto")
    public AddProductBindingDto initProductAddDto() {
        return new AddProductBindingDto();
    }

    @ModelAttribute("editedProductDto")
    public EditProductBindingDto initEditProductDto() {
        return new EditProductBindingDto();
    }

    @GetMapping("/add")
    public String getAddProduct() {
        return "add-product";
    }

    @PostMapping("/add")
    public String postAddProduct(@Valid AddProductBindingDto productDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("productDto", productDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productDto"
                    , bindingResult);

            return "redirect:/products/add";

        }

        this.productService.saveProduct(productDto);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String getEditProduct(@PathVariable("id") Long productId,
                                 Model model) {

        model.addAttribute(PRODUCT, this.productService.getProductById(productId));

        return "edit-product";
    }

    @PatchMapping("/edited/{id}")
    public String editProduct(@Valid EditProductBindingDto editedProductDto,
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

        return "redirect:/menu/" + this.productService.getCategoryName(productId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {

        final String category = this.productService.getCategoryName(productId);

        this.productService.deleteProduct(productId);

        return "redirect:/menu/" + category;
    }

}
