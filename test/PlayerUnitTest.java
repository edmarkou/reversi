import org.junit.Test;

public class PlayerUnitTest {
    private final String PLAYER_1 = "Black";
    private final String PLAYER_2 = "White";

    @Test
    public void playerTest(){
        Players players = new Players(PLAYER_1, PLAYER_2);
        assert players.getPlayer().equals(PLAYER_1);
        assert players.getOpponent().equals(PLAYER_2);
    }

    @Test
    public void playersChangeRoles(){
        Players players = new Players(PLAYER_1, PLAYER_2);
        assert players.getPlayer().equals(PLAYER_1);
        assert players.getOpponent().equals(PLAYER_2);
        players.changeRoles(PLAYER_1, PLAYER_2);
        assert players.getPlayer().equals(PLAYER_2);
        assert players.getOpponent().equals(PLAYER_1);
    }
}