import java.io.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import  java    .util.List;
/** An instantiable class to add a new course to the file
 *Author: Ivan Segade Carou
 */




public class AddCourse extends JFrame  implements ActionListener {

    JFrame addCourseFrame = new JFrame("Add course");
JPanel formPanel = new JPanel( new GridLayout(4,2,5,5));
JPanel buttonsPanel = new JPanel(new FlowLayout());

BorderLayout layout = new BorderLayout(2,2);

JLabel nameLabel = new JLabel("Name:");
JLabel priceLabel = new JLabel("Price:");
JLabel payLabel = new JLabel("Payment:");
JLabel teacherLabel = new JLabel("Teacher ID:");
    JLabel blank = new JLabel("   ");


JTextField nameTextField = new JTextField(25);
JTextField priceTextField = new JTextField(10);
JTextField payTextField = new JTextField(10);
JTextField teacherTextField = new JTextField(10);

JButton addButton = new JButton("Add");
JButton cancelButton = new JButton("Cancel");


  //I declare the ArrayList to hild the data in the file to add the new course and then save it in the file

List<AcademyInterface> allCourses = new ArrayList<AcademyInterface>()  ;
List<AcademyInterface> allTeachers = new ArrayList<AcademyInterface>();
int size = 0;

Teacher t = null;

/**
 * course Declaration of the GUI components
  * frame
 * panels
 * textfields
 * lables
 * buttons
 */




    /**
     * constructor with all the forms
     * the programme will ask the user to enter the data
     *course ID
     * Course name
     * Course Price
     * Course Pay
     */


public AddCourse (){
    // I set the frame
addCourseFrame.setSize(500,500);
addCourseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
addCourseFrame.setLayout(layout);

// I set up the main form
formPanel.add(nameLabel);
formPanel.add(nameTextField);
formPanel.add(priceLabel);
formPanel.add(priceTextField);
formPanel.add(payLabel);
formPanel.add(payTextField);
formPanel.add(teacherLabel);
formPanel.add(teacherTextField);

// I add the listener to the buttons
addButton.addActionListener(this);
cancelButton.addActionListener(this);

// I set up the panel where I put the buttons
buttonsPanel.add(cancelButton);
buttonsPanel.add(blank);
buttonsPanel.add(addButton);

// I set up the main frame
addCourseFrame.add(formPanel, BorderLayout.CENTER);
addCourseFrame.add(buttonsPanel, BorderLayout.SOUTH);

addCourseFrame.setVisible(true);;

// I read the Course and Teacher file to have the data or to create it in case the file does not exists yet

allCourses = AcademyFunctionality.open("courses.data");
allTeachers = AcademyFunctionality.open("teachers.data");
size= allCourses .size();

} // end constructor


    /**
     * method declares the listener
     * @param e
     */

    public void actionPerformed(ActionEvent e){
// I get the option the user picked
    String option = e.getActionCommand();

    // I deal the option and the corresponding class will run
switch(option){
    case "Add":
         if (!searchTeacher()){

             JOptionPane.showMessageDialog(null, "Teacher not found\nPlease, enter a correct teacher ID", "Teacher not found", JOptionPane.PLAIN_MESSAGE);
         }else {  // end if search teacher
              save();


             nameTextField.setText("");
             priceTextField.setText(""  );
             payTextField.setText("");
             nameTextField.requestFocus();
         }
        break;
    case "Cancel" :
 addCourseFrame.dispose();

break;
} // end switch
} // end action performed


    /**
     * method which reads the course and retrieves data
     * it catches IO exceptions
     */

    public void open() {
        try {
            File file = new File("courses.data");

            if (file.exists()) {
// if the file already exists, I get the data

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allCourses = (ArrayList<AcademyInterface>) is.readObject();
                size = allCourses.size();
                is.close();

            } else {
                //  if the file does exist, I create it
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
    } // end open


    /**
     * method which reads the Teacher file and retrieves data from it
     */



    /**
     * method which searchs the required teacher in the ArrayList from the file
     * @return
     */

        private  boolean searchTeacher (){
        boolean pass = false;
        String id = teacherTextField.getText();
        int x= 0;

        // I go through all the teachers looking for the right one
        while (x<allTeachers.size()){
            t =(Teacher) allTeachers.get(x);

            // I compare the id from the file with the id entered by the user
            // if I find it the method returns true, otherwise false
            if (t.getId().equalsIgnoreCase(id)) {
                pass = true;
                x = allTeachers.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search teacher


    /**
     * method which saves the data in the Course file
     */

    private void save()
    {
// I get all the data from the form
 String id = "Cou" + String.format("%02d", (size+1));
        String name = nameTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
double pay = Double.parseDouble(payTextField.getText());

         Course c = new Course(id, name, price, pay, t);
allCourses.add(c);
JOptionPane.showMessageDialog(null, allCourses.get(allCourses.size()-1).toString(), " Course added successfuly", JOptionPane.PLAIN_MESSAGE);


  AcademyFunctionality.save(allCourses, "courses.data");
    } // end save course



} // end class
