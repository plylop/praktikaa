package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Train {
    private String destination;
    private String trainNumber;
    private int departureHour;
    private int totalSeats;
    private int coupeSeats;
    private int platzkartSeats;
    private int luxurySeats;
    public Train(String destination, String trainNumber, int departureHour, int totalSeats, int coupeSeats, int platzkartSeats, int luxurySeats) {
        this.destination = destination;
        this.trainNumber = trainNumber;
        this.departureHour = departureHour;
        this.totalSeats = totalSeats;
        this.coupeSeats = coupeSeats;
        this.platzkartSeats = platzkartSeats;
        this.luxurySeats = luxurySeats;
    }

    public Train(String destination, String trainNumber, int departureHour) {
        this(destination, trainNumber, departureHour, 0, 0, 0, 0);
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setDepartureHour(int departureHour) {
        this.departureHour = departureHour;
    }

    public int getDepartureHour() {
        return departureHour;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setCoupeSeats(int coupeSeats) {
        this.coupeSeats = coupeSeats;
    }

    public int getCoupeSeats() {
        return coupeSeats;
    }

    public void setPlatzkartSeats(int platzkartSeats) {
        this.platzkartSeats = platzkartSeats;
    }

    public int getPlatzkartSeats() {
        return platzkartSeats;
    }

    public void setLuxurySeats(int luxurySeats) {
        this.luxurySeats = luxurySeats;
    }

    public int getLuxurySeats() {
        return luxurySeats;
    }

    @Override
    public String toString() {
        return String.format("Train Number: %s, Destination: %s, Departure Hour: %d, Total Seats: %d, Coupe Seats: %d, Platzkart Seats: %d, Luxury Seats: %d",
                trainNumber, destination, departureHour, totalSeats, coupeSeats, platzkartSeats, luxurySeats);
    }
}

class TrainArray {
    private List<Train> trains;

    public TrainArray() {
        this.trains = new ArrayList<>();
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public void printTrainsToDestination(String destination) {
        for (Train train : trains) {
            if (train.getDestination().equalsIgnoreCase(destination)) {
                System.out.println(train);
            }
        }
    }

    public void printTrainsToDestinationAfterHour(String destination, int hour) {
        for (Train train : trains) {
            if (train.getDestination().equalsIgnoreCase(destination) && train.getDepartureHour() > hour) {
                System.out.println(train);
            }
        }
    }

    public void printTrainsToDestinationWithGeneralSeats(String destination) {
        for (Train train : trains) {
            if (train.getDestination().equalsIgnoreCase(destination) && train.getCoupeSeats() > 0) {
                System.out.println(train);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TrainArray trainArray = new TrainArray();


        trainArray.addTrain(new Train("Moscow", "123A", 15, 100, 10, 20, 5));
        trainArray.addTrain(new Train("Saint Petersburg", "456B", 10, 150, 20, 30, 10));
        trainArray.addTrain(new Train("Moscow", "789C", 18, 200, 30, 40, 15));
        trainArray.addTrain(new Train("Novosibirsk", "101D", 7, 120, 15, 25, 5));

        System.out.print("Введите пункт назначения: ");
        String destination = scanner.nextLine();

        System.out.print("Введите время отправления (час): ");
        int hour = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nПоезда до пункта назначения:");
        trainArray.printTrainsToDestination(destination);

        System.out.println("\nПоезда до пункта назначения, отправляющиеся после указанного часа:");
        trainArray.printTrainsToDestinationAfterHour(destination, hour);

        System.out.println("\nПоезда до пункта назначения с общими местами:");
        trainArray.printTrainsToDestinationWithGeneralSeats(destination);

        scanner.close();
    }
}
