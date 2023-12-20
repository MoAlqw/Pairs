package com.example.pairs

class Diamond(
    val img: Int
) {

    companion object {

        fun loadDiamonds(): List<Diamond> {
            return listOf(
                Diamond(R.drawable.diamond_1),
                Diamond(R.drawable.diamond_2),
                Diamond(R.drawable.diamond_3),
                Diamond(R.drawable.diamond_4),
                Diamond(R.drawable.diamond_5),
                Diamond(R.drawable.diamond_6),
                Diamond(R.drawable.diamond_7),
                Diamond(R.drawable.diamond_8),
                Diamond(R.drawable.diamond_9),
                Diamond(R.drawable.diamond_10),
                Diamond(R.drawable.diamond_1),
                Diamond(R.drawable.diamond_2),
                Diamond(R.drawable.diamond_3),
                Diamond(R.drawable.diamond_4),
                Diamond(R.drawable.diamond_5),
                Diamond(R.drawable.diamond_6),
                Diamond(R.drawable.diamond_7),
                Diamond(R.drawable.diamond_8),
                Diamond(R.drawable.diamond_9),
                Diamond(R.drawable.diamond_10)
            )
        }
    }

}