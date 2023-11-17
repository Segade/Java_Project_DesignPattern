

public class StudentAdapter extends Teacher{

    public StudentAdapter(Student student){
super(student.getId(), student.getName(), student.getSurname(), student.getAddress(), student.getTown(), student.getCounty(), student.getDob(), student.getEmail(), student.getPhone(),student.getGender(), "student");


    } // end constructor


} // end of class
