package com.example.vault;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceConfig {

  private String apiKey;

  private String replyTo;

  private Map<String, String> templates;
}
