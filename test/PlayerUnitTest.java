import org.junit.Test;

public class PlayerUnitTest {
    private final String PLAYER_1 = "Black";
    private final String PLAYER_2 = "White";

    @Test
    public void playerTest(){
        Turn turn = new Turn(PLAYER_1, PLAYER_2);
        assert turn.getActivePlayer().equals(PLAYER_1);
        assert turn.getInactivePlayer().equals(PLAYER_2);
    }

    @Test
    public void playersChangeRoles(){
        Turn turn = new Turn(PLAYER_1, PLAYER_2);
        assert turn.getActivePlayer().equals(PLAYER_1);
        assert turn.getInactivePlayer().equals(PLAYER_2);
        turn.changeRoles(PLAYER_1, PLAYER_2);
        assert turn.getActivePlayer().equals(PLAYER_2);
        assert turn.getInactivePlayer().equals(PLAYER_1);
    }
}