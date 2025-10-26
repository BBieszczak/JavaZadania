import java.time.LocalDate;
import java.util.Date;
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String imie;
    private String nazwisko;
    protected long numer_albumu;
    Date data = new Date();


    public Student() {
    }

    public Student(String imie, String nazwisko, long numer_albumu, LocalDate data) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numer_albumu = numer_albumu;
    }

    public Student(String imie, String nazwisko, long numer_albumu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numer_albumu = numer_albumu;
    }

    public long getNumer_albumu() {
        return numer_albumu;
    }

    public void setNumer_albumu(long numer_albumu) {
        this.numer_albumu = numer_albumu;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Override
    public String toString() {
        return "Student{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", numer_albumu=" + numer_albumu +
                ", data=" + data +
                '}';
    }
}
