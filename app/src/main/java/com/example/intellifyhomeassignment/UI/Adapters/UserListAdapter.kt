package com.example.intellifyhomeassignment.UI.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.intellifyhomeassignment.R
import com.example.intellifyhomeassignment.UI.User
import kotlinx.android.synthetic.main.user_lists.view.*

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserDataViewHolder>()
{
    //creating inner class first
    inner class UserDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    // creating a diffCallBack as it is important for using with async List Differ
    private val differCallBack = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
    // creating async list differ now
    val asyncDiffer = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewHolder {
        return UserDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_lists,
            parent, false))


    }

    override fun onBindViewHolder(holder: UserDataViewHolder, position: Int) {
                val userData = asyncDiffer.currentList[position]
holder.itemView.apply {
    Name.text=userData.name
    Age.text=userData.age
    City.text=userData.city
    Gender.text=userData.gender
setOnClickListener{
onItemClickListener?.let{
it(User())
}
}
}
    }

    override fun getItemCount(): Int {
        return asyncDiffer.currentList.size
    }


}
private var onItemClickListener: ((User) -> Unit)? = null
fun setOnItemClickListener(listener: (User) -> Unit) {
    Log.d("THEListener", "articlesUrl is $listener")
    onItemClickListener = listener
}
