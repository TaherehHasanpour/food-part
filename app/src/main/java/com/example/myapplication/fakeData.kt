package com.example.myapplication


data class Category(
    val image: Int,
    var titel: String,
    val caption: String,
    val id: Int,
    var category: List<SubCategory>?,

    )

data class SubCategory(
    val title: String,
    val id: Int,
)

enum class Hardness {
    EASY,
    MEDIUM,
    HARD
}

data class AllFood(
    var image: Int,
    val title: String,
    val caption: String,
    val idSubCategory: Int,
    val hardness: Hardness,
    val idCategory: Int
)
val subcategory= listOf(
    SubCategory("ابگوشت",1),
    SubCategory("ابگوشت",2),
    SubCategory("ابگوشت",3),
    SubCategory("ابگوشت",4),
    SubCategory("ابگوشت",5),
    SubCategory("ابگوشت",6),

    )
val allFood = listOf(
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 1, Hardness.HARD,1),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 1, Hardness.EASY,1),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, Hardness.HARD,3),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 4, Hardness.MEDIUM,4),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 1, Hardness.MEDIUM,3),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 4, Hardness.HARD,5),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, Hardness.EASY,3),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 1, Hardness.EASY,1),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 4, Hardness.MEDIUM,2),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, Hardness.EASY,1),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 6, Hardness.MEDIUM,4),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 5, Hardness.HARD,2),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 1, Hardness.MEDIUM,3),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 6, Hardness.EASY,2),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 1, Hardness.HARD,1),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 2, Hardness.MEDIUM,4),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, Hardness.HARD,2),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 1, Hardness.HARD,2),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 2, Hardness.HARD,5),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 2, Hardness.HARD,3),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, Hardness.HARD,1),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 2, Hardness.HARD,2),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, Hardness.HARD,1),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, Hardness.HARD,3),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 2, Hardness.HARD,1),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, Hardness.HARD,2),
    AllFood(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, Hardness.HARD,5),

    )
val categories = listOf(
    Category(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 1, subcategory),
    Category(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 2, null),
    Category(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 3, subcategory),
    Category(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 4, null),
    Category(R.drawable.food, "ابگوشت", "زمان 12 دقیقه", 5, subcategory),

    )


