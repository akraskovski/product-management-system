package by.intexsoft.service.impl;

import by.intexsoft.model.Store;
import by.intexsoft.repository.StoreRepository;
import by.intexsoft.service.ImageService;
import by.intexsoft.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ImageService imageService;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, ImageService imageService) {
        this.storeRepository = storeRepository;
        this.imageService = imageService;
    }

    @Override
    public Store create(Store object) {
        return storeRepository.save(object);
    }

    @Override
    public Store find(int id) {
        return storeRepository.findOne(id);
    }

    @Override
    public Store findByName(String name) {
        return storeRepository.findByName(name);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store update(Store object) {
        return storeRepository.save(object);
    }

    @Override
    public void delete(int id) {
        Store storeToDelete = storeRepository.findOne(id);
        if (isNotEmpty(storeToDelete.getLogo()))
            imageService.delete(storeToDelete.getLogo());
        storeRepository.delete(storeToDelete);
    }
}
