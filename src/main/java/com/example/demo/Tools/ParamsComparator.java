package com.example.demo.Tools;

import java.util.Comparator;

public class ParamsComparator implements Comparator<String> {
	@Override
    public int compare(String lhs, String rhs) {
        return lhs.compareTo(rhs);
    }
}
