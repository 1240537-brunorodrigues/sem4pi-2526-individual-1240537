package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.application.RegisterAirControlAreaRequest;
import pt.ipp.isep.dei.application.RegisterAirControlAreaService;
import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;

public class RegisterAirControlAreaController {

    private final RegisterAirControlAreaService registerAirControlAreaService;

    public RegisterAirControlAreaController(RegisterAirControlAreaService registerAirControlAreaService) {
        if (registerAirControlAreaService == null) {
            throw new IllegalArgumentException("Register air control area service cannot be null.");
        }

        this.registerAirControlAreaService = registerAirControlAreaService;
    }

    public AirControlArea registerAirControlArea(RegisterAirControlAreaRequest request) {
        return registerAirControlAreaService.registerAirControlArea(request);
    }
}