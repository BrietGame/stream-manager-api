package com.streammanager.api.controller;

import com.streammanager.api.model.Overlay;
import com.streammanager.api.service.OverlayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/overlay")
public class OverlayController {
    
    System.Logger logger = System.getLogger(OverlayController.class.getName());
    
    OverlayService overlayService;
    
    @GetMapping
    public List<Overlay> getAllOverlays() {
        logger.log(System.Logger.Level.INFO, "Getting all overlays");
        return overlayService.getAll();
    }
    
    @GetMapping("/{id}")
    public Overlay getOverlay(String id) {
        logger.log(System.Logger.Level.INFO, "Getting overlay with id: " + id);
        return overlayService.get(id);
    }
    
    @PostMapping
    public Overlay createOverlay(Overlay overlay) {
        logger.log(System.Logger.Level.INFO, "Creating overlay: " + overlay);
        return overlayService.create(overlay);
    }
    
    @PutMapping("/{id}")
    public Overlay updateOverlay(String id, Overlay overlay) {
        logger.log(System.Logger.Level.INFO, "Updating overlay with id: " + id);
        overlay.setId(id);
        return overlayService.update(overlay);
    }
    
    @DeleteMapping("/{id}")
    public Overlay deleteOverlay(String id) {
        logger.log(System.Logger.Level.WARNING, "Deleting overlay with id: " + id);
        return overlayService.delete(id);
    }
}
