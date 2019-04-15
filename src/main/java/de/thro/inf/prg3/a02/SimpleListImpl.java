package de.thro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {

    private static Element head;
    private int size;

    public SimpleListImpl() {
        head = null;
    }

    @Override
    public void add(Object o) {
        Element newElement = new Element(o);
        if (head == null) {
            head = newElement;
        }
        else {
            Element current = head;
            while(current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newElement);
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList result = new SimpleListImpl();
        for(Object o: this) {
            if(filter.include(o)) {
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleIteratorImpl();
    }

    private class SimpleIteratorImpl implements Iterator<Object> {

        private Element current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object temp = current.getItem();
            current = current.getNext();
            return temp;
        }
    }

    private static class Element {
        private Object item;
        private Element next;

        Element(Object item) {
            this.item = item;
        }

        Element getNext() {
            return next;
        }

        void setNext(Element next) {
            this.next = next;
        }

        Object getItem() {
            return item;
        }
    }
}
