package pl.legol.futstats.service.ext;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class NinetyMinutesFacadeImpl implements NinetyMinutesFacade {

    private static final String EKSTRAKLASA_URL = "http://www.90minut.pl/skarb.php?id_rozgrywki=8069";

    private static final String MATCHES_URL = "http://www.90minut.pl/liga/0/liga8069.html";

    @Override
    public String loadTeams() {
        return getResponseBody(EKSTRAKLASA_URL);
    }

    @Override
    public String loadMatches() {
        return getResponseBody(MATCHES_URL);
    }

    private String getResponseBody(String url) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet req = new HttpGet(url);
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= HttpStatus.OK.value() && status < HttpStatus.MULTIPLE_CHOICES.value()) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new RuntimeException("Unexpected response status: " + status);
                    }
                }
            };
            return httpclient.execute(req, responseHandler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
