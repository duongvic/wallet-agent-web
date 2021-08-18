package vn.mog.ewallet.consumer.web.util.taglib;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.InvoiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.CardType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.FundOrderFlowStageType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.TxnStatus;
import vn.mog.ewallet.consumer.web.util.tools.CommonUtil;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.PhoneNumberUtil;


public class WalletTagLib {

  //"\u2605"
  public static final String STAR = "\u2605";

  public static String getNameTelco(String code) {
    try {
      CardType card = CardType.getCardType(code);
      if (card == null) {
        return code;
      }
      return card.name;
    } catch (Exception ex) {
      return code;
    }
  }

  public static String numberToText(String number) {
    try {
      String langLocal = LocaleContextHolder.getLocale().getLanguage();
      if (langLocal.equals("vi")) {
        return CommonUtil.numberToText(number);
      } else {
        number = number.replaceAll("\\D+", "");
        return CommonUtil.numberToTextEn(NumberUtil.convertToLong(number));
      }
    } catch (Exception ex) {
      return number;
    }
  }

  public static String formatNumber(String number) {
    try {
      Long numberL = NumberUtil.convertToLong(number);
      return NumberUtil.formatNumber(numberL);
    } catch (Exception ex) {
      return StringUtils.EMPTY;
    }
  }

  public static String splitNumber(String number) {
    try {
      Long numberL = Long.valueOf(number.split("\\.")[0]);
      return NumberUtil.formatNumber(numberL);
    } catch (Exception ex) {
      return StringUtils.EMPTY;
    }
  }

  public static String validatePhone(String phone) {
    try {
      Boolean check = NumberUtil.validatePhone(phone);
      if (check.equals(true))
        return StringUtils.EMPTY;
    } catch (Exception ex) {
      return "Số điện thoại không hợp lệ";
    }
    return "Số điện thoại không hợp lệ";
  }

  public static String getInvoiceTypeCodeName(String invoiceTypeId) {
    return InvoiceType.BILL_PAYMENT_LIST.get(NumberUtil.convertToInt(invoiceTypeId));
  }

  public static String getTxnStatusName(Integer txnStatusId) {
    return TxnStatus.TXN_STATUS_LIST.get(txnStatusId);
  }

  public static String getFundOrderStatusName(Integer status) {
    if (status == null) {
      return StringUtils.EMPTY;
    }
    return FundOrderFlowStageType.getWorkFlowState(status).displayText;
  }

  public static String encodePhone(String number) {
    try {
      return hiddenPhone(number);
    } catch (Exception ex) {
      return number;
    }
  }

  public static String hiddenPhone(String phone) {
    return PhoneNumberUtil.hiddenPhone(phone);
  }


  public static String encodeEmail(String email) {
    try {
      return hiddenEmail(email);
    } catch (Exception ex) {
      return email;
    }
  }

  public static String hiddenEmail(String email) {
    String maskid = "";
    try {
      String prefix = email.substring(0, email.lastIndexOf("@"));
      String postfix = email.substring(email.lastIndexOf("@"));
      char[] chars = prefix.toCharArray();

      for (int i = 0; i < prefix.length(); i++) {
        if (i == 0 || i == prefix.length() - 1) {   ////////
          maskid = maskid + chars[i];
        } else {
          maskid = maskid + "*";
        }
      }
      return maskid = maskid + postfix;
    } catch (Exception ex) {
      return maskid;
    }
  }

  public static String printRegex(String text) {

    int length = text.length();
    int textLength16 = 16;
    int textLength15 = 15;
    int textLength14 = 14;
    int textLength13 = 13;
    int textLength12 = 12;
    int textLength10 = 10;
    int textLength9 = 9;

    try {
      if (length == textLength15) {
        return text.replaceAll("(.{5})(?!$)", "$1-");
      }
      if (length == textLength14) {
        return text.substring(0, 4) + "-" + text.substring(4, 7) + "-" + text.substring(7, 10) + "-" + text.substring(10, length);
      }
      if (length == textLength13) {
        return text.substring(0, 5) + "-" + text.substring(5, 8) + "-" + text.substring(8, length);
      }
      if (length == textLength10) {
        return text.substring(0, 3) + "-" + text.substring(3, 7) + "-" + text.substring(7, length);
      }
      if (length == textLength12 || length == textLength16) {
        return text.replaceAll("(.{4})(?!$)", "$1-");
      }
      if (length == textLength9) {
        return text.replaceAll("(.{3})(?!$)", "$1-");
      }
      return text;
    } catch (Exception ex) {
      return text;
    }

  }
}
