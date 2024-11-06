package task7_8_9;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class AntDrawing extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillOval(50, 50, 30, 30);
        g2d.fillOval(40, 80, 50, 40);
        g2d.fillOval(30, 110, 70, 40);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(40, 100, 20, 120);
        g2d.drawLine(90, 100, 110, 120);
        g2d.drawLine(40, 130, 20, 150);
        g2d.drawLine(90, 130, 110, 150);
        g2d.drawLine(40, 140, 20, 160);
        g2d.drawLine(90, 140, 110, 160);
        g2d.drawLine(65, 50, 55, 30);
        g2d.drawLine(80, 50, 90, 30);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(60, 60, 5, 5);
        g2d.fillOval(75, 60, 5, 5);
    }

    private Shape createWingShape(int x, int y, int xDirection, int yDirection) {
        GeneralPath wing = new GeneralPath();
        wing.moveTo(x, y);
        wing.quadTo(x + 30 * xDirection, y - 50 * yDirection, x + 60 * xDirection, y);
        wing.quadTo(x + 30 * xDirection, y + 50 * yDirection, x, y + 100 * yDirection);
        wing.closePath();
        return wing;
    }
}

class Node {
    String data;
    Node next;

    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public void add(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void remove(String value) {
        if (head == null) return;
        if (head.data.equals(value)) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && !current.next.data.equals(value)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void removeFrom(String value) {
        if (head == null) return;

        if (head.data.equals(value)) {
            head = null;
            return;
        }

        Node current = head;
        while (current.next != null && !current.next.data.equals(value)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = null;
        }
    }

    public void clear() {
        head = null;
    }

    public String printList() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString();
    }
}

class MathGame {
    private List<Card> cards;
    private int[][] board;

    public MathGame() {
        cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(new Card(i));
            }
        }
        Collections.shuffle(cards);
        board = new int[5][5];
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int filledCells = 0;
        while (filledCells < 25) {
            if (cards.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Больше нет карт!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            Card drawnCard = cards.remove(cards.size() - 1);
            String input = JOptionPane.showInputDialog("Вытащенная карта: " + drawnCard + "\nВведите строку (0-4) и столбец (0-4):");
            String[] inputs = input.split(" ");
            int row = Integer.parseInt(inputs[0]);
            int col = Integer.parseInt(inputs[1]);

            if (row < 0 || row >= 5 || col < 0 || col >= 5 || board[row][col] != 0) {
                JOptionPane.showMessageDialog(null, "Неверная позиция или ячейка уже заполнена. Попробуйте снова.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            board[row][col] = drawnCard.getNumber();
            filledCells++;
            printBoard();
        }
        JOptionPane.showMessageDialog(null, "Игра окончена!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
        printBoard();
    }

    private void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Игровая доска", JOptionPane.INFORMATION_MESSAGE);
    }
}

class Card {
    private final int number;

    public Card(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}

public class MainApp {
    public static void main(String[] args) {
        // Меню программы
        while (true) {
            String[] options = {"Рисование муравья", "Задание 8", "Игра с картами", "Выход"};
            int choice = JOptionPane.showOptionDialog(null, "Выберите задачу:", "Меню", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 3 || choice == -1) {
                break; // Выход из программы
            }

            switch (choice) {
                case 0:
                    // Окно с рисованием муравья
                    JFrame frame = new JFrame("Butterfly Drawing");
                    AntDrawing panel = new AntDrawing();
                    frame.add(panel);
                    frame.setSize(400, 400);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    break;
                case 1:
                    // Окно для Задания 8 с выводом списка в JTextArea
                    LinkedList list = new LinkedList();
                    list.add("A");
                    list.add("B");
                    list.add("C");
                    list.add("D");
                    list.add("E");

                    // Создаём JTextArea для отображения информации
                    JTextArea textArea = new JTextArea(10, 30);
                    textArea.setEditable(false);
                    textArea.append("Изначальный список:\n" + list.printList() + "\n");

                    // Удаляем элементы и выводим изменения
                    list.remove("C");
                    textArea.append("\nПосле удаления элемента 'C':\n" + list.printList() + "\n");
                    list.removeFrom("D");
                    textArea.append("\nПосле удаления элемента 'D' (с конца):\n" + list.printList() + "\n");
                    list.clear();
                    textArea.append("\nПосле очистки списка:\n" + list.printList() + "\n");

                    // Размещение JTextArea в JScrollPane
                    JScrollPane scrollPane = new JScrollPane(textArea);

                    // Создаём JFrame для отображения текста
                    JFrame listFrame = new JFrame("Результаты задания 8");
                    listFrame.setSize(400, 300);
                    listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    listFrame.add(scrollPane, BorderLayout.CENTER);
                    listFrame.setVisible(true);
                    break;
                case 2:
                    // Игра с картами
                    MathGame game = new MathGame();
                    game.playGame();
                    break;
            }
        }
    }
}
