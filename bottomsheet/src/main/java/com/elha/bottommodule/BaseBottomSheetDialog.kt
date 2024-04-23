package com.elha.bottommodule

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.imageview.ShapeableImageView

/**
 * Created by elnurh on 4/23/2024 for GoldenPay.
 */
abstract class BaseBottomSheetDialog() : BottomSheetDialogFragment() {

    open var showFullscreen: Boolean = true
    open var isDraggable: Boolean = true
    open var notFullscreen: () -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_ELha_BottomSheetDialog)
    }

    override fun getTheme(): Int  = R.style.Theme_ELha_BottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { parentView ->
                val behaviour = BottomSheetBehavior.from(parentView)
                if (showFullscreen) {
                    setupFullHeight(parentView)
                    behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                }
                behaviour.isDraggable = isDraggable

            }
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val behavior = BottomSheetBehavior.from(view.parent as View)
        behavior.skipCollapsed = true
        if (!showFullscreen) {
            notFullscreen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        view?.hideKeyboard()
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

}