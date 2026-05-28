package pt.ipp.isep.dei.application;

public class CreateAircraftModelRequest {

    private final String modelName;
    private final double emptyWeight;
    private final double maximumTakeOffWeight;
    private final double maximumZeroFuelWeight;
    private final double maximumFuelCapacity;
    private final double serviceCeiling;
    private final double cruiseSpeed;
    private final double wingArea;
    private final double dragCoefficient;
    private final double liftCoefficient;
    private final int capacity;
    private final String manufacturerName;
    private final String manufacturerCountry;
    private final String aircraftType;

    public CreateAircraftModelRequest(
            String modelName,
            double emptyWeight,
            double maximumTakeOffWeight,
            double maximumZeroFuelWeight,
            double maximumFuelCapacity,
            double serviceCeiling,
            double cruiseSpeed,
            double wingArea,
            double dragCoefficient,
            double liftCoefficient,
            int capacity,
            String manufacturerName,
            String manufacturerCountry,
            String aircraftType
    ) {
        this.modelName = modelName;
        this.emptyWeight = emptyWeight;
        this.maximumTakeOffWeight = maximumTakeOffWeight;
        this.maximumZeroFuelWeight = maximumZeroFuelWeight;
        this.maximumFuelCapacity = maximumFuelCapacity;
        this.serviceCeiling = serviceCeiling;
        this.cruiseSpeed = cruiseSpeed;
        this.wingArea = wingArea;
        this.dragCoefficient = dragCoefficient;
        this.liftCoefficient = liftCoefficient;
        this.capacity = capacity;
        this.manufacturerName = manufacturerName;
        this.manufacturerCountry = manufacturerCountry;
        this.aircraftType = aircraftType;
    }

    public String modelName() {
        return modelName;
    }

    public double emptyWeight() {
        return emptyWeight;
    }

    public double maximumTakeOffWeight() {
        return maximumTakeOffWeight;
    }

    public double maximumZeroFuelWeight() {
        return maximumZeroFuelWeight;
    }

    public double maximumFuelCapacity() {
        return maximumFuelCapacity;
    }

    public double serviceCeiling() {
        return serviceCeiling;
    }

    public double cruiseSpeed() {
        return cruiseSpeed;
    }

    public double wingArea() {
        return wingArea;
    }

    public double dragCoefficient() {
        return dragCoefficient;
    }

    public double liftCoefficient() {
        return liftCoefficient;
    }

    public int capacity() {
        return capacity;
    }

    public String manufacturerName() {
        return manufacturerName;
    }

    public String manufacturerCountry() {
        return manufacturerCountry;
    }

    public String aircraftType() {
        return aircraftType;
    }
}