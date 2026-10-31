package uz.daymarket.qa.interview.framework.api.order;

import classes.OrderApi.Order;
import enums.OrderApi.ClientStatus;
import enums.OrderApi.OrderStatus;
import okhttp3.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static utils.OrderApi.OrderApiMappers.mapOrder;
import static utils.OrderApi.OrderApiMappers.mapOrdersData;

public class CreateOrderTests extends OrderBaseTest {

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 1.0, 1000000.01})
    public void createNewOrderTest(Double orderAmount) {

        Response createOrderResponse = orderApi.createOrder(
                orderAmount, clientUtils.getRandomClient(ClientStatus.enabled).getClientId());

        Order createdOrder = mapOrder(createOrderResponse);

        List<Order> foundOrders = orderUtils.findOrdersById(
                mapOrdersData(orderApi.getAllOrders()),
                createdOrder.getOrderId()
        );

        step("Проверить параметры созданного заказа", () -> {
            assertAll(
                    () -> assertThat(createOrderResponse.code())
                            .as("Expected HTTP status 201 when creating an order")
                            .isEqualTo(201),
                    () -> assertThat(createdOrder.getStatus())
                            .as("Expected created order to have status " + OrderStatus.Created)
                            .isEqualTo(OrderStatus.Created)
                    // place for more assertions
            );
        });
    }

}
