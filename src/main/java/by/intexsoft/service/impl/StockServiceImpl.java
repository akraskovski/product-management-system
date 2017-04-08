package by.intexsoft.service.impl;

import by.intexsoft.model.Stock;
import by.intexsoft.repository.StockRepository;
import by.intexsoft.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock create(Stock object) {
        return stockRepository.save(object);
    }

    @Override
    public Stock find(int id) {
        return stockRepository.findOne(id);
    }

    @Override
    public Stock findBySpecialize(String specialize) {
        return stockRepository.findBySpecialize(specialize);
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock update(Stock object) {
        return stockRepository.save(object);
    }

    @Override
    public void delete(int id) {
        stockRepository.delete(id);
    }
}
