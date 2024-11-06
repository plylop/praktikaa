package task3_6;

import java.util.Scanner;
class InvalidFieldValueException extends Exception {
    public InvalidFieldValueException(String message) {
        super(message);
    }
}
class CustomIOException extends Exception {
    public CustomIOException(String message) {
        super(message);
    }
}

class Animal {
    private String name;

    public Animal(String name) throws InvalidFieldValueException {
        if (name == null || name.isEmpty()) {
            throw new InvalidFieldValueException("Имя животного не может быть пустым.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void makeSound() {
        System.out.println(name + " издает звук.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal animal = (Animal) obj;
        return name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Животное: " + name;
    }
}

class Dog extends Animal {
    public Dog(String name) throws InvalidFieldValueException {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " гавкает.");
    }

    public void jump() {
        System.out.println(getName() + " прыгает.");
    }

    public void run() {
        System.out.println(getName() + " бегает.");
    }

    public void bite() {
        System.out.println(getName() + " кусает.");
    }

    @Override
    public String toString() {
        return "Собака: " + getName();
    }
}

// Класс Щенок, наследуется от Собака
class Puppy extends Dog {
    public Puppy(String name) throws InvalidFieldValueException {
        super(name);
    }

    @Override
    public String toString() {
        return "Щенок: " + getName();
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите имя щенка: ");
            String name = scanner.nextLine();

            Puppy puppy = new Puppy(name);
            System.out.println(puppy);
            puppy.makeSound();
            puppy.jump();
            puppy.run();
            puppy.bite();

        } catch (InvalidFieldValueException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
