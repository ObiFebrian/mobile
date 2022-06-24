package com.obi.project_1901010156.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.expandable.ExpandableTransformationWidget
import com.obi.project_1901010156.R
import com.obi.project_1901010156.databinding.ActivityAddNoteBinding
import com.obi.project_1901010156.kotlin.database.Note
import com.obi.project_1901010156.kotlin.helper.ViewModelFactory
import com.obi.project_1901010156.kotlin.viewmodel.NoteAddViewModel

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNoteBinding
    private lateinit var model : NoteAddViewModel
    private var note : Note? = null
    private var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this, ViewModelFactory.getInstance(this.application))[NoteAddViewModel::class.java]

        note = intent.getParcelableExtra(EXTRA_DAT)
        if (note != null){
            isEdit = true
        }else{
            note = Note()
        }

        var actionBar : String
        var btnTittle : String

        if (isEdit){
            actionBar = getString(R.string.edit_tittle)
            btnTittle = getString(R.string.edit_tittle1)
            if (note != null){
                binding.apply {
                    edtTitle.setText(note?.title)
                    edtDescription.setText(note?.description)
                }
            }
        }else{
            actionBar = getString(R.string.addnote_tittle)
            btnTittle = getString(R.string.save_tittle)
        }

        supportActionBar?.title = actionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnSubmit.setText(btnTittle)

        binding.btnSubmit.setOnClickListener {
            var tittle : String
            var deskripsi : String

            binding.apply {
                tittle = edtTitle.text.toString()
                deskripsi = edtDescription.text.toString()
            }

            when{
                tittle.isEmpty() -> {
                    binding.edtTitle.error = getString(R.string.tittle_validasi)
                }
                deskripsi.isEmpty() -> {
                    binding.edtDescription.error = getString(R.string.tittle_validasi)
                }
                else -> {
                    note.let { note ->
                        note?.title = tittle
                        note?.description = deskripsi
                    }
                    if (isEdit){
                        model.update(note as Note)
                        showToast("Edit data succesfull")
                    }else{
                        model.insert(note as Note)
                        showToast("Save data succesful")
                    }
                    finish()
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit){
            menuInflater.inflate(R.menu.menu_option, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                finish()
            }
            R.id.delete -> {
                model.delete(note as Note)
                showToast("Delete Data Succeffull")
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showToast(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val EXTRA_DAT = "extra_data"
    }
}