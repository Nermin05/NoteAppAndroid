package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dateList: ArrayList<Date>
    private lateinit var categoriesList: ArrayList<Categories>
    private lateinit var noteList: ArrayList<Note>
    private lateinit var myDatabase: SQLiteDatabase
    private lateinit var notesAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateList = ArrayList()
        categoriesList = ArrayList()
        noteList = ArrayList()
        myDatabase = this.openOrCreateDatabase("Notes", Context.MODE_PRIVATE, null)

        setupDates()
        setupCategories()
        setupNotesRecyclerView()

        binding.fab.setOnClickListener {
            val intent = Intent(this, Details::class.java)
            intent.putExtra("info", "new")
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupDates() {
        dateList.add(Date("Tue", "23", "Apr"))
        dateList.add(Date("Wed", "24", "Apr"))
        dateList.add(Date("Thu", "25", "Apr"))
        dateList.add(Date("Fri", "26", "Apr"))
        dateList.add(Date("Sat", "27", "Apr"))
        dateList.add(Date("Sun", "28", "Apr"))
        dateList.add(Date("Mon", "29", "Apr"))
        dateList.add(Date("Tue", "30", "Apr"))

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = DateAdapter(dateList)
    }

    private fun setupCategories() {
        categoriesList.add(Categories("All"))
        categoriesList.add(Categories("Important"))
        categoriesList.add(Categories("Lecture notes"))
        categoriesList.add(Categories("To-do lists"))
        categoriesList.add(Categories("Shopping"))

        binding.recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView2.adapter = CategoriesAdapter(categoriesList)
    }

    private fun setupNotesRecyclerView() {
        binding.recyclerView3.layoutManager = GridLayoutManager(this, 2)

        notesAdapter = NoteAdapter(noteList) { note ->
            showDeleteConfirmationDialog(note)
        }
        binding.recyclerView3.adapter = notesAdapter

        loadNotesFromDatabase()
    }

    private fun showDeleteConfirmationDialog(note: Note) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Delete") { _, _ ->
                deleteNoteFromDatabase(note)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun deleteNoteFromDatabase(note: Note) {
        try {
            myDatabase.execSQL("DELETE FROM notes WHERE id = '${note.id}'")
            noteList.remove(note)
            notesAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Note deleted successfully", Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "Failed to delete note", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadNotesFromDatabase() {
        try {
            val cursor: Cursor = myDatabase.rawQuery("SELECT * FROM notes", null)

            val idIx: Int = cursor.getColumnIndex("id")
            val titleIx: Int = cursor.getColumnIndex("title")
            val detailIx: Int = cursor.getColumnIndex("details")

            while (cursor.moveToNext()) {
                val idInt: Int = cursor.getInt(idIx)
                val titleStr: String = cursor.getString(titleIx)
                val detailStr: String = cursor.getString(detailIx)
                val note = Note(idInt.toString(), titleStr, detailStr)
                noteList.add(note)
            }

            cursor.close()
            notesAdapter.notifyDataSetChanged()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }
}