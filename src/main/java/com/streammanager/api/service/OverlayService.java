package com.streammanager.api.service;

import com.streammanager.api.model.Overlay;
import com.streammanager.api.respository.OverlayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverlayService {
    
    OverlayRepository overlayRepository;
    
    public List<Overlay> getAll() {
        return overlayRepository.findAll();
    }
    
    public Overlay get(String id) {
        return overlayRepository.findById(id).orElse(null);
    }
    
    public Overlay create(Overlay overlay) {
        return overlayRepository.save(overlay);
    }
    
    public Overlay update(Overlay overlay) {
        return overlayRepository.save(overlay);
    }
    
    public Overlay delete(String id) {
        Overlay overlay = overlayRepository.findById(id).orElse(null);
        overlayRepository.deleteById(id);
        return overlay;
    }
}
