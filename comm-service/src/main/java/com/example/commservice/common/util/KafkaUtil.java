package com.example.commservice.common.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class KafkaUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaUtil.class);

    private static void sendMsg(String topic, String key, String value) throws IOException {
        InputStream ins = ClassLoader.getSystemClassLoader().getResourceAsStream("kafka.properties");
        Properties prop = new Properties();
        prop.load(ins);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);
        Future<RecordMetadata> send = producer.send(new ProducerRecord<>(topic, key, value));
        if (send.isDone()) {
            try {
                RecordMetadata recordMetadata = send.get(5, TimeUnit.SECONDS);
                LOGGER.debug("消息成功发送到TOPIC: {}, OFFSET: {}, PARTITION: {}", recordMetadata.topic(), recordMetadata.offset(), recordMetadata.partition());
            } catch (Exception e) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(key, value);
                LOGGER.warn("消息发送失败: {}", jsonObject.toJSONString());
                e.printStackTrace();
            }
        }
        producer.close();
    }

    public static void main(String[] args) {
        try {
            String msg = "{\n" +
                    "  \"configs\": [\n" +
                    "    {\n" +
                    "      \"server\": \"139.180.223.235\",\n" +
                    "      \"server_port\": 8388,\n" +
                    "      \"password\": \"272232\",\n" +
                    "      \"method\": \"aes-256-cfb\",\n" +
                    "      \"plugin\": \"\",\n" +
                    "      \"plugin_opts\": \"\",\n" +
                    "      \"plugin_args\": \"\",\n" +
                    "      \"remarks\": \"\",\n" +
                    "      \"timeout\": 5\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"strategy\": null,\n" +
                    "  \"index\": 0,\n" +
                    "  \"global\": false,\n" +
                    "  \"enabled\": false,\n" +
                    "  \"shareOverLan\": false,\n" +
                    "  \"isDefault\": false,\n" +
                    "  \"localPort\": 1080,\n" +
                    "  \"portableMode\": true,\n" +
                    "  \"pacUrl\": null,\n" +
                    "  \"useOnlinePac\": false,\n" +
                    "  \"secureLocalPac\": true,\n" +
                    "  \"availabilityStatistics\": false,\n" +
                    "  \"autoCheckUpdate\": true,\n" +
                    "  \"checkPreRelease\": false,\n" +
                    "  \"isVerboseLogging\": true,\n" +
                    "  \"logViewer\": {\n" +
                    "    \"topMost\": false,\n" +
                    "    \"wrapText\": false,\n" +
                    "    \"toolbarShown\": false,\n" +
                    "    \"Font\": \"Consolas, 8pt\",\n" +
                    "    \"BackgroundColor\": \"Black\",\n" +
                    "    \"TextColor\": \"White\"\n" +
                    "  },\n" +
                    "  \"proxy\": {\n" +
                    "    \"useProxy\": false,\n" +
                    "    \"proxyType\": 0,\n" +
                    "    \"proxyServer\": \"\",\n" +
                    "    \"proxyPort\": 0,\n" +
                    "    \"proxyTimeout\": 3\n" +
                    "  },\n" +
                    "  \"hotkey\": {\n" +
                    "    \"SwitchSystemProxy\": \"\",\n" +
                    "    \"SwitchSystemProxyMode\": \"\",\n" +
                    "    \"SwitchAllowLan\": \"\",\n" +
                    "    \"ShowLogs\": \"\",\n" +
                    "    \"ServerMoveUp\": \"\",\n" +
                    "    \"ServerMoveDown\": \"\",\n" +
                    "    \"RegHotkeysAtStartup\": false\n" +
                    "  }\n" +
                    "}";
            sendMsg("test", "1", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
