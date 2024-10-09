package com.mycompany.myapp.Components;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class PatientList {

    List<Map<String, Object>> list;

    public PatientList() {

        list = new ArrayList<>();

    }

    public void add(Map<String, Object> map) {

        list.add(map);

    }

    public Map<String, Object> get(int i) {

        return list.get(i);

    }

    public int size() {

        return list.size();

    }

    public void clear() {

        list.clear();

    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

}
