package com.example.noteapp
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Date
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dateList: ArrayList<Date>
    private lateinit var categoriesList: ArrayList<Categories>
    private lateinit var noteList: ArrayList<Note>

//    private lateinit var myDatabase: SQLiteDatabase
//    private lateinit var noteList: ArrayList<Date>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dateList = ArrayList<Date>()
        val uc = Date("Tue", "23", "Apr")
        val dord = Date("Wed", "24", "Apr")
        val bes = Date("Thu", "25", "Apr")
        val alti = Date("Fri", "26", "Apr")
        val yeddi = Date("Sat", "27", "Apr")
        val sekkiz = Date("Sun", "28", "Apr")
        val doqquz = Date("Mon", "29", "Apr")
        val otuz = Date("Tue", "30", "Apr")

        dateList.add(uc)
        dateList.add(dord)
        dateList.add(bes)
        dateList.add(alti)
        dateList.add(yeddi)
        dateList.add(sekkiz)
        dateList.add(doqquz)
        dateList.add(otuz)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val dateAdapter = DateAdapter(dateList)
        binding.recyclerView.adapter = dateAdapter


        categoriesList = ArrayList<Categories>()
        val all = Categories("All")
        val important = Categories("Important")
        val lecture = Categories("Lecture notes")
        val todoList = Categories("To-do lists")
        val shopping = Categories("Shopping")


        categoriesList.add(all)
        categoriesList.add(important)
        categoriesList.add(lecture)
        categoriesList.add(todoList)
        categoriesList.add(shopping)
        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView2.layoutManager = layoutManager2
        val categoriesAdapter = CategoriesAdapter(categoriesList)
        binding.recyclerView2.adapter = categoriesAdapter


        noteList = ArrayList<Note>()
        val note1 = Note("Product Meeting","1.Review of Previous Action Items\n" +
                "2.Product Development Update\n" +
                "3.User Feedback and Customer Insights\n" +
                "4.Competitive Analysis\n" +
                "5.Roadmap Discussion ", Color.parseColor("#D9E8FC"))
        val note2 = Note("To-do List","Reply to emails\n" +
                "Prepare presentation slides for the marketing meeting\n" +
                "Conduct research on competitor products\n" +
                "Schedule and plan customer interviews\n" +
                "Take a break and recharge ",Color.parseColor("#FFD8F4"))
        val note3 = Note("Shopping List","Rice\n" +
                "Pasta\n" +
                "Cereal\n" +
                "Yogurt\n" +
                "Cheese\n" +
                "Butter ",Color.parseColor("#FDE99D"))
        val note4 = Note(" Important","Summarize the key action items identified during the meeting.\n" +
                "Assign responsible team members and set deadlines for each action item.\n" +
                "Clarify the next steps and ensure everyone is clear on their respective tasks.",Color.parseColor("#B0E9CA"))
        val note5 = Note("Notes","Share insights and findings from recent competitive analysis.",Color.parseColor("#FFEADD"))
        val note6 = Note("Product meeting","Product Development Update ",Color.parseColor("#FCFAD9"))

        noteList.add(note1)
        noteList.add(note2)
        noteList.add(note3)
        noteList.add(note4)
        noteList.add(note5)
        noteList.add(note6)

        val layoutManager3 = GridLayoutManager(this, 2)
        binding.recyclerView3.layoutManager = layoutManager3
        val notesAdapter = NoteAdapter(noteList)
        binding.recyclerView3.adapter = notesAdapter




////        binding.fab.setOnClickListener {
////            val intent = Intent(this, Details::class.java)
////            intent.putExtra("info","new")
////            startActivity(intent)
////        }
//        noteList= ArrayList<Date>()
//
//        binding.recyclerView.layoutManager= LinearLayoutManager(this)
//        val dateAdapter= DateAdapter(noteList)
//        binding.recyclerView.adapter=dateAdapter
//
//
//        try {
//
//            myDatabase = this.openOrCreateDatabase("Notes", Context.MODE_PRIVATE, null)
//
//
//            val cursor = myDatabase.rawQuery("SELECT * FROM notes", null)
//
//            val gun = cursor.getColumnIndex("Gun")
//            val tarix = cursor.getColumnIndex("Tarix")
//            val ay = cursor.getColumnIndex("Ay")
//
//            while (cursor.moveToNext()) {
//                val gunStr = cursor.getString(gun)
//                val tarixStr = cursor.getString(tarix)
//                val ayStr = cursor.getString(ay)
//                val date = Date(gunStr,tarixStr, ayStr)
//                println(gunStr+ " " + tarixStr + " " + ayStr)
//                noteList.add(date)
//
//            }
//
//            cursor.close()
//
//
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}