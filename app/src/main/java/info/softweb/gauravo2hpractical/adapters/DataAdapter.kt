package info.softweb.gauravo2hpractical.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import info.softweb.gauravo2hpractical.R
import info.softweb.gauravo2hpractical.databinding.RowUserBinding
import info.softweb.gauravo2hpractical.models.UserResult
import info.softweb.gauravo2hpractical.utils.TAG

class DataAdapter(private var context: Context, var listData: ArrayList<UserResult>?) : androidx.recyclerview.widget.RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_user,parent,false)
    )

    override
    fun onBindViewHolder(holder: ViewHolder, position: Int) = holder!!.bindItems(listData?.get(position),context)

    inner class ViewHolder(val binding: RowUserBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bindItems(data: UserResult?, context: Context) {
            binding.apply {
                data?.let {
                  // textViewName.text="${it.} ${it.lastName}"
                    textViewEmail.text=it.email
                    Log.d(TAG, "bindItems: "+it.picture?.medium)
                    Glide
                        .with(context)
                        .load(it.picture?.medium)
                        .centerCrop()
                        .placeholder(R.drawable.ic_placeholder)
                        .into(binding.imageView)
                }
   }
        }
    }

    fun updateData(listNewData: List<UserResult>)
    {
        listData?.addAll(listNewData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listData!!.size
    }
}