package org.yearup.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.CartItem;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_USER')")
public class ShoppingCartController
{
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService){
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCart getCart(Principal principal)
    {
        String username = principal.getName();
        return shoppingCartService.getCartByUserId(userService.getIdByUsername(username));
    }
    @PostMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCart addProductToCart(@PathVariable Long productId, Principal principal){
        String username = principal.getName();
        return shoppingCartService.addProductToCart(userService.getIdByUsername(username), productId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCart deleteCart(Principal principal){
        String username = principal.getName();
        return shoppingCartService.clearCart(userService.getIdByUsername(username));
    }

    @PutMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCart updateProductQuantity(@PathVariable Long productId, @RequestBody CartItem cartItem, Principal principal){
        String username = principal.getName();
        return shoppingCartService.updateProductQuantity(userService.getIdByUsername(username), productId, cartItem.getQuantity());
    }

}
