package uz.gita.newsappfinaly.data.model

import com.google.firebase.firestore.DocumentSnapshot

object Mapper {

    fun DocumentSnapshot.toCategory() = Category(
        id = this["id"].toString().toInt(),
        name = this["name"].toString(),
        image = this["image"].toString(),
        isChecking = this["is_cheking"].toString()
    )
}