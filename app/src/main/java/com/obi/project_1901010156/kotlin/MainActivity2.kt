package com.obi.project_1901010156.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    private var noData = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

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
                visibility(data.size)
            }

        })

        binding.fab.setOnClickListener(this)
    }

    private fun visibility(data : Int){
        if (data == 0){
            binding.rvNote.visibility = View.GONE
            binding.viewNodata.visibility = View.VISIBLE
            binding.textNodata.visibility = View.VISIBLE
        }else{
            binding.rvNote.visibility = View.VISIBLE
            binding.viewNodata.visibility = View.GONE
            binding.textNodata.visibility = View.GONE
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.fab -> {
                startActivity(Intent(this, AddNoteActivity::class.java))
            }
        }
    }
}