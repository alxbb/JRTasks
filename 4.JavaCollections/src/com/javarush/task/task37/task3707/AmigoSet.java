package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet implements Set, Serializable, Cloneable{
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<E, Object>((int) Math.max(16, Math.ceil(collection.size()/.75f)));
        for(E e : collection){
            map.put(e, PRESENT);
        }
    }

    @Override
    public Object clone() {
        try {
            AmigoSet copy = (AmigoSet) super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    @Override
    public boolean add(Object o) {
        return map.put((E) o, PRESENT) == null? true : false;
    }

    @Override
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() { return map.isEmpty();}

    @Override
    public boolean contains(Object o) {return map.containsKey(o);}

    @Override
    public void clear() {map.clear();}

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null ? true : false;
    }


    private void writeObject(ObjectOutputStream out){
        try {
            float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
            int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");

            out.defaultWriteObject();
            out.writeFloat(loadFactor);
            out.writeInt(capacity);
            for(E e : map.keySet()) out.writeObject(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readObject(ObjectInputStream in){
        try {
            in.defaultReadObject();
            float loadFactor = in.readFloat();
            int capacity = in.readInt();
            E e;
            map = new HashMap<>(capacity, loadFactor);
//            while((e = (E) in.readObject())!= null) map.put(e, PRESENT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
