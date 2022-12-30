package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RacingGame {

    private final Referee referee;

    public RacingGame(String inputCarNames, int roundToPlay, NumberSelector numberSelector) {
        List<Car> registeredCars = registerCars(inputCarNames);
        this.referee = new Referee(roundToPlay, registeredCars, numberSelector);
    }

    public List<Map<String, Integer>> operateRacingGame() {
        List<Map<String, Integer>> roundResults = new ArrayList<>();
        while (announceWinnersIfGameEnded().isEmpty()) {
            proceedGame();
            roundResults.add(announceRoundResult());
        }
        return roundResults;
    }

    private List<Car> registerCars(String inputCarNames) {
        String[] carNames = inputCarNames.split(",");
        List<Car> registeredCars = new ArrayList<>();
        for (String carName : carNames) {
            registeredCars.add(new Car(carName));
        }
        return registeredCars;
    }

    public Optional<List<String>> announceWinnersIfGameEnded() {
        if (referee.isGameEnd()) {
            return Optional.of(referee.announceWinners());
        }
        return Optional.empty();
    }

    public void proceedGame() {
        referee.moveCars();
    }

    public Map<String, Integer> announceRoundResult() {
        return referee.announceRoundResult();
    }
}
