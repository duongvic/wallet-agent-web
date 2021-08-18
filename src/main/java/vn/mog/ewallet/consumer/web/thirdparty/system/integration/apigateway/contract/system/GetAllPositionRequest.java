package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Position;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetAllPositionRequest extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private List<Position> positions;

  public List<Position> getPositions() {
    return positions;
  }

  public void setPositions(List<Position> positions) {
    this.positions = positions;
  }
}
