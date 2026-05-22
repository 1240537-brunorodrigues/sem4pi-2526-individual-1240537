package pt.ipp.isep.dei.application;

public class CreateAircraftEngineModelRequest {

    private final String modelName;
    private final double power;
    private final double efficiency;
    private final String manufacturerName;
    private final String manufacturerCountry;
    private final String engineType;
    private final String fuelType;

    public CreateAircraftEngineModelRequest(
            String modelName,
            double power,
            double efficiency,
            String manufacturerName,
            String manufacturerCountry,
            String engineType,
            String fuelType
    ) {
        this.modelName = modelName;
        this.power = power;
        this.efficiency = efficiency;
        this.manufacturerName = manufacturerName;
        this.manufacturerCountry = manufacturerCountry;
        this.engineType = engineType;
        this.fuelType = fuelType;
    }

    public String modelName() {
        return modelName;
    }

    public double power() {
        return power;
    }

    public double efficiency() {
        return efficiency;
    }

    public String manufacturerName() {
        return manufacturerName;
    }

    public String manufacturerCountry() {
        return manufacturerCountry;
    }

    public String engineType() {
        return engineType;
    }

    public String fuelType() {
        return fuelType;
    }
}