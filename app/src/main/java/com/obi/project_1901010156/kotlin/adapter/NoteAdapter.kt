package com.obi.project_1901010156.kotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.obi.project_1901010156.databinding.CardNoteRowBinding
import com.obi.project_1901010156.kotlin.AddNoteActivity
import com.obi.project_1901010156.kotlin.database.Note
import com.obi.project_1901010156.kotlin.helper.NoteDiffCallback

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val listNote = ArrayList<Note>()
    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(listNote, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNote.clear()
        this.listNote.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    class NoteViewHolder(private val binding : CardNoteRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note){
            binding.apply {
                txtTittle.setText(note.title)
                txtContent.setText(note.description)
            }

            binding.itemNoteRow.setOnClickListener {
                val data = Note(
                    note.id,
                    note.title,
                    note.description
                )
                val move = Intent(itemView.context, AddNoteActivity::class.java)
                move.putExtra(AddNoteActivity.EXTRA_DAT, data)
                itemView.context.startActivity(move)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = CardNoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNote[position])
    }

    override fun getItemCount(): Int = listNote.size
}