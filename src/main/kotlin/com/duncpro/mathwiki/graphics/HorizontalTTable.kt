package com.duncpro.mathwiki.graphics

import com.duncpro.webk.*

sealed interface TRow
class TextTRow(vararg val cells: String): TRow
class UIComponentTRow(vararg val components: UIComponent): TRow
fun TRow(vararg cells: String): TextTRow = TextTRow(*cells)
fun TRow(vararg cells: UIComponent): UIComponentTRow = UIComponentTRow(*cells)

fun HorizontalTTable(vararg rows: TRow) = run {
    val `$td` = RCStyle(const(AnonymousCSSClass("border: 1px solid black;")))
    val `$table` = RCStyle(const(AnonymousCSSClass("border-collapse: collapse")))
    table(`$table`) {
        for (row in rows) {
            +tr {
                when (row) {
                    is TextTRow -> {
                        for (cell in row.cells) {
                            +td(`$td`) { +cell }
                        }
                    }
                    is UIComponentTRow -> {
                        for (cell in row.components) {
                            +td(`$td`) { +cell }
                        }
                    }
                }
            }
        }
    }
}