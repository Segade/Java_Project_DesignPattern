import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** an instantiable class which adds new teacher to the Teacher file
 *Author: Ivan Segade Carou
 */

public class AddTeacherView extends JFrame implements ActionListener{
    // I declare all the GUI components
    JFrame addTeacherFrame = new JFrame("Add teacher");

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
    JLabel departmentLabel = new JLabel("Department:");

    JLabel phoneLabel = new JLabel("Phone:");
    JLabel emailLabel = new JLabel("Email:");

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

    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");

    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();


    /** I declare all the GUI components
     * frame
     * panels
     * textfields
     * labels
     * buttons
     * comboboxes
     * radiobuttons
     */

ControllerInterface controller;
/*     * constructor with all the forms
     * the programme will ask the user to enter the required data
     * name. the name of the teacher
     * surname. the surname of the teacher
     * address. the address of the teacher
     * town. the town of the teacher
     * county. the county of the teacher
     *Data of Birth. the date of Birth of teacher
     * gender. the gender of the teacher
     * phone. the phone of the teacher
     * email. the email of the teacher
    *department. the department of the teacher
     */



    AddTeacherView(ControllerInterface controller)
    {
        this.controller = controller;
// I set upt the main frame
        addTeacherFrame.setSize(500,500);
        addTeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(layout);

        // I call this method to fill the ComboBoxes for the DOB
        generateComboBoxes();

        // I set up the panel where the ComboBoxes will be
        addDob();
// I set up the panel where the RadioButtons for the gender will be
        addGender();
        // I add all the components to the main form
addLabelsTextboxes();

// I add the listener to the buttons
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // I set up the panel where the buttons will be
addButtons();

        display();

    } // end constructor


    /** I declare the listener
     *
     * @param e
     */


    public void actionPerformed(ActionEvent e)
    {
        // I deal with theoption picked by the user
        String option = e.getActionCommand();
// It depends on the option the programme will run one case or another
        switch(option)
        {
            case "Add":
controller.save();

                break;
            case "Cancel":
  controller.close();

                break;

        } // end switch

    } //end actionperformed

    private  void  addDob(){
        dobPanel.add(dayComboBox);
        dobPanel.add(slashLabel1);
        dobPanel.add(monthComboBox);
        dobPanel.add(slashLabel2);
        dobPanel.add(yearComboBox);
        } // end add dob

    private  void  addGender(){
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

    } // end add gender

    private void addLabelsTextboxes(){
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
        formPanel.add(departmentLabel);
        formPanel.add(departmentTextField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneTextField);
        formPanel.add(emailLabel);
        formPanel.add(emailTextField);

    } // end add labels textboxes


    private  void  addButtons(){
        buttonsPanel.add(addButton, BorderLayout.EAST);
        buttonsPanel.add(cancelButton, BorderLayout.WEST);

        // I add the panels to the main frame
        addTeacherFrame.add(formPanel, BorderLayout.CENTER);
        addTeacherFrame.add(buttonsPanel, BorderLayout.SOUTH);
    } // end add buttons


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
     *
     */


    public void emptyForm(){
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
nameTextField.requestFocus();
}// end empty form

public void  display(){
    formPanel.setVisible(true);
    addTeacherFrame.setVisible(true);

} // end display



} // end class
