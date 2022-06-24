package com.obi.project_1901010156.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.obi.project_1901010156.R
import com.obi.project_1901010156.databinding.ActivityMain2Binding
import com.obi.project_1901010156.kotlin.adapter.NoteAdapter
import com.obi.project_1901010156.kotlin.helper.ViewModelFactory
import com.obi.project_1901010156.kotlin.viewmodel.MainViewModel

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMain2Binding
    private lateinit var adapter : NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NoteAdapter()
        binding.apply {
            rvNote.layoutManager = LinearLayoutManager(this@MainActivity2)
            rvNote.setHasFixedSize(true)
            rvNote.adapter = adapter
        }

        val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this.application))[MainViewModel::class.java]
        viewModel.getNOte().observe(this, {data ->
            if (data != null){
                adapter.setListNotes(data)
            }
        })

        binding.fab.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.fab -> {
                startActivity(Intent(this, AddNoteActivity::class.java))
            }
        }
    }
}