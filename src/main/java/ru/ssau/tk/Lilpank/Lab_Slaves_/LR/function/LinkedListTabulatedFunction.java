package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable, Insertable, Removable {
    private static final long serialVersionUID = 6518678659890191671L;
    private Node head;
    private int count = 0;

    protected static class Node implements Serializable {
        private static final long serialVersionUID = 7508846417220233246L;
        public double x;
        public double y;
        public Node next;
        public Node prev;
    }


    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);

        if (xValues.length < 2) {
            throw new IllegalArgumentException("Длина меньше минимальной.");
        } else {
            for (int i = 0; i < xValues.length; i++) {
                this.addNode(xValues[i], yValues[i]);
            }
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Неправильные значения параметров.");
        } else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                this.addNode(xFrom, source.apply(xFrom));
                xFrom += step;
            }
        }
    }

    public void addNode(double x, double y) {
        Node newNode = new Node();
        newNode.x = x;
        newNode.y = y;
        if (head == null) {
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node last = head.prev;
            newNode.prev = last;
            newNode.next = head;
            last.next = newNode;
        }
        head.prev = newNode;
        count++;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    private Node getNode(int index) {
        checkIndex(index);
        Node indexNode;
        if (index <= (count / 2)) {
            indexNode = head;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.next;
                }
            }
        } else {
            indexNode = head.prev;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.prev;
                }
            }
        }
        throw new UnsupportedOperationException("");
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 | index >= count) {
            throw new IllegalArgumentException();
        } else {
            return getNode(index).x;
        }
    }

    @Override
    public double getY(int index) {
        if (index < 0 | index >= count) {
            throw new IllegalArgumentException();
        } else {
            return getNode(index).y;
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(node.x, node.y);
                if (node == head.prev) {
                    node = null;
                } else {
                    node = node.next;
                }
                return point;
            }
        };
    }

    @Override
    public void setY(int index, double valueY) {
        getNode(index).y = valueY;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x == x) {
                return i;
            } else {
                indexNode = indexNode.next;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.y == y) {
                return i;
            } else {
                indexNode = indexNode.next;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException();
        } else {
            Node indexNode = head;
            for (int i = 0; i < count; i++) {
                if (indexNode.x < x) {
                    indexNode = indexNode.next;
                } else {
                    if (i == 0) {
                        return 0;
                    }
                    return i - 1;
                }
            }
            return getCount();
        }
    }

    private Node floorNodeOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X меньше минимального значения в списке.");
        }
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x <= x) {
                indexNode = indexNode.next;
            } else {
                return indexNode.prev;
            }
        }
        return head.prev;
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        }
        return interpolate(x, floorIndexOfX(x));
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        if (x < leftNode.x || x > rightNode.x) {
            throw new InterpolationException("X не находится внутри интервала интерполирования.");
        } else {
            if (head.x == head.prev.x) {
                return head.y;
            } else {
                return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
            }
        }
    }

    @Override
    public void insert(double x, double y) {
        if (count == 0) {
            addNode(x, y);
        } else if (indexOfX(x) != -1) {
            setY(indexOfX(x), y);
        } else {
            int index = x < head.x ? 0 : floorIndexOfX(x);
            Node newNode = new Node();
            if (index == 0 || index == count) {
                newNode.next = head;
                newNode.prev = head.prev;
                newNode.x = x;
                newNode.y = y;
                head.prev.next = newNode;
                if (index == 0) {
                    head = newNode;
                } else {
                    head.prev = newNode;
                }
            } else {
                Node previous = getNode(index);
                newNode.next = previous.next;
                newNode.prev = previous;
                newNode.x = x;
                newNode.y = y;
                previous.next = newNode;
                newNode.next.prev = newNode;
            }
            count++;
        }
    }

    @Override
    public void remove(int index) {
        if (count <= 2) {
            throw new UnsupportedOperationException("Список должен состоять минимум из 2 точек.");
        }
        Node executedNode = getNode(index);
        if (index == 0) {
            head = executedNode.next;
            head.prev = executedNode.prev;
        }
        executedNode.prev.next = executedNode.next;
        executedNode.next.prev = executedNode.prev;
        count--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > count - 1) {
            throw new IndexOutOfBoundsException("Индекс за пределами списка.");
        }
    }
}
