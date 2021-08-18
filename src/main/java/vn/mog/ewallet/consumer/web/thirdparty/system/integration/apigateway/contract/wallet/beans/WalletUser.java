package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletUser implements Serializable {

  private String msisdn;
  private String name;
  private String id_number; // "CMT, Mã thẻ Căn cước công dân"
}