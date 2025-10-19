package com.blackjack.api;

import com.blackjack.api.dto.SimulationRequest;
import com.blackjack.api.dto.SimulationResponse;
import com.blackjack.api.dto.SimulationResultDetailDTO;
import com.blackjack.api.dto.SimulationSummaryDTO;
import com.blackjack.service.SimulationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SimulationController {

    private final SimulationService service;

    public SimulationController(SimulationService service) {
        this.service = service;
    }

    @PostMapping("/simulate")
    public ResponseEntity<SimulationResponse> simulate(@Valid @RequestBody SimulationRequest request) {
        SimulationResponse resp = service.runSimulation(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/simulations")
    public ResponseEntity<List<SimulationSummaryDTO>> allSimulations(){
        List<SimulationSummaryDTO> simulationSummaries = service.getAllSimulations();
        return ResponseEntity.ok(simulationSummaries);
    }

    @GetMapping("/simulations/{id}")
    public ResponseEntity<SimulationResultDetailDTO> getSimulationById(@PathVariable Long id){
        return ResponseEntity.ok(service.getSimulationDetails(id));
    }
}
