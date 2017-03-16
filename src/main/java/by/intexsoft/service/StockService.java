package by.intexsoft.service;

import by.intexsoft.model.Stock;

import java.util.List;

public interface StockService {

    Stock create(Stock object);

    Stock find(Integer id);

    Stock findBySpecialize(String specialize);

    List<Stock> findAll();

    Stock update(Integer id, Stock object);

    void delete(Integer id);
}
