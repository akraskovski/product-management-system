package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.model.Store;
import by.kraskovski.pms.repository.StoreRepository;
import by.kraskovski.pms.service.ImageService;
import by.kraskovski.pms.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ImageService imageService;

    @Autowired
    public StoreServiceImpl(final StoreRepository storeRepository, final ImageService imageService) {
        this.storeRepository = storeRepository;
        this.imageService = imageService;
    }

    @Override
    public Store create(final Store object) {
        return storeRepository.save(object);
    }

    @Override
    public Store find(final int id) {
        return storeRepository.findOne(id);
    }

    @Override
    public Store findByName(final String name) {
        return storeRepository.findByName(name);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store update(final Store object) {
        return storeRepository.save(object);
    }

    @Override
    public void delete(final int id) {
        final Store storeToDelete = storeRepository.findOne(id);
        if (isNotEmpty(storeToDelete.getLogo())) {
            imageService.delete(storeToDelete.getLogo());
        }
        storeRepository.delete(storeToDelete);
    }
}
