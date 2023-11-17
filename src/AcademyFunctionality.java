import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AcademyFunctionality {


    /**
     * method which reads the Student file and retrieves data from it
     */

    public static List<AcademyInterface> open(String fileName) {
        List<AcademyInterface> allItems = new ArrayList<AcademyInterface>();


        try {

            File file = new File(fileName);

            if(file.exists()) { //this if-else added by JB
// if the exists I get the data from it

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allItems = (ArrayList<AcademyInterface>) is.readObject();
                int size = allItems.size();
                 is.close();
                JOptionPane.showMessageDialog(null, "", "size. "+ size, JOptionPane.INFORMATION_MESSAGE);


            }
            else{
                // if the file does not exist I create it
                file.createNewFile();

            }
        }
        catch(ClassNotFoundException cce) {
            JOptionPane.showMessageDialog(null,"Class of object deserialised not a match for anything used in this application","Error",JOptionPane.ERROR_MESSAGE);
            cce.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Problem reading from the file","Error",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }

        return allItems;
    } // end open


    public static void  save(List<AcademyInterface> allItems, String fileName)
    {
        File outFile  = new File(fileName);
        try{
            FileOutputStream outStream = new FileOutputStream(outFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);


// I write the ArrayList with the new student to the Student file
            objectOutStream.writeObject(allItems);

            outStream.close();
        }
        catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(null, "File could not be found!",
                    "Problem Finding File!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            System.out.println(ioe.getStackTrace());
            JOptionPane.showMessageDialog(null,"File could not be written!",
                    "Problem Writing to File!",JOptionPane.ERROR_MESSAGE);
        }

    } // end save


public  static AcademyInterface search(List<AcademyInterface> allItems, String id){
AcademyInterface item = null;
        int x = 0;

    while (x<allItems.size()){
        item =  allItems.get(x);

        if (item.getId().equalsIgnoreCase(id)) {
            x = allItems.size() +2;
        }
        x++;
    } // end while

     if (x == allItems.size())
          return  null    ;

    return  item;
} // end search



} // end class