import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFigureClass {

    private String pathCPoint = "res" + File.separator + "CPoint.txt";
    private String pathCcoloredPoint = "res" + File.separator + "CcoloredPoint.txt";
    private String pathCLine = "res" + File.separator + "CLine.txt";
    private String pathCcoloredLine = "res" + File.separator + "CcoloredLine.txt";
    private String pathTriangle = "res" + File.separator + "TriangleClass.txt";
    private String pathColorTriangle = "res" + File.separator + "ColorTriangle.txt";

    public MainFigureClass() throws IOException {

        AbstractFigureFabric s = new SimpleFigureFabric();
        AbstractFigureFabric c = new ColorFigureFabric();

        Figure[] masFig = new Figure[10];
        for (int i = 0; i < masFig.length; i++) {
            masFig[i] = s.rand();
            System.out.print(masFig[i]);
            System.out.print("\n");
        }

        ColorAble[] masColor = new ColorAble[10];
        for (int i = 0; i < masColor.length; i++) {
            masColor[i] = (ColorAble) c.rand();
            System.out.print(masColor[i]);
            System.out.print("\n");
        }

        GUIFigureClass guiFig = new GUIFigureClass(c, s, masColor, masFig);

        for(Figure figure : masFig) {
            if (figure.getClass().getName().equals("CPoint")) {
                writeFigures(figure, pathCPoint);
            }
            if (figure.getClass().getName().equals("CLine")) {
                writeFigures(figure, pathCLine);
            }
            if (figure.getClass().getName().equals("TriangleClass")) {
                writeFigures(figure, pathTriangle);
            }
        }



        for(ColorAble color : masColor) {

            if (color.getClass().getName().equals("CcoloredPoint")) {
                writeFigures(color, pathCcoloredPoint);
            }
            if (color.getClass().getName().equals("CcoloredLine")) {
                writeFigures(color, pathCcoloredLine);
            }

            if (color.getClass().getName().equals("ColorTriangle")) {
                writeFigures(color, pathColorTriangle);
            }
        }




    }


    public void readFigures(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objRead = new ObjectInputStream(new FileInputStream(path));
        objRead.readObject();
    }

    public void writeFigures(Object o, String path) throws FileNotFoundException, IOException {

        ObjectOutputStream objSave = new ObjectOutputStream(new FileOutputStream(path));
        objSave.writeObject(o);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFigureClass();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

