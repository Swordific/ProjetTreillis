 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis;

import java.util.HashMap;

/**
 *
 * @author Asus
 */
public class Numeroteur {

    //private Class type;
    private int maxIndex;
    private HashMap<Integer, Object> objectMap;
    private HashMap<Object, Integer> indexMap;

    public Numeroteur() {//(Class type) {
        //this.type = type;
        maxIndex = 0;
        objectMap = new HashMap<Integer, Object>();
        indexMap = new HashMap<Object, Integer>();
    }

//    public Numeroteur() {
//        this.type = Object;
//        this.maxIndex = 0;
//        this.objectMap = objectMap;
//        this.indexMap = indexMap;
//    }
    public Object getObject(int i) {
        if (objectMap.containsKey(i)) {
            return objectMap.get(i);
        }
        return null;
    }

    public int getIndex(Object o) {
        if (indexMap.containsKey(o)) {
            return indexMap.get(o);
        }
        return -1;
    }

    public int add(Object o) {
        //if (o.getClass() == type) {
        maxIndex++;
        objectMap.put(maxIndex, o);
        indexMap.put(o, maxIndex);
        return maxIndex;
        //}
        //return -1;
    }

    public void addAll(Object... os) {
        for (Object o : os) {
            this.add(o);
        }
    }

    public int getOrAdd(Object o) {
        if (!objectMap.containsValue(o)) {
            return this.add(o);
        }
        return indexMap.get(o);
    }

}
