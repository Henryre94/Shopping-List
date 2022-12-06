package at.aschowurscht.dev.saadi.erp.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public class RSAKeyPais {
    public record RSAKeyPair(RSAPublicKey publicKey, RSAPrivateKey privateKey) { }
}
