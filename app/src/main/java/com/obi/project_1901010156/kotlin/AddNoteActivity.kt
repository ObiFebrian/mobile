package com.obi.project_1901010156.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.obi.project_1901010156.R
import com.obi.project_1901010156.databinding.ActivityAddNoteBinding
import com.obi.project_1901010156.kotlin.database.Note
import com.obi.project_1901010156.kotlin.helper.ViewModelFactory
import com.obi.project_1901010156.kotlin.viewmodel.NoteAddViewModel

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNoteBinding
    private lateinit var model : NoteAddViewModel
    private var note : Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this, ViewModelFactory.getInstance(this.application))[NoteAddViewModel::class.java]

        note = Note()
        binding.btnSubmit.setOnClickListener {
            var tittle = binding.edtTitle.text.toString().trim()
            var deskripsi = binding.edtDescription.text.toString().trim()

//            binding.apply {
//                tittle = edtTitle.text.toString()
//                deskripsi = edtDescription.text.toString()
//            }
//            note?.title = tittle
//            note?.description = deskripsi

            note.let { note ->
                note?.title = tittle
                note?.description = deskripsi
            }

            if (tittle.isNotEmpty() && deskripsi.isNotEmpty()){
                model.insert(note as Note)
//                finish()
            }else{
                Toast.makeText(this, "ini tidak ada isi", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

    }
}