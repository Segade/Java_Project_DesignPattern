import javax.swing.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class AddTeacherController implements ControllerInterface{
    // I declare the ArrayList that will hold the data from the Teacher file
    List<AcademyInterface> allTeachers = new ArrayList<AcademyInterface>();


AddTeacherView view;

    public AddTeacherController(){

     view = new AddTeacherView(this);
allTeachers = AcademyFunctionality.open("teachers.data");


} // end constructor

    public void save(){
        String myDay = (String) view.dayComboBox.getSelectedItem();
        String myMonth = (String) view.monthComboBox.getSelectedItem();
        String myYear = (String) view.yearComboBox.getSelectedItem();

        Teacher teacherValidation = new Teacher("--", view.nameTextField.getText(), view.surnameTextField.getText(), view.addressTextField.getText(),
                view.townTextField.getText(), view.countyTextField.getText(), null, view.emailTextField.getText(), view.phoneTextField.getText(), 'X', view.departmentTextField.getText());

        String texto = Validator.validateForm(teacherValidation, new String[]{myDay, myMonth, myYear});

        if (! view.maleRadioButton.isSelected() && ! view.femaleRadioButton.isSelected() )
            texto += "\nEnter a gender";

        if (texto.equals("") ) {
            subSave();
            view.emptyForm();
        }else
            JOptionPane.showMessageDialog(null, texto , "Error", JOptionPane.ERROR_MESSAGE);
        } // end save


    private    void subSave()
    {
        // I get all the information entered by the user
        String id = "TEA" + String.format("%03d", (allTeachers.size()+1));
        String name = view.nameTextField.getText();
        String surname = view.surnameTextField.getText();
        String address = view.addressTextField.getText();
        String town = view.townTextField.getText();
        String county = view.countyTextField.getText();
        String email = view.emailTextField.getText();
        String phone = view.phoneTextField.getText();
        String myDay = (String) view.dayComboBox.getSelectedItem();
        String myMonth = (String) view.monthComboBox.getSelectedItem();
        String myYear = (String) view.yearComboBox.getSelectedItem();
        GregorianCalendar dob = new GregorianCalendar(Integer.parseInt(myYear), Integer.parseInt(myMonth), Integer.parseInt(myDay));
        String department = view.departmentTextField.getText();
        char gender = 'X';

        if (view.maleRadioButton.isSelected())
            gender = 'M';
        else
            gender = 'F';

// I create a new Teacher class to add it to the ArrayList and then add it to the Teacher file
        Teacher teacher = new Teacher(id, name, surname, address, town, county, dob, email, phone, gender, department );
        allTeachers.add(teacher);
        AcademyFunctionality.save(allTeachers, "teachers.data");
        JOptionPane.showMessageDialog(null, "Teacher added successfully\n\n" + teacher, "Teacher added successfully", JOptionPane.INFORMATION_MESSAGE);

    } // end sub save


    public  void close(){
//        JOptionPane.showInputDialog(null, "pass", "pass", JOptionPane.PLAIN_MESSAGE);
         view.addTeacherFrame.dispose();

    } // end close

} // end class
