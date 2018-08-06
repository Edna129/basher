package ru.ltcnt.basher.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_item_post.view.*
import ru.ltcnt.basher.R
import ru.ltcnt.basher.domain.viewModels.BashPostView

class PostListAdapter(
        private val onScrollToBottom: () -> Unit
): RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private var data = arrayListOf<BashPostView>()

    fun setData(newDataList: ArrayList<BashPostView>){
        data = newDataList
        notifyDataSetChanged()
    }

    fun clearData() {
        notifyItemRangeRemoved(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item_post, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(item: BashPostView){
            if (adapterPosition >= data.lastIndex){
                onScrollToBottom.invoke()
            }
            itemView.text.text = item.text
        }
    }
}