package by.kraskovski.service.impl;

import by.kraskovski.model.Stock;
import by.kraskovski.repository.StockRepository;
import by.kraskovski.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock create(final Stock object) {
        return stockRepository.save(object);
    }

    @Override
    public Stock find(final int id) {
        return stockRepository.findOne(id);
    }

    @Override
    public Stock findBySpecialize(final String specialize) {
        return stockRepository.findBySpecialize(specialize);
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock update(final Stock object) {
        return stockRepository.save(object);
    }

    @Override
    public void delete(final int id) {
        stockRepository.delete(id);
    }
}
