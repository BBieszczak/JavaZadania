import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Kalkulator {
    int kalkulatorSzeokosc = 360;
    int kalkulatorWysokosc = 540;

    Color jasnySzary = new Color(212, 212, 210);
    Color ciemnySzary = new Color(80, 80, 80);
    Color czarny = new Color(28, 28, 28);
    Color pomaranczowy = new Color(255, 149, 0);

    //A+B, A-B, A*B, A/B
    String A = "0";
    String operator = null;
    String B = null;

    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};


    JFrame frame = new JFrame("Kalkulator");
    JLabel displayLable = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();


    Kalkulator() {
        frame.setVisible(true);
        frame.setSize(kalkulatorSzeokosc, kalkulatorWysokosc);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); // Możliwość rozciągania okna
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Jeśli kliknie się X to program się przerywa
        frame.setLayout(new BorderLayout());

        displayLable.setBackground(czarny);
        displayLable.setForeground(Color.white);
        displayLable.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLable.setHorizontalAlignment(JLabel.RIGHT);//tekst po lewej
        displayLable.setText("0"); // tekst w etykiecie stawia się na 0
        displayLable.setOpaque(true); // pozwala ustawić kolor (domyślnie przezroczysty)

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLable);
        frame.add(displayPanel, BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(czarny);
        frame.add(buttonsPanel);

        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String wartoscPrzycisku = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(wartoscPrzycisku);
            button.setFocusable(false);
            button.setBorder(new LineBorder(Color.BLACK));

            if (Arrays.asList(topSymbols).contains(wartoscPrzycisku)) {
                button.setBackground(jasnySzary);
                button.setForeground(czarny);
            } else if (Arrays.asList(rightSymbols).contains(wartoscPrzycisku)) {
                button.setBackground(pomaranczowy);
                button.setForeground(Color.white);
            } else {
                button.setForeground(Color.white);
                button.setBackground(czarny);
            }

            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String wartoscPrzycisku = button.getText();
                    if
                    (Arrays.asList(rightSymbols).contains(wartoscPrzycisku)) {
                        if (wartoscPrzycisku == "=") {
                            if (A != null) {
                                B = displayLable.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if (operator == "+") {
                                    displayLable.setText(zmianaZnaku(numA + numB));
                                } else if (operator == "-") {
                                    displayLable.setText(zmianaZnaku(numA - numB));
                                } else if (operator == "×") {
                                    displayLable.setText(zmianaZnaku(numA * numB));
                                } else if (operator == "÷") {
                                    displayLable.setText(zmianaZnaku(numA / numB));
                                }
                            }
                        } else if (("÷×-+").contains(wartoscPrzycisku)) {
                            if (operator == null) {
                                A = displayLable.getText();
                                displayLable.setText("0");
                                B = "0";
                            }
                            operator = wartoscPrzycisku;
                        }
                    } else if (Arrays.asList(topSymbols).contains(wartoscPrzycisku)) {
                        if (wartoscPrzycisku == "AC") {
                            clearAll();
                            displayLable.setText("0");
                        } else if (wartoscPrzycisku == "+/-") {
                            double numDisplay = Double.parseDouble(displayLable.getText()); // pobiera tekst z okna i zmienia na typ double
                            numDisplay *= -1;
                            displayLable.setText(zmianaZnaku(numDisplay));
                        } else if (wartoscPrzycisku == "%") {
                            double numDisplay = Double.parseDouble(displayLable.getText()); // pobiera tekst z okna i zmienia na typ double
                            numDisplay /= 100;
                            displayLable.setText(zmianaZnaku(numDisplay));
                        }
                    } else {
                        //digits or .
                        if (wartoscPrzycisku == ".") {
                            if (!displayLable.getText().contains(wartoscPrzycisku))// pozwala dodać . tylko raz
                            {
                                displayLable.setText(displayLable.getText() + wartoscPrzycisku);
                            }
                        } else if ("0123456789".contains(wartoscPrzycisku)) {
                            if (displayLable.getText() == "0") {
                                displayLable.setText(wartoscPrzycisku);
                            } else {
                                displayLable.setText(displayLable.getText() + wartoscPrzycisku);
                            }
                        } else if (wartoscPrzycisku == "√") {
                            double numDisplay = Double.parseDouble(displayLable.getText());
                            numDisplay = Math.sqrt(numDisplay);
                            displayLable.setText(zmianaZnaku(numDisplay));
                        }
                    }
                }
            });

        }

    }

    void clearAll() {
        A = "0";
        operator = null;
        B = null;
    }

    String zmianaZnaku(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        } else {
            return Double.toString(numDisplay);
        }
    }
}

