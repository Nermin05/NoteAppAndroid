package com.example.noteapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteapp.databinding.ActivityDetailsBinding

class Details : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var myDatabase: SQLiteDatabase
    private var isHeartSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.boxsend.setOnClickListener {
            insertDatabase()
        }
        binding.clipboard.setOnClickListener {
            updateDatabase()
        }
        binding.delete.setOnClickListener {
            deleteNote()
        }

        binding.arrowleft.setOnClickListener {
            val intent = Intent(this@Details, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val id = intent.getStringExtra("id")
        val info = intent.getStringExtra("info")

        if (info == "new") {
            binding.basliq.setText("")
            binding.text.setText("")
        } else {
            loadNoteData(id)
        }

        val heartIcon: ImageView = findViewById(R.id.heart)

        heartIcon.setOnClickListener {
            isHeartSelected = !isHeartSelected
            if (isHeartSelected) {
                heartIcon.setImageResource(R.drawable.heart)
            } else {
                heartIcon.setImageResource(R.drawable.heart_outline)
            }
        }

    }

    @SuppressLint("Range")
    private fun loadNoteData(id: String?) {
        try {
            myDatabase = this.openOrCreateDatabase("Notes", Context.MODE_PRIVATE, null)
            val cursor = myDatabase.rawQuery("SELECT * FROM notes WHERE id = ?", arrayOf(id))

            if (cursor.moveToFirst()) {
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val details = cursor.getString(cursor.getColumnIndex("details"))
                binding.basliq.setText(title)
                binding.text.setText(details)
            }
            cursor.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun insertDatabase() {
        try {
            myDatabase = this.openOrCreateDatabase("Notes", Context.MODE_PRIVATE, null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Notes(id INTEGER PRIMARY KEY,title VARCHAR,details VARCHAR)")
            val sqlSorgu = "INSERT INTO notes(title,details) VALUES(?,?)"
            val statement = myDatabase.compileStatement(sqlSorgu)
            statement.bindString(1, binding.basliq.text.toString())
            statement.bindString(2, binding.text.text.toString())
            statement.execute()
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun updateDatabase() {
        try {
            myDatabase = this.openOrCreateDatabase("Notes", Context.MODE_PRIVATE, null)

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY, title VARCHAR, details VARCHAR)")

            val sqlSorgu = "UPDATE notes SET title=?, details=? WHERE id=?"
            val statement = myDatabase.compileStatement(sqlSorgu)
            statement.bindString(1, binding.basliq.text.toString())
            statement.bindString(2, binding.text.text.toString())

            val noteId = intent.getStringExtra("id")

            if (noteId != null) {
                statement.bindString(3, noteId)
                statement.execute()
                Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failed to update: Note ID is null", Toast.LENGTH_LONG).show()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "Failed to update note", Toast.LENGTH_LONG).show()
        }
    }
    private fun deleteNote() {
        try {
            val id = intent.getStringExtra("id")
            myDatabase = this.openOrCreateDatabase("Notes", Context.MODE_PRIVATE, null)
            myDatabase.execSQL("DELETE FROM notes WHERE id = ?", arrayOf(id))
            Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show()
            finish()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }



}
