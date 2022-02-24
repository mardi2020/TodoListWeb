package com.mardi2020.todoapp.geoLocation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class GeoLocationService {

    @Value("${geolocationAccessKey}")
    private String accessKey;

    @Value("${geolocationSecretKey}")
    private String secretKey;

    private final CloseableHttpClient httpClient;

    private String getGeoLocationInfo(String ip) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String hostName = "https://geolocation.apigw.ntruss.com";
        String requestUrl = "/geolocation/v2/geoLocation";

        UriComponents uri = UriComponentsBuilder.newInstance()
                .queryParam("ip", ip)
                .queryParam("ext", "t")
                .queryParam("responseFormatType", "json")
                .build();

        String baseUrl = requestUrl + uri;
        String fullUrl = hostName + requestUrl + uri;

        // http header
        final HttpGet request = new HttpGet(fullUrl);
        String timeStamp = getTimestamp();
        request.setHeader("x-ncp-apigw-timestamp",timeStamp);
        request.setHeader("x-ncp-iam-access-key",accessKey);
        request.setHeader("x-ncp-apigw-signature-v2", makeSignature("GET", baseUrl, timeStamp, accessKey, secretKey));

        final CloseableHttpResponse response = httpClient.execute(request);

        final StringBuffer buffer = new StringBuffer();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        System.out.println("buffer = " + buffer);
        return buffer.toString();
    }

    public String getTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }

    public String makeSignature(final String method, final String baseString, final String timestamp, final String accessKey, final String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";                       // one space
        String newLine = "\n";                    // new line

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(baseString)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(rawHmac);
    }

    public GeoResults fiteringInfo(String ip) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GeoResults geoResults = new GeoResults();

        try {

            String results = getGeoLocationInfo(ip);
            geoResults = mapper.readValue(results, GeoResults.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("geoResults = " + geoResults);
        return geoResults;
    }
}
