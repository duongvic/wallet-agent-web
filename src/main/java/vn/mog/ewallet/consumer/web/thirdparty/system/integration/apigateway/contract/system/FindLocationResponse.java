package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.util.ArrayList;
import java.util.Collection;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Location;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindLocationResponse extends MobiliserResponseType {

  private Collection<Location> locations;

  public Collection<Location> getLocations() {
    if (locations == null) {
      locations = new ArrayList<>();
    }
    return locations;
  }

  public void setLocations(Collection<Location> locations) {
    this.locations = locations;
  }
}
