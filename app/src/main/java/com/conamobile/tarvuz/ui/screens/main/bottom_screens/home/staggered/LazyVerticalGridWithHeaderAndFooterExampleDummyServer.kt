package com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.staggered

import com.conamobile.tarvuz.R

object LazyVerticalGridWithHeaderAndFooterExampleDummyServer {

    fun fetchDataFromDummyServer(offset: Int, limit: Int): List<LVGTestDataModel> {

        val list = mutableListOf<LVGTestDataModel>()

        for (i in offset until (offset + limit)) {

            // Let's suppose the maximum is 42, so the index is from 0 to 41
            if (i == 42) {
                return list
            }

            LVGTestDataModel(
                index = i,
                imageResource = R.drawable.watermelon_img,
                content = "grid item."
            ).apply {
                list.add(this)
            }
        }

        return list
    }

}