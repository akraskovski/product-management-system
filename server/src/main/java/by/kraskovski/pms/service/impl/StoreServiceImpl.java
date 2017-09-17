package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.model.Store;
import by.kraskovski.pms.repository.StoreRepository;
import by.kraskovski.pms.service.ImageService;
import by.kraskovski.pms.service.StockService;
import by.kraskovski.pms.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StockService stockService;
    private final ImageService imageService;

    @Autowired
    public StoreServiceImpl(final StoreRepository storeRepository,
                            final ImageService imageService,
                            final StockService stockService) {
        this.storeRepository = storeRepository;
        this.imageService = imageService;
        this.stockService = stockService;
    }

    @Override
    public Store create(final Store object) {
        return storeRepository.save(object);
    }

    @Override
    public Store find(final String id) {
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
    public boolean addStock(final String storeId, final String stockId) {
        final Store store = storeRepository.findOne(storeId);
        final Stock stock = stockService.find(stockId);
        if (store == null || stock == null) {
            return false;
        }
        if (store.getStockList().contains(stock)) {
            return false;
        }
        store.getStockList().add(stock);
        storeRepository.save(store);
        return true;
    }

    @Override
    public boolean deleteStock(final String storeId, final String stockId) {
        final Store store = storeRepository.findOne(storeId);
        final Stock stock = stockService.find(stockId);
        if (store == null || stock == null) {
            return false;
        }
        if (store.getStockList().contains(stock)) {
            store.getStockList().remove(stock);
            storeRepository.save(store);
            return true;
        }
        return false;
    }

    @Override
    public Store update(final Store object) {
        return storeRepository.save(object);
    }

    @Override
    public void delete(final String id) {
        final Store storeToDelete = storeRepository.findOne(id);
        if (isNotEmpty(storeToDelete.getLogo())) {
            imageService.delete(storeToDelete.getLogo());
        }
        storeRepository.delete(storeToDelete);
    }

    @Override
    public void deleteAll() {
        storeRepository.deleteAll();
    }
}
