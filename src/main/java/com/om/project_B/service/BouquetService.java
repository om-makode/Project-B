package com.om.project_B.service;


import com.om.project_B.model.Bouquet;
import com.om.project_B.repository.BouquetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BouquetService {

    private final BouquetRepository repository;

    public BouquetService(BouquetRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Bouquet createBouquet(Bouquet bouquet) {
        return repository.save(bouquet);
    }

    // READ ALL
    public List<Bouquet> getAllBouquets() {
        return repository.findAll();
    }

    // READ ONE
    public Bouquet getBouquetById(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bouquet not found with id: " + id));
    }

    // UPDATE
    public Bouquet updateBouquet(UUID id, Bouquet updatedData) {
        Bouquet existing = getBouquetById(id);

        existing.setName(updatedData.getName());
        existing.setFlowersUsed(updatedData.getFlowersUsed());
        existing.setSellerName(updatedData.getSellerName());
        existing.setSellerAddress(updatedData.getSellerAddress());
        existing.setLatitude(updatedData.getLatitude());
        existing.setLongitude(updatedData.getLongitude());
        existing.setPrice(updatedData.getPrice());
        existing.setAvailability(updatedData.getAvailability());
        existing.setSizeWidth(updatedData.getSizeWidth());
        existing.setSizeHeight(updatedData.getSizeHeight());
        existing.setMainScreenImage(updatedData.getMainScreenImage());
        existing.setGalleryImages(updatedData.getGalleryImages());
        existing.setDescription(updatedData.getDescription());
        
        // NOTE: rating and reviewCount are explicitly skipped here.

        return repository.save(existing);
    }

    // DELETE
    public void deleteBouquet(UUID id) {
        Bouquet existing = getBouquetById(id);
        repository.delete(existing);
    }
}
