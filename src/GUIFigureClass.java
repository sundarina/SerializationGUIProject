import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.*;


public class GUIFigureClass extends JFrame implements ActionListener, ItemListener {

    private Figure[] masFig;
    private CPoint[] masPoint;
    private CLine[] masLines;
    private TriangleClass[] masTri;
    private ColorAble[] masColor;
    private CcoloredPoint[] masColorPoint;
    private ColorTriangle[] masColorTriangle;
    private CcoloredLine[] masColorLine;

    private JPanel panelCheckBox;
    private JPanel panelFigurePaint;
    private JPanel panelButton;

    private JButton clear;
    private JCheckBox pointChk, colorPointChk, lineChk, colorLineChk, triangleChk, colorTriangleChk;
    AbstractFigureFabric colorFigureFabric;
    AbstractFigureFabric simpleFigureFabric;



    public GUIFigureClass(AbstractFigureFabric a, AbstractFigureFabric b, ColorAble[] masColor, Figure[] masFig ) {
        this.colorFigureFabric = a;
        this.simpleFigureFabric = b;
        this.masColor = masColor;
        this.masFig = masFig;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


       // simpleFigureFabric = new SimpleFigureFabric();

      //  colorFigureFabric = new ColorFigureFabric();

 /* обьединение под общим интерфейсом, каждый из обьектов содержит метод, описаный в интерф.
  класс обязан выполнить контракт - реализов метод*/



//		masCPoint[0] = ccp1; // реализация полиморфизма. ссылка и родительского
//								// класса, засовывать можно и этот класс и
//								// потомкка


        int countPoint = 0;
        masPoint = new CPoint[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof CPoint) {
                masPoint[countPoint++] = (CPoint) figure;
                //figureWriter("CPoint", pathCPoint);
            }
        }

        int countLine = 0;
        masLines = new CLine[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof CLine) {
                masLines[countLine++] = (CLine) figure;
                // figureWriter("CLine", pathCLine);
            }
        }

        int countTriangle = 0;
        masTri = new TriangleClass[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof TriangleClass) {
                masTri[countTriangle++] = (TriangleClass) figure;
                //("TriangleClass", pathTriangle);
            }
        }

        int countColorPoint = 0;
        masColorPoint = new CcoloredPoint[masColor.length];
        for (ColorAble colorAble : masColor) {
            if (colorAble instanceof CcoloredPoint)
                masColorPoint[countColorPoint++] = (CcoloredPoint) colorAble;
            // figureWriter("CcoloredPoint", pathCcoloredPoint);
        }

        int countColorLine = 0;
        masColorLine = new CcoloredLine[masColor.length];
        for (ColorAble colorAble : masColor) {
            if (colorAble instanceof CcoloredLine)
                masColorLine[countColorLine++] = (CcoloredLine) colorAble;
            // figureWriter("CcoloredLine", pathCcoloredLine);

        }

        int countColorTriangle = 0;
        masColorTriangle = new ColorTriangle[masColor.length];
        for (ColorAble colorAble : masColor) {
            if (colorAble instanceof ColorTriangle)
                masColorTriangle[countColorTriangle++] = (ColorTriangle) colorAble;
            // figureWriter("ColorTriangle", pathColorTriangle);
        }


        final int cp = countPoint;
        final int cl = countLine;
        final int ct = countTriangle;
        final int ccp = countColorPoint;
        final int ccl = countColorLine;
        final int cct = countColorTriangle;


        panelButton = new JPanel();
        panelCheckBox = new JPanel();
        clear = new JButton("Clear");

        pointChk = new JCheckBox("Point");
        colorPointChk = new JCheckBox("Color Point");
        lineChk = new JCheckBox("Line");
        colorLineChk = new JCheckBox("Color Line");
        triangleChk = new JCheckBox("Triangle");
        colorTriangleChk = new JCheckBox("Color Triangle");

        panelCheckBox.setLayout(new GridLayout(1, 7));
        panelCheckBox.add(pointChk);
        pointChk.addItemListener(this);
        panelCheckBox.add(colorPointChk);
        colorPointChk.addItemListener(this);
        panelCheckBox.add(lineChk);
        lineChk.addItemListener(this);
        panelCheckBox.add(colorLineChk);
        colorLineChk.addItemListener(this);
        panelCheckBox.add(triangleChk);
        triangleChk.addItemListener(this);
        panelCheckBox.add(colorTriangleChk);
        colorTriangleChk.addItemListener(this);

        panelFigurePaint = new JPanel() {
            public void paint(Graphics g) {

                if (pointChk.isSelected()) {
                    for (int i = 0; i < cp; i++) {
                        g.setColor(Color.BLACK);
                        if (masPoint[i] != null) {
                            g.fillOval(masPoint[i].getX(), masPoint[i].getY(), 10, 10);
                        }
                    }
                }

                if (pointChk.isSelected()) {
                    for (int i = 0; i < cp; i++) {
                        g.setColor(Color.BLACK);
                        if (masPoint[i] != null) {
                            g.fillOval(masPoint[i].getX(), masPoint[i].getY(), 10, 10);
                        }
                    }
                }


                if (lineChk.isSelected()) {
                    for (int i = 0; i < cl; i++) {
                        g.setColor(Color.BLACK);
                        if (masLines[i] != null)
                            g.drawLine(masLines[i].getStart().getX(), masLines[i].getStart().getY(), masLines[i].getEnd().getX(), masLines[i].getEnd().getY());
                    }
                }

                if (triangleChk.isSelected()) {
                    for (int i = 0; i < ct; i++) {
                        g.setColor(Color.BLACK);
                        if (masTri[i] != null)
                            g.drawPolygon(new int[]{masTri[i].getApexA().getX(), masTri[i].getApexB().getX(), masTri[i].getApexC().getX()}, new int[]{masTri[i].getApexA().getY(), masTri[i].getApexB().getY(), masTri[i].getApexC().getY()}, 3);
                    }
                }


                if (colorPointChk.isSelected()) {
                    for (int i = 0; i < ccp; i++) {
                        g.setColor(new Color(masColorPoint[i].getColorR(), masColorPoint[i].getColorG(), masColorPoint[i].getColorB()));
                        if (masColorPoint[i] != null)
                            g.fillOval(masColorPoint[i].getX(), masColorPoint[i].getY(), 10, 10);
                    }
                }


                if (colorLineChk.isSelected()) {
                    for (int i = 0; i < ccl; i++) {
                        g.setColor(new Color(masColorLine[i].getColorR(), masColorLine[i].getColorG(), masColorLine[i].getColorB()));
                        if (masLines[i] != null)
                            g.drawLine(masColorLine[i].getStart().getX(), masColorLine[i].getStart().getY(), masColorLine[i].getEnd().getX(), masColorLine[i].getEnd().getY());
                    }
                }

                if (colorTriangleChk.isSelected()) {
                    for (int i = 0; i < cct; i++) {
                        if (masColorTriangle[i].getClass().getName().equals("ColorTriangle")) {
                            g.setColor(new Color(masColorTriangle[i].getColorR(), masColorTriangle[i].getColorG(), masColorTriangle[i].getColorB()));
                            if (masTri[i] != null)
                                g.drawPolygon(new int[]{masColorTriangle[i].getApexA().getX(), masColorTriangle[i].getApexB().getX(), masColorTriangle[i].getApexC().getX()}, new int[]{masColorTriangle[i].getApexA().getY(), masColorTriangle[i].getApexB().getY(), masColorTriangle[i].getApexC().getY()}, 3);
                        }
                    }
                }
            }
        };


        panelButton.add(clear, new FlowLayout());
        clear.addActionListener(this);

        add(panelFigurePaint, BorderLayout.CENTER);

        add(panelButton, BorderLayout.SOUTH);

        add(panelCheckBox, BorderLayout.NORTH);

        setSize(800, 800);
        panelFigurePaint.setVisible(false);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == clear) {
            pointChk.setSelected(false);
            colorPointChk.setSelected(false);
            lineChk.setSelected(false);
            colorLineChk.setSelected(false);
            triangleChk.setSelected(false);
            colorTriangleChk.setSelected(false);
            panelCheckBox.repaint();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        boolean visible = true;

        if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
            visible = false;
        } else {
            visible = true;
        }


        if (itemEvent.getItemSelectable() == pointChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == colorPointChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == lineChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == colorLineChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == triangleChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == colorTriangleChk) {
            panelFigurePaint.setVisible(visible);
            panelFigurePaint.repaint();
        }
    }
}
