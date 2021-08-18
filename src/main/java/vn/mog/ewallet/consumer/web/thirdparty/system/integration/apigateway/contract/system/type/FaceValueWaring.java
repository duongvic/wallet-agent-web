package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

import java.util.HashMap;
import java.util.List;


public class FaceValueWaring {


  private HashMap<String, List<Integer>> faceValueWaring;

  public HashMap<String, List<Integer>> getFaceValueWaring() {
    return (faceValueWaring == null) ? new HashMap<String, List<Integer>>() : faceValueWaring;
  }

  public void setFaceValueWaring(HashMap<String, List<Integer>> faceValueWaring) {
    this.faceValueWaring = faceValueWaring;
  }
}
