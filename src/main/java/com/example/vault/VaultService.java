package com.example.vault;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@ConfigurationProperties
@RefreshScope
@Slf4j
public class VaultService {

  @Autowired
  private ObjectMapper objectMapper;

  private Map<String, String> services;

  private Map<String, ServiceConfig> mappings;

  public ServiceConfig getConfig(String serviceName) {

    return mappings.get(serviceName);

  }

  public void setServices(Map<String, String> services) {

    if (!services.isEmpty()) {

      Map<String, ServiceConfig> serviceMappings = new HashMap<>();

      services.entrySet().stream().forEach(service -> {

        ServiceConfig serviceConfig;

        try {

          serviceConfig = objectMapper.readValue(service.getValue(), ServiceConfig.class);
          serviceMappings.put(service.getKey(), serviceConfig);

        } catch (JsonProcessingException e) {
          log.error(e.getMessage());
        }
      });

      this.mappings = serviceMappings;
    }

    this.services = services;
  }
}
