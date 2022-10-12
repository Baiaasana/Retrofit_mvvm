package com.example.apichat.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apichat.R
import com.example.apichat.databinding.MessageItemBinding
import com.example.apichat.presenter.model.UserUI
import com.example.apichat.utility.Constants
import com.example.apichat.utility.Glide
import java.text.SimpleDateFormat
import java.util.*

class UserAdapter : ListAdapter<UserUI, UserAdapter.MessageViewHolder>(ItemCallback) {

    inner class MessageViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            binding.apply {
                tvFirstName.text = item.firstName.toString()
                tvLastName.text = item.lastName.toString()
                Glide().setImage(item.avatar.toString(), ivUser)
                when (item.messageType) {
                    Constants.TEXT.value -> {
                        tvMessage.text = item.lastMessage.toString()
                    }
                    Constants.ATTACHMENT.value -> {
                        ivType.isVisible = true
                        ivType.setImageResource(R.drawable.ic_attachment)
                        tvMessage.text = Constants.ATTACHMENT_MESSAGE.value
                    }
                    Constants.VOICE.value -> {
                        ivType.isVisible = true
                        ivType.setImageResource(R.drawable.ic_recorder)
                        tvMessage.text = Constants.VOICE_MESSAGE.value
                    }
                }
                if (item.isTyping == false) {
                    if (item.unreadMessage != 0 && item.unreadMessage != 1) {
                        ivTyping.isVisible = false
                        tvTime.setTextColor(Color.WHITE)
                        tvMessage.setTextColor(Color.WHITE)
                        tvUnreadMessageNumber.text = item.unreadMessage.toString()
                        tvUnreadMessageNumber.isVisible = true
                    } else if (item.unreadMessage == 1) {
                        tvTime.setTextColor(Color.WHITE)
                        tvMessage.setTextColor(Color.WHITE)
                    }
                } else {
                    ivTyping.isVisible = true
                }
                tvTime.text = item.updated_date?.let { getData(it, "HH:mm aa").toString() }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getData(milliSeconds: Long, dateFormat: String?): String? {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
        MessageViewHolder(MessageItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) = holder.bind()

    object ItemCallback : DiffUtil.ItemCallback<UserUI>() {
        override fun areItemsTheSame(
            oldItem: UserUI,
            newItem: UserUI,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserUI,
            newItem: UserUI,
        ): Boolean {
            return oldItem == newItem
        }
    }
}