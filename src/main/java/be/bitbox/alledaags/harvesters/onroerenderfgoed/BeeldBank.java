package be.bitbox.alledaags.harvesters.onroerenderfgoed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public abstract class BeeldBank {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeeldBank.class);
    private static final String SCRIPT_FUNCTION = "setPrimaireFotoSrc(";

    public static String getImageSrc(RestTemplate restTemplate, String html) {
        String result = OnroerendErfgoed.BASE_URL + "static/img/gn_afbeelding.gif";

        var startIndex = html.indexOf(SCRIPT_FUNCTION);
        if (startIndex > 0) {
            startIndex += 20;
            var endIndex = html.indexOf("\"", startIndex);
            var url = html.substring(startIndex, endIndex);
            LOGGER.info("retrieving image from beeldbank: {}", url);

            try {
                var responseEntity = restTemplate.getForEntity(url, String.class);
                var body = Objects.requireNonNull(responseEntity.getBody());

                result = String.format("data:%s;base64,%s",
                        responseEntity.getHeaders().getContentType(),
                        Base64.getEncoder().encodeToString(body.getBytes(StandardCharsets.ISO_8859_1)));
            } catch (Exception exception) {
                LOGGER.error("Error retrieving image", exception);
            }
        }

        return result;
    }
}
