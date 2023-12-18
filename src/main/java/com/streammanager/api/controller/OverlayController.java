package com.streammanager.api.controller;

import com.streammanager.api.model.Overlay;
import com.streammanager.api.response.ResponseJson;
import com.streammanager.api.service.OverlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/overlay")
public class OverlayController {
    
    System.Logger logger = System.getLogger(OverlayController.class.getName());
    
    OverlayService overlayService;
    
    @Autowired
    public OverlayController(OverlayService overlayService) {
        this.overlayService = overlayService;
    }
    
    @GetMapping
    public ResponseJson<List<Overlay>> getAllOverlays() {
        logger.log(System.Logger.Level.INFO, "Getting all overlays");
        return new ResponseJson<>(overlayService.getAll(), 200);
    }
    
    @GetMapping("/{id}")
    public ResponseJson<Overlay> getOverlay(String id) {
        logger.log(System.Logger.Level.INFO, "Getting overlay with id: " + id);
        return new ResponseJson<>(overlayService.get(id), 200);
    }
    
    @PostMapping
    public ResponseJson<Overlay> createOverlay(Overlay overlay) {
        logger.log(System.Logger.Level.INFO, "Creating overlay: " + overlay);
        return new ResponseJson<>(overlayService.create(overlay), 200);
    }
    
    @PutMapping("/{id}")
    public ResponseJson<Overlay> updateOverlay(String id, Overlay overlay) {
        logger.log(System.Logger.Level.INFO, "Updating overlay with id: " + id);
        overlay.setId(id);
        return new ResponseJson<>(overlayService.update(overlay), 200);
    }
    
    @DeleteMapping("/{id}")
    public void deleteOverlay(@PathVariable String id) {
        logger.log(System.Logger.Level.WARNING, "Deleting overlay with id: " + id);
        overlayService.delete(id);
    }
}
