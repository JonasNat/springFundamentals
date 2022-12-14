package be.vdab.luigi.controllers;

import be.vdab.luigi.domain.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    private final Pizza[] allePizzas = {
            new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true),
            new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
            new Pizza(3, "Calzone", BigDecimal.valueOf(4), false)};

    private Optional<Pizza> findByIdHelper(long id) {
        return Arrays.stream(allePizzas)
                .filter(pizza -> pizza.getId() == id)
                .findFirst();
    }

    @GetMapping
    public ModelAndView findAll() {
        return new ModelAndView("pizzas", "allePizzas", allePizzas);
    }

    @GetMapping("{id}")
    public ModelAndView findById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("pizza");
        findByIdHelper(id).ifPresent(modelAndView::addObject);
        return modelAndView;
    }
}
