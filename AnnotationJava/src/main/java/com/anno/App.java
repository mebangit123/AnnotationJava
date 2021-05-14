package com.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class App {

    @Override
    @MethodInfo(author = "Narayan", comments = "Main method", date = "May 13 2021", revision = 1)
    public String toString(){
        return "Overridden toString method";
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "May 17 2021")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }


    public static void main(String[] args) {
        try{
            for(Method method : App.class.getMethods()){
                if(method.isAnnotationPresent(MethodInfo.class)){
                    try{
                        for(Annotation anno : method.getDeclaredAnnotations()){
                            System.out.println("Annotation in method'" + method + "' : " + anno);
                        }
                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                        if(methodAnno.revision() == 1){
                            System.out.println("Method with revision no 1 = " + method);
                        }
                    } catch ( Throwable ex){
                        ex.printStackTrace();
                    }
                }
            }
        } catch(SecurityException e){
            e.printStackTrace();
        }
    }
}
                        
    