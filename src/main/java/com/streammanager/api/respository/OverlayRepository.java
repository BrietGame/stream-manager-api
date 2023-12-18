package com.streammanager.api.respository;

import com.streammanager.api.model.Overlay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverlayRepository extends MongoRepository<Overlay, String> {
}
