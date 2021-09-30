package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProps props;

    @GetMapping("/current")
    public String orderForm(Model model, @SessionAttribute Order order, @AuthenticationPrincipal User user) {
//        model.addAttribute("order", new Order());
        order.setCity(user.getCity());
        order.setName(user.getFullname());
        order.setStreet(user.getStreet());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") Order order, Errors errors, SessionStatus sessionStatus,@AuthenticationPrincipal User user) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        log.info(order.toString());
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.isComplete();
        return "redirect:/";
    }

    @GetMapping
    public String orderList(Model model, @AuthenticationPrincipal User user) {
        Pageable pageRequest = PageRequest.of(0, props.getPageSize());
        List<Order> orders = orderRepository.findOrdersByUserOrderByPlacedAt(user, pageRequest);
        model.addAttribute("orders", orders);
        return "orderList";
    }
}
