package com.online.store.utils;

import com.online.store.model.Item;

import java.util.List;
import java.util.stream.DoubleStream;

public class PriceCount {

    public static Double getPrice(List<Item> list) {
        return list
                .stream()
                .flatMapToDouble(x -> DoubleStream.of(x.getPrice()))
                .sum();
    }
}
