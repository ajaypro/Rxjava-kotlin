package com.rxjava2.android.samples.ui.operators;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Ajay Deepak on 18-06-2019.
 */
public class mapvsflatmap {

    public static void main(String []args){
        Observable.just("item1").map( str -> {
            System.out.println("inside the map " + str);
            return str;
        }).subscribe(System.out::println);

        Observable.just("item2").flatMap( str -> {
            System.out.println("inside the flatMap " + str);
            return Observable.just(str + "+", str + "++" , str + "+++");
        }).subscribe(System.out::println);

        Map<String, ObjectA> map = new HashMap<>();

        Observable.just(map)
                .map(stringMap -> stringMap.entrySet())
                .flatMapIterable(entries -> entries)
                .map(entries -> new AbstractMap.SimpleEntry(entries.getKey(), entries.getValue().name) {})
                .toMap(e -> e)
                .subscribe(m ->{
                     System.out.println(m.entrySet().toString());
                });
    }

    class ObjectA{
        String name;
        String place;
    }
}

