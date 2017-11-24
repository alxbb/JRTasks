package com.javarush.task.task35.task3507;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Set<? extends Animal> allAnimals = getAllAnimals((Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data").substring(1));
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws IOException {
        Set<Animal> set = new HashSet<>();

        List<String> list = Files
                .list(Paths.get(pathToAnimals))
                .filter(path -> !path.endsWith(".class"))
                .map(path -> path.toString())
                .collect(Collectors.toList());

        for (String s : list) {
            try {
                Class clazz = new myClassLoader().load(s, Solution.class.getPackage().getName());
                for (Class intrfce : clazz.getInterfaces()) {
                    if (Animal.class.isAssignableFrom(intrfce)) {
                        for (Constructor constructor : clazz.getDeclaredConstructors()) {
                            if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterCount() == 0) {
                                Animal obj = (Animal) constructor.newInstance();
                                set.add(obj);
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                continue;
            } catch (InstantiationException e) {
                continue;
            } catch (InvocationTargetException e) {
                continue;
            }
        }
        return set;
    }

    public static class myClassLoader extends ClassLoader {
        public Class<?> load(String classPath, String packageName) {
            try {
                Path path = Paths.get(classPath);
                String className = packageName + ".data." + path.getFileName().toString().replace(".class","");

                System.out.println(" className = " + className);
                byte[] buf = Files.readAllBytes(path);
                return defineClass(className, buf, 0, buf.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

