import data.chemicalElementsMap
import model.GameModel
import model.MAX_NO_OF_QUESTIONS

// Author: Edzon Jaeve B. Bausa

/**
 * This program is a Console Guessing Game/Quiz Game with the
 * mechanics of guessing the Atomic Name of a given Atomic Symbol
 * i.e. H = Hydrogen, He = Helium, Li = Lithium...
 * You can input "skip" to the console if you don't know the answer
 * Game info:
 *  Number of Questions = 10
 *  Correct Answer += 20 points
 *  Wrong Answer -= 5 points
 *  Skip Answer = no deduction
 */

private val gameModel = GameModel()
fun main(args: Array<String>) {
    println("GUESS ELEMENT CONSOLE GAME")
    gameStart()
}

/**
 * This function is called in the main to start the game,
 * Iterating the nextElement(), onUserInput(), and showScore().
 */
private fun gameStart() {
    while (gameModel.nextElement()) {
        onUserInput()
        showScore()
    }
    playAgain()
}

/**
 * This function is used to get the user input
 * of the player's answer, it calls a recursive call
 * if the player's answer is wrong until the user
 * inputs the "correct name of the element" or
 * enters the "skip" word.
 * Skipping the question will display the "Correct Answer"
 * and there's no deduction of score.
 */
private fun onUserInput() {
    print("Answer: ")
    val input = readLine()!!
    if (input.equals("skip", true)) {
        println("Correct Answer: ${chemicalElementsMap[gameModel.currentElement]}")
    } else if (!gameModel.isUserWordCorrect(input)) {
        showScore()
        onUserInput()
    }
}

/**
 * This function is used to display the updated score
 * from the [GameModel]
 */
private fun showScore() {
    if (gameModel.currentElementCount == MAX_NO_OF_QUESTIONS) {
        println("Final Score: ${gameModel.score}")
    } else {
        println("Score: ${gameModel.score}")
    }
}

/**
 * This function will prompt a Scanner asking if the user wants to play again.
 * when the answer(lowercase) is 'y' calls restartGame()
 * else if 'n' -> terminate the program
 * else (other character) -> will call a recursive call.
 */
private fun playAgain() {
    print("Play Again? Y/N: ")
    when (readLine()!!.lowercase()) {
        "y" -> restartGame()
        "n" -> return
        else -> playAgain()
    }
}

/**
 * This function is used to restart the game that is passed on
 * playAgain() function, this uses the reinitializeData() function
 * to reset all game data from the beginning state, and then call
 * the gameStart() function to restart the game.
 */
private fun restartGame() {
    gameModel.reinitializeData()
    gameStart()
}







