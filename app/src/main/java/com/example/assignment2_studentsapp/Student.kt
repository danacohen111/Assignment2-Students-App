import com.example.assignment2_studentsapp.R

data class Student(
    var name: String,
    var id: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean,
    var picture: Int = R.drawable.student_pic
)
