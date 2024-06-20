package com.example.lansiasehat.ui.kuis

import com.example.lansiasehat.R


object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val SCORE: String = "score"
    const val QUESTION_TYPE: String = "question_type"

    const val TYPE_MUDAH: String = "mudah"
    const val TYPE_SEDANG: String = "sedang"
    const val TYPE_SULIT: String = "sulit"

    fun getQuestions(type: String): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        when (type) {
            TYPE_MUDAH -> {
                questionsList.add(Question(1, "Berapa kali dalam seminggu lansia disarankan untuk berolahraga?", R.drawable.exercise, arrayListOf("1-2 kali", "3-4 kali", "5-6 kali", "Setiap hari"), 1))
                questionsList.add(Question(2, "Makanan apa yang baik untuk kesehatan jantung lansia?", R.drawable.healthy_food, arrayListOf("Gorengan", "Buah dan sayur", "Makanan cepat saji", "Minuman bersoda"), 1))
                questionsList.add(Question(3, "Berapa liter air yang sebaiknya dikonsumsi lansia setiap hari?", R.drawable.waterpic, arrayListOf("1 liter", "1.5 liter", "2 liter", "3 liter"), 2))
                questionsList.add(Question(4, "Olahraga apa yang cocok untuk lansia?", R.drawable.walking, arrayListOf("Angkat beban berat", "Lari maraton", "Jalan kaki", "Bersepeda gunung"), 2))
                questionsList.add(Question(5, "Berapa jam tidur yang cukup untuk lansia setiap malam?", R.drawable.sleep_pic, arrayListOf("4-5 jam", "6-7 jam", "7-8 jam", "9-10 jam"), 2))
            }
            TYPE_SEDANG -> {
                questionsList.add(Question(1, "Apa manfaat dari tidur yang cukup bagi lansia?", R.drawable.sleep_pic, arrayListOf("Meningkatkan daya ingat", "Menurunkan risiko penyakit jantung", "Meningkatkan mood", "Semua jawaban benar"), 3))
                questionsList.add(Question(2, "Makanan apa yang baik untuk kesehatan jantung lansia?", R.drawable.healthy_food, arrayListOf("Gorengan", "Buah dan sayur", "Makanan cepat saji", "Minuman bersoda"), 1))
                questionsList.add(Question(3, "Berapa liter air yang sebaiknya dikonsumsi lansia setiap hari?", R.drawable.waterpic, arrayListOf("1 liter", "1.5 liter", "2 liter", "3 liter"), 2))
                questionsList.add(Question(4, "Olahraga apa yang cocok untuk lansia?", R.drawable.walking, arrayListOf("Angkat beban berat", "Lari maraton", "Jalan kaki", "Bersepeda gunung"), 2))
                questionsList.add(Question(5, "Apa yang sebaiknya dilakukan lansia untuk menjaga kesehatan mental?", R.drawable.mental_health, arrayListOf("Mengisolasi diri", "Berinteraksi sosial", "Menghindari aktivitas", "Mengonsumsi alkohol"), 1))
                questionsList.add(Question(6, "Makanan apa yang sebaiknya dihindari oleh lansia?", R.drawable.unhealthy_food, arrayListOf("Sayuran hijau", "Buah-buahan", "Makanan tinggi gula", "Ikan"), 2))

            }
            TYPE_SULIT -> {
                questionsList.add(Question(1, "Apa yang sebaiknya dilakukan lansia untuk menjaga kesehatan mental?", R.drawable.mental_health, arrayListOf("Mengisolasi diri", "Berinteraksi sosial", "Menghindari aktivitas", "Mengonsumsi alkohol"), 1))
                questionsList.add(Question(2, "Apa yang sebaiknya dilakukan lansia untuk menjaga kesehatan pencernaan?", R.drawable.digestive_health, arrayListOf("Mengonsumsi makanan tinggi serat", "Menghindari air putih", "Mengonsumsi makanan cepat saji", "Menghindari sayuran"), 0))
                questionsList.add(Question(3, "Berapa liter air yang sebaiknya dikonsumsi lansia setiap hari?", R.drawable.waterpic, arrayListOf("1 liter", "1.5 liter", "2 liter", "3 liter"), 2))
                questionsList.add(Question(4, "Olahraga apa yang cocok untuk lansia?", R.drawable.walking, arrayListOf("Angkat beban berat", "Lari maraton", "Jalan kaki", "Bersepeda gunung"), 2))
                questionsList.add(Question(5, "Makanan apa yang kaya akan kalsium dan baik untuk tulang lansia?", R.drawable.calcium_food, arrayListOf("Daging merah", "Produk susu", "Makanan cepat saji", "Minuman bersoda"), 1))
                questionsList.add(Question(6, "Apa manfaat dari berolahraga secara teratur bagi lansia?", R.drawable.exercise_benefit, arrayListOf("Meningkatkan kekuatan otot", "Menurunkan risiko penyakit kronis", "Meningkatkan keseimbangan", "Semua jawaban benar"), 3))
                questionsList.add(Question(7, "Bagaimana cara lansia menjaga kesehatan otak?", R.drawable.exercise, arrayListOf("Menghindari aktivitas mental", "Mengonsumsi makanan tinggi gula", "Melakukan aktivitas mental seperti membaca", "Menghindari interaksi sosial"), 2))
            }
        }

        return questionsList
    }
}