package com.om.project_B.service;

import com.om.project_B.model.Hall;
import com.om.project_B.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public Hall createHall(Hall hall) {
        return hallRepository.save(hall);
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Hall getHallById(Long id) {
        return hallRepository.findById(id).orElse(null);
    }

    public Hall updateHall(Long id, Hall hall) {
        hall.setId(id); // Ensures the existing record is updated instead of creating a new one
        return hallRepository.save(hall);
    }

    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }
}