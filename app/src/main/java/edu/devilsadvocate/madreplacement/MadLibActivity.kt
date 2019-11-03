package edu.devilsadvocate.madreplacement

import androidx.appcompat.app.AppCompatActivity

abstract class MadLibActivity : AppCompatActivity() {
    abstract val stringIdsOfWordTypes: List<Int>
    abstract val stringsOfContent: List<String>
}