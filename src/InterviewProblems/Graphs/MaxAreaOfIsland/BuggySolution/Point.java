package InterviewProblems.Graphs.MaxAreaOfIsland.BuggySolution;

class Point {

    int x;
    int y;

    public Point(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public boolean equals(Point p1, Point p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }
}
