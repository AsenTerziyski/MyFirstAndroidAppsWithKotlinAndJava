package com.example.mychucknoris.datasources

import com.example.mychucknoris.model.ChuckCategories
import com.example.mychucknoris.model.enums.CategoriesEnum
import java.util.*

class DataLocalSource {
    fun getLocalCategories() : ChuckCategories {
        return ChuckCategories(CategoriesEnum.values().map { it.toString().lowercase(Locale.ROOT) })
    }
}