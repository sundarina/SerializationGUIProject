import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

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
//        for (int i = 0; i < masFig.length; i++) {
//            masFig[i] = s.rand();
//            System.out.print(masFig[i]);
//            System.out.print("\n");
//        }

        ColorAble[] masColor = new ColorAble[10];
//        for (int i = 0; i < masColor.length; i++) {
//            masColor[i] = (ColorAble) c.rand();
//            System.out.print(masColor[i]);
//            System.out.print("\n");
//        }


//        for (Figure figure : masFig) {
//            if (figure.getClass().getName().equals("CPoint")) {
//                writeFigures(figure, pathCPoint);
//            }
//            if (figure.getClass().getName().equals("CLine")) {
//                writeFigures(figure, pathCLine);
//            }
//            if (figure.getClass().getName().equals("TriangleClass")) {
//                writeFigures(figure, pathTriangle);
//            }
//        }
//
//        for (ColorAble color : masColor) {
//
//            if (color.getClass().getName().equals("CcoloredPoint")) {
//                writeFigures(color, pathCcoloredPoint);
//
//            }
//            if (color.getClass().getName().equals("CcoloredLine")) {
//                writeFigures(color, pathCcoloredLine);
//
//            }
//
//            if (color.getClass().getName().equals("ColorTriangle")) {
//                writeFigures(color, pathColorTriangle);
//
//            }
//        }
//

        int count = 0;
        ArrayList<Object> list = readFigures(pathCPoint);

        for (int i = 0; i < list.size(); i++) {
            masFig[i] = (CPoint) list.get(i);
            count++;
        }

        list = readFigures(pathCLine);
        for (int i = 0; i < list.size(); i++) {
            masFig[count + i] = (CLine) list.get(i);
            count++;
        }

        list = readFigures(pathTriangle);
        for (int i = 0; i < list.size(); i++) {
            masFig[i + count] = (TriangleClass) list.get(i);
        }


        int countC = 0;
        ArrayList<Object> listColor = readFigures(pathCcoloredPoint);

        for (int i = 0; i < listColor.size(); i++) {
            masColor[i] = (CcoloredPoint) listColor.get(i);
            countC++;
        }

        listColor = readFigures(pathCcoloredLine);
        for (int i = 0; i < listColor.size(); i++) {
            masColor[i + countC] = (CcoloredLine) listColor.get(i);
            countC++;
        }

        listColor = readFigures(pathColorTriangle);
        for (int i = 0; i < listColor.size(); i++) {
            masColor[i + countC] = (ColorTriangle) listColor.get(i);
        }

        new GUIFigureClass(s, c, masFig, masColor);
    }


    public ArrayList<Object> readFigures(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objRead = new ObjectInputStream(new FileInputStream(path));
        ArrayList<Object> list = new ArrayList<>();
        Object o;
        while (true) {
            try {
                o = objRead.readObject();
            } catch (EOFException e) {
                break;
            }
            list.add(o);

            if (o == null) {
                break;

            }

        }

        objRead.close();
        return list;
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

