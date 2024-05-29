package com.example.orders.models;

import java.util.Iterator;

public class OrderIterator<Order> implements Iterable<Order> {

    private Order[] arrayList;
    private int currentSize;

    public OrderIterator(Order[] newArray) {
        this.arrayList = newArray;
        this.currentSize = arrayList.length;
    }

    @Override
    public Iterator<Order> iterator() {
        Iterator<Order> it = new Iterator<Order>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public Order next() {
                return arrayList[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
