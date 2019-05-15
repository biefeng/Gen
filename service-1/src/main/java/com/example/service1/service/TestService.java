package com.example.service1.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TestService {

    private RestTemplate restTemplate;

    {
        restTemplate = new RestTemplate();
    }

    public String getImageByBing(String idx) {
        try {
            Map<String, Object> entity = restTemplate.getForObject(MessageFormat.format("http://cn.bing.com/HPImageArchive.aspx?format=js&idx={0}&n=1&nc=1547049423589&pid=hp&video=1", idx), Map.class);

            Map<String, String> images = (Map<String, String>) ((ArrayList) entity.get("images")).get(0);
            String url = images.get("url");
            if (null != url && !url.equals("")) {
                return "https://cn.bing.com" + url;
            }
        } catch (Exception e) {
            return "https://goss.veer.com/creative/vcg/veer/1600water/veer-145511750.jpg";
        }
        return null;
    }

}

