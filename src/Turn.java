public class Turn extends Player{

    public Turn(String active, String inactive) {
        super(active, inactive);
    }

    public void changeRoles(String active, String inactive) {
        this.setActivePlayer(inactive);
        this.setInactivePlayer(active);
    }
}