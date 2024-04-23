package com.elha.bottommodule

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.elha.bottommodule.databinding.BottomsheetMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Created by elnurh on 4/23/2024 with builder pattern.
 */
class ElhaBottomSheetDialog: BaseBottomSheetDialog() {


    private var backButton = false
    private lateinit var itemList: List<BottomModule>
    private var onItemsSelected: ((BottomModule) -> Unit)? = null
    private var onDismiss: (() -> Unit)? = null
    private var onBack: (() -> Unit)? = null

    override var showFullscreen: Boolean
        get() = false
        set(value) {}

    lateinit var bottomAdapter: BottomAdapter

    private lateinit var binding: BottomsheetMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomsheetMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
        }
        initView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        with(binding) {
            recyclerViewList.layoutManager = LinearLayoutManager(context)
            recyclerViewList.setHasFixedSize(true)

            bottomAdapter = BottomAdapter(
                BottomAdapter.BottomItemClick {
                    onItemsSelected?.invoke(it)
                    this@ElhaBottomSheetDialog.dismiss()
                })
            recyclerViewList.adapter = bottomAdapter
            bottomAdapter.submitList(itemList)
            binding.backButton.setOnClickListener {
                onDismiss?.invoke()
            }

        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismiss?.invoke()
    }


    class Builder {
        var showFullScreen = false
        var itemList: List<BottomModule>? = null
        var onItemsSelected: ((BottomModule) -> Unit)? = null
        var onDismiss: (() -> Unit)? = null
        var onBack: (() -> Unit)? = null

        fun showFullScreen() {
            this.showFullScreen = showFullScreen
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
        fun onBack(onDismiss: () -> Unit) {
            this.onBack = onBack
        }

        fun build(): ElhaBottomSheetDialog? {
            val bottomSheet = ElhaBottomSheetDialog()
            bottomSheet.showFullscreen = showFullScreen
            bottomSheet.itemList = itemList?:return null
            bottomSheet.onItemsSelected = onItemsSelected
            bottomSheet.onDismiss = onDismiss
            bottomSheet.onBack = onBack
            return bottomSheet
        }
    }


}
fun elhaBottomSheet(lambda: ElhaBottomSheetDialog.Builder.() -> Unit) =
    ElhaBottomSheetDialog.Builder().apply(lambda).build()