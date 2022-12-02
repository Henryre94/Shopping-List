package at.aschowurscht.dev.saadi.erp.backend;

import at.aschowurscht.dev.saadi.erp.backend.demands.DemandCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import at.aschowurscht.dev.saadi.erp.backend.vendors.VendorCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpApplication implements CommandLineRunner {

    @Autowired
    ProductCRUDRepository productCRUDRepository;
    @Autowired
    PubCRUDRepository pubCRUDRepository;
    @Autowired
    DemandCRUDRepository demandCRUDRepository;
    @Autowired
    VendorCRUDRepository vendorCRUDRepository;


	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        try {
            pubCRUDRepository.save(new Pub("Cafe SAADI 1090"));
            pubCRUDRepository.save(new Pub("Cafe SAADI 1160"));
            vendorCRUDRepository.save(new Vendor("Metro","Gurkgasse"));
            vendorCRUDRepository.save(new Vendor("Billa","Pilgrammgasse"));
        }catch (Exception e){
            System.err.println("Fehler beim einfügen des Datensatzes: " + e.getMessage());
        }

    }
}
