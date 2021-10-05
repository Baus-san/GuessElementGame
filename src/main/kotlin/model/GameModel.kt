package model

import data.chemicalElementsMap

// Constant values
const val MAX_NO_OF_QUESTIONS = 10
const val INCREASE_SCORE = 20
const val DECREASE_SCORE = 5

/**
 * This class contains the game logic and
 * functions to process the game data.
 */
class GameModel {
    // Initialize backing properties of the game
    private var _score = 0
    val score: Int
        get() = _score
    private var _currentElementCount = 0
    val currentElementCount: Int
        get() = _currentElementCount

    private lateinit var _currentElement: String
    val currentElement: String
        get() = _currentElement

    // List of elements used in the game
    private var elementsList: MutableList<String> = mutableListOf()
    private lateinit var tempElement: String

    /*
    * Updates the currentElement with the next element.
    * This is passed on the nextElement() function
    */
    private fun getNextElement() {
        // Set the temporary element to select from the keys of chemicalElementsMap
        tempElement = chemicalElementsMap.keys.random()
        // Calls a recursive call if the tempElement is already in elementsList in the game.
        if (elementsList.contains(tempElement)) {
            getNextElement()
        }
        // If not set _currentElement as tempElement and
        // add the tempElement from the elementsList.
        // then print the currentElement with number inclusively.
        else {
            _currentElement = tempElement
            ++_currentElementCount
            elementsList.add(tempElement)
            println("$_currentElementCount. $_currentElement")
        }
    }

    /**
    * Re-initializes the game data to restart the game.
    */
    fun reinitializeData() {
        _score = 0
        _currentElementCount = 0
        elementsList.clear()
    }

    /**
    * Increases the game score if the player's word is correct.
    * and displays a message if the answer is correct.
    */
    private fun increaseScore() {
        _score += INCREASE_SCORE
        println("Correct! +$INCREASE_SCORE points❤")
    }
    /*
    * Increases the game score if the player's word is correct
    * and displays a message if the answer is wrong.
    */
    private fun decreaseScore() {
        _score -= DECREASE_SCORE
        println("Wrong! -$DECREASE_SCORE points :(")
    }

    /**
    * Returns true if the player word is correct,
    * Increases the score accordingly using increaseScore(),
     * Otherwise decreases the score accordingly using decreaseScore(),
    */
    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(chemicalElementsMap[currentElement], true)) {
            increaseScore()
            return true
        }
        decreaseScore()
        return false
    }

    /**
    * Returns true if the current element count is less than MAX_NO_OF_QUESTIONS
    * To determine if the game is still ongoing.
    * This is passed to gameStart() function in [Main].
    */
    fun nextElement(): Boolean {
        return if (_currentElementCount < MAX_NO_OF_QUESTIONS) {
            getNextElement()
            true
        } else false
    }
}