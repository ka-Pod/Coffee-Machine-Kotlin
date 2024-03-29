package machine

var water = 400
var milk = 540
var coffeeBeans = 120
var disposableCups = 9
var money = 550

fun main() {
    chooseAction()
}

fun chooseAction() {
    print("Write action (buy, fill, take, remaining, exit): ")
    when(readln()) {
        "buy" -> chooseDrink()
        "fill" -> fillResources()
        "take" -> withdrawMoney()
        "remaining" -> printRemainder()
        "exit" -> return
    }
    chooseAction()
}

fun chooseDrink() {
    print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to machine.main menu: ")
    when(readln()) {
        "1" -> makeCoffee(250, 0, 16, 4)
        "2" -> makeCoffee(350, 75, 20, 7)
        "3" -> makeCoffee(200, 100, 12, 6)
        "back" -> return
    }
}

fun fillResources() {
    print("\nWrite how many ml of water do you want to add: ")
    water += readln().toInt()
    print("Write how many ml of milk do you want to add: ")
    milk += readln().toInt()
    print("Write how many grams of coffee beans do you want to add: ")
    coffeeBeans += readln().toInt()
    print("Write how many disposable cups of coffee do you want to add: \n")
    disposableCups += readln().toInt()
}

fun printRemainder() {
    println("The coffee machine has:")
    println("$water ml of water")
    println("$milk ml of milk")
    println("$coffeeBeans g of coffee beans")
    println("$disposableCups disposable cups")
    println("$$money of money")
}

fun withdrawMoney() {
    println("\nI gave you \$$money/n")
    money = 0
}

fun makeCoffee(requiredWater: Int, requiredMilk: Int, requiredCoffeeBeans: Int, cost: Int) {
    val missingResource = getMissingResource(requiredWater, requiredMilk, requiredCoffeeBeans)
    if (missingResource == null) {
        water -= requiredWater
        milk -= requiredMilk
        coffeeBeans -= requiredCoffeeBeans
        disposableCups--
        money += cost
        println("I have enough resources, making you a coffee!\n")
    } else {
        println("Sorry, not enough $missingResource!\n")
        return
    }
}

fun getMissingResource(requiredWater: Int, requiredMilk: Int, requiredCoffeeBeans: Int): String? {
    when {
        requiredWater > water -> return "water"
        requiredMilk > milk -> return "milk"
        requiredCoffeeBeans > coffeeBeans -> return "coffee beans"
        disposableCups == 0 -> return "disposable cups"
    }
    return null
}