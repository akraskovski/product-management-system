package by.intexsoft.service.impl;

import by.intexsoft.model.Stock;
import by.intexsoft.repository.StockRepository;
import by.intexsoft.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock create(Stock object) {
        return stockRepository.save(object);
    }

    @Override
    public Stock find(Integer id) {
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
    public Stock update(Integer id, Stock object) {
        return stockRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        stockRepository.delete(id);
    }
}
