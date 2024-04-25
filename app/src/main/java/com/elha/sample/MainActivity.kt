package com.elha.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.elha.bottommodule.BottomModule
import com.elha.bottommodule.ElhaBottomSheetDialog
import com.elha.bottommodule.elhaBottomSheet

class MainActivity : AppCompatActivity() {

    lateinit var bottomView: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomView = findViewById(R.id.bottom_view)

        bottomView.setOnClickListener {
            openBottomModule(dummyData())
        }
    }

    private fun dummyData(): List<BottomModule> {
        val arrayList = arrayListOf<BottomModule>()
        for (i in 0..15) arrayList.add(BottomModule("Mastercard", "**** $i","$$i 000"))
        return arrayList
    }

    private fun openBottomModule(list: List<BottomModule>){
        elhaBottomSheet {
            // set true for fullscreen or false half screen
            showFullScreen(true)
            // submit list for adapter
            itemList = list
            onItemsSelected = {
                // handle item selection
            }
            onBack {
                // handle back button click
            }
            setTopMarginView(70)
        }?.show(supportFragmentManager, ElhaBottomSheetDialog::class.java.canonicalName)
    }
}