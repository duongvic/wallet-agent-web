package vn.mog.ewallet.consumer.web.util.tools;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

  private static final Logger LOG = LogManager.getLogger(JsonUtil.class);

  private static ObjectMapper objectMapper = new ObjectMapper();


  public static String objectToJson(Object obj) {
    try {
      String json = objectMapper.writeValueAsString(obj);
      return json;
    } catch (JsonProcessingException e) {
      LOG.error("JsonProcessingException", e);
    }
    return null;
  }

  public static <T> T jsonToObject(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (JsonGenerationException e) {
      LOG.error("JsonGenerationException", e);
    } catch (JsonMappingException e) {
      LOG.error("JsonMappingException", e);
    } catch (IOException e) {
      LOG.error("IOException", e);
    }
    return null;
  }
}
