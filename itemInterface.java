package Assignment3;

public interface itemInterface {

    String getName();

    double getPrice();

    int getPriority();

    void setPrice(double newPrice);
}

// Defines operations and it must be implemented by our classes
interface ItemOperations {
    void updatePrice(double newPrice);
}