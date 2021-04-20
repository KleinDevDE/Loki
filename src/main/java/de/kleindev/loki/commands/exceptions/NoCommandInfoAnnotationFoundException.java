package de.kleindev.loki.commands.exceptions;

public class NoCommandInfoAnnotationFoundException extends Exception{
    public NoCommandInfoAnnotationFoundException(Class<?> clazz){
        super("Could not register command \""+clazz.getName()+"\"!\nMissing @COmmandInfo annotation!");
    }
}
