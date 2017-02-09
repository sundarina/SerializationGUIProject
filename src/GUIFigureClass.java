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
    // private JPanel panelButton;

    //private JButton repaint;
    private JCheckBox pointChk, colorPointChk, lineChk, colorLineChk, triangleChk, colorTriangleChk;
    AbstractFigureFabric colorFigureFabric;
    AbstractFigureFabric simpleFigureFabric;


    public GUIFigureClass(AbstractFigureFabric a, AbstractFigureFabric b, Figure[] masFig, ColorAble[] masColor) {
        this.colorFigureFabric = b;
        this.simpleFigureFabric = a;
        this.masColor = masColor;
        this.masFig = masFig;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int countPoint = 0;
        masPoint = new CPoint[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof CPoint) {
                masPoint[countPoint++] = (CPoint) figure;
            }
        }

        int countLine = 0;
        masLines = new CLine[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof CLine) {
                masLines[countLine++] = (CLine) figure;
            }
        }

        int countTriangle = 0;
        masTri = new TriangleClass[masFig.length];
        for (Figure figure : masFig) {
            if (figure instanceof TriangleClass) {
                masTri[countTriangle++] = (TriangleClass) figure;
            }
        }

        int countColorPoint = 0;
        masColorPoint = new CcoloredPoint[masColor.length];
        for (ColorAble colorAble : masColor) {
            if (colorAble instanceof CcoloredPoint)
                masColorPoint[countColorPoint++] = (CcoloredPoint) colorAble;
        }

        int countColorLine = 0;
        masColorLine = new CcoloredLine[masColor.length];
        for (ColorAble colorAble : masColor) {
            if (colorAble instanceof CcoloredLine)
                masColorLine[countColorLine++] = (CcoloredLine) colorAble;

        }

        int countColorTriangle = 0;
        masColorTriangle = new ColorTriangle[masColor.length];
        for (ColorAble colorAble : masColor) {
            if (colorAble instanceof ColorTriangle)
                masColorTriangle[countColorTriangle++] = (ColorTriangle) colorAble;
        }


        final int cp = countPoint;
        final int cl = countLine;
        final int ct = countTriangle;
        final int ccp = countColorPoint;
        final int ccl = countColorLine;
        final int cct = countColorTriangle;


        // panelButton = new JPanel();
        panelCheckBox = new JPanel();
        // repaint = new JButton("Repaint");

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
                } //else g.clearRect(0, 0, 800, 800);


                if (lineChk.isSelected()) {
                    for (int i = 0; i < cl; i++) {
                        g.setColor(Color.BLACK);
                        if (masLines[i] != null)
                            g.drawLine(masLines[i].getStart().getX(), masLines[i].getStart().getY(), masLines[i].getEnd().getX(), masLines[i].getEnd().getY());
                    }
                } //else g.clearRect(0, 0, 800, 800);

                if (triangleChk.isSelected()) {
                    for (int i = 0; i < ct; i++) {
                        g.setColor(Color.BLACK);
                        if (masTri[i] != null)
                            g.drawPolygon(new int[]{masTri[i].getApexA().getX(), masTri[i].getApexB().getX(), masTri[i].getApexC().getX()}, new int[]{masTri[i].getApexA().getY(), masTri[i].getApexB().getY(), masTri[i].getApexC().getY()}, 3);
                    }
                } //else g.clearRect(0, 0, 800, 800);


                if (colorPointChk.isSelected()) {
                    for (int i = 0; i < ccp; i++) {
                        g.setColor(new Color(masColorPoint[i].getColorR(), masColorPoint[i].getColorG(), masColorPoint[i].getColorB()));
                        if (masColorPoint[i] != null)
                            g.fillOval(masColorPoint[i].getX(), masColorPoint[i].getY(), 10, 10);
                    }
                } //else g.clearRect(0, 0, 800, 800);


                if (colorLineChk.isSelected()) {
                    for (int i = 0; i < ccl; i++) {
                        g.setColor(new Color(masColorLine[i].getColorR(), masColorLine[i].getColorG(), masColorLine[i].getColorB()));
                        if (masLines[i] != null)
                            g.drawLine(masColorLine[i].getStart().getX(), masColorLine[i].getStart().getY(), masColorLine[i].getEnd().getX(), masColorLine[i].getEnd().getY());
                    }
                } //else g.clearRect(0, 0, 800, 800);

                if (colorTriangleChk.isSelected()) {
                    for (int i = 0; i < cct; i++) {
                        if (masColorTriangle[i].getClass().getName().equals("ColorTriangle")) {
                            g.setColor(new Color(masColorTriangle[i].getColorR(), masColorTriangle[i].getColorG(), masColorTriangle[i].getColorB()));
                            if (masTri[i] != null)
                                g.drawPolygon(new int[]{masColorTriangle[i].getApexA().getX(), masColorTriangle[i].getApexB().getX(), masColorTriangle[i].getApexC().getX()}, new int[]{masColorTriangle[i].getApexA().getY(), masColorTriangle[i].getApexB().getY(), masColorTriangle[i].getApexC().getY()}, 3);
                        }
                    }
                } //else g.clearRect(0, 0, 800, 800);
            }
        };


//        panelButton.add(repaint, new FlowLayout());
//        repaint.addActionListener(this);

        add(panelFigurePaint, BorderLayout.CENTER);

        //  add(panelButton, BorderLayout.SOUTH);

        add(panelCheckBox, BorderLayout.NORTH);

        setSize(800, 800);
        panelFigurePaint.setVisible(false);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
//        boolean visible = true;
//
//        if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
//            visible = false;
//        } else {
//            visible = true;
//        }


        if (itemEvent.getItemSelectable() == pointChk) {
            panelFigurePaint.setVisible(true);
            panelFigurePaint.repaint();
        }


        if (itemEvent.getItemSelectable() == colorPointChk) {
            panelFigurePaint.setVisible(true);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == lineChk) {
            panelFigurePaint.setVisible(true);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == colorLineChk) {
            panelFigurePaint.setVisible(true);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == triangleChk) {
            panelFigurePaint.setVisible(true);
            panelFigurePaint.repaint();
        }

        if (itemEvent.getItemSelectable() == colorTriangleChk) {
            panelFigurePaint.setVisible(true);
            panelFigurePaint.repaint();
        }
    }
}
