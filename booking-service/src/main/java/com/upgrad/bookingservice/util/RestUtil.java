package com.upgrad.bookingservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Log4j2
public class RestUtil {
    private static RestTemplate restTemplate = new RestTemplate();

    public static ResponseEntity<Integer> callPOSTRequestWithJson(String url, String jsonBody, Map<String, Object> headersMap) throws JsonProcessingException {
        log.info("callPOSTRequestWithJson Method called for url  : " + url +  ", body: "  + jsonBody + ", headers: " + headersMap);

        HttpHeaders headers = createHttpHeaders(headersMap);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(jsonBody, headers);

        ResponseEntity<Integer> response = restTemplate.postForEntity(url, request , Integer.class);

        return response;
    }


    private static HttpHeaders createHttpHeaders(Map <String, Object> headersMap)
    {
        HttpHeaders headers = new HttpHeaders();

        if(headersMap != null) {
            //Adding headers
            for(String key : headersMap.keySet()) {
                String value = null;
                if(headersMap.get(key) != null) {
                    value = headersMap.get(key).toString();
                }
                if(value != null)
                    headers.set(key, value);
            }
        }
        return headers;
    }


}
