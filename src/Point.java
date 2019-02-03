public class Point {
    private int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "["+x+", "+y+"]";
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode()==this.hashCode();
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(x+""+y);
    }
}