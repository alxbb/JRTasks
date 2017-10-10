package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;

    public CustomTree() {
        this.root = new Entry<String>(String.valueOf(0));
    }

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
            System.out.println("i =" + i + " size = " + list.size());
        }
        System.out.println();
        System.out.println(list.size());
        System.out.println(list);
        System.out.println("Expected 7, actual is " + ((CustomTree) list).getParent("15"));

        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        System.out.println(list.size());
        list.remove("15");
        System.out.println(list.size());
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
        System.out.println(list.size());
        System.out.println(list);
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            availableToAddLeftChildren = leftChild == null ? true : false;
            availableToAddRightChildren = rightChild == null ? true : false;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        @Override
        public String toString() {
            return elementName;
        }
    }

//                     1
//               2          3
//           4     5     6      7
//         8 9  10 11  12 13  14 15


    @Override
    public boolean add(String s) {
        Entry<String> toAdd = new Entry<>(s);
        if (root == null) {
            root = toAdd;
            root.lineNumber = 0;
            root.checkChildren();
            return true;
        }

        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> current = root;
        queue.add(current);
        while (true) {
            if (!queue.isEmpty()) current = queue.poll();
            else break;

            if (current.isAvailableToAddChildren()) {
                if (current.availableToAddLeftChildren) {
                    current.leftChild = toAdd;
                } else {
                    current.rightChild = toAdd;
                }
                toAdd.parent = current;
                toAdd.lineNumber = current.lineNumber + 1;
                current.checkChildren();
                return true;
            } else {
                if (!current.availableToAddLeftChildren) queue.add(current.leftChild);
                if (!current.availableToAddRightChildren) queue.add(current.rightChild);
            }
        }
        return false;
    }


    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int size = 0;
        if (root == null) return 0;
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> current = root;
        queue.add(root);
        while (true) {
            if (!queue.isEmpty()) {
                current = queue.poll();
                if(current!=root)size++;
            } else break;
            if (!current.availableToAddLeftChildren) {
                queue.add(current.leftChild);
            }
            if (!current.availableToAddRightChildren) {
                queue.add(current.rightChild);
            }
        }

        return size;
    }

    public String getParent(String s){
        String out = null;
        if(root == null || s == null) return "0";
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> current = null;
        queue.add(root);
        while(true){
            if(!queue.isEmpty()) current = queue.poll();
            else break;
            if(current.elementName.equals(s))return current.parent.elementName;
            if(!current.availableToAddLeftChildren ) queue.add(current.leftChild);
            if(!current.availableToAddRightChildren) queue.add(current.rightChild);
        }
        return out;
    }

    @Override
    public boolean remove(Object o) {
        if (root == null || o == null) return false;
        String toRemove = (String) o;
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> current = null;
        queue.add(root);
        while (true) {
            if (!queue.isEmpty()) {
                current = queue.poll();
            } else break;
            if (toRemove.equals(current.elementName)) {
                if (current.parent == null) {
                    root = null;
                } else if (current.parent.leftChild == current) {
                    current.parent.leftChild = null;
                } else current.parent.rightChild = null;
                current.parent.checkChildren();
                return true;
            }
            if (!current.availableToAddLeftChildren ) queue.add(current.leftChild);
            if (!current.availableToAddRightChildren) queue.add(current.rightChild);
        }
        return false;
    }

    @Override
    public String toString() {
        int line = 0;
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> current = root;
//        System.out.println(root);
        queue.add(root);

        while (true) {
            if (!queue.isEmpty()) {
                current = queue.poll();
            } else break;

            if (line == current.lineNumber) {
                System.out.print(current + " ");
            } else {
                System.out.println("");
                line = current.lineNumber;
                System.out.print(current + " ");
            }
            if (!current.availableToAddLeftChildren) {
                queue.add(current.leftChild);
            }
            if (!current.availableToAddRightChildren) {
                queue.add(current.rightChild);
            }
        }

        return "";
    }
}
