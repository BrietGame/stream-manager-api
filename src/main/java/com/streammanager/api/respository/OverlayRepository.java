package com.streammanager.api.respository;

import com.streammanager.api.model.Overlay;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OverlayRepository extends MongoRepository<Overlay, String> {
}
