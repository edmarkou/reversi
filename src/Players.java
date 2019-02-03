public class Players {
    private String player;
    private String opponent;

    public Players(String player, String opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public String getPlayer() {
        return player;
    }

    public String getOpponent() {
        return opponent;
    }

    public void changeRoles(String player, String opponent) {
        this.player = opponent;
        this.opponent = player;
    }
}
