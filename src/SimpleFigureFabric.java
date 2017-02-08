class SimpleFigureFabric extends AbstractFigureFabric {

    public Figure rand() {
        return getFigure((int) (Math.random() * 3));
    }

    /**
     * фабричный метод
     * можно сколько угодно добавлять фигур
     */

    public int[] randColor() {
        int[] r = new int[3];
        for (int i = 0; i < r.length; i++) {
            r[i] = (int) (Math.random() * 255);
        }
        return r;
    }

    public Figure getFigure(int n) { //метод, что будет возвращать какуюто фигуру
        switch (n) {
            case 0:
                return new CPoint((int) (Math.random() * 250), (int) (Math.random() * 700));
            case 1:
                return new CLine((int) (Math.random() * 250), (int) (Math.random() * 250), (int) (Math.random() * 700), (int) (Math.random() * 700));
            case 2:
                return new TriangleClass(new CPoint((int) (Math.random() * 250), (int) (Math.random() * 250)), new CPoint((int) (Math.random() * 250), (int) (Math.random() * 700)), new CPoint((int) (Math.random() * 250), (int) (Math.random() * 700)));
            default:
                return null;
        }
    }
}