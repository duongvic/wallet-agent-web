package vn.mog.ewallet.consumer.web.controller.bill.payment;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;

@Controller
@RequestMapping(value = "/shop-list")
public class DMMuaSamController extends AbstractController {

  private static final Logger LOGGER = LogManager.getLogger(DMMuaSamController.class);

  private static final String DT_MTB_PREFIX = "DT_MTB";
  private static final String DT_DL_PREFIX = "DT_DL";
  private static final String PK_TBS_PREFIX = "PK_TBS";
  private static final String LT_TBIT_PREFIX = "LT_TBIT";
  private static final String MA_QP_PREFIX = "MA_QP";

  private static final String SUCCESS = "success";
  private static final String FAILED = "failed";

  private static final List<String> PREFIXS = Arrays
      .asList(DT_MTB_PREFIX, DT_DL_PREFIX, PK_TBS_PREFIX, LT_TBIT_PREFIX,MA_QP_PREFIX);

  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;
  @Autowired
  GatewayServiceAPIClient gatewayAPIClient;


  @GetMapping(value = "/{bill_payment_method}/management")
  public String billPaymentInfo(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("bill_payment_method") String billPaymentMethod)
      throws Exception {
    if (!PREFIXS.contains(billPaymentMethod)) {
      return "/about/404";
    }
    try {
      map.put("bill_payment_method", billPaymentMethod);

      return "/bill_payment/shop_list_management";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/bill_payment/shop_list_management";
    }
  }


}