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

    public MainFigureClass() throws IOException, ClassNotFoundException {


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


        for (Figure figure : masFig) {
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


        for (ColorAble color : masColor) {

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

        int count = 0;
        Object obj = readFigures(pathCPoint);
        for (int i = 0; i < masFig.length; i++) {
            masFig[i] = (CPoint) obj;
            count++;
        }

        Object obj1 = readFigures(pathCLine);
        for (int i = count; i < masFig.length; i++) {
            masFig[i] = (CLine) obj1;
            count++;
        }

        Object obj2 = readFigures(pathTriangle);
        for (int i = count; i < masFig.length; i++) {
            masFig[i] = (TriangleClass) obj2;
        }



        int countC = 0;
        Object obj4 = readFigures(pathCcoloredPoint);
        for (int i = 0; i < masColor.length; i++) {
            masColor[i] = (CcoloredPoint) obj4;
            countC++;
        }
        Object obj5 = readFigures(pathCcoloredLine);
        for (int i = countC; i < masColor.length; i++) {
            masColor[i] = (CcoloredLine) obj5;
            countC++;
        }
            Object obj6 = readFigures(pathColorTriangle);
            for (int i = countC; i < masColor.length; i++) {
                masColor[i] = (ColorTriangle) obj6;
                countC++;
        }

       new GUIFigureClass(s, c, masFig, masColor);
    }


    public Object readFigures(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objRead = new ObjectInputStream(new FileInputStream(path));
        Object o = null;
       do {
            o = objRead.readObject();
        }  while (objRead.available() > 0);
       objRead.close();
        return o;
    }

    public void writeFigures(Object o, String path) throws FileNotFoundException, IOException {

        ObjectOutputStream objSave = new ObjectOutputStream(new FileOutputStream(path));
        objSave.writeObject(o);
        try {
            objSave.flush();
            objSave.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFigureClass();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

