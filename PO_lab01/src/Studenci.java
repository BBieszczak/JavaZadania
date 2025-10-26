import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Studenci {
    ArrayList<Student> studenci = new ArrayList<Student>();
    Scanner scanner = new Scanner(System.in);

    public void dodajStudenta() {
        System.out.println("Podaj imie: ");
        String imie = scanner.next();


        System.out.println("Podaj nazwisko: ");
        String nazwsiko = scanner.next();

        System.out.println("Podaj numer albumu: ");
        long numer_albumu = scanner.nextLong();

        //Sprawdzanie czy numer albumu się powtarza
        for (Student x : studenci) {
            if (x.getNumer_albumu() == numer_albumu) {
                throw new IllegalArgumentException("Studnet o podanym numerze albumu już istnieje");

            }
        }


        studenci.add(new Student(imie, nazwsiko, numer_albumu));
    }

    public void usunStudenta() {
        System.out.println("Podaj numer albumu ucznia którego chcesz usunąć: ");
        long numer_albumu = scanner.nextLong();
        boolean usunieto = false;

        usunieto = studenci.removeIf(x -> x.numer_albumu == numer_albumu);


        if (usunieto) {
            System.out.println("Użytkownik o ID " + numer_albumu + " został usunięty.");
        } else {
            System.out.println("Nie znaleziono użytkownika o ID " + numer_albumu + ".");
        }
    }

    public Student znajdzStudenta(long numer_albumu) {
        for (Student x : studenci) {
            if (x.getNumer_albumu() == numer_albumu) {
                System.out.println(x.toString());
                return x;
            }
        }
        return null;
    }

    public void znajdzStudentow(long numer_albumu) {
        for (Student x : studenci) {
            if (x.getNumer_albumu() <= numer_albumu) {
                System.out.println(x.toString());
            }
        }
    }

    public void edytujStudent(long numer_albumu) {
        for (Student x : studenci) {
            if (x.getNumer_albumu() == numer_albumu) {
                System.out.println("Co chcesz edytować \n 1 -> Imie \n 2 -> Nazwisko");
                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.println("Podaj imie");
                        x.setImie(scanner.nextLine());
                    }
                    case 2 -> {
                        System.out.println("Podaj nazwisko");
                        x.setNazwisko(scanner.nextLine());
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + scanner.nextInt());
                }
            }
        }
    }

    public void zapiszDoPliku(String nazwaPliku) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            oos.writeObject(studenci);
            System.out.println("Zapisano listę studentów do pliku: " + nazwaPliku);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu: " + e.getMessage());
        }
    }

    public void wczytajZPliku(String nazwaPliku) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            studenci = (ArrayList<Student>) ois.readObject();
            System.out.println("Wczytano listę studentów z pliku: " + nazwaPliku);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd podczas wczytywania: " + e.getMessage());
        }
    }
}
