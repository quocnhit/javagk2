import java.io.*;
import java.util.*;

interface ICar {
    void showInfo();

    void writeToFile(FileWriter writer) throws IOException;

    void readFromFile(Scanner scanner);
}

class Vehicle implements ICar {
    protected String id;
    protected String brand;
    protected int publishYear;
    protected double price;
    protected String color;

    public Vehicle(String id, String brand, int publishYear, double price, String color) {
        this.id = id;
        this.brand = brand;
        this.publishYear = publishYear;
        this.price = price;
        this.color = color;
    }

    @Override
    public void showInfo() {
        System.out.println("ID: " + id);
        System.out.println("Brand: " + brand);
        System.out.println("Publish Year: " + publishYear);
        System.out.println("Price: " + price);
        System.out.println("Color: " + color);
    }

    @Override
    public void writeToFile(FileWriter writer) throws IOException {
        writer.write("ID: " + id + "\n");
        writer.write("Brand: " + brand + "\n");
        writer.write("Publish Year: " + publishYear + "\n");
        writer.write("Price: " + price + "\n");
        writer.write("Color: " + color + "\n");
    }

    @Override
    public void readFromFile(Scanner scanner) {
        id = scanner.nextLine().split(": ")[1];
        brand = scanner.nextLine().split(": ")[1];
        publishYear = Integer.parseInt(scanner.nextLine().split(": ")[1]);
        price = Double.parseDouble(scanner.nextLine().split(": ")[1]);
        color = scanner.nextLine().split(": ")[1];
    }
}

class Car extends Vehicle {
    private int slots;
    private String engineType;

    public Car(String id, String brand, int publishYear, double price, String color, int slots, String engineType) {
        super(id, brand, publishYear, price, color);
        this.slots = slots;
        this.engineType = engineType;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Slots: " + slots);
        System.out.println("Engine Type: " + engineType);
    }

    @Override
    public void writeToFile(FileWriter writer) throws IOException {
        super.writeToFile(writer);
        writer.write("Slots: " + slots + "\n");
        writer.write("Engine Type: " + engineType + "\n");
    }

    @Override
    public void readFromFile(Scanner scanner) {
        super.readFromFile(scanner);
        slots = Integer.parseInt(scanner.nextLine().split(": ")[1]);
        engineType = scanner.nextLine().split(": ")[1];
    }
}

class Motorcycle extends Vehicle {
    private int capacity;

    public Motorcycle(String id, String brand, int publishYear, double price, String color, int capacity) {
        super(id, brand, publishYear, price, color);
        this.capacity = capacity;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Capacity: " + capacity);
    }

    @Override
    public void writeToFile(FileWriter writer) throws IOException {
        super.writeToFile(writer);
        writer.write("Capacity: " + capacity + "\n");
    }

    @Override
    public void readFromFile(Scanner scanner) {
        super.readFromFile(scanner);
        capacity = Integer.parseInt(scanner.nextLine().split(": ")[1]);
    }
}

class Truck extends Vehicle {
    private double loadWeight;

    public Truck(String id, String brand, int publishYear, double price, String color, double loadWeight) {
        super(id, brand, publishYear, price, color);
        this.loadWeight = loadWeight;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Load Weight: " + loadWeight);
    }

    @Override
    public void writeToFile(FileWriter writer) throws IOException {
        super.writeToFile(writer);
        writer.write("Load Weight: " + loadWeight + "\n");
    }

    @Override
    public void readFromFile(Scanner scanner) {
        super.readFromFile(scanner);
        loadWeight = Double.parseDouble(scanner.nextLine().split(": ")[1]);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<ICar> cars = new ArrayList<>();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Thêm xe mới");
            System.out.println("2. Hiển thị danh sách xe");
            System.out.println("3. Ghi danh sách xe vào file");
            System.out.println("4. Đọc danh sách xe từ file");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character from the buffer

            switch (choice) {
                case 1:
                    addNewCar(cars, scanner);
                    break;
                case 2:
                    displayCarList(cars);
                    break;
                case 3:
                    writeToFile(cars);
                    break;
                case 4:
                    readFromFile(cars);
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void addNewCar(List<ICar> cars, Scanner scanner) {
        System.out.print("Loại xe (Car/Motorcycle/Truck): ");
        String type = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Hãng sản xuất: ");
        String brand = scanner.nextLine();
        System.out.print("Năm sản xuất: ");
        int publishYear = scanner.nextInt();
        System.out.print("Giá bán: ");
        double price = scanner.nextDouble();
        System.out.print("Màu xe: ");
        scanner.nextLine(); // Clear the newline character from the buffer
        String color = scanner.nextLine();

        if (type.equalsIgnoreCase("Car")) {
            System.out.print("Số chỗ ngồi: ");
            int slots = scanner.nextInt();
            System.out.print("Kiểu động cơ: ");
            scanner.nextLine(); // Clear the newline character from the buffer
            String engineType = scanner.nextLine();
            cars.add(new Car(id, brand, publishYear, price, color, slots, engineType));
        } else if (type.equalsIgnoreCase("Motorcycle")) {
            System.out.print("Công suất: ");
            int capacity = scanner.nextInt();
            cars.add(new Motorcycle(id, brand, publishYear, price, color, capacity));
        } else if (type.equalsIgnoreCase("Truck")) {
            System.out.print("Trọng tải: ");
            double loadWeight = scanner.nextDouble();
            cars.add(new Truck(id, brand, publishYear, price, color, loadWeight));
        } else {
            System.out.println("Loại xe không hợp lệ.");
        }
    }

    private static void displayCarList(List<ICar> cars) {
        if (cars.isEmpty()) {
            System.out.println("Danh sách xe trống.");
        } else {
            System.out.println("Danh sách xe:");
            for (ICar car : cars) {
                car.showInfo();
                System.out.println();
            }
        }
    }

    private static void writeToFile(List<ICar> cars) {
        try {
            FileWriter writer = new FileWriter("vehicles.txt");
            for (ICar car : cars) {
                car.writeToFile(writer);
                writer.write("\n");
            }
            writer.close();
            System.out.println("Ghi danh sách xe vào file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi danh sách xe vào file.");
            e.printStackTrace();
        }
    }

    private static void readFromFile(List<ICar> cars) {
        try {
            File file = new File("vehicles.txt");
            Scanner scanner = new Scanner(file);
            List<ICar> loadedCars = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String id = line.split(": ")[1];
                String brand = scanner.nextLine().split(": ")[1];
                int publishYear = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                double price = Double.parseDouble(scanner.nextLine().split(": ")[1]);
                String color = scanner.nextLine().split(": ")[1];
                String type = scanner.nextLine().split(": ")[1];
                ICar car;
                if (type.equals("Car")) {
                    int slots = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                    String engineType = scanner.nextLine().split(": ")[1];
                    car = new Car(id, brand, publishYear, price, color, slots, engineType);
                } else if (type.equals("Motorcycle")) {
                    int capacity = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                    car = new Motorcycle(id, brand, publishYear, price, color, capacity);
                } else if (type.equals("Truck")) {
                    double loadWeight = Double.parseDouble(scanner.nextLine().split(": ")[1]);
                    car = new Truck(id, brand, publishYear, price, color, loadWeight);
                } else {
                    continue;
                }
                loadedCars.add(car);
            }
            scanner.close();

            if (!loadedCars.isEmpty()) {
                System.out.println("Danh sách xe đọc từ file:");
                for (ICar car : loadedCars) {
                    car.showInfo();
                    System.out.println();
                }
            } else {
                System.out.println("File không chứa thông tin xe.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file.");
            e.printStackTrace();
        }
    }
}
