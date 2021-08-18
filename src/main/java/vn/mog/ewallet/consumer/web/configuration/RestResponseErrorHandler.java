package vn.mog.ewallet.consumer.web.configuration;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

import java.io.IOException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestResponseErrorHandler implements ResponseErrorHandler {
  private final static Logger logger = LogManager.getLogger(RestResponseErrorHandler.class);

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    // Return true if HTTP response has ERROR.
    return (CLIENT_ERROR == httpResponse.getStatusCode().series()
        || SERVER_ERROR == httpResponse.getStatusCode().series());
  }

  @Override
  public void handleError(ClientHttpResponse httpResponse) throws IOException {
    if (SERVER_ERROR == httpResponse.getStatusCode().series()
        || HttpStatus.SC_UNAUTHORIZED == httpResponse.getStatusCode().value()) {
      // Handle error from Server side or Un-Authorized error
      int statusCode = httpResponse.getStatusCode().value();
      logger.error("RESPONE HTTP CODE: " + statusCode);
      logger.error("RESPONE: " + httpResponse.getStatusCode().name());
      throw new HttpStatusCodeException(httpResponse.getStatusCode(),
          httpResponse.getStatusCode().name()) {
      };
    }
  }

}