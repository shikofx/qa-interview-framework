package uz.daymarket.qa.interview.framework.api.order;

import apis.OrderApi.OrdersApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.OrderApi.ClientUtils;
import utils.OrderApi.OrderUtils;
import uz.daymarket.qa.interview.framework.extensions.LoggingSetupExtension;

@ExtendWith(LoggingSetupExtension.class)
public abstract class OrderBaseTest {
    protected ClientUtils clientUtils;
    protected OrderUtils orderUtils;
    protected OrdersApi orderApi;

    @BeforeEach
    void setUp() {
        clientUtils = new ClientUtils();
        orderApi = new OrdersApi();
        orderUtils = new OrderUtils();
    }

}
