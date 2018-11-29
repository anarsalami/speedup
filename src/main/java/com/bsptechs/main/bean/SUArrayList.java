/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean;

import java.util.ArrayList;

/**
 *
 * @author sarkhanrasullu
 */
public class SUArrayList<T> extends ArrayList<T> {

    @Override
    public T get(int index) {
        if (size() > 0) {
            return super.get(index);
        } else {
            return null;
        }
    }

    public T getByName(String name) {
        for (int i = 0; i < size(); i++) {
            T t = get(i);
            if (t.toString().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }
}
