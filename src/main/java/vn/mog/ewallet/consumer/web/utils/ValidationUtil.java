package vn.mog.ewallet.consumer.web.utils;

import static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_OUT_ORDER_MIN_VALUE_PER_ORDER;
import static vn.mog.ewallet.consumer.web.exception.MessageNotify.ERROR_CODE;
import static vn.mog.ewallet.consumer.web.exception.MessageNotify.SUCCESS_CODE;
import static vn.mog.ewallet.consumer.web.exception.MessageNotify.SUCCESS_NAME;
import static vn.mog.ewallet.consumer.web.util.tools.StringUtil.SPECIAL_NAME_MATCHERS;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import vn.mog.ewallet.consumer.web.contract.AjaxResponse;
import vn.mog.ewallet.consumer.web.exception.MessageNotify;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.StringUtil;


@Component
public class ValidationUtil {

  public static final String DATA_INVALIDATE = "message.data.invalidate";

  public static final String MESAGE_ERROR = "message.response.value.error";
  public static final String MESAGE_SUCCESS = "message.response.value.success";

  public static final String TRANS_RULE_ID_NULL = "system.txn.rule.id.null";

  public static final String SERVICE_PARENT_ID_NULL = "system.trueservice.message.parent.id.null";
  public static final String SERVICE_CODE_NULL = "system.trueservice.message.servicecode.null";
  public static final String SERVICE_CODE_SPECIAL = "system.trueservice.message.servicecode.specialcharacters";

  public static final String MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS = "message.order.flow.process.approve.success";
  public static final String MESAGE_ORDER_FLOW_APPROVE_PROCESS_ERROR = "message.order.flow.process.approve.error";
  public static final String MESAGE_ORDER_FLOW_REJECT_PROCESS_SUCCESS = "message.order.flow.process.reject.success";
  public static final String MESAGE_ORDER_FLOW_REJECT_PROCESS_ERROR = "message.order.flow.process.reject.error";
  public static final String TXN_RULE_CODE_SPECIAL = "system.txn.rule.code.special";
  public static final String TXN_RULE_NAME_SPECIAL = "system.txn.rule.name.special";
  public static final String TXN_RULE_CODE_NULL = "system.txn.rule.code.null";
  public static final String TXN_RULE_NAME_NULL = "system.txn.rule.name.null";

  @Autowired
  protected MessageSource messageSource;

  public String notify(String code) {
    return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
  }

  public String notify(String code, String defaultMessage) {
    return messageSource.getMessage(code, null, (defaultMessage == null) ? "Thực hiện lỗi" : defaultMessage, LocaleContextHolder.getLocale());
  }

  public String notify(String code, Object[] params) {
    return messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
  }

  public String isLinkBankTranfer(Long amountOfLinkBank, long balance) {
    if (amountOfLinkBank == 0) {
      return notify("error.fundout.linkbank.01");

    } else if (amountOfLinkBank > balance) {
      return notify("error.fundout.linkbank.02");

    } else if (amountOfLinkBank < FUND_OUT_ORDER_MIN_VALUE_PER_ORDER) {

      String minNumber = NumberUtil.formatNumber(FUND_OUT_ORDER_MIN_VALUE_PER_ORDER);
      String linkBankNumber = NumberUtil.formatNumber(amountOfLinkBank);
      List<String> strings = Arrays.asList(linkBankNumber, minNumber);
      return notify("error.fundout.linkbank.03", strings.toArray());
    }
    return StringUtils.EMPTY;
  }

  public AjaxResponse updateRule(String serviceTypeId, String ruleCode, String ruleName) {

    if (StringUtils.isEmpty(serviceTypeId)) {
      return new AjaxResponse(ERROR_CODE, notify("system.txn.rule.servicetype.null"));
    }

    if (StringUtils.isEmpty(ruleCode)) {
      return new AjaxResponse(ERROR_CODE, notify(TXN_RULE_CODE_NULL));
    }

    if (StringUtils.isEmpty(ruleName)) {
      return new AjaxResponse(ERROR_CODE, notify(TXN_RULE_NAME_NULL));
    }

    if (StringUtil.specialSymbols(ruleCode, SPECIAL_NAME_MATCHERS)) {
      return new AjaxResponse(ERROR_CODE, notify(TXN_RULE_CODE_SPECIAL));
    }

    if (StringUtil.specialSymbols(ruleName, SPECIAL_NAME_MATCHERS)) {
      return new AjaxResponse(ERROR_CODE, notify(TXN_RULE_NAME_SPECIAL));
    }

    return new AjaxResponse(SUCCESS_CODE, SUCCESS_NAME);
  }

  public AjaxResponse updateRuleStep() {
    return new AjaxResponse(SUCCESS_CODE, SUCCESS_NAME);
  }

  public AjaxResponse requestSubmitTransfer() {
    return new AjaxResponse(SUCCESS_CODE, SUCCESS_NAME);

  }

  public void validateOrderInitiate() {

  }

  public MessageNotify simpleSpecific(String... roles) {
    for (String item : roles) {
      if (!StringUtils.EMPTY.equals(item) && StringUtil.specialSymbols(item, SPECIAL_NAME_MATCHERS)) {
        return new MessageNotify(ERROR_CODE, notify("message.name.specialcharacters"));
      }
    }
    return new MessageNotify(SUCCESS_CODE, SUCCESS_NAME);
  }
}
