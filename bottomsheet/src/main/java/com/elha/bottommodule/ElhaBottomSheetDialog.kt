package com.elha.bottommodule

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import com.elha.bottommodule.databinding.BottomsheetMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


/**
 * Created by elnurh on 4/23/2024 with builder pattern.
 */
class ElhaBottomSheetDialog : BaseBottomSheetDialog() {

    private lateinit var itemList: List<BottomModule>
    private var onItemsSelected: ((BottomModule) -> Unit)? = null
    private var onDismiss: (() -> Unit)? = null
    private var onBack: (() -> Unit)? = null
    private var isFullscreen: Boolean = false
    private var topMargin: Int = 0


    lateinit var bottomAdapter: BottomAdapter

    private lateinit var binding: BottomsheetMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = BottomsheetMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.showFullscreen = isFullscreen

        (dialog as? BottomSheetDialog)?.behavior?.apply {
            if (isFullscreen)
                state = BottomSheetBehavior.STATE_EXPANDED
            else
                state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }

        initView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        with(binding) {
            recyclerViewList.layoutManager = LinearLayoutManager(context)
            recyclerViewList.setHasFixedSize(true)

            bottomAdapter = BottomAdapter(BottomAdapter.BottomItemClick {
                onItemsSelected?.invoke(it)
                this@ElhaBottomSheetDialog.dismiss()
            })
            recyclerViewList.adapter = bottomAdapter
            bottomAdapter.submitList(itemList)
            binding.backButton.setOnClickListener {
                onBack?.invoke()
            }

        }
        setViewMargin(topMargin)
    }

    fun setViewMargin(top: Int){
        val newLayoutParams = binding.mainLayout.layoutParams as FrameLayout.LayoutParams
        newLayoutParams.topMargin = top
        binding.mainLayout.layoutParams = newLayoutParams
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismiss?.invoke()
    }


    class Builder {
        var isFullscreen: Boolean = false
        var itemList: List<BottomModule>? = null
        var onItemsSelected: ((BottomModule) -> Unit)? = null
        var onDismiss: (() -> Unit)? = null
        var onBack: (() -> Unit)? = null
        var topMargin: Int = 0

        fun showFullScreen(show: Boolean) {
            this.isFullscreen = show
        }

        fun setTopMarginView(topMargin: Int) {
            this.topMargin = topMargin
        }

        fun itemList(itemList: () -> List<BottomModule>) {
            this.itemList = itemList()
        }

        fun onItemsSelected(onItemsSelected: (BottomModule) -> Unit) {
            this.onItemsSelected = onItemsSelected
        }

        fun onDismiss(onDismiss: () -> Unit) {
            this.onDismiss = onDismiss
        }

        fun onBack(onBack: () -> Unit) {
            this.onBack = onBack
        }

        fun build(): ElhaBottomSheetDialog? {
            val bottomSheet = ElhaBottomSheetDialog()
            bottomSheet.isFullscreen = isFullscreen
            bottomSheet.itemList = itemList ?: return null
            bottomSheet.onItemsSelected = onItemsSelected
            bottomSheet.onDismiss = onDismiss
            bottomSheet.onBack = onBack
            bottomSheet.topMargin = topMargin
            return bottomSheet
        }
    }


}

fun elhaBottomSheet(lambda: ElhaBottomSheetDialog.Builder.() -> Unit) =
    ElhaBottomSheetDialog.Builder().apply(lambda).build()
