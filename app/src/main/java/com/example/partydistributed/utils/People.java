package com.example.partydistributed.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class People {
    private long id;
    private String name;

    public People(){}
    public People(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return "id: " + this.id + " name: " + this.name;
    }
}
