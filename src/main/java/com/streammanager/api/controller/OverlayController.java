package com.streammanager.api.controller;

import com.streammanager.api.model.Overlay;
import com.streammanager.api.service.OverlayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/overlay")
public class OverlayController {
    
    OverlayService overlayService;
    
    @GetMapping
    public List<Overlay> getAllOverlays() {
        return overlayService.getAll();
    }
    
    @GetMapping("/{id}")
    public Overlay getOverlay(String id) {
        return overlayService.get(id);
    }
    
    @PostMapping
    public Overlay createOverlay(Overlay overlay) {
        return overlayService.create(overlay);
    }
    
    @PutMapping("/{id}")
    public Overlay updateOverlay(String id, Overlay overlay) {
        overlay.setId(id);
        return overlayService.update(overlay);
    }
    
    @DeleteMapping("/{id}")
    public Overlay deleteOverlay(String id) {
        return overlayService.delete(id);
    }
}
