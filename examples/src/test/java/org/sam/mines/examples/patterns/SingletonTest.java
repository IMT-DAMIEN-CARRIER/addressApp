package org.sam.mines.examples.patterns;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class Singleton {

    private static Singleton instance;

    private Singleton(){
    }

    public static Singleton getInstance(){

        if (instance == null){
            instance = new Singleton();
        }

        return instance;
    }
}

public class SingletonTest {

    @Test
    public void shouldGetSingleInstance(){
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        assertEquals(instance1, instance2);
    }
}

