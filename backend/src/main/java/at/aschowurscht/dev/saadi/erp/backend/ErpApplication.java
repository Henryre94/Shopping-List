package at.aschowurscht.dev.saadi.erp.backend;

import at.aschowurscht.dev.saadi.erp.backend.config.RSAKeyPair;
import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialService;
import at.aschowurscht.dev.saadi.erp.backend.demands.DemandCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import at.aschowurscht.dev.saadi.erp.backend.vendors.VendorCRUDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(RSAKeyPair.class)
public class ErpApplication implements CommandLineRunner {


    final ProductCRUDRepository productCRUDRepository;

    final PubCRUDRepository pubCRUDRepository;

    final DemandCRUDRepository demandCRUDRepository;

    final VendorCRUDRepository vendorCRUDRepository;
    final CredentialService credentials;


	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        try {
            credentials.save("admin","admin",true);
            credentials.save("pub1","pub1",false);
            credentials.save("pub2","pub2",false);
            pubCRUDRepository.save(new Pub("Cafe SAADI 1090"));
            pubCRUDRepository.save(new Pub("Cafe SAADI 1160"));
            vendorCRUDRepository.save(new Vendor("Metro","Gurkgasse"));
            vendorCRUDRepository.save(new Vendor("Billa","Pilgrammgasse"));
        }catch (Exception e){
            System.err.println("Fehler beim einfügen des Datensatzes: " + e.getMessage());
        }

    }
}
