package uk.nhs.nhsbsa.vault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaultController {

  @Autowired
  private VaultService vaultService;

  @GetMapping
  public ServiceConfig getServiceConfig() {

    return vaultService.getConfig("PPC");
  }
}
