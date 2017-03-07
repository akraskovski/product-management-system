package by.intexsoft.service.impl;

import by.intexsoft.model.Store;
import by.intexsoft.repository.StoreRepository;
import by.intexsoft.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store create(Store object) {
        return storeRepository.save(object);
    }

    @Override
    public Store find(Integer id) {
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
    public Store update(Integer id, Store object) {
        return storeRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        storeRepository.delete(id);
    }
}
