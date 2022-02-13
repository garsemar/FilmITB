package cat.itb.naimgomez7e5.m03.uf2.filmITB.ui

import cat.itb.naimgomez7e5.m03.uf2.filmITB.utils.containsCharInRange
import java.util.*

fun inputString(sc:Scanner, msg: String = "Input:"): String {
    print("$msg ")
    var input = sc.nextLine().replace(" ", "")

    while (input == "")
    {
        print("$msg ")
        input = sc.nextLine()
    }
    return input
}

fun inputInt(sc : Scanner, msg: String = "Input:"): Int {
    print("$msg ")
    var rawInput = sc.nextLine().replace(" ", "")

    while (rawInput == "" || !containsCharInRange(rawInput, 48, 57))
    {
        print("$msg ")
        rawInput = sc.nextLine().replace(" ", "")
    }
    return rawInput.toInt()
}