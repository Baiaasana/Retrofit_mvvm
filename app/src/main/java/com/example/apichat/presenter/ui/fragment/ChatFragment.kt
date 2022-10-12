package com.example.apichat.presenter.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apichat.adapter.UserAdapter
import com.example.apichat.common.BaseFragment
import com.example.apichat.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>(FragmentChatBinding::inflate) {

    private val viewModel: ChatViewModel by viewModels()
    private val userAdapter: UserAdapter = UserAdapter()

    override fun listeners() {
    }

    override fun init() {
        viewModel.getInfo()
        initRecyclers()
    }

    private fun initRecyclers() {
        binding.messageRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collect {
                    userAdapter.submitList(it.data)
                }
            }
        }
    }
}