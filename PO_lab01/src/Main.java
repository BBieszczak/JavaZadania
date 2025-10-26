import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> studenci = new ArrayList<Student>();
        Scanner scanner = new Scanner(System.in);
        LocalDate data = LocalDate.now();
        String nazwaPliku = "studenci.bin";

        Student student01 = new Student("Jan", "Karpiński", 143543);
        Student student02 = new Student("Anna", "Piekarz", 125243, data);
        Student student03 = new Student("Wojciech", "Tatar", 542321, data);

        studenci.add(student01);
        studenci.add(student02);
        studenci.add(student03);

        Studenci stud = new Studenci();

        stud.wczytajZPliku(nazwaPliku);

        while (true) {
            System.out.println("Wybierz opcje \n 1 -> Dodanie studenta\n 2 -> Usuniecie studenta\n 3 -> Wyswietlenie danych studenta\n 4 -> Wyswietlanie danych studentow\n 5 -> Edytuj dane studenta\n 6 -> Wyjscie");
            int wybor = scanner.nextInt();
            switch (wybor) {
                case 1 -> stud.dodajStudenta();
                case 2 -> stud.usunStudenta();
                case 3 -> {
                    System.out.println("Podaj numer albumu");
                    long numer_albumu = scanner.nextLong();
                    stud.znajdzStudenta(numer_albumu);
                }
                case 4 -> {
                    System.out.println("Podaj numer albumu");
                    long numer_albumu = scanner.nextLong();
                    stud.znajdzStudentow(numer_albumu);
                }
                case 5 -> {
                    System.out.println("Podaj numer albumu");
                    long numer_albumu = scanner.nextLong();
                    stud.edytujStudent(numer_albumu);
                }
                case 6 -> {
                    stud.zapiszDoPliku(nazwaPliku);
                    exit(0);
                }
                default -> throw new IllegalStateException("Unexpected value: " + wybor);
            }
        }
    }


}