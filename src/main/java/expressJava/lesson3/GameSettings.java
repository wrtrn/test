package expressJava.lesson3;

public class GameSettings {

    static int maxPlayers;
    final String gameName;
    int currentPlayers;

    GameSettings(String gameName) {
        this.gameName = gameName;
    }

    static void setMaxPlayers(int maxPlayersValue) {
        maxPlayers = maxPlayersValue;
    }

    void addPlayer() {
        if (currentPlayers < maxPlayers)
            currentPlayers++;
    }

    void printGameStatus() {
        System.out.println(gameName + " " + currentPlayers + " " + maxPlayers);
    }
}
