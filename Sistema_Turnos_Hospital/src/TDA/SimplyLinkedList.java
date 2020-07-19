/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import java.util.Iterator;

/**
 *
 * @author Josue
 * @param <E> Tipo de dato generico
 */
public class SimplyLinkedList<E> implements List<E>, Iterable<E> {

    private Node<E> first;
    private Node<E> last;
    private int current;

    public SimplyLinkedList() {
        first = last = null;
        current = 0;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> p = first;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public E next() {
                E tmp = p.data;
                p = p.next;
                return tmp;
            }
        };
        return it;
    }

    private class Node<E> {

        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }

    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        Node<E> n = new Node<>(e);
        if (isEmpty()) {
            first = last = n;
        } else {
            n.next = first;
            first = n;
        }
        current++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        }
        Node<E> p = new Node(e);
        if (isEmpty()) {
            first = last = p;
        } else {
            last.next = p;
            last = p;
        }
        current++;
        return true;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("La lista está vacía.");
        } else {
            return first.data;
        }
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("La lista está vacía.");
        } else {
            return last.data;
        }
    }

    @Override
    public int indexOf(E e) {
        if (e == null) {
            return -1;
        }
        int i = 0;
        for (Node<E> p = first; p != null; p = p.next) {
            if (p.data.equals(e)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean removeLast() {
        if (isEmpty()) {
            return false;
        }
        last.data = null; // help GC
        if (first == last) {
            first = last = null;
        } else {
            Node<E> prev = getPrevious(last);
            prev.next = null;
            last = prev;
        }
        current--;
        return true;
    }

    private Node<E> getPrevious(Node<E> p) {
        if (p == first) {
            return null;
        }
        Node<E> q = first;
        while (q != null && q.next != p) {
            q = q.next;
        }
        return q;
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        if (first == last) {
            first.data = null;//help GC
            first = last = null;

        } else {
            Node<E> p = first;
            first = first.next;
            p.data = null;//help GC
            p.next = null;
        }
        current--;
        return true;
    }

    private Node<E> getNode(int index) {
        Node<E> p = first;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    @Override
    public boolean insert(int index, E e) {
        if (e == null) {
            return false;
        }
        if (index >= current || index < 0) {
            throw new IndexOutOfBoundsException("Indice fuera del limite");
        }
        if (index == 0) {
            return addFirst(e);
        }
        if (index == current) {
            return addLast(e);
        }
        Node<E> q = new Node(e);
        Node<E> prev = getNode(index - 1);
        Node<E> sgte = prev.next;
        prev.next = q;
        q.next = sgte;
        return true;
    }

    @Override
    public boolean set(int index, E e) {
        if (e == null) {
            return false;
        }
        if (index >= current || index < 0) {
            throw new IndexOutOfBoundsException("Indice fuera del limite");
        }
        if (index == current - 1) {
            last.data = e;
        } else {
            Node<E> q = getNode(index);
            q.data = e;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= current) {
            throw new IndexOutOfBoundsException("El indice esta fuera del limite");
        }
        if (index == current - 1) {
            return last.data;
        } else {
            Node<E> p = getNode(index);
            return p.data;
        }
    }

    @Override
    public boolean contains(E e) {
        if (e == null) {
            return false;
        }
        for (Node<E> p = first; p != null; p = p.next) {
            if (p.data.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index > current - 1) {
            throw new IndexOutOfBoundsException("Indice fuera del limite.");
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == current - 1) {
            return removeLast();
        } else {
            Node<E> p = getNode(index - 1);
            Node<E> elim = p.next;
            Node<E> sgte = elim.next;
            elim.data = null;
            elim.next = null;
            p.next = sgte;
            return true;
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<E> p = first; p != last; p = p.next)//hasta el penultimo
        {
            sb.append(p.data);
            sb.append(",");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> iteratorStep(int start, int step) {
        Iterator<E> it = new Iterator<>() {
            int i = start;
            Node<E> p = getNode(i);

            @Override
            public boolean hasNext() {
                return i < current;
            }

            @Override
            public E next() {
                E tmp = p.data;
                int fin = i + 4;
                while (i < fin && p != null) {
                    p = p.next;
                    i++;
                }
                return tmp;
            }

        };
        return it;
    }
}
