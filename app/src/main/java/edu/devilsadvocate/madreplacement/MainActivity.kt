package edu.devilsadvocate.madreplacement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var wordCounter: Int = 0
    private var currentMadLibIdList: List<Int> = listOf()
    private var currentMadLibStringList: MutableList<String> = mutableListOf()
    private val currentMadLibActivity: MadLibActivity = LetterFromCampActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Possible Future Functionality: Choose your MadLibActivity
        wordCounter = 1
        currentMadLibIdList = currentMadLibActivity.stringIdsOfWordTypes

        wordButton.setOnClickListener(this)
        grammarType.text = getString(currentMadLibIdList[0])
    }

    private fun setUp() {
        grammarType.text = getString(currentMadLibIdList[wordCounter])

        if (wordCounter < currentMadLibIdList.size - 1)
            wordButton.text = getString(R.string.next_word_string)
        else
            wordButton.text = getString(R.string.submit_words_string)

        currentMadLibStringList.add(wordEdit.text.toString())
        wordEdit.text.clear()
    }

    private fun addStringsToIntent(intent: Intent) {
        for (i in currentMadLibStringList.indices)
        {
            intent.putExtra(getString(R.string.mad_lib_intent_strings_base, i), currentMadLibStringList[i])
        }
    }

    override fun onClick(v: View?) {
        if (wordCounter < currentMadLibIdList.size) {
            setUp()
            wordCounter++
        } else {
            currentMadLibStringList.add(wordEdit.text.toString())
            val currentMadLibActivityIntent: Intent = Intent(this, currentMadLibActivity.javaClass)
            addStringsToIntent(currentMadLibActivityIntent)
            startActivity(currentMadLibActivityIntent)
        }
    }
}
