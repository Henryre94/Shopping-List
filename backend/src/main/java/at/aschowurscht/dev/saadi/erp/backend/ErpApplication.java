package at.aschowurscht.dev.saadi.erp.backend;

import at.aschowurscht.dev.saadi.erp.backend.config.RSAKeyPair;
import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialsService;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(RSAKeyPair.class)
public class ErpApplication implements CommandLineRunner {

    final PubRepository pubRepository;
    final CredentialsService credentials;

    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
    }

    @Override
    public void run(String... args) {

        try {
            pubRepository.save(new Pub("Cafe SAADI 1090"));
            pubRepository.save(new Pub("Cafe SAADI 1160"));
            credentials.save("admin", "admin", true, null);
            credentials.save("1090", "1090", false, pubRepository.findPubByPubName("Cafe SAADI 1090"));
            credentials.save("1160", "1160", false, pubRepository.findPubByPubName("Cafe SAADI 1160"));
        } catch (Exception e) {
            System.err.println("Fehler beim einfügen des Datensatzes: " + e.getMessage());
        }
    }
}