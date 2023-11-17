
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;

import java.util.GregorianCalendar;
import   java.util.List;
/** an instantiable class which defines the UpdateTeacher class
 * it updates the information of a teacher
 *
 */





public class UpdateTeacher extends JFrame implements ActionListener {
    // I declare all the GUI components
    JFrame updateTeacherFrame = new JFrame("Update teacher");
    JPanel formPanel = new JPanel(new GridLayout(10,2, 5,5));
    JPanel buttonsPanel = new JPanel(new BorderLayout(10, 10));
    JPanel dobPanel = new JPanel(new FlowLayout());
    JPanel genderPanel = new JPanel(new FlowLayout());
    JPanel searchPanel = new JPanel(new GridLayout(1,3,5,5));


    BorderLayout layout = new BorderLayout(2, 2);



JTextField searchTextField = new JTextField(10);
    JTextField nameTextField = new JTextField(20);
    JTextField surnameTextField = new JTextField(30);
    JTextField addressTextField = new JTextField(40);
    JTextField townTextField = new JTextField(20);
    JTextField countyTextField = new JTextField(20);
    JTextField departmentTextField = new JTextField(25);
    JTextField phoneTextField = new JTextField(12);
    JTextField emailTextField = new JTextField(30);

    JComboBox dayComboBox = new JComboBox();
    JComboBox monthComboBox = new JComboBox();
    JComboBox yearComboBox = new JComboBox();

    JLabel nameLabel = new JLabel("Name:");
    JLabel surnameLabel = new JLabel("Surname:");
    JLabel addressLabel = new JLabel("Address:");
    JLabel townLabel = new JLabel("Town:");
    JLabel countyLabel = new JLabel("County:");
    JLabel dobLabel = new JLabel("Date of Birth:");
    JLabel slashLabel1  = new JLabel(" / ");
    JLabel slashLabel2 = new JLabel(" / ");
    JLabel genderLabel = new JLabel("Gender:");
    JLabel departmentLabel = new JLabel("Department:");
    JLabel blank = new JLabel("   ");
JLabel searchLabel = new JLabel("ID:");
JLabel phoneLabel = new JLabel("Phone:");
JLabel emailLabel = new JLabel("Email:");

JButton searchButton = new JButton("Search");
JButton updateButton = new JButton("Update");
JButton cancelButton = new JButton("Cancel");

    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();

// I declare the ArrayList where I will save the data from the Teacher file
    List<AcademyInterface> allTeachers = new ArrayList<AcademyInterface>();
    Teacher teacher = null;


    /** constructor with no arguments
     * it displays the form where the user will insert the data
     *
     */

     UpdateTeacher (){
// I set up the main frame
         updateTeacherFrame.setSize(500,500);
         updateTeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
updateTeacherFrame.setLayout(layout);

// I add the listener to the search button
searchButton.addActionListener(this);

// I set up the search panel
         searchPanel.add(searchLabel);
         searchPanel.add(searchTextField);
         searchPanel.add(searchButton);


         // I set up the panel for the DOB
         dobPanel.add(dayComboBox);
         dobPanel.add(slashLabel1);
         dobPanel.add(monthComboBox);
         dobPanel.add(slashLabel2);
         dobPanel.add(yearComboBox);


// I set up t he gender panel
         genderGroup.add(maleRadioButton);
         genderGroup.add(femaleRadioButton);
         genderPanel.add(maleRadioButton);
         genderPanel.add(femaleRadioButton);

         // I set up the main form
         formPanel.add(nameLabel);
         formPanel.add(nameTextField);
         formPanel.add(surnameLabel);
         formPanel.add(surnameTextField);
         formPanel.add(addressLabel);
         formPanel.add(addressTextField);
         formPanel.add(townLabel);
         formPanel.add(townTextField);
         formPanel.add(countyLabel);
         formPanel.add(countyTextField);
         formPanel.add(dobLabel);
         formPanel.add(dobPanel);
         formPanel.add(genderLabel);
         formPanel.add(genderPanel);
         formPanel.add(phoneLabel);
         formPanel.add(phoneTextField);
         formPanel.add(emailLabel);
         formPanel.add(emailTextField);
         formPanel.add(departmentLabel);
         formPanel.add(departmentTextField);

         // I add the listener to the buttons
updateButton.addActionListener(this);
cancelButton.addActionListener(this);

// I set up the buttons panel
buttonsPanel.add(updateButton, BorderLayout.EAST);
         buttonsPanel.add(cancelButton, BorderLayout.WEST);


         // I set up the main frame
         updateTeacherFrame.add(searchPanel, BorderLayout.NORTH);
         updateTeacherFrame.add(formPanel, BorderLayout.CENTER);
updateTeacherFrame.add(buttonsPanel, BorderLayout.SOUTH );
formPanel.setVisible(false);
updateTeacherFrame.setVisible(true);
updateButton.setVisible(false) ;

// I call this method to retrieve the data from the Teacher file

          allTeachers   = AcademyFunctionality.open("teachers.data");
     } // end constructor

    /** I declare the listener
     *
     * @param e
     */


    public  void actionPerformed (ActionEvent e) {
        // I deal with the option picked by the user
        String option = e.getActionCommand();
// It depends on the option one case will be executed or another
        switch (option) {
            case "Search":
                search();

                break;
            case "Update":
                String myDay = (String) dayComboBox.getSelectedItem();
                String myMonth = (String) monthComboBox.getSelectedItem();
                String myYear = (String) yearComboBox.getSelectedItem();

                    Teacher teacherValidation = new Teacher("--", nameTextField.getText(), surnameTextField.getText(), addressTextField.getText(), townTextField.getText(), countyTextField.getText(), null, emailTextField.getText(), phoneTextField.getText(), 'X', departmentTextField.getText());

                 String texto = Validator.validateForm(teacherValidation, new String[]{myDay, myMonth, myYear});

                if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
                    texto += "\nEnter a gender";

                if (texto.equals("")) {
                    save();
                    emptyForm();
                    formPanel.setVisible(false);
                    updateButton.setVisible(false);

                } else
                    JOptionPane.showMessageDialog(null, texto, "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Cancel":
                updateTeacherFrame.dispose();
        } // end switch
    } // end listener


    /** it generates the days, omths and years
     * if selects the right date of birth for the teacher
     *
     * @param day
     * @param month
     * @param year
     */

        public void setComboBoxes(String day, String month, String year)
{

    dayComboBox.addItem("Day");;
    for (int x=1;x<32;x++) {
        dayComboBox.addItem(Integer.toString(x));
        if (day.equals(Integer.toString(x)))
dayComboBox.setSelectedIndex(x);
    } // end for day

    monthComboBox.addItem("Month");
    for (int x=1;x<13;x++) {
        monthComboBox.addItem(Integer.toString(x));
        if (month.equals(Integer.toString(x)))
            monthComboBox.setSelectedIndex(x);
    } // end for month

int y = 0;
    yearComboBox.addItem("Year");;
    for (int x=2021;x>1910;x--) {
        yearComboBox.addItem(Integer.toString(x));
        y++;
        if (year.equals(Integer.toString(x)))
yearComboBox.setSelectedIndex(y);
    } // end for year

} // end generate combo boxes

    /** method which reads the Teacher file and retrieves data from it
     *
     */

    /** method which searches the required teacher in the ArrayList
     *
     */

    private void search (){
    String id = searchTextField.getText();

        teacher= (Teacher) AcademyFunctionality.search(allTeachers, id);

if (teacher == null) {
    JOptionPane.showMessageDialog(null, "Teacher not found", "Techar not found", JOptionPane.ERROR_MESSAGE);
    formPanel.setVisible(false);
    updateButton.setVisible(false);
}
else
    display();
    } // end search


    /** method which displays the information of the teacher on the form
     *
     *
     */

    private void display(){
         // I fill the form with the teacher data
    nameTextField.setText(teacher.getName());
 surnameTextField.setText(teacher.getSurname());
 addressTextField.setText(teacher.getAddress());
 townTextField.setText(teacher.getTown());
 countyTextField.setText(teacher.getCounty());
 phoneTextField.setText(teacher.getPhone());
 emailTextField.setText(teacher.getEmail());

 int year =teacher.getDob().get(GregorianCalendar.YEAR);
        int month =teacher.getDob().get(GregorianCalendar.MONTH);
        int day =teacher.getDob().get(GregorianCalendar.DATE);
         setComboBoxes(Integer.toString(day),  Integer.toString(month), Integer.toString(year));

         char gender = teacher.getGender();
         if (gender == 'M')
             maleRadioButton.setSelected(true);
         else
             femaleRadioButton.setSelected(true);

         departmentTextField.setText(teacher.getDepartment());

         formPanel.setVisible(true);
         updateButton.setVisible(true);
    } // end display


    /** method which saves the ArrayList with the updated information
     *
     */

    private void save()
    {

        String myDay = (String) dayComboBox.getSelectedItem();
        String myMonth = (String) monthComboBox.getSelectedItem();
        String myYear = (String)yearComboBox.getSelectedItem();
        GregorianCalendar dob = new GregorianCalendar(Integer.parseInt(myYear), Integer.parseInt(myMonth), Integer.parseInt(myDay));
        String department = departmentTextField.getText();
        char gender = 'X';

        if (maleRadioButton.isSelected())
            gender = 'M';
        else
            gender = 'F';

    teacher.setName(nameTextField.getText());
teacher.setSurname(surnameTextField.getText());
teacher.setAddress(addressTextField.getText());
teacher.setTown(townTextField.getText());
    teacher.setCounty(countyTextField.getText());
    teacher.setGender(gender);
    teacher.setDob(dob);
     teacher.setPhone(phoneTextField.getText());
     teacher.setEmail(emailTextField.getText());
     teacher.setDepartment(department);


     AcademyFunctionality.save(allTeachers, "teachers.data");
        JOptionPane.showMessageDialog(null, "Teacher updated successfully\n\n" + teacher, "Teacher updated successfully", JOptionPane.INFORMATION_MESSAGE);

    } // end save teacher


    /** method which resets the form
     *
     */

    private void emptyForm(){
searchTextField.setText("");
        nameTextField.setText("");
        surnameTextField.setText("");
        addressTextField.setText("");
        townTextField.setText("");
        countyTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        departmentTextField.setText("");
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        searchTextField.requestFocus();
    }// end empty form


} // end class
