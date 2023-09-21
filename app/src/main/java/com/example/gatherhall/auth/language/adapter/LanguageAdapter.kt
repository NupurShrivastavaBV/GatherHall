package com.example.gatherhall.auth.language.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gatherhall.R
import com.example.gatherhall.auth.language.model.Language
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView


class LanguageAdapter(private val languageList: List<Language>, private val context: Context):
    RecyclerView.Adapter<LanguageAdapter.RecycleViewHolder>(){

    class RecycleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val language : MaterialTextView = itemView.findViewById(R.id.txtLanguage)
        val imgLanguageSelect : ShapeableImageView = itemView.findViewById(R.id.imgLanguageSelect)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecycleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycleview_language,parent,false)
        return RecycleViewHolder(view)
    }

    var selectedPosition = -1
    var lastSelectedPosition = -1

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.language.text = languageList[position].language
        holder.itemView.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition == holder.adapterPosition){
            holder.imgLanguageSelect.visibility = View.VISIBLE
        } else{
            holder.imgLanguageSelect.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return languageList.size
    }


}