class ColorFigureFabric extends AbstractFigureFabric {

    public Figure rand() {
        return getFigure((int) (Math.random() * 3));
    }

    public int[] randColor() {
        int[] r = new int[3];
        for (int i = 0; i < r.length; i++) {
            r[i] = (int) (Math.random() * 255);
        }
        return r;
    }

    /**
     * фабричный метод
     * можно сколько угодно добавлять фигур
     */

    public Figure getFigure(int n) { //метод, что будет возвращать какуюто фигуру
        switch (n) {
            case 0:
                return new CcoloredPoint((int) (Math.random() * 250), (int) (Math.random() * 700), this.randColor());
            case 1:
                return new CcoloredLine((int) (Math.random() * 250), 5, (int) (Math.random() * 250), (int) (Math.random() * 700), this.randColor());
            case 2:
                return new ColorTriangle(new CPoint((int) (Math.random() * 250), (int) (Math.random() * 700)), new CPoint((int) (Math.random() * 250), (int) (Math.random() * 700)), new CPoint((int) (Math.random() * 250), (int) (Math.random() * 700)), this.randColor());
            default:
                return null;
        }
    }
}