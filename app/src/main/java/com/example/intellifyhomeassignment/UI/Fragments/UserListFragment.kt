package com.example.intellifyhomeassignment.UI.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intellifyhomeassignment.R
import com.example.intellifyhomeassignment.UI.Adapters.UserListAdapter
import com.example.intellifyhomeassignment.UI.Adapters.setOnItemClickListener
import com.example.intellifyhomeassignment.UI.UserViewModel
import kotlinx.android.synthetic.main.fragment_userlist.*
import kotlinx.android.synthetic.main.user_lists.*


class UserListFragment : Fragment(R.layout.fragment_userlist) {
       lateinit var userAdapter: UserListAdapter

    private lateinit var viewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setupRecyclerView()
        userAdapter.apply {
            setOnItemClickListener {
                val bundle = Bundle().apply {
                   putSerializable("users",it)
                }
        findNavController().navigate(R.id.action_userListFragment_to_itemDetailsFragment,bundle)
        }}
        getResponseUsingCoroutines()
    }
    private fun setupRecyclerView() {
        userAdapter= UserListAdapter()
RecyclerView.apply {
adapter=userAdapter
    layoutManager=LinearLayoutManager(context)
}
    }

    private fun getResponseUsingCoroutines() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, Observer { response->
            print(response)
            userAdapter.asyncDiffer.submitList(response.users?.toList())
        })
    }
}


