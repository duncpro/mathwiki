package com.duncpro.mathwiki.topics.projection

import com.duncpro.mathwiki.banner
import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*
import org.w3c.dom.HTMLImageElement

fun CavalierProjection() = WikiSection("Cavalier Projection") {
    +p {
        +"A form of three-dimensional projection where the "; +Math("z"); +"-axis and "; +Math("y"); +"-axis are perpendicular on "
        +"the 2D plane, with the "; +Math("x"); +"-axis drawn at a 45 degree angle."
    }
    +p {
        +"The length of the "; +Math("x"); +"-axis is not scaled."
    }
    +p {
        +"By convention, "; +Math("x"); +" increases in the direction towards the viewer. ";
        +"And "; +Math("y"); +" increases in the the direction towards the right-side of the 2D plane. "
        +"And "; +Math("z"); +" increases in the direction towards the top of the 2D plane. ";
    }
    +p {
        +" Cavalier projection is useful for graphing figures by hand using graph paper."
    }
    +div(banner, RCStyle(const(AnonymousCSSClass("display: flex; flex-direction: column; align-items: center")))) {
        +img(
            attr(HTMLImageElement::src, const("cavalier-coordinate-system.png")),
            RCStyle(const(AnonymousCSSClass("")))
        )
    }
}