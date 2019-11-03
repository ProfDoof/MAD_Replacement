package edu.devilsadvocate.madreplacement

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.core.text.backgroundColor
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.underline
import kotlinx.android.synthetic.main.activity_letter_from_camp.*

class LetterFromCampActivity : MadLibActivity() {

    override val stringIdsOfWordTypes: List<Int>
        get() = listOf(
            R.string.relative,
            R.string.adjective,
            R.string.adjective,
            R.string.adjective,
            R.string.name_of_person_in_room,
            R.string.adjective,
            R.string.adjective,
            R.string.verb_end_in_ed,
            R.string.body_part,
            R.string.verb_end_in_ing,
            R.string.plural_noun,
            R.string.noun,
            R.string.adverb,
            R.string.verb,
            R.string.verb,
            R.string.relative,
            R.string.name_of_person_in_room)

    override val stringsOfContent: List<String>
        get() = List(resources.getStringArray(R.array.letter_from_camp_string_list).size, init = {
            resources.getStringArray(R.array.letter_from_camp_string_list)[it]
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_from_camp)

        val replacementStrings: MutableList<String> = mutableListOf()
        val spannableStringBuilder: SpannableStringBuilder = SpannableStringBuilder()

        for (i in stringIdsOfWordTypes.indices) {
            val testString: String? = intent.getStringExtra(getString(R.string.mad_lib_intent_strings_base, i))
            if (testString != null)
                replacementStrings.add(testString)
        }
        
        val replaceRegexChecker: Regex = Regex("\\{\\{(.?.?)\\}\\}")
        for (string in stringsOfContent) {
            if (replaceRegexChecker matches string) {
                var matchResult = replaceRegexChecker.find(string)
                val index = matchResult!!.groups[1]!!.value.toInt()
                spannableStringBuilder.bold { underline { backgroundColor(Color.BLACK) { color(Color.WHITE) { append(" "+replacementStrings[index-1]+" ") } } } }
            } else {
                spannableStringBuilder.append(string)
            }
        }

        outputTextView.text = spannableStringBuilder
    }
}
