import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *An instantiable class which  adds a new student to the Student file
 */


public class AddStudent extends JFrame implements ActionListener{
    // I declare all the GUI  components
    JFrame addStudentFrame = new JFrame("Add student");
    JPanel formPanel = new JPanel(new GridLayout(10,2, 5,5));
    JPanel buttonsPanel = new JPanel(new BorderLayout(10, 10));
    JPanel dobPanel = new JPanel(new FlowLayout());
    JPanel genderPanel = new JPanel(new FlowLayout());

    BorderLayout layout = new BorderLayout(2, 2);

    JLabel nameLabel = new JLabel("Name:");
    JLabel surnameLabel = new JLabel("Surname:");
    JLabel addressLabel = new JLabel("Address:");
    JLabel townLabel = new JLabel("Town:");
    JLabel countyLabel = new JLabel("County:");
    JLabel dobLabel = new JLabel("Date of Birth:");
    JLabel slashLabel1  = new JLabel(" / ");
    JLabel slashLabel2 = new JLabel(" / ");
    JLabel genderLabel = new JLabel("Gender:");

    JLabel phoneLabel = new JLabel("Phone:");
    JLabel emailLabel = new JLabel("Email:");

    JTextField nameTextField = new JTextField(20);
    JTextField surnameTextField = new JTextField(30);
    JTextField addressTextField = new JTextField(40);
    JTextField townTextField = new JTextField(20);
    JTextField countyTextField = new JTextField(20);
    JTextField phoneTextField = new JTextField(12);
    JTextField emailTextField = new JTextField(30);

    JComboBox dayComboBox = new JComboBox();
    JComboBox monthComboBox = new JComboBox();
    JComboBox yearComboBox = new JComboBox();

    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");

    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();



// I declare this ArrayList to hold the data from the file
    List<AcademyInterface> allStudents = new ArrayList<AcademyInterface>();

    /**
     * Declaration of the all GUI components
     * frame
     * panels
     * textfield
     * label
     * buttons
     * comboboxes
     * radiobuttons
     */


    /**
     * constructor with all the forms
     * the programme will ask the user to enter the required data
     * name. the name of the student
     * surname. the surname of the student
     * address. the address of the student
     * town. the town of the student
     * county. the county of the student
     *Data of Birth. the date of Birth of student
     * gender. the gender of the student
     * phone. the phone of the student
     * email. the email of the student
     */

    AddStudent ()
    {
// I set up the main frame
        addStudentFrame.setSize(500,500);
        addStudentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(layout);

        // I call the method to fill the years to the Comboboxes
        generateComboBoxes();

// I set up the panel for the Comboboxes
        dobPanel.add(dayComboBox);
        dobPanel.add(slashLabel1);
        dobPanel.add(monthComboBox);
        dobPanel.add(slashLabel2);
        dobPanel.add(yearComboBox);
        // I set up the panel for the gender with RadioButtons
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

// I set up the main form adding all the components
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


// I add the listener tothe buttons
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // I set up the buttons where they will be
        buttonsPanel.add(addButton, BorderLayout.EAST);
        buttonsPanel.add(cancelButton, BorderLayout.WEST);

        // I set up the main frame
        addStudentFrame.add(formPanel, BorderLayout.CENTER);
        addStudentFrame.add(buttonsPanel, BorderLayout.SOUTH);
        formPanel.setVisible(true);
        addStudentFrame.setVisible(true);

        // I read the data from the Student file
        allStudents = AcademyFunctionality.open("students.data");

    } // end constructor


    /**
     *method which declares the listener
     */



    public void actionPerformed(ActionEvent e)
    {
        // I deal the option picked by the user
        String option = e.getActionCommand();

        // It depends on the options  I execute one case
        switch(option) {
            case "Add":
                String myDay = (String) dayComboBox.getSelectedItem();
                String myMonth = (String) monthComboBox.getSelectedItem();
                String myYear = (String) yearComboBox.getSelectedItem();

                Student studentValidation = new Student("--",nameTextField.getText(), surnameTextField.getText(), addressTextField.getText(),townTextField.getText(), countyTextField.getText(), null, emailTextField.getText(), phoneTextField.getText(), ' ');

                String texto = Validator.validateForm(new StudentAdapter(studentValidation), new String[]{myDay, myMonth, myYear});

                if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
                    texto += "\nEnter a gender";

                if (texto.equals("")){
                    save();
                    emptyForm();
        }else
                    JOptionPane.showMessageDialog(null, texto , "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Cancel":
                addStudentFrame.dispose();
                break;

        } // end switch

    } //end actionperformed


    /**
     * method which saves the data in the Student file
     */

    public void save()
    {
        // I get all the information entered by the user
        String id = "STU" + String.format("%05d", (allStudents.size()+1));
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String address = addressTextField.getText();
        String town = townTextField.getText();
        String county = countyTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String myDay = (String) dayComboBox.getSelectedItem();
        String myMonth = (String) monthComboBox.getSelectedItem();
        String myYear = (String)yearComboBox.getSelectedItem();
        GregorianCalendar dob = new GregorianCalendar(Integer.parseInt(myYear), Integer.parseInt(myMonth), Integer.parseInt(myDay));
        char gender = 'X';

        if (maleRadioButton.isSelected())
            gender = 'M';
        else
            gender = 'F';

        // I create a new Student class to add it to ArrayList and then to the Student file
        Student student = new Student(id, name, surname, address, town, county, dob, email, phone, gender );
        allStudents.add(student);

    AcademyFunctionality.save(allStudents, "students.data");
        JOptionPane.showMessageDialog(null, "Student added successfully\n\n" + student, "Student added successfully", JOptionPane.INFORMATION_MESSAGE);

    } // end save student



    /**
     * method which fills the comoboxes for the data of birth
     * it enters the days, months and years
     */

    public void generateComboBoxes()
    {

        dayComboBox.addItem("Day");;
        for (int x=1;x<32;x++)
            dayComboBox.addItem(Integer.toString(x));

        monthComboBox.addItem("Month");
        for (int x=1;x<13;x++)
            monthComboBox.addItem(Integer.toString(x));

        yearComboBox.addItem("Year");;
        for (int x=2021;x>1910;x--)
            yearComboBox.addItem(Integer.toString(x));

    } // end generate combo boxes


/** method which resets the form
     */

    private void emptyForm(){
        nameTextField.setText("");
        surnameTextField.setText("");
        addressTextField.setText("");
        townTextField.setText("");
        countyTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        nameTextField.requestFocus();
    }// end empty form




} // end class
