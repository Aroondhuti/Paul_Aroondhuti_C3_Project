import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    RestaurantService service = new RestaurantService();
    Order order = new Order();
    Restaurant restaurant;

    public void restaurant_data_for_testing() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe", "chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>Order Total<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void should_add_price_of_all_items() {
        restaurant_data_for_testing();

        order.addToCart("Sweet corn soup", 119);
        order.addToCart("Vegetable lasagne", 269);
        List<Item> cart = order.getCart();

        assertEquals(388, order.getOrderTotal(cart) );
    }

    @Test
    public void should_add_price_of_only_the_items_selected() {
        restaurant_data_for_testing();
        order.addToCart("Sweet corn soup", 119);
        List<Item> cart = order.getCart();

        assertEquals(119, order.getOrderTotal(cart) );
    }

    @Test
    public void should_return_total_as_zero_if_cart_is_empty() {
        restaurant_data_for_testing();
        List<Item> cart = order.getCart();

        assertEquals(0, order.getOrderTotal(cart));
    }

}
//>>>>>>>>>>>>>>>>>>>>>>Order Total<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<