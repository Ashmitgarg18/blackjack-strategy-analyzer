package com.blackjack.service;

import com.blackjack.api.dto.*;
import com.blackjack.core.*;
import com.blackjack.persistence.entity.SimulationResultEntity;
import com.blackjack.persistence.repo.SimulationResultRepository;
import com.blackjack.security.AuthUserPrincipal;
import com.blackjack.service.StrategyFactory.StrategyType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimulationService {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String pwd;

    private final SimulationResultRepository simulationResultRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SimulationService(SimulationResultRepository simulationResultRepository){
        this.simulationResultRepository = simulationResultRepository;
    }

    public SimulationResponse runSimulation(SimulationRequest req) {
        GameRules rules = mapRules(req.rules());

        SimulationConfig cfg = new SimulationConfig(
                req.rounds(),
                req.decks(),
                req.penetration(),
                rules,
                req.startingBankroll(),
                req.tableMinBet()
        );

        Map<String, Object> strategyParams = new HashMap<>();
        if (req.strategyParams() != null) {
            strategyParams.putAll(req.strategyParams());
        }

        Strategy strategy = StrategyFactory.create(
                StrategyType.valueOf(req.strategy().toUpperCase()),
                req.tableMinBet(),
                strategyParams,
                req.seed()
        );

        Stats stats = Simulator.run(cfg, strategy, req.threads(), req.seed());

        SimulationResponse response = new SimulationResponse(
                strategy.name(),
                req.rounds(),
                req.decks(),
                req.penetration(),
                rules,
                stats.getHands(),
                stats.getWins(),
                stats.getLosses(),
                stats.getPushes(),
                stats.getBlackjacks(),
                stats.getPlayerBusts(),
                stats.getDealerBusts(),
                stats.getNetUnits(),
                stats.getTotalBet(),
                calcEvPerHand(stats),
                calcAverageBet(stats),
                calcRoi(stats)
        );

        SimulationResultEntity entity = toEntity(response);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof AuthUserPrincipal principal) {
            entity.setUserId(principal.getUserId());
        }

        simulationResultRepository.save(entity);

        return response;

    }

    public List<SimulationSummaryDTO> getAllSimulations(){
        return simulationResultRepository.findAll()
                .stream()
                .map(this::toSummary)
                .toList();
    }

    public SimulationResultDetailDTO getSimulationDetails(Long id){
        return simulationResultRepository.findById(id)
                .map(this::toSimulationDetail)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Simulation not found"));
    }

    private double calcEvPerHand(Stats s) {
        long hands = Math.max(1, s.getHands());
        return (double) s.getNetUnits() / hands;
    }

    private double calcAverageBet(Stats s) {
        long hands = Math.max(1, s.getHands());
        return (double) s.getTotalBet() / hands;
    }

    private double calcRoi(Stats s) {
        long totalBet = Math.max(1, s.getTotalBet());
        return (double) s.getNetUnits() / totalBet * 100.0;
    }

    private GameRules mapRules(RulesDTO r) {
        if (r == null) return GameRules.standard();
        return new GameRules(
                r.dealerHitsSoft17(),
                r.blackjackPayout(),
                r.doubleAfterSplit(),
                r.surrenderAllowed(),
                r.maxSplits()
        );
    }

    private SimulationResultEntity toEntity(SimulationResponse resp){
        return SimulationResultEntity.builder()
                .strategyName(resp.strategyName())
                .rounds(resp.rounds())
                .decks(resp.decks())
                .penetration(resp.penetration())
                .rules(convertRulesToJson(resp.rules()))
                .hands(resp.hands())
                .wins(resp.wins())
                .losses(resp.losses())
                .pushes(resp.pushes())
                .blackjacks(resp.blackjacks())
                .playerBusts(resp.playerBusts())
                .dealerBusts(resp.dealerBusts())
                .netUnits(resp.netUnits())
                .totalBet(resp.totalBet())
                .evPerHand(resp.evPerHand())
                .averageBet(resp.averageBet())
                .roiPercent(resp.roiPercent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private String convertRulesToJson(GameRules rules) {
        try {
            return objectMapper.writeValueAsString(rules);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize rules", e);
        }
    }

    private SimulationSummaryDTO toSummary(SimulationResultEntity entity){
        return SimulationSummaryDTO.builder()
                .id(entity.getId())
                .strategyName(entity.getStrategyName())
                .rounds(entity.getRounds())
                .decks(entity.getDecks())
                .penetration(entity.getPenetration())
                .roiPercent(entity.getRoiPercent())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private SimulationResultDetailDTO toSimulationDetail(SimulationResultEntity entity){
        return SimulationResultDetailDTO.builder()
                .id(entity.getId())
                .strategyName(entity.getStrategyName())
                .rounds(entity.getRounds())
                .decks(entity.getDecks())
                .penetration(entity.getPenetration())
                .rules(entity.getRules())
                .hands(entity.getHands())
                .wins(entity.getWins())
                .losses(entity.getLosses())
                .pushes(entity.getPushes())
                .blackjacks(entity.getBlackjacks())
                .playerBusts(entity.getPlayerBusts())
                .dealerBusts(entity.getDealerBusts())
                .netUnits(entity.getNetUnits())
                .totalBet(entity.getTotalBet())
                .evPerHand(entity.getEvPerHand())
                .averageBet(entity.getAverageBet())
                .roiPercent(entity.getRoiPercent())
                .createdAt(entity.getCreatedAt())
                .build();
    }


}
