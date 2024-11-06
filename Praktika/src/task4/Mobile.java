package task4;

public class Mobile {

    public static class PhoneModel {
        private String model;
        private String manufacturer;
        private String processor;
        private int ram; // Оперативная память в гигабайтах


        public PhoneModel(String model, String manufacturer, String processor, int ram) {
            this.model = model;
            this.manufacturer = manufacturer;
            this.processor = processor;
            this.ram = ram;
        }


        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getProcessor() {
            return processor;
        }

        public void setProcessor(String processor) {
            this.processor = processor;
        }

        public int getRam() {
            return ram;
        }

        public void setRam(int ram) {
            this.ram = ram;
        }

        @Override
        public String toString() {
            return String.format("Model: %s, Manufacturer: %s, Processor: %s, RAM: %d GB",
                    model, manufacturer, processor, ram);
        }
    }

    public static void main(String[] args) {

        PhoneModel phone1 = new PhoneModel("Galaxy S21", "Samsung", "Exynos 2100", 8);
        PhoneModel phone2 = new PhoneModel("iPhone 13", "Apple", "A15 Bionic", 6);
        PhoneModel phone3 = new PhoneModel("Pixel 6", "Google", "Google Tensor", 8);


        System.out.println("Phone 1: " + phone1);
        System.out.println("Phone 2: " + phone2);
        System.out.println("Phone 3: " + phone3);
    }
}
