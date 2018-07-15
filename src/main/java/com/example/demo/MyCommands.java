package com.example.demo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MyCommands{

    @ShellMethod("Add two integers together")
    public int add(int a, int b){
        return a + b;
    }

    @ShellMethod("a miuns b")
    public int minus(int a, int b){
        return a - b;
    }
}