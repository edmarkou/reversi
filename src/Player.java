public abstract class Player {
    private String player;
    private String opponent;

    public Player(String player, String opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public String getInactivePlayer() {
        return opponent;
    }

    public void setInactivePlayer(String opponent) {
        this.opponent = opponent;
    }

    public String getActivePlayer() {
        return player;
    }

    public void setActivePlayer(String player) {
        this.player = player;
    }
}